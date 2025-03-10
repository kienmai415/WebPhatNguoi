<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String status = request.getParameter("status");
    if ("fail".equals(status)) { 
%>
    <div class="alert alert-danger text-center">⚠️ Đăng ký thất bại. Vui lòng thử lại!</div>
<% } %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Register</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Link Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- File CSS Custom -->
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card p-4 shadow-lg" style="width: 400px;">
                <h3 class="text-center">Register</h3>
                <a href="login.jsp" class="text-primary text-center d-block mb-3">Already have an account?</a>

                <!-- Form đăng ký -->
                <form action="InsertUser" method="post">
                    <div class="mb-3">
                        <label class="form-label">Full Name</label>
                        <input type="text" name="fullName" class="form-control" placeholder="Full Name" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email Address</label>
                        <input type="email" name="email" class="form-control" placeholder="Email Address" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" placeholder="Password" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Phone</label>
                        <input type="text" name="phone" class="form-control" placeholder="Phone Number" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Address</label>
                        <input type="text" name="address" class="form-control" placeholder="Address" required>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Register</button>
                    <p>${message}</p>
                </form>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
