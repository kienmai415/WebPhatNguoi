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
import java.util.regex.Pattern;

/**
 *
 * @author maiki
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Regex kiểm tra định dạng email
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        response.sendRedirect("login.jsp");
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

        // Lấy dữ liệu từ form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Email và mật khẩu không được để trống!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

//        if (email == null || email.trim().isEmpty()) {
//            errorMessage = "Email không được để trống!";
//        } else if (!Pattern.matches(EMAIL_PATTERN, email)) {
//            errorMessage = "Email không hợp lệ!";
//        } else if (password == null || password.trim().isEmpty()) {
//            errorMessage = "Mật khẩu không được để trống!";
//        } else if (password.length() < 6) {
//            errorMessage = "Mật khẩu phải có ít nhất 6 ký tự!";
//        }
//        // Nếu có lỗi, quay lại trang đăng nhập
//        if (errorMessage != null) {
//            request.setAttribute("errorMessage", errorMessage);
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//            return;
//        }
        // Gọi DAO để kiểm tra tài khoản
        UsersDao usersDao = new UsersDao();
        Users user = usersDao.authenticate(email, password);

        if (user != null) {
            // Đăng nhập thành công -> Lưu vào session
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            if (user.getRoleID() == 1) {
                response.sendRedirect("dashboard/admin.jsp");
            } else if (user.getRoleID() == 2) {
                response.sendRedirect("dashboard/police.jsp");
            } else {
                response.sendRedirect("home.jsp");
            }
            // Điều hướng về trang home.jsp
//            response.sendRedirect("home.jsp");
        } else {
            // Đăng nhập thất bại -> Hiển thị lỗi
            request.setAttribute("error", "❌ Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
