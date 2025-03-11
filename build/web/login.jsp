<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng Nhập</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="text-center">🔑 Đăng Nhập</h2>

            <%-- Hiển thị thông báo lỗi --%>


            <form action="LoginServlet" method="post" class="mt-4">
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Mật khẩu:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Đăng Nhập</button>
            </form>
            <% if (error != null) { %>
            <p style="color: red;"><%= error %></p>
            <% } %>
            <div class="text-center mt-3">
                <a href="register.jsp">Chưa có tài khoản? Đăng ký ngay</a>
            </div>
        </div>
    </body>
</html>
