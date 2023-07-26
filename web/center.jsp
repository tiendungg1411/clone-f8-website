<%-- 
    Document   : center
    Created on : 17/05/2023, 9:49:43 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/base.css">
        <link rel="stylesheet" type="text/css" href="./css/main.css">
        <link rel="stylesheet" href="./font/fontawesome-free-6.4.0-web/fontawesome-free-6.4.0-web/css/all.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="myform" action="searchCourse" method="post">
            <div class="center">
                <div class="element"><jsp:include page="sidebar.jsp"></jsp:include></div>
                <div style="display: <c:if test="${xemthem != null || hoc != null}">none</c:if>" class="slide">
                        <!--                    <div class="slideshow-container">
                        
                                                <div class="mySlides fade">
                                                    <div class="mySlides_left">
                                                        <h2>Học ReactJS Miễn Phí!</h2>
                                                        <p>Khóa học ReactJS từ cơ bản tới nâng cao. Kết quả của khóa học này là bạn có thể làm hầu hết các dự án thường gặp với ReactJS.</p>
                                                        <span>Đăng ký ngay</span>
                                                    </div>
                                                    <div class="mySlides_right">
                                                        <img src="https://files.fullstack.edu.vn/f8-prod/banners/Banner_web_ReactJS.png" alt="">
                                                    </div>
                                                </div>
                        
                                                
                        
                                            </div>-->
                        <div class="Slideshow_item__zjVUA" style="--cta-hover-color: #2877FA; background: linear-gradient(to right, rgb(40, 119, 250), rgb(103, 23, 205));">
                            <div class="Slideshow_left__jdrc-">
                                <h2 class="Slideshow_heading__fHSLk">
                                    <a rel="noreferrer noopener noreferrer" target="_blank" href="#">
                                        Học ReactJS Miễn Phí!
                                    </a>
                                </h2>
                                <p class="Slideshow_desc__XsRel">
                                    Khóa học ReactJS từ cơ bản tới nâng cao. Kết quả của khóa học này là bạn có thể làm hầu hết các dự án thường gặp với ReactJS.
                                </p>
                                <div>
                                    <a rel="noreferrer noopener noreferrer" class="Slideshow_ctaBtn__xcyon" target="_blank" href="#">
                                        Đăng ký ngay
                                    </a>
                                </div>
                            </div>
                            <div class="Slideshow_right__1lu8d">
                                <a rel="noreferrer noopener noreferrer" target="_blank" href="#"><img class="Slideshow_img__K-c9" src="https://files.fullstack.edu.vn/f8-prod/banners/Banner_web_ReactJS.png" alt="Khoá học ReactJS online tại F8" title="Học ReactJS Miễn Phí!">
                                </a>
                            </div>
                        </div>

                        <div class="Slideshow_item__zjVUA" style="--cta-hover-color: #2877FA; background: linear-gradient(to right, rgb(40, 119, 250), rgb(103, 23, 205));">
                            <div class="Slideshow_left__jdrc-">
                                <h2 class="Slideshow_heading__fHSLk">
                                    <a rel="noreferrer noopener noreferrer" target="_blank" href="#">
                                        Thành Quả của Học Viên
                                    </a>
                                </h2>
                                <p class="Slideshow_desc__XsRel">
                                    Để đạt được kết quả tốt trong mọi việc ta cần xác định mục tiêu rõ ràng cho việc đó. Học lập trình cũng không là ngoại lệ.
                                </p>
                                <div>
                                    <a rel="noreferrer noopener noreferrer" class="Slideshow_ctaBtn__xcyon" target="_blank" href="#">
                                        Xem thành quả
                                    </a>
                                </div>
                            </div>
                            <div class="Slideshow_right__1lu8d">
                                <a rel="noreferrer noopener noreferrer" target="_blank" href="#"><img class="Slideshow_img__K-c9" src="https://files.fullstack.edu.vn/f8-prod/banners/Banner_01_2.png"" alt="Khoá học ReactJS online tại F8" title="Học ReactJS Miễn Phí!">
                                </a>
                            </div>
                        </div>

                        <div class="Slideshow_item__zjVUA" style="--cta-hover-color: #2877FA; background: linear-gradient(to right, rgb(40, 119, 250), rgb(103, 23, 205));">
                            <div class="Slideshow_left__jdrc-">
                                <h2 class="Slideshow_heading__fHSLk">
                                    <a rel="noreferrer noopener noreferrer" target="_blank" href="#">
                                        F8 trên Facebook
                                    </a>
                                </h2>
                                <p class="Slideshow_desc__XsRel">
                                    F8 được nhắc tới ở mọi nơi, ở đâu có cơ hội việc làm cho nghề IT và có những con người yêu thích lập trình F8 sẽ ở đó.
                                </p>
                                <div>
                                    <a rel="noreferrer noopener noreferrer" class="Slideshow_ctaBtn__xcyon" target="_blank" href="#">
                                        Truy cập Facebook
                                    </a>
                                </div>
                            </div>
                            <div class="Slideshow_right__1lu8d">
                                <a rel="noreferrer noopener noreferrer" target="_blank" href="#"><img class="Slideshow_img__K-c9" src="https://files.fullstack.edu.vn/f8-prod/banners/Banner_04_2.png" alt="Khoá học ReactJS online tại F8" title="Học ReactJS Miễn Phí!">
                                </a>
                            </div>
                        </div>
                        <br>

                        <div style="text-align:center">
                            <span class="dot" onclick="dot0()"></span> 
                            <span class="dot" onclick="dot1()"></span> 
                            <span class="dot" onclick="dot2()"></span> 
                        </div>
                        <div class="next_slide_left" onclick="nextLeft()"><i class="fa-solid fa-angle-left"></i></div>
                        <div class="next_slide_right" onclick="nextRight()"><i class="fa-solid fa-angle-right"></i></div>
                    </div>
                    <div class="pro_course" style="display: <c:if test="${xemthem != null}">none</c:if>">
                        <h3 class="pro_course_title" style="display: inline-block">Khóa học Pro</h3>
                        <span class="pro_course_new">Mới</span>
                        <div class="free_course_list">
                        <c:forEach items="${listProCourse}" var="course">
                            <div class="free_course_list_course">
                                <a class="free_course_list_course_link" href="#" style="background-image: url(&quot;${course.image}&quot;);"><div onclick="courseDetail(${course.id})" class="free_course_list_course_viewCoursebtn">Xem khóa học</div></a>
                                <h3>${course.title}</h3>
                                <div class="free_course_list_course_count">
                                    <span style="color: #f05123;"><fmt:formatNumber value="${course.price}" pattern="#,###" />đ</span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="course" style="display: <c:if test="${xemthem != null}">none</c:if>">
                        <div class="khmp">
                            <div>Khóa học miễn phí</div>
                            <div>Xem lộ trình</div>
                        </div>
                        <div class="free_course_list">
                        <c:forEach items="${listCourse}" var="course">
                            <div class="free_course_list_course">
                                <a class="free_course_list_course_link" href="#" style="background-image: url(&quot;${course.image}&quot;);"><div onclick="courseDetail(${course.id})" class="free_course_list_course_viewCoursebtn">Xem khóa học</div></a>

                                <h3>${course.title}</h3>
                                <div class="free_course_list_course_count">
                                    <i class="fa-solid fa-users"></i>
                                    <span><fmt:formatNumber value="${course.numOfPeopleJoin}" pattern="#,###" /></span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <!--CourseMore-->
            <div class="course_more" style="display: <c:if test="${xemthem == null}">none</c:if>">
                <input id="ipfcsc" type="text" name="searchtxt" placeholder="Tìm Kiếm" onfocus="inputFocusSearchCourse()" value="${txt}" onkeydown="searchCourse()"/>
                <c:forEach items="${listCourseDetailTitle}" var="course">
                    <div class="course_wrap">
                        <div class="free_course_list_course">
                            <a class="free_course_list_course_link" href="#" style="background-image: url(&quot;${course.image}&quot;);"><div 

                                    onclick="courseDetail(${course.id})"

                                    class="free_course_list_course_viewCoursebtn">Xem khóa học</div></a>
                            <h3>${course.title}</h3>
                            <div class="free_course_list_course_count">
                                <i class="fa-solid fa-users"></i>
                                <span><fmt:formatNumber value="${course.numOfPeopleJoin}" pattern="#,###" /></span>
                            </div>
                        </div>
                        <div class="free_course_list_course_detail">
                            <div>${course.title}</div>
                            <div>${course.detailCourseDes}</div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </form>
        <c:forEach items="${listCourse}" var="course">
            <form id="frm-course-${course.id}" action="videoCourse" method="post">
                <input type="hidden" name="action" value="view"/>
                <input type="hidden" name="courseID" value="${course.id}"/>
            </form>
        </c:forEach>
        <script src="./js/slideShow.js"></script>
        <script>
                                        function courseDetail(id) {
                                            var frmID = 'frm-course-'+id;
                                            document.getElementById(frmID).submit();
                                        }
                                        function searchCourse() {
                                            if (event.key === 'Enter') {
                                                document.getElementById("myform").submit();
                                            }
                                        }
        </script>
    </body>
</html>
