<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Model.Users" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    Users user = (Users) session.getAttribute("loggedUser"); 
    String status = request.getParameter("status");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Traffic Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-3">
        <% if (status != null) { %>
        <% if ("logout".equals(status)) { %>
        <div class="alert alert-info text-center">🚪 Bạn đã đăng xuất thành công!</div>
        <% } %>
        <% } %>
    </div>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="admin.jsp">🚦 Admin Dashboard</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="manageProfileDropdown" role="button" data-bs-toggle="dropdown">
                            ⚙️ Quản Lý Tài Khoản
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="managePolice.jsp">Cảnh Sát</a></li>
                            <li><a class="dropdown-item" href="manageUsers.jsp">Người Dân</a></li>
                        </ul>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="manageReports.jsp">📜 Quản Lý Báo Cáo</a></li>
                    <li class="nav-item"><a class="nav-link" href="manageTrafficLaws.jsp">⚖️ Quản Lý Luật Giao Thông</a></li>
                    <% if (user != null) { %>
                    <div style="display: flex; align-items: center;">
                        <span>Xin chào, quản trị viên ${sessionScope.FullName}!</span>
                        <a class="nav-link btn btn-danger text-white" href="LogoutServlet">🚪 Đăng Xuất</a>
                    </div>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4 text-center">
        <div class="alert alert-primary">
            <h3>🚦 Hệ Thống Quản Lý Giao Thông 🚗</h3>
            <p>Quản lý tài khoản, phản ánh và luật giao thông một cách hiệu quả.</p>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
