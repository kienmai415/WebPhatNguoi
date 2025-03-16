///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package Controller;
//
//import Dao.ReportsDao;
//import Model.Reports;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
///**
// *
// * @author maiki
// */
//@WebServlet(name="GetReportByUserID", urlPatterns={"/GetReportByUserID"})
//public class GetReportByUserID extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet GetReportByUser</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet GetReportByUser at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // Lấy ReportID từ request
//        String reportIdParam = request.getParameter("reporterId");
//
//        if (reportIdParam == null || reportIdParam.isEmpty()) {
//            request.setAttribute("error", "Vui lòng nhập Reporter ID!");
//            request.getRequestDispatcher("reportUser.jsp").forward(request, response);
//            return;
//        }
//
//        int reportId;
//        try {
//            reportId = Integer.parseInt(reportIdParam);
//        } catch (NumberFormatException e) {
//            request.setAttribute("error", "Report ID không hợp lệ!");
//            request.getRequestDispatcher("reportUser.jsp").forward(request, response);
//            return;
//        }
//
//        // Gọi DAO để lấy báo cáo
//        Reports report = ReportsDao.getReportByUser(reportId);
//
//        if (report != null) {
//            request.setAttribute("rp", report);
//        } else {
//            request.setAttribute("error", "Không tìm thấy báo cáo!");
//        }
//
//        // Chuyển hướng về trang JSP
//        request.getRequestDispatcher("reportUser.jsp").forward(request, response);
//
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
//
