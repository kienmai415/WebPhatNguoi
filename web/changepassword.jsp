<%-- 
    Document   : changepassword
    Created on : Mar 9, 2025, 12:31:32 AM
    Author     : maiki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ChangePassword</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Link Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- File CSS Custom -->
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        
        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card p-4 shadow-lg" style="width: 400px;">
                <h3 class="text-center">Đổi mật khẩu</h3>
                

                
                <form action="ChangePasswordServlet" method="post">
                    <div class="mb-3">
                        <label class="form-label">Mật khẩu cũ</label>
                        <input type="password" name="oldPassword" class="form-control" placeholder="Old Password" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Mật khẩu mới</label>
                        <input type="password" name="newPassword" class="form-control" placeholder="New Password" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Xác nhận mật khẩu mới</label>
                        <input type="password" name="confirmPassword" class="form-control" placeholder="Confirm Password" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Đổi mật khẩu</button>
                    <p>${message}</p>
                    </form>
                
            </div>
        </div>
    </body>
</html>
