<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Users" %>
<%@ page import="Dao.UsersDao" %>

<%
    // Kiểm tra nếu admin chưa đăng nhập
    Users admin = (Users) session.getAttribute("loggedUser");
    if (admin == null || admin.getRoleID() != 1) { 
        response.sendRedirect("login.jsp");
        return;
    }

    // Nhận role từ URL (mặc định là 3 - Người Dân)
    int selectedRole = request.getParameter("role") != null ? Integer.parseInt(request.getParameter("role")) : 3;

    // Xử lý thay đổi trạng thái tài khoản
    if (request.getParameter("action") != null && request.getParameter("userID") != null) {
        int userID = Integer.parseInt(request.getParameter("userID"));
        String action = request.getParameter("action");

        UsersDao usersDao = new UsersDao();
        if ("disable".equals(action)) {
            usersDao.updateUserStatus(userID, false); // Vô hiệu hóa tài khoản
        } else if ("enable".equals(action)) {
            usersDao.updateUserStatus(userID, true); // Kích hoạt lại tài khoản
        }
        
        // Tải lại trang để cập nhật danh sách
        response.sendRedirect("manageusers.jsp?role=" + selectedRole);
        return;
    }

    // Lấy danh sách tài khoản theo vai trò, bao gồm cả đã vô hiệu hóa và đang hoạt động
    UsersDao usersDao = new UsersDao();
    List<Users> activeUsers = usersDao.getUsersByRole(selectedRole, true);
    List<Users> disabledUsers = usersDao.getUsersByRole(selectedRole, false);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Tài Khoản</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function confirmAction(userID, action, role) {
            let message = action === "disable" ? "Bạn có chắc chắn muốn vô hiệu hóa tài khoản này?" 
                                               : "Bạn có chắc chắn muốn kích hoạt lại tài khoản này?";
            if (confirm(message)) {
                window.location.href = "manageusers.jsp?role=" + role + "&action=" + action + "&userID=" + userID;
            }
        }
    </script>
</head>
<body>

<div class="container mt-3">
    <h2 class="text-center">📋 Quản Lý Tài Khoản (<%= selectedRole == 2 ? "Cảnh Sát" : "Người Dân" %>)</h2>

    <!-- Danh sách tài khoản đang hoạt động -->
    <h4>✅ Tài khoản đang hoạt động</h4>
    <table class="table table-bordered">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Họ và Tên</th>
                <th>Email</th>
                <th>Số Điện Thoại</th>
                <th>Địa Chỉ</th>
                <th>Hành Động</th>
            </tr>
        </thead>
        <tbody>
            <% if (activeUsers.isEmpty()) { %>
                <tr><td colspan="6" class="text-center">Không có tài khoản hoạt động.</td></tr>
            <% } else { 
                for (Users u : activeUsers) { %>
                <tr>
                    <td><%= u.getUserID() %></td>
                    <td><%= u.getFullName() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getPhone() %></td>
                    <td><%= u.getAddress() %></td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="confirmAction(<%= u.getUserID() %>, 'disable', <%= selectedRole %>)">
                            🚫 Vô Hiệu Hóa
                        </button>
                    </td>
                </tr>
            <% } } %>
        </tbody>
    </table>

    <!-- Danh sách tài khoản đã vô hiệu hóa -->
    <h4>❌ Tài khoản đã vô hiệu hóa</h4>
    <table class="table table-bordered">
        <thead class="table-danger">
            <tr>
                <th>ID</th>
                <th>Họ và Tên</th>
                <th>Email</th>
                <th>Số Điện Thoại</th>
                <th>Địa Chỉ</th>
                <th>Hành Động</th>
            </tr>
        </thead>
        <tbody>
            <% if (disabledUsers.isEmpty()) { %>
                <tr><td colspan="6" class="text-center">Không có tài khoản bị vô hiệu hóa.</td></tr>
            <% } else { 
                for (Users u : disabledUsers) { %>
                <tr>
                    <td><%= u.getUserID() %></td>
                    <td><%= u.getFullName() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getPhone() %></td>
                    <td><%= u.getAddress() %></td>
                    <td>
                        <button class="btn btn-success btn-sm" onclick="confirmAction(<%= u.getUserID() %>, 'enable', <%= selectedRole %>)">
                            🔓 Kích Hoạt Lại
                        </button>
                    </td>
                </tr>
            <% } } %>
        </tbody>
    </table>

    <div class="text-center mt-3">
        <a href="admin.jsp" class="btn btn-primary">🏠 Trở Về Trang Chủ</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
