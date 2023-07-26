<%-- 
Document   : ViewProfile
Created on : May 16, 2023, 10:29:22 PM
Author     : HP
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <!--Css-->
        <link href="css/profile.css" rel="stylesheet" type="text/css"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" type="text/css" href="./css/sidebar.css.css">
        <link rel="stylesheet" href="./font/fontawesome-free-6.4.0-web/fontawesome-free-6.4.0-web/css/all.min.css">
        <style>
            a:hover{
                opacity: 0.8;
                text-decoration: underline;
            }

            .comment:hover{
                opacity: 0.8;
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
        <!--Include page header and sidebar-->
        <jsp:include page="header.jsp"></jsp:include> 
        <jsp:include page="sidebar.jsp"></jsp:include>
            <form action="profile" method="post" enctype="multipart/form-data">
                <!--Cover image-->
                <div class="Profile_banner">
                    <img id="image" src="${inforUser.getCoverImage()}" height="800px">
            </div>
            <input type="text" name="uid" value="${inforUser.getId()}" style="display: none">
            <!--Button change cover image-->
            <c:if test="${sessionScope.account.getId() == inforUser.getId()}">

                <input type="file"  name="file" id="file" class="input_img" accept=".jpg, .png, .gif" value="${inforUser.getCoverImage()}">
                <label for="file" ><i class='bx bxs-camera'></i> Chỉnh sửa ảnh bìa </label>
                <div class="option" style="margin-left: 960px; margin-top: -210px; position: absolute; display: none" >
                    <button type="submit" name="save"><i class='bx bx-check'></i></button>
                    <button type="submit" name="cancel"><i class='bx bx-x'></i></button>
                </div>
            </c:if>
        </form>



        <!--Avatar user-->
        <div class="avt_user">
            <div class="avatar" style="--font-size:12px;">
                <c:choose>
                    <c:when test="${inforUser.getAvatar() == null}">
                        <img class="avt" src ="./image/f8_logo.png">
                    </c:when>
                    <c:otherwise>
                        <img class="avt" src="${inforUser.getAvatar()}">
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <!--Full name of User-->
        <div class="full_name_user">
            <span>${inforUser.getFirstAndLastName()}</span>
        </div>
        <!--Content-->
        <div class="content">
            <div class="content_left">
                <!--Introduce-->
                <div class="introduce">
                    <h3>Giới thiệu</h3> <br>
                    <c:if test="${inforUser.getBio() != null}">
                        <p style="text-align: center; margin-top: 10px">${inforUser.getBio()}</p> <br> <hr><br>
                    </c:if>
                    <p style="line-height: 1.5"> <ion-icon name="people-outline"></ion-icon> Thành viên của <b>F8 - Học lập trình để đi làm</b>
                    từ<i> ${inforUser.getRegistrationDate()}</i></p>
                </div>
                <!--Recent Activity-->
                <div class="work">
                    <h3>Hoạt động gần đây </h3> <br>
                    <c:choose>
                        <c:when test="${listComment.size() == 0}">
                            <p>Chưa có hoạt động gần đây</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${listComment}" var="item">
                                <a href="blogDetail?id=${item.getBlogID()}" style="text-decoration: none; color: black"> <p class="comment" style="font-size: 16px"> Ðã bình luận: ${item.getContent()}</p> <br> </a>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                </div>
            </div>
            <!--Course attended-->
            <div class="content_right">
                <div class="course">
                    <h3>Các khóa học đã tham gia</h3> <br>
                    <c:choose>
                        <c:when test="${listCourseOfAccount.size() == 0}">
                            <p>Chưa có khóa học nào được đăng kí</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${listCourseOfAccount}" var="item">
                                    <div class="course_account">
                                        <div class="left">
                                            <a href="videoCourse?courseID=${item.getId()}"><img src="${item.getImage()}"></a>
                                        </div>
                                        <div class="right">
                                            <h4>${item.getTitle()}</h4> <br>
                                            <p>${item.getDetailCourseDes()}</p>
                                        </div>
                                    </div> <hr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>

        <!--footer-->
        <div>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
        <script>
            const change = document.querySelector("#file");
            const option = document.querySelector(".option");
            change.addEventListener('click', () => {
                option.style.display = 'block';
            });


        </script>
        <script src="js/profile.js"></script>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    </body>
</html>
