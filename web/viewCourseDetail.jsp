<%-- 
    Document   : viewCourseDetail
    Created on : May 25, 2023, 3:45:49 PM
    Author     : DELL
--%>

<%@page import="Model.Chapter"%>
<%@page import="Model.Lesson"%>
<%@page import="Model.CourseLearnWhat"%>
<%@page import="Model.CourseRequirement"%>
<%@page import="DAO.LearnDAO"%>
<%@page import="Model.CourseDetail"%>
<%@page import="Model.Course"%>
<%@page import="java.util.ArrayList"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Course Detail</title>
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" type="text/css" href="./css/sidebar.css">
        <link rel="stylesheet" href="./font/fontawesome-free-6.4.0-web/fontawesome-free-6.4.0-web/css/all.min.css">
        <!--<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>-->
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css'>
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Raleway:300,400,500,600,700i,800'>
        <link href="./css/vid.css" rel="stylesheet" type="text/css"/>

        <link href="css/learning.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/learning.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            .course-outline .chapter .lessons {
                display: none;
            }

            .course-outline .chapter.active .lessons {
                display: block;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div style="position: absolute;"><jsp:include page="sidebar.jsp"></jsp:include></div>


            <div class="container" style="margin-left: 10%;">
                <div class="row">
                    <div class="col-6 col-sm-8" style="">
                        <h1 style="font-size: 35px;">${listCourse.get(0).title}</h1>
                    <p style="font-size: 15px;">${listCourseDes.get(0).detailCourseDes}</p>
                    <h3 style="margin-top: 30px; font-size: 27px;">Bạn sẽ học được gì?</h3>

                    <div  style="display: flex;flex-wrap: wrap">
                        <c:forEach items="${ListTarget}" var="ListTarget" >                        
                            <div class="row" style="display: flex;flex-basis: 50%;  align-self: flex-start">
                                <img style="width: 25px; height: 10px;margin-top: 6px" src="image/tick_real.png">
                                <div style="width:89% ;font-size: 15px" class="col-6">${ListTarget.content}</div>
                            </div>
                        </c:forEach>
                    </div>

                    <h3 style="margin-top: 30px; font-size: 27px;">Nội dung khóa học</h3>
                    <div class="button-container" style="margin-bottom: 15px">
                        <p id="expand-all-button" class="expand-button" style="margin-bottom: 10px;padding-right: 20px;cursor: pointer;float: right; color: #f05123;font-weight: bold">Mở rộng tất cả</p>
                    </div>

                    <div class="course-outline" style="padding-top: 10px">
                        <c:forEach items="${listChapter}" var="chapter">
                            <div class="chapter">
                                <h3 style="cursor: pointer;padding: 14px 20px;border-radius: 10px ;border: 1px solid rgba(0,0,0,.15); background-color: #e0e0e0" class="chapter-title">${chapter.name}</h3>
                                <ul class="lessons">
                                    <c:forEach items="${listLesson}" var="lesson">
                                        <c:if test="${chapter.id == lesson.chapterID}">
                                            
                                            
                                            <div style="display: flex;border-bottom: 1px solid gray">
                                                <i class="<c:if test="${lesson.type == 'video'}">fa-solid fa-circle-play</c:if>
                                                   <c:if test="${lesson.type == 'practice'}">fa-regular fa-file-audio</c:if>
                                                   <c:if test="${lesson.type == 'feedback'}">fa fa-commenting-o</c:if>"
                                                   style="margin-right: 4px; margin-top: 15px"></i>
                                                <li style="margin-top: 10px;list-style-type: none;" class="lesson">${lesson.name}</li>
                                            </div>

                                        </c:if>
                                    </c:forEach>                                    
                                </ul>
                            </div>
                        </c:forEach>
                    </div>
                    <h3 style="margin-top: 30px; font-size: 27px;">Yêu cầu</h3>
                    <c:forEach items="${ListRequirement}" var="ListRequirement">
                        <div class="row">
                            <img style="width: 25px; height: 10px" src="image/tick_real.png">
                            <div style="font-size: 15px;" class="col-6">${ListRequirement.content}</div>
                        </div>
                    </c:forEach>
                </div>


                <div class="col-6 col-sm-4">                     
                    <section>
                        <div class="d-flex flex-column justify-content-center align-items-center gap-3"
                             style="width: 360px;">
                            <div data-bs-toggle="modal" data-bs-target="#modalIntroductionCourse"
                                 class="position-relative rounded-4 text-center cursor-pointer" style="height: 200px;">
                                <!--                                Change your image of each course here -->
                                <image class="rounded-4" width="100%" height="100%"
                                       src="${listCourseDes.get(0).image}"></image>
                                <div class="position-absolute top-0 start-0 end-0 bottom-0 rounded-4"
                                     style="background-color: #1c1d1f40;">
                                </div>
<!--                                <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle-play"
                                     style="width: 60px; height: 60px; bottom: 50%; right: 50%; transform: translateX(50%) translateY(50%);"
                                     class="svg-inline--fa fa-circle-play CourseDetail_icon__sLJtd position-absolute"
                                     role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                                <path fill="#000000"
                                      d="M512 256C512 397.4 397.4 512 256 512C114.6 512 0 397.4 0 256C0 114.6 114.6 0 256 0C397.4 0 512 114.6 512 256zM176 168V344C176 352.7 180.7 360.7 188.3 364.9C195.8 369.2 205.1 369 212.5 364.5L356.5 276.5C363.6 272.1 368 264.4 368 256C368 247.6 363.6 239.9 356.5 235.5L212.5 147.5C205.1 142.1 195.8 142.8 188.3 147.1C180.7 151.3 176 159.3 176 168V168z">
                                </path>
                                </svg>-->
                                <div class="position-absolute start-0 end-0"
                                     style="color: white; font-weight: 700; bottom: 12px;">
                                </div>
                            </div>
                            <h2 style="color: #f05123;">
                                <c:if test="${listCourse.get(0).price==0}"> 
                                    <p style="font-size: 30px;font-weight: bold; color: orangered; text-align: center; margin-top: 20px">Miễn Phí</p>
                                </c:if>
                                <c:if test="${listCourse.get(0).price!=0}"> 
                                    <p style="font-size: 30px;font-weight: bold; color: orangered; text-align: center; margin-top: 20px">Pro</p>
                                </c:if>
                            </h2>
                            <form action="videoCourse" method="post" class="w-100 d-flex justify-content-center">
                                <input type="hidden" name="action" value="register"/>
                                <input type="hidden" name="courseID" value="${listCourseDes.get(0).courseID}"/>
                                <button class="btn btn-primary btn-lg"
                                        style="color: white; border: 1px solid #f05123;border-radius: 40px; background-color: #f05123;font-size: 18px;width: 60%;">
                                    ĐĂNG KÝ HỌC
                                </button>
                            </form>
                            <div class="" style="font-size: 15px; font-weight: 400;">
                                <div>
                                    <i class="fa-solid fa-gauge-high me-3"></i>
                                    Trình độ ${listCourseDes.get(0).level}
                                </div>
                                <div>
                                    <i class="fa-solid fa-film me-3"></i>
                                    Tổng số ${totalLesson} bài học
                                </div>
                                <div>
                                    <i class="fa-solid fa-battery-full me-3"></i>
                                    Học mọi lúc, mọi nơi
                                </div>
                            </div>
                        </div>

                        <!--                     Modal introduction course -->
                        <div class="modal fade" id="modalIntroductionCourse" tabindex="-1"
                             aria-labelledby="modalIntroductionCourseLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h2 class="modal-title fs-5" style="font-size: 18px !important;"
                                            id="exampleModalLabel">Giới
                                            thiệu khóa học</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h2 style="font-weight: bold;">${listLesson.get(0).name}</h5>
                                            <div class="mt-4" style="height: 480px;font-size: 24px">
                                                <iframe width="100%" height="100%"
                                                        src="${listLesson.get(0).link}" frameborder="0"
                                                        allowfullscreen></iframe>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>

                </div>

            </div>
        </div>

        <div class="footer">
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
        <!-- partial -->

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js'></script>
        <script src="js/scrips.js" type="text/javascript"></script>
        <script>
            // Lắng nghe sự kiện khi nhấp vào tiêu đề chương
            const chapterTitles = document.querySelectorAll('.chapter-title');
            chapterTitles.forEach((title) => {
                title.addEventListener('click', () => {
                    const currentChapter = title.parentNode;

                    // Kiểm tra xem chương hiện tại đã có lớp "active" hay chưa
                    const isActive = currentChapter.classList.contains('active');

                    // Loại bỏ lớp "active" khỏi tất cả các chương
                    const chapters = document.querySelectorAll('.chapter');
                    chapters.forEach((chapter) => {
                        chapter.classList.remove('active');
                    });

                    // Nếu chương chưa được kích hoạt, thêm lớp "active"
                    if (!isActive) {
                        currentChapter.classList.add('active');
                    }
                });
            });
            // Lắng nghe sự kiện khi nhấn vào nút "Mở rộng tất cả" / "Thu nhỏ tất cả"
            const expandAllButton = document.getElementById('expand-all-button');
            expandAllButton.addEventListener('click', () => {
                const chapters = document.querySelectorAll('.chapter');
                const isAllExpanded = Array.from(chapters).every((chapter) =>
                    chapter.classList.contains('active')
                );

                if (isAllExpanded) {
                    // Thu nhỏ tất cả các chương
                    chapters.forEach((chapter) => {
                        chapter.classList.remove('active');
                    });
                    expandAllButton.textContent = 'Mở rộng tất cả';
                } else {
                    // Mở rộng tất cả các chương
                    chapters.forEach((chapter) => {
                        chapter.classList.add('active');
                    });
                    expandAllButton.textContent = 'Thu nhỏ tất cả';
                }
            });


        </script>
    </body>
</html>
