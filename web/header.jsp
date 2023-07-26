<%-- 
    Document   : header
    Created on : 14/05/2023, 8:22:47 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="./css/base.css">
        <!--<link rel="stylesheet" type="text/css" href="./css/header.css">-->
        <link rel="stylesheet" href="./font/fontawesome-free-6.4.0-web/fontawesome-free-6.4.0-web/css/all.min.css">
        <title>Document</title>
    </head>
    <body>
        <form id="myform" action="searchCourse">
            <div class="header">
                <div class="header_left">
                    <div class="header_left_logo">
                        <a href="/g4/home">
                            <img src="./image/f8_logo.png" alt="F8">
                        </a>
                        <h4 style="display: <c:if test="${xemthem != null || newBl != null || hoc != null}">none</c:if>" class="header_left_logo_text">Học Lập Trình Để Đi Làm</h4>
                        <div style="display: <c:if test="${xemthem == null && newBl == null && hoc == null}">none</c:if>" class="header_left_logo_back" onclick="goBackHome()">
                                <i class="fa-solid fa-angle-left"></i>
                                <div>QUAY LẠI</div>
                            </div>
                        </div>
                        <div style="display: <c:if test="${xemthem != null || createc != null || newBl != null}">none</c:if>" class="header_left_search">
                            <div id="ipfc" class="header_left_search_wrap">
                                <div class="header_left_search_icon">
                                </div>
                                <input name="searchtxt" class="header_left_search_input" spellcheck="false" placeholder="Tìm kiếm khóa học, bài viết, video, ..." value="${txt}" onfocus="inputFocus()" onblur="inputBlur()" onkeydown="search(e)">
                        </div>
                        <div class="header_left_search_popup">
                            <c:if test="${listCourseDetailTitle != null}">
                                <div class="header_left_search_popup_wrap">
                                    <div class="header_left_search_popup_result">
                                        <i class="fa-solid fa-magnifying-glass"></i>
                                        <div>
                                            <c:if test="${empty listCourseDetailTitle}">
                                                Không có kết quả cho '${txt}'
                                            </c:if>
                                            <c:if test="${!empty listCourseDetailTitle}">
                                                Kết quả cho '${txt}'
                                            </c:if>
                                        </div>
                                    </div>
                                    <c:if test="${!empty listCourseDetailTitle}">
                                        <div class="header_left_search_popup_head">
                                            <div>KHÓA HỌC</div>
                                            <div onclick="moreDetail()">Xem thêm</div>
                                        </div>
                                    </c:if>

                                    <div class="header_left_search_popup_content">
                                        <c:forEach items="${listCourseDetailTitle}" var="c">
                                            <div onclick="courseDetail(${c.id})" class="header_left_search_popup_content_course">
                                                <img src="${c.image}" alt="F8">
                                                <div>${c.title}</div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="header_right_register" style="display: <c:if test="${sessionScope.account != null}">none</c:if>">
                            <div class="header_right_register_login">
                                <span onclick='login()'>ĐĂNG NHẬP</span>
                            </div>
                            <div class="header_right_register_register" onclick="redirect_register()">
                                <span>ĐĂNG KÝ</span>
                            </div>
                        </div>
                        <div class="header_right_login" style="display: <c:if test="${sessionScope.account == null}">none</c:if>">
                            <div class="my_course_wrap">
                                <h4 onclick="mycoursePopup()">Khóa học của tôi</h4>
                                <div class="my_course_wrap_popup" style="display: none">
                                    <div class="my_course_wrap_popup_wrap">
                                        <div class="my_course_wrap_popup_wrap_head">
                                            <div>Khóa học của tôi</div>
                                            <div onclick="window.location = 'myCourse'">Xem tất cả</div>
                                        </div>
                                    <c:if test="${myCourses == null}">
                                        <div class="my_course_wrap_popup_wrap_content">
                                            <div>Bạn chưa đăng ký khóa học nào</div>
                                        </div>
                                    </c:if>
                                        <!--display MyCourses-->
                                    <c:forEach items="${myCourses}" var="c">
                                        <div class="my_list_course_wrap"  onclick="window.location = 'videoCourse?courseID=${c.id}'">
                                            <div class="my_list_course">
                                                <img onclick="" src="${c.image}" alt="alt"/>
                                                <div class="my_list_course_title">
                                                    <div class="my_list_course_title_1">${c.title}</div>
                                                    <div class="my_list_course_title_2">Học cách đây ${c.timeRegistration}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                        <div class="bell_wrap">
                            <div><i class="fa-solid fa-bell" onclick="rellPopup()"></i></div>
                            <div class="bell_wrap_popup" style="display: none">
                                <div class="bell_wrap_popup_wrap">
                                    <div class="bell_wrap_popup_wrap_head">
                                        <div>Thông báo</div>
                                        <div onclick="window.location = 'notification'">Xem tất cả</div>
                                    </div>
                                    <c:if test="${notificationList == null}">
                                        <div class="bell_wrap_popup_wrap_content">
                                            <div>Chưa có thông báo nào</div>
                                        </div>
                                    </c:if>
                                    <div>
                                        <div class="header_notify_wrap">
                                            <c:forEach items="${notificationList}" var="n">
                                                <div class="header_notify_item">
                                                    <img src="${n.fromAccountAvatar}" alt="F8">
                                                    <div><span>${n.fromAccountName}</span> ${n.content}</div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>

                        <div class="header_right_login_logo_wrap">
                            <img src="${sessionScope.account.avatar}" class="header_right_login_logo" onclick="showPopupAvatar()"/>
                            <div class="header_right_login_logo_popup">
                                <div class="header_right_login_logo_popup_content" style="display: none">
                                    <div class="header_right_login_logo_popup_logo">
                                        <img src="${sessionScope.account.avatar}" class="header_right_login_logo"/>
                                        <div>
                                            <div>${sessionScope.account.firstAndLastName}</div>
                                            <div>@${sessionScope.account.userName}</div>
                                        </div>
                                    </div><hr>
                                    <div class="header_right_login_logo_popup_detail">
                                        <div onclick="window.location = 'profile?id=${sessionScope.account.getId()}'">Trang cá nhân</div><hr>
                                        <div>Viết blog</div>
                                        <div onclick="window.location = 'myblog'">Bài viết của tôi</div><hr>
                                        <div onclick="window.location = 'blogSaved'">Bài viết đã lưu</div>
                                        <div onclick="window.location = 'account'" style="display: <c:if test="${!(sessionScope.account.roleID != null && sessionScope.account.roleID == 1)}">none</c:if>">Danh sách người dùng</div>
                                        <div onclick="window.location = 'commentManage'" style="display: <c:if test="${!(sessionScope.account.roleID != null && sessionScope.account.roleID == 1)}">none</c:if>">Danh sách bình luận</div><hr>
                                        <div onclick="window.location = 'blogManagementServlet'" style="display: <c:if test="${!(sessionScope.account.roleID != null && sessionScope.account.roleID == 1)}">none</c:if>">Danh sách Blog</div><hr>
                                            <div onclick="window.location = 'generalManagerment.jsp'" style="display: <c:if test="${!(sessionScope.account.roleID != null && sessionScope.account.roleID == 1)}">none</c:if>">Quản lí chung</div>


                                        <div onclick="window.location = 'setting'">Cài đặt</div>

                                        <div onclick='logout()'>Đăng xuất</div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script>
            function login() {
                window.location = 'login';
            }
            function logout() {
                window.location = 'logout';
            }
            function courseDetail(id) {
                window.location = "courseDetail?courseID=" + id;
            }
            function inputFocus() {
                document.getElementById("ipfc").style.border = 'solid 2px #444444'
            }
            function inputBlur() {
                document.getElementById("ipfc").style.border = '2px solid #e8e8e8'
            }
            function showPopupAvatar() {
                var popLogo = document.querySelector(".header_right_login_logo_popup_content");
                var popRell = document.querySelector(".bell_wrap_popup");
                var popMyCourse = document.querySelector(".my_course_wrap_popup");
                if (popLogo.style.display === "none") {
                    popLogo.style.display = "block";
                    popRell.style.display = "none";
                    popMyCourse.style.display = "none";
                } else {
                    popLogo.style.display = "none";
                }
            }
            function rellPopup() {
                var popLogo = document.querySelector(".header_right_login_logo_popup_content");
                var popRell = document.querySelector(".bell_wrap_popup");
                var popMyCourse = document.querySelector(".my_course_wrap_popup");
                if (popRell.style.display === "none") {
                    popRell.style.display = "block";
                    popLogo.style.display = "none";
                    popMyCourse.style.display = "none";
                } else {
                    popRell.style.display = "none";
                }
            }
            function mycoursePopup() {
                var popLogo = document.querySelector(".header_right_login_logo_popup_content");
                var popRell = document.querySelector(".bell_wrap_popup");
                var popMyCourse = document.querySelector(".my_course_wrap_popup");
                if (popMyCourse.style.display === "none") {
                    popMyCourse.style.display = "block";
                    popLogo.style.display = "none";
                    popRell.style.display = "none";
                } else {
                    popMyCourse.style.display = "none";
                }
            }
            function search(e) {
                if (event.key === 'Enter') {
                    document.getElementById("myform").submit();
                }
            }
            window.addEventListener('click', function (e) {
                document.querySelector(".header_left_search_popup").style.display = 'none';
            });
            function moreDetail() {
                var form = document.getElementById("myform");
                form.method = 'post';
                form.submit();
            }
            function goBackHome() {
                window.location = 'home';
            }
            function redirect_register() {
                window.location.href = "/g4/registerMainPage.jsp";
            }
        </script>
    </body>
</html>
