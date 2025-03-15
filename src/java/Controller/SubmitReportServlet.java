/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.ReportsDao;
import Model.Reports;
import Model.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.logging.Level;
import java.sql.Timestamp;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
@WebServlet(name = "SubmitReportServlet", urlPatterns = {"/SubmitReportServlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 20, // 20MB
        maxFileSize = 1024 * 1024 * 100, // 100MB
        maxRequestSize = 1024 * 1024 * 500 // 500MB
)
public class SubmitReportServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SubmitReportServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitReportServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger LOGGER = Logger.getLogger(SubmitReportServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false); // Không tạo session mới nếu chưa có
            if (session == null || session.getAttribute("loggedUser") == null) {
                LOGGER.log(Level.SEVERE, "❌ Lỗi: Session không tồn tại hoặc không có loggedUser!");
                response.sendRedirect("login.jsp");
                return;
            }

            Users users = (Users) session.getAttribute("loggedUser");
            LOGGER.log(Level.INFO, "✅ Lấy User từ session - ID: {0}, Role: {1}", new Object[]{users.getUserID(), users.getRoleID()});
            int reporterID = users.getUserID();

            String violationType = request.getParameter("ViolationType");
            String description = request.getParameter("Description");
            String plateNumber = request.getParameter("PlateNumber");
            String location = request.getParameter("Location");
            String status = "Pending"; // Mặc định trạng thái
            int processedBy = 0; // Chưa được xử lý

            // Lưu file ảnh và video (nếu có)
            String imageURL = saveFile(request, "imageFile");
            String videoURL = saveFile(request, "videoFile");

            // Log dữ liệu nhận được
            LOGGER.log(Level.INFO, "ReporterID: {0}, ViolationType: {1}, ImageURL: {2}, VideoURL: {3}",
                    new Object[]{3, violationType, imageURL, videoURL});

            // Tạo đối tượng báo cáo
            Reports report = new Reports();
            Users user = new Users();
            report.setReporterID(reporterID);
            report.setViolationType(violationType);
            report.setDescription(description);
            report.setPlateNumber(plateNumber);
            report.setImageURL(imageURL);
            report.setVideoURL(videoURL);
            report.setLocation(location);
            report.setStatus(status);
            report.setProcessedBy(processedBy);

            // Lưu vào database
            ReportsDao dao = new ReportsDao();
            if (dao.submitReport(report)) {
                request.setAttribute("messageSuccess", "Báo cáo vi phạm đã được gửi thành công.");
            } else {
                request.setAttribute("messageError", "Lỗi khi gửi báo cáo.");
            }
        } catch (Exception e) {
            request.setAttribute("messageError", "Lỗi xử lý dữ liệu: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Lỗi khi gửi báo cáo", e);
        }
        request.getRequestDispatcher("report.jsp").forward(request, response);

    }

    // Hàm lưu file (ảnh hoặc video)
    private String saveFile(HttpServletRequest request, String inputName) throws IOException, ServletException {
        String uploadDirectory = getServletContext().getRealPath("/") + "uploads";

        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        Part filePart = request.getPart(inputName);
        if (filePart == null || filePart.getSubmittedFileName().isEmpty()) {
            return null;
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String filePath = uploadDirectory + File.separator + fileName;
        filePart.write(filePath);
        return filePath;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
