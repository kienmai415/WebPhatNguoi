<%-- 
    Document   : updateprofile
    Created on : Mar 9, 2025, 1:42:34 AM
    Author     : maiki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Link Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- File CSS Custom -->
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card p-4 shadow-lg" style="width: 400px;">
                <h3 class="text-center">Cập nhật hồ sơ</h3>


                <form action="UpdateProfileServlet" method="post">
                    <div class="mb-3">
                        <label class="form-label">Họ và tên</label>
                        <input type="text" name="fullName" class="form-control" 
                               value="${getuser.fullName}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Số điện thoại</label>
                        <input type="text" name="phone" class="form-control" 
                               value="${getuser.phone}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Địa chỉ</label>
                        <input type="text" name="address" class="form-control" 
                               value="${getuser.address}" required>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Cập nhật</button>
                    <p class="text-success">${message}</p>
                </form>

            </div>
        </div>
    </body>
</html>
