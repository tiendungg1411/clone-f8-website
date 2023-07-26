<%-- 
    Document   : myCourse
    Created on : 17/06/2023, 8:51:00 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/base.css">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/myCourse.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" href="./font/fontawesome-free-6.4.0-web/fontawesome-free-6.4.0-web/css/all.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div><jsp:include page="header.jsp"></jsp:include></div>
            <!--Center-->
            <div class="container_wrap">
                <div style="position: absolute"><jsp:include page="sidebar.jsp"></jsp:include></div>
                <div class="container">
                    <div class="container_blog_wrap">
                        <h1>Khóa học của tôi</h1>
                        <p>Bạn chưa hoàn thành khóa học nào.</p>
                        <div class="my_course_list">
                            <c:forEach items="${listCourseDetail}" var="c">
                                <div class="free_course_list_course">
                                    <a class="free_course_list_course_link" href="#" style="background-image: url(&quot;${c.image}&quot;);"><div onclick="window.location = 'videoCourse?courseID=${c.id}'" class="free_course_list_course_viewCoursebtn">Bắt đầu học</div></a>
                                    <h3 style="margin-top: 12px;">${c.title}</h3>
                                    <div style="margin-top: 18px;" class="free_course_list_course_count">
                                        <span>Học cách đây ${c.timeRegistration}</span>
                                    </div>
                                </div>
                            </c:forEach>
                            
                            <div class="free_course_list_register">
                                <div onclick="window.location = 'learn'" class="free_course_list_register_content">
                                    <i class="fa-solid fa-circle-plus"></i>
                                    <button class="Button_btn__RW1e2 UsersCourses_add-btn__J6fxq">Thêm khóa học</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
