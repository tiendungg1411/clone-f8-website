<%-- 
    Document   : MyBlog
    Created on : Jun 6, 2023, 9:52:24 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bài viết của tôi</title>
        <link rel="stylesheet" href="css/header.css"/>
        <link rel="stylesheet" href="css/sidebar.css"/>
        <link rel="stylesheet" href="css/footer.css"/>
        <link rel="stylesheet" href="css/myBlog.css"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

    </head>
    <body>
        <!--Include header-->
        <jsp:include page="header.jsp"></jsp:include>

            <div class="content" >
                <div class="content_left">
                    <h1>Bài viết của tôi</h1>
                    <div style="display: flex">
                        <h2 style="margin-left: 20px;">Đã xuất bản (${numOfMyBlog})</h2>
                    </div>
                    <hr>
                <c:choose>
                    <c:when test="${listBlog.size() == 0}">
                        <h3>Chưa có blog nào...</h3>
                    </c:when>
                    <c:otherwise>
                        <c:set var="idPopup" value="1"></c:set>
                        <c:forEach items="${listBlog}" var="blog">
                            <div class="main">
                                <h4 style="font-size: 18px;  cursor: pointer;" onclick="window.location='blogDetail?id=${blog.id}'">${blog.getTitle()}</h4>
                                <div class="option">
                                    <i onclick="myFunction(${idPopup})" class='bx bx-dots-horizontal-rounded'></i>
                                </div>
                                <div style="display: flex">
                                    <p class="edit">${blog.getTime()}</p>
                                    <span style="padding: 0 8px 0 8px">·</span>
                                    <p class="read">${blog.getReadMinute()}</p>
                                </div>
                                <div class="popup" id="${idPopup}">
                                    <div class="popup_item" onclick="window.location = 'editBlog?id=${blog.id}'">Chỉnh sửa</div>
                                    <div class="popup_item" onclick="window.location = 'myblog?id=${blog.id}&mod=1'">Xóa</div>
                                </div>
                            </div>
                            <c:set var="idPopup" value="${idPopup+1}"></c:set>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="content_right" >
                <jsp:include page="Slider.jsp"></jsp:include>
            </div>
        </div>
        <!--Include sidebar-->
        <jsp:include page="sidebar.jsp"></jsp:include>
            <!--Include footer-->
            <div style="margin-top: 500px" >
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
        <script>
            function myFunction(idpopup) {
                var option = document.getElementById(idpopup);
                if (option.style.display === "none") {
                    option.style.display = "block";
                } else {
                    option.style.display = "none";
                }

            }
        </script>
    </body>
</html>
