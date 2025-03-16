<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.*, javax.servlet.*, javax.servlet.http.*" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Police Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function showSection(sectionId) {
            document.querySelectorAll('.section').forEach(sec => sec.classList.add('d-none'));
            document.getElementById(sectionId).classList.remove('d-none');
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
<body class="bg-light">
    <div class="container mt-4">
        <h2 class="text-center text-primary">🚔 Xin chào,cảnh sát ${sessionScope.FullName}!</h2>
        <div class="btn-group d-flex justify-content-center my-3">
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    👤 Profile
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="changepassword.jsp">🔑 Đổi mật khẩu</a></li>
                    <li><a class="dropdown-item" href="updateprofile.jsp">📄 Xem chi tiết hồ sơ</a></li>
                </ul>
            </div>
            <button class="btn btn-success" onclick="showSection('report')">📩 Gửi phản ánh</button>
            <button class="btn btn-warning" onclick="showSection('manage-reports')">📜 Xử lý phản ánh</button>
            <button class="btn btn-danger" onclick="window.location.href='LogoutServlet'">🚪 Đăng xuất</button>
        </div>

        <div id="report" class="section card p-4 d-none">
            <h3>📩 Gửi phản ánh</h3>
            <form action="submit_report.jsp" method="post" enctype="multipart/form-data">
                <label class="form-label">Biển số xe:</label>
                <input type="text" name="license_plate" class="form-control" required>
                <label class="form-label mt-2">Loại xe:</label>
                <select id="vehicle_type" name="vehicle_type" class="form-select" onchange="calculateFine()">
                    <option value="oto">Ô tô</option>
                    <option value="xe_dap">Xe đạp</option>
                    <option value="bus">Xe bus</option>
                    <option value="xe_tai">Xe tải</option>
                </select>
                <label class="form-label mt-2">Lỗi vi phạm:</label>
                <select id="violation" name="violation" class="form-select" onchange="calculateFine()">
                    <option value="speeding">Vượt tốc độ</option>
                    <option value="parking">Đỗ xe sai quy định</option>
                </select>
                <label class="form-label mt-2">Số tiền phạt:</label>
                <input type="text" id="fine_amount" name="fine_amount" class="form-control" readonly>
                <label class="form-label mt-2">Upload hình ảnh:</label>
                <input type="file" name="image" class="form-control" accept="image/*">
                <label class="form-label mt-2">Upload video:</label>
                <input type="file" name="video" class="form-control" accept="video/*">
                <input type="hidden" name="status" value="approved">
                <button type="submit" class="btn btn-primary mt-3">📩 Gửi phản ánh</button>
            </form>
            <div class="mt-3">
                <a href="view_last_report.jsp" class="btn btn-outline-secondary">🔍 Xem lại phản ánh vừa rồi</a>
                <a href="police.jsp" class="btn btn-outline-primary">➕ Gửi phản ánh khác</a>
            </div>
        </div>

        <div id="manage-reports" class="section card p-4 d-none">
            <h3>📜 Xử lý phản ánh</h3>
            <label class="form-label">Filter theo trạng thái:</label>
            <select id="filter_status" class="form-select">
                <option value="all">Tất cả</option>
                <option value="pending">Pending</option>
                <option value="approved">Approved</option>
                <option value="rejected">Rejected</option>
            </select>
            <div id="report-list" class="mt-3">
                <!-- Danh sách phản ánh sẽ được hiển thị ở đây -->
            </div>
        </div>
    </div>
</body>
</html>
