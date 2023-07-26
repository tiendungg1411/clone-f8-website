<%-- 
    Document   : OptionSetting
    Created on : May 26, 2023, 4:08:34 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/optionSetting.css"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <div class="content_left">
            <h1>Cài đặt</h1> <br>
            <h2> <a href="setting"><i class='bx bx-user'></i> Thông tin cá nhân </a></h2>
            <h2> <a href="myorder"> <i class='bx bxs-store'></i> Đơn hàng của tôi </a></h2>
            <h2> <a href="passwordChange.jsp"> <ion-icon name="shield-half-outline"></ion-icon> Đổi mật khẩu </a></h2>
            <h2> <a onclick='logout()' style="cursor: pointer"> <i class='bx bx-log-out'></i> Đăng xuất </a></h2>
        </div>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script>
            function logout() {
                window.location = 'logout';
            }
        </script>
    </body>
</html>
