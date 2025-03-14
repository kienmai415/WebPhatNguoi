<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Reports" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<% 
    // Lấy thông tin từ request
    Reports rp = (Reports) request.getAttribute("rp");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home - Traffic Feedback</title>
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
                        <li class="nav-item"><a class="nav-link" href="history.jsp">📜 Lịch Sử</a></li>
                        <li class="nav-item"><a class="nav-link" href="notifications.jsp">🔔 Thông Báo</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Nội dung chính -->
        <div class="container mt-4">
            <!-- Banner -->
            <div class="alert alert-primary text-center">
                <h3>🚦 Hãy cùng nhau xây dựng giao thông an toàn! 🚗</h3>
                <p>Gửi phản ánh vi phạm giao thông nhanh chóng và minh bạch.</p>
            </div>

            <!-- Form lấy thông tin user -->
            <div class="card w-50 mx-auto p-4 shadow">
                <h4 class="text-center mb-3">🔍 Tra cứu thông tin người dùng</h4>
                <form action="GetReportByUser" method="post">
                    <label for="userId" class="form-label">Nhập ID người dùng:</label>
                    <input type="text" id="reporterId" name="reporterId" class="form-control" required>
                    <button type="submit" class="btn btn-primary w-100 mt-3">Lấy thông tin báo cáo</button>
                </form>

                <% if (error != null) { %>
                <div class="alert alert-danger mt-3"><%= error %></div>
                <% } else if (rp != null) { %>
                <div class="alert alert-success mt-3">
                    <p><strong>Report ID:</strong> <%= rp.getReportID() %></p>
                    <p><strong>Reporter ID:</strong> <%= rp.getReporterID() %></p>
                    <p><strong>Violation Type:</strong> <%= rp.getViolationType() %></p>
                    <p><strong>Description:</strong> <%= rp.getDescription() %></p>
                    <p><strong>Plate Number:</strong> <%= rp.getPlateNumber() %></p>

                    <%-- Hiển thị ảnh từ thư mục nếu có --%>
                    <% if (rp.getImageURL() != null && !rp.getImageURL().isEmpty()) { %>
                    <p><strong>Hình ảnh vi phạm:</strong></p>
                    <img src="assets/images/<%= rp.getImageURL() %>" alt="Hình ảnh vi phạm" class="img-fluid rounded" style="max-width: 100%; height: auto;">
                    <% } %>

                    <%-- Hiển thị video từ thư mục nếu có --%>
                    <% if (rp.getVideoURL() != null && !rp.getVideoURL().isEmpty()) { %>
                    <p><strong>Video vi phạm:</strong></p>
                    <video controls class="w-100">
                        <source src="assets/videos/<%= rp.getVideoURL() %>" type="video/mp4">
                        Trình duyệt không hỗ trợ phát video.
                    </video>
                    <% } %>

                    <p><strong>Location:</strong> <%= rp.getLocation() %></p>
                    <p><strong>Report Date:</strong> <%= rp.getReportDate() %></p>
                    <p><strong>Status:</strong> <%= rp.getStatus() %></p>
                </div>
                <% } %>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>


<!--tạo them folder assets có 2 folder con là image và video r ném data vào-->