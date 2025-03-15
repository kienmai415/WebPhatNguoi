<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Reports" %>
<%@ page import="Model.Users" %>
<%@ page import="Dao.ReportsDao" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    // Kiểm tra người dùng có đăng nhập không
    HttpSession sessionUser = request.getSession(false);
    if (sessionUser == null || sessionUser.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Users user = (Users) sessionUser.getAttribute("loggedUser");
    int userId = user.getUserID();

    // Lấy danh sách báo cáo từ database
    ReportsDao reportsDao = new ReportsDao();
    List<Reports> reportList = reportsDao.getReportsByUser(userId);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lịch Sử Báo Cáo Vi Phạm</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container">
                <a class="navbar-brand" href="home.jsp">🚦 An Toàn Giao Thông</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item"><a class="nav-link" href="report.jsp">📢 Gửi Báo Cáo</a></li>
                        <li class="nav-item"><a class="nav-link" href="notifications.jsp">🔔 Thông Báo</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h2 class="text-center">📜 Lịch Sử Báo Cáo Vi Phạm</h2>
            <div class="card p-4 shadow">

                <% if (reportList.isEmpty()) { %>
                <div class="alert alert-info text-center">Bạn chưa gửi báo cáo nào.</div>
                <% } else { %>
                <table class="table table-bordered text-center">
                    <thead class="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Loại Vi Phạm</th>
                            <th>Biển Số Xe</th>
                            <th>Địa Điểm</th>
                            <th>Ngày Báo Cáo</th>
                            <th>Trạng Thái</th>
                            <th>Hình Ảnh</th>
                            <th>Video</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% int count = 1;
                           for (Reports report : reportList) { %>
                        <tr>
                            <td><%= count++ %></td>
                            <td><%= report.getViolationType() %></td>
                            <td><%= report.getPlateNumber() %></td>
                            <td><%= report.getLocation() %></td>
                            <td><%= report.getReportDate() %></td>
                            <td>
                                <% if (report.getStatus().equals("Pending")) { %>
                                <span class="badge bg-warning">Chờ xử lý</span>
                                <% } else if (report.getStatus().equals("Approved")) { %>
                                <span class="badge bg-success">Đã duyệt</span>
                                <% } else { %>
                                <span class="badge bg-danger">Bị từ chối</span>
                                <% } %>
                            </td>
                            <td>
                                <% if (report.getImageURL() != null && !report.getImageURL().isEmpty()) { %>
                                <img src="<%= report.getImageURL() %>" alt="Ảnh vi phạm" width="80">
                                <% } else { %>
                                Không có ảnh
                                <% } %>
                            </td>
                            <td>
                                <% if (report.getVideoURL() != null && !report.getVideoURL().isEmpty()) { %>
                                <a href="<%= report.getVideoURL() %>" target="_blank">🔗 Xem Video</a>
                                <% } else { %>
                                Không có video
                                <% } %>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% } %>
            </div>
        </div>
            <div class="d-flex justify-content-center" style="padding-top: 30px">
            <a href="home.jsp" class="btn btn-secondary">🏠 Trở về trang chủ</a>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
