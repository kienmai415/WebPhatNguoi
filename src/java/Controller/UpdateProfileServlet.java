/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.UsersDao;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author maiki
 */
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})
public class UpdateProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileServlet at " + request.getContextPath() + "</h1>");
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
    UsersDao userdao = new UsersDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //processRequest(request, response);
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("loggedUser");
        String email = user.getEmail();

        Users users = userdao.getUserByEmail(email);
        request.setAttribute("getuser", users);

        request.getRequestDispatcher("updateprofile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Lấy session và kiểm tra user đã đăng nhập chưa
    HttpSession session = request.getSession();
    Users loggedUser = (Users) session.getAttribute("loggedUser");

    if (loggedUser == null) {
        response.sendRedirect("login.jsp"); // Nếu chưa đăng nhập, chuyển hướng về trang login
        return;
    }

    // Lấy dữ liệu từ form
    String fullName = request.getParameter("fullName");
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");

    // Cập nhật thông tin user
    UsersDao userDao = new UsersDao();
    boolean updateSuccess = userDao.updateProfile(loggedUser.getEmail(), fullName, phone, address);

    if (updateSuccess) {
        // Cập nhật lại thông tin user trong session
        loggedUser.setFullName(fullName);
        loggedUser.setPhone(phone);
        loggedUser.setAddress(address);
        session.setAttribute("loggedUser", loggedUser);

        request.setAttribute("message", "✅ Cập nhật thành công!");
    } else {
        request.setAttribute("message", "❌ Cập nhật thất bại. Vui lòng thử lại!");
    }

    // Chuyển hướng về trang cập nhật profile
    request.getRequestDispatcher("updateprofile.jsp").forward(request, response);
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
