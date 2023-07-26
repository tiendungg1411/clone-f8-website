<%-- 
    Document   : AccountSettings
    Created on : May 17, 2023, 8:40:30 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thiết lập về tôi tại F8</title>
        <!--Css-->
        <link rel="stylesheet" type="text/css" href="css/header.css">
        <link rel="stylesheet" type="text/css" href="css/footer.css">
        <link rel="stylesheet" type="text/css" href="css/settings.css">
        <link rel="stylesheet" type="text/css" href="css/optionSetting.css">
    </head>
    <body>
        <!--Header-->
        <jsp:include page="header.jsp"></jsp:include>
            <!--Content-->
            <div class="content">
                <!--Content left-->
            <jsp:include page="OptionSetting.jsp"></jsp:include>
                <!--Content right-->
                <form action="setting" method="post" enctype="multipart/form-data">
                    <input type="text" name="uid" value="${account.getId()}" style="display: none">
                    <div class="content_right">
                        <div>     
                            <h1>Thay đổi thông tin cá nhân </h1>
                        </div>
                        <hr><br>
                        <h2>User name*</h2>
                        <input type="text" name="username" class="input_infor" maxlength="20"  value="${account.getUserName()}" >
                    <h2>Họ tên</h2>
                    <input type="text" name="full_name" class="input_infor" maxlength="50" placeholder="Thêm tên của bạn" value="${account.getFirstAndLastName()}" >
                    <div class="avatar">
                        <div>
                            <h2>User Avatar</h2>
                        </div>
                        <div class="change_avt">
                            <c:choose>
                                <c:when test="${account.getAvatar() == null}">
                                    <img id="image" src="./image/f8_logo.png" height="50px">
                                </c:when>
                                <c:otherwise>
                            <img id="image" src="${account.getAvatar()}" height="50px">
                                </c:otherwise>
                            </c:choose>
                            <!--Button change avatar-->
                            <input type="file" name="file" id="file" accept=".jpg, .png, .gif" class="input_img" value="${account.getAvatar()}"/>
                            <label for="file" >Change</label>
                        </div>
                    </div> <br>
                    <h2 style="margin-top: -110px">Email</h2>
                    <input type="text" name="email" class="input_infor"  value="${account.getMail()}">
                    <br>
                    <h2>Điện thoại di động</h2>
                    <input type="text" name="phone_number" class="input_infor" value="${account.getPhoneNumber()}" placeholder="Thêm số điện thoại" >
                    <br>
                    <h2>Bio</h2>
                    <input type="text" name="bio" class="input_infor" maxlength="100" placeholder="Thêm giới thiệu" value="${account.getBio()}" >
                    <br>
                    <button type="submit" class="btn" name="btnSave">Xác nhận thay đổi</button>
                </div>
            </form>
        </div>




        <!--Footer-->
        <jsp:include page="footer.jsp"></jsp:include>
        <script src="js/profile.js"></script>

    </body>
</html>
