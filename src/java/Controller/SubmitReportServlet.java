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
import java.io.PrintWriter;
import java.nio.file.Paths;
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
        response.sendRedirect("report.jsp");
//        request.getRequestDispatcher("report.jsp").forward(request, response);
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
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Users user = (Users) session.getAttribute("loggedUser");
        int reporterID = user.getUserID();

        try {
            String violationType = request.getParameter("ViolationType");
            String description = request.getParameter("Description");
            String plateNumber = request.getParameter("PlateNumber");
            String location = request.getParameter("Location");
            String reportDateString = request.getParameter("ReportDate");
            String status = "Pending";
            int processedBy = 0;

            String imageURL = saveFile(request, "imageFile");
            String videoURL = saveFile(request, "videoFile");

            Reports report = new Reports();
            report.setReporterID(reporterID);
            report.setViolationType(violationType);
            report.setDescription(description);
            report.setPlateNumber(plateNumber);
            report.setImageURL(imageURL);
            report.setVideoURL(videoURL);
            report.setLocation(location);
            report.setStatus(status);
            report.setProcessedBy(processedBy);
            report.setReportDate(reportDateString);

            ReportsDao dao = new ReportsDao();
            if (dao.submitReport(report)) {
                request.setAttribute("messageSuccess", "Báo cáo vi phạm đã được gửi thành công.");
                request.getRequestDispatcher("report.jsp").forward(request, response);
            } else {
                request.setAttribute("messageError", "Lỗi khi gửi báo cáo.");
                request.getRequestDispatcher("report.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("messageError", "Lỗi trong quá trình xử lý: " + e.getMessage());
            request.getRequestDispatcher("report.jsp").forward(request, response);
        }
    }

    // Hàm lưu file (ảnh hoặc video)
    // Hàm lưu file ảnh hoặc video vào thư mục "uploads" trong thư mục gốc của ứng dụng
    private String saveFile(HttpServletRequest request, String inputName) throws IOException, ServletException {
        // Định nghĩa thư mục lưu trữ file trong thư mục gốc của ứng dụng (web/uploads/)
        String uploadDirectory = getServletContext().getRealPath("") + "uploads";

        // Kiểm tra và tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Lấy file từ request
        Part filePart = request.getPart(inputName);
        if (filePart == null || filePart.getSubmittedFileName().isEmpty()) {
            return null; // Không có file nào được chọn
        }

        // Lấy tên file gốc và phần mở rộng
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();

        // Chỉ cho phép các định dạng hợp lệ
        if (!fileExtension.matches("\\.(jpg|png|mp4|avi|jfif|jpeg)$")) {
            return null; // File không hợp lệ
        }

        // Tạo tên file duy nhất (timestamp + random)
        String uniqueFileName = System.currentTimeMillis() + "_" + Math.random() * 1000 + fileExtension;

        // Định nghĩa đường dẫn đích để lưu file
        String filePath = uploadDirectory + File.separator + uniqueFileName;

        // Lưu file vào thư mục
        filePart.write(filePath);

        // Trả về đường dẫn tương đối (dùng để lưu vào database)
        return "uploads/" + uniqueFileName;
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
