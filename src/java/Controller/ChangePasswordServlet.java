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
@WebServlet(name="ChangePasswordServlet", urlPatterns={"/ChangePasswordServlet"})
public class ChangePasswordServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ChangePasswordServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("loggedUser"); // Lấy user từ session

        if (user == null) {
            response.sendRedirect("login.jsp"); // Nếu chưa đăng nhập, chuyển hướng về login
            return;
        }

        // Lấy thông tin từ form
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Kiểm tra mật khẩu cũ có đúng không
        if (!user.getPassword().equals(oldPassword)) {
            request.setAttribute("message", "❌ Mật khẩu cũ không chính xác!");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu mới có khớp với xác nhận không
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("message", "⚠️ Mật khẩu mới không khớp!");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        }

        // Cập nhật mật khẩu mới vào database
        user.setPassword(newPassword);
        boolean updateSuccess = UsersDao.updatePassword(user.getEmail(), newPassword);

        if (updateSuccess) {
            session.setAttribute("loggedUser", user); // Cập nhật lại session
            request.setAttribute("message", "✅ Đổi mật khẩu thành công!");
        } else {
            request.setAttribute("message", "❌ Đổi mật khẩu thất bại, vui lòng thử lại.");
        }

        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
    }
    

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
