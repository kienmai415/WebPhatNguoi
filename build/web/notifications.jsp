<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Reports" %>
<%@ page import="Dao.ReportsDao" %>
<%@ page import="Model.Users" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    // Kiểm tra nếu người dùng chưa đăng nhập, chuyển hướng về login.jsp
    HttpSession sessionUser = request.getSession(false);
    if (sessionUser == null || sessionUser.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Users user = (Users) sessionUser.getAttribute("loggedUser");
    int userId = user.getUserID();

    // Lấy danh sách phản ánh từ database theo UserID
    ReportsDao reportsDao = new ReportsDao();
    List<Reports> reportList = reportsDao.getReportsByUser(userId);
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thông Báo - Tình Trạng Đơn Phản Ánh</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-4">
        <h2 class="text-center text-primary">🔔 Thông Báo Tình Trạng Đơn Phản Ánh</h2>

        <table class="table table-bordered mt-4">
            <thead class="table-dark">
                <tr>
                    <th>ID Phản Ánh</th>
                    <th>Ngày Gửi</th>
                    <th>Biển Số Xe</th>
                    <th>Loại Vi Phạm</th>
                    <th>Trạng Thái</th>
                    <th>Chi Tiết</th>
                </tr>
            </thead>
            <tbody>
                <% if (reportList.isEmpty()) { %>
                    <tr>
                        <td colspan="6" class="text-center">Không có thông báo nào.</td>
                    </tr>
                <% } else { 
                    for (Reports report : reportList) { %>
                        <tr>
                            <td><%= report.getReportID() %></td>
                            <td><%= report.getReportDate() %></td>
                            <td><%= report.getPlateNumber() %></td>
                            <td><%= report.getViolationType() %></td>
                            <td>
                                <% if ("Approved".equals(report.getStatus())) { %>
                                    <span class="badge bg-success">✅ Đã Chấp Nhận</span>
                                <% } else if ("Rejected".equals(report.getStatus())) { %>
                                    <span class="badge bg-danger">❌ Bị Từ Chối</span>
                                <% } else { %>
                                    <span class="badge bg-warning">⏳ Đang Xử Lý</span>
                                <% } %>
                            </td>
                            <td>
                                <a href="viewReport.jsp?reportId=<%= report.getReportID() %>" class="btn btn-info btn-sm">📄 Xem Chi Tiết</a>
                            </td>
                        </tr>
                    <% } 
                } %>
            </tbody>
        </table>

        <div class="text-center mt-3">
            <a href="home.jsp" class="btn btn-primary">🏠 Trở Về Trang Chủ</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
