<%-- 
    Document   : home.jsp
    Created on : Mar 8, 2025
    Author     : maiki
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Model.Users" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    // Lấy thông tin user từ session
    Users user = (Users) session.getAttribute("loggedUser"); 

    // Lấy trạng thái từ request (logout, đăng ký thành công, thất bại)
    String status = request.getParameter("status");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home - Traffic Feedback</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            /* Tùy chỉnh để căn giữa dropdown */
            .dropdown-menu {
                left: 0 !important; /* Bắt đầu từ vị trí của nút */
                right: auto; /* Không lệch sang phải */
                transform: none !important; /* Xóa hiệu ứng dịch chuyển mặc định */
                /* Điều chỉnh vị trí theo ý muốn */
                margin-left: 0px; /* Thay đổi giá trị này để dịch trái/phải */
                margin-top: 5px; /* Khoảng cách từ nút đến dropdown */
            }
            .nav-item {
                position: static;
            }
            /* Tùy chỉnh màu nền và kiểu dáng (tùy chọn) */
            .navbar-nav .nav-link {
                color: #000;
            }
            .dropdown-menu {
                background-color: #f8f9fa;
                border: 1px solid #dee2e6;
            }
        </style>
    </head>
    <body>

        <!-- Hiển thị thông báo nếu có -->
        <div class="container mt-3">
            <% if (status != null) { %>
            <% if ("logout".equals(status)) { %>
            <div class="alert alert-info text-center">🚪 Bạn đã đăng xuất thành công!</div>
            <% } else if ("registerSuccess".equals(status)) { %>
            <div class="alert alert-success text-center">✅ Đăng ký thành công! Vui lòng đăng nhập.</div>
            <% } else if ("registerFail".equals(status)) { %>
            <div class="alert alert-danger text-center">❌ Đăng ký thất bại. Vui lòng thử lại.</div>
            <% } %>
            <% } %>
        </div>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="home.jsp">🚦 An Toàn Giao Thông</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item"><a class="nav-link" href="history.jsp">📜 Lịch Sử Phản Ánh</a></li>
                        <li class="nav-item"><a class="nav-link" href="notifications.jsp">🔔 Thông Báo</a></li>
                        <a class="nav-link dropdown-toggle" href="#" id="profileDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            👤 Hồ Sơ
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="profileDropdown" style="margin-left: 1150px">
                            <li><a class="dropdown-item" href="UpdateProfileServlet">Chỉnh sửa hồ sơ</a></li>
                            <li><a class="dropdown-item" href="changepassword.jsp">🔑 Đổi mật khẩu</a></li>
                        </ul>
                        <!-- Kiểm tra đăng nhập -->
                        <% if (user != null) { %>
                        <div style="display: flex; align-items: center;">
                            <span>Xin chào, ${sessionScope.FullName}!</span>

                            <a class="nav-link btn btn-danger text-white" href="LogoutServlet">🚪 Đăng Xuất</a>

                        </div>
                        <% } else { %>
                        <li class="nav-item">
                            <a class="nav-link btn btn-primary text-white" href="login.jsp">🔑 Đăng Nhập</a>
                        </li>
                        <% } %>
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

            <!-- Nút gửi phản ánh -->
            <div class="text-center">
                <a href="report.jsp" class="btn btn-primary btn-lg">📸 Gửi phản ánh ngay</a>
            </div>

            <!-- Thanh tra cứu phương tiện -->
            <div class="container" style="margin-top: 50px;">
                <h2>TRA CỨU PHƯƠNG TIỆN VI PHẠM GIAO THÔNG</h2>
                <form action="SearchVehicleServlet" method="POST">
                    <div class="form-group">
                        <label for="plateNumber">Biển kiểm soát</label>
                        <input type="text" class="form-control" id="plateNumber" name="plateNumber" placeholder="VD: 30A12345" required>
                    </div>

                    <div class="form-group">
                        <label for="vehicleType">Loại phương tiện</label>
                        <select class="form-control" id="vehicleType" name="vehicleType" required>
                            <option value="Ô tô">Ô tô</option>
                            <option value="Xe máy">Xe máy</option>
                            <option value="Xe đạp">Xe đạp</option>
                            <!-- Thêm các loại phương tiện khác nếu cần -->
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Tra cứu</button>
                </form>

                <!-- Hiển thị kết quả tra cứu -->
                <div id="result" style="margin-top: 20px;">
                    <c:if test="${not empty vehicle}">
                        <h3>Thông tin phương tiện:</h3>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Biển kiểm soát</th>
                                    <th>Loại phương tiện</th>
                                    <th>ID chủ sở hữu</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${vehicle.plateNumber}</td>
                                    <td>${vehicle.vehicleType}</td>
                                    <td>${vehicle.ownerID}</td>
                                </tr>
                            </tbody>
                        </table>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            ${error}
                        </div>
                    </c:if>
                </div>
            </div>

            <!-- Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
