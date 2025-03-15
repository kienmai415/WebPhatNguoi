<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Reports" %>
<%@ page import="Model.Users" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<% String messageSuccess = (String) request.getAttribute("messageSuccess"); %>
<%
    Users user = (Users) session.getAttribute("loggedUser"); 
%>
<% String messageError = (String) request.getAttribute("messageError"); %>
<%
    out.println("User in session: " + session.getAttribute("loggedUser"));
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gửi Báo Cáo Vi Phạm</title>
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
        <div class="container mt-4">
            <h2 class="text-center">📢 Gửi Báo Cáo Vi Phạm</h2>
            <div class="card w-50 mx-auto p-4 shadow">

                <% if (messageSuccess != null) { %>
                <div class="alert alert-success text-center"><%= messageSuccess %></div>
                <% } %>

                <% if (messageError != null) { %>
                <div class="alert alert-danger text-center"><%= messageError %></div>
                <% } %>

                <form action="SubmitReportServlet" method="post" enctype="multipart/form-data">
                    
                    <div class="mb-3">
                        <label for="ViolationType" class="form-label">Loại Vi Phạm:</label>
                        <select class="form-select" id="ViolationType" name="ViolationType">
                            <option value="no_helmet">Không đội mũ bảo hiểm</option>
                            <option value="wrong_lane">Đi sai làn đường</option>
                            <option value="red_light">Vượt đèn đỏ</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="Description" class="form-label">Mô Tả:</label>
                        <textarea class="form-control" id="Description" name="Description" rows="3">${param.Description}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="PlateNumber" class="form-label">Biển Số Xe:</label>
                        <input type="text" class="form-control" id="PlateNumber" name="PlateNumber" value="${param.PlateNumber}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Hình Ảnh Vi Phạm:</label>
                        <input type="file" class="form-control" name="imageFile">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Video Vi Phạm:</label>
                        <input type="file" class="form-control" name="videoFile">
                    </div>

                    <div class="mb-3">
                        <label for="Location" class="form-label">Địa Điểm Vi Phạm:</label>
                        <input type="text" class="form-control" id="Location" name="Location" value="${param.Location}">
                    </div>

                    <div class="mb-3">
                        <label for="ReportDate" class="form-label">Ngày Báo Cáo:</label>
                        <input type="date" class="form-control" id="ReportDate" name="ReportDate" required>
                    </div>



                    <button type="submit" class="btn btn-primary">Gửi Báo Cáo</button>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
