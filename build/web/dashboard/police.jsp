<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.*, javax.servlet.*, javax.servlet.http.*" %>

<!--    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("2")) {
        response.sendRedirect("home.jsp");
        return;
    }-->

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Police Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; }
        .container { width: 80%; margin: auto; background: white; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px #aaa; }
        .menu { display: flex; gap: 15px; margin-bottom: 20px; }
        .menu button { padding: 10px; cursor: pointer; border: none; background: #007bff; color: white; border-radius: 5px; }
        .menu button:hover { background: #0056b3; }
        .section { display: none; }
        .active { display: block; }
    </style>
    <script>
        function showSection(sectionId) {
            document.querySelectorAll('.section').forEach(sec => sec.classList.remove('active'));
            document.getElementById(sectionId).classList.add('active');
        }

        function calculateFine() {
            const vehicleType = document.getElementById("vehicle_type").value;
            const violation = document.getElementById("violation").value;
            let fineAmount = 0;
            
            const fines = {
                "oto": {"speeding": 500000, "parking": 300000},
                "xe_dap": {"speeding": 100000, "parking": 50000},
                "bus": {"speeding": 700000, "parking": 400000},
                "xe_tai": {"speeding": 800000, "parking": 500000}
            };

            if (fines[vehicleType] && fines[vehicleType][violation]) {
                fineAmount = fines[vehicleType][violation];
            }

            document.getElementById("fine_amount").value = fineAmount;
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Xin chào, ${sessionScope.username}!</h2>
        <div class="menu">
            <button onclick="showSection('profile')">Profile</button>
            <button onclick="showSection('report')">Gửi phản ánh</button>
            <button onclick="showSection('manage-reports')">Xử lý phản ánh</button>
        </div>

        <!-- Profile Section -->
        <div id="profile" class="section active">
            <h3>Profile</h3>
            <button onclick="window.location.href='change_password.jsp'">Đổi mật khẩu</button>
            <button onclick="window.location.href='edit_profile.jsp'">Xem chi tiết hồ sơ</button>
        </div>

        <!-- Report Section -->
        <div id="report" class="section">
            <h3>Gửi phản ánh</h3>
            <form action="submit_report.jsp" method="post" enctype="multipart/form-data">
                <label>Biển số xe:</label>
                <input type="text" name="license_plate" required>
                <br>
                <label>Loại xe:</label>
                <select id="vehicle_type" name="vehicle_type" onchange="calculateFine()">
                    <option value="oto">Ô tô</option>
                    <option value="xe_dap">Xe đạp</option>
                    <option value="bus">Xe bus</option>
                    <option value="xe_tai">Xe tải</option>
                </select>
                <br>
                <label>Lỗi vi phạm:</label>
                <select id="violation" name="violation" onchange="calculateFine()">
                    <option value="speeding">Vượt tốc độ</option>
                    <option value="parking">Đỗ xe sai quy định</option>
                </select>
                <br>
                <label>Số tiền phạt:</label>
                <input type="text" id="fine_amount" name="fine_amount" readonly>
                <br>
                <label>Upload hình ảnh:</label>
                <input type="file" name="image" accept="image/*">
                <br>
                <label>Upload video:</label>
                <input type="file" name="video" accept="video/*">
                <br>
                <input type="hidden" name="status" value="approved">
                <button type="submit">Gửi phản ánh</button>
            </form>
            <a href="view_last_report.jsp">Xem lại phản ánh vừa rồi chi tiết</a>
            <a href="police.jsp">Gửi phản ánh khác</a>
        </div>

        <!-- Manage Reports Section -->
        <div id="manage-reports" class="section">
            <h3>Xử lý phản ánh</h3>
            <label>Filter theo trạng thái:</label>
            <select id="filter_status">
                <option value="all">Tất cả</option>
                <option value="pending">Pending</option>
                <option value="approved">Approved</option>
                <option value="rejected">Rejected</option>
            </select>
            <div id="report-list">
                <!-- Danh sách phản ánh sẽ được hiển thị ở đây -->
            </div>
        </div>
    </div>
</body>
</html>
