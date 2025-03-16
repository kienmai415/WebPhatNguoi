<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.*, javax.servlet.*, javax.servlet.http.*" %>
<%@ page import="Model.Reports" %>
<%@ page import="Dao.ReportsDao" %> 
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Police Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <%
            // Kiểm tra nếu cảnh sát đã bấm "Xử Lý Phản Ánh"
            boolean showReports = request.getParameter("showReports") != null;
            String status = request.getParameter("status");

            // Nếu chưa chọn trạng thái, mặc định là "Pending"
            if (status == null) {
                status = "Pending";
            }

            List<Reports> reportList = null;
            if (showReports) {
                ReportsDao reportsDao = new ReportsDao();
                reportList = reportsDao.getReportsByStatus(status);
            }
        %>

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
            <h2 class="text-center text-primary">🚔 Xin chào, cảnh sát ${sessionScope.FullName}!</h2>

            <% if (!showReports) { %>
            <div class="btn-group d-flex justify-content-center my-3">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" style="height: 50px">
                        👤 Profile
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="changepassword.jsp">🔑 Đổi mật khẩu</a></li>
                        <li><a class="dropdown-item" href="updateprofile.jsp">📄 Xem chi tiết hồ sơ</a></li>
                    </ul>
                </div>
                <button class="btn btn-success" onclick="showSection('report')">📩 Gửi phản ánh</button>
                <a href="police.jsp?showReports=true" class="btn btn-warning btn-lg">📜 Xử Lý Phản Ánh</a>
                <button class="btn btn-danger" onclick="window.location.href = 'LogoutServlet'">🚪 Đăng xuất</button>
            </div>
            <% } %>

            <% if (showReports) { %>
            <h2 class="text-center text-warning">📜 Danh Sách Phản Ánh</h2>

            <!-- Bộ lọc trạng thái -->
            <form action="police.jsp" method="get" class="mb-3">
                <input type="hidden" name="showReports" value="true">
                <label for="statusFilter" class="form-label">🔍 Chọn trạng thái:</label>
                <select id="statusFilter" name="status" class="form-select" onchange="this.form.submit()">
                    <option value="Pending" <%= "Pending".equals(status) ? "selected" : "" %>>⏳ Pending</option>
                    <option value="Approved" <%= "Approved".equals(status) ? "selected" : "" %>>✅ Approved</option>
                    <option value="Rejected" <%= "Rejected".equals(status) ? "selected" : "" %>>❌ Rejected</option>
                </select>
            </form>

            <!-- Danh sách phản ánh -->
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Người Báo Cáo</th>
                        <th>Ngày Gửi</th>
                        <th>Biển Số Xe</th>
                        <th>Loại Vi Phạm</th>
                        <th>Mô Tả</th>
                        <th>Địa Điểm</th>
                        <th>Hình Ảnh</th>
                        <th>Video</th>
                        <th>Trạng Thái</th>
                        <th>Hành Động</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (reportList == null || reportList.isEmpty()) { %>
                    <tr>
                        <td colspan="11" class="text-center">Không có phản ánh nào.</td>
                    </tr>
                    <% } else { 
                    for (Reports report : reportList) { %>
                    <tr>
                        <td><%= report.getReportID() %></td>
                        <td><%= report.getReporterID() %></td>
                        <td><%= report.getReportDate() %></td>
                        <td><%= report.getPlateNumber() %></td>
                        <td><%= report.getViolationType() %></td>
                        <td><%= report.getDescription() %></td>
                        <td><%= report.getLocation() %></td>


                        <td>
                            <% if (report.getImageURL() != null && !report.getImageURL().isEmpty()) { %>
                            <% String imageUrl = "http://localhost:8080/WebPhatNguoi/" + report.getImageURL(); %>
                            <a href="<%= imageUrl %>" target="_blank" class="btn btn-primary btn-sm">
                                🔍 Xem Ảnh
                            </a>
                            <% } else { %>
                            Không có ảnh
                            <% } %>
                        </td>

                        <td>
                            <% if (report.getVideoURL() != null && !report.getVideoURL().isEmpty()) { %>
                            <% String videoUrl = "http://localhost:8080/WebPhatNguoi/" + report.getVideoURL(); %>
                            <a href="<%= videoUrl %>" target="_blank" class="btn btn-danger btn-sm">
                                🎥 Xem Video
                            </a>
                            <% } else { %>
                            Không có video
                            <% } %>
                        </td>



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
                            <% if ("Pending".equals(report.getStatus())) { %>
                            <form action="UpdateReportStatusServlet" method="post" class="d-inline">
                                <input type="hidden" name="reportId" value="<%= report.getReportID() %>">
                                <button type="submit" name="status" value="Approved" class="btn btn-success btn-sm">✅ Duyệt</button>
                                <button type="submit" name="status" value="Rejected" class="btn btn-danger btn-sm">❌ Từ Chối</button>
                            </form>
                            <% } else { %>
                            <button class="btn btn-secondary btn-sm" disabled>🔒 Không thể chỉnh sửa</button>
                            <% } %>
                        </td>
                    </tr>
                    <% } } %>
                </tbody>
            </table>

            <div class="text-center mt-3">
                <a href="police.jsp" class="btn btn-primary">🏠 Trở Về Trang Chủ</a>
            </div>
            <% } %>
        </div>
    </body>
</html>
