<%-- 
    Document   : editQuiz
    Created on : 12/07/2023, 3:27:50 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <title>JSP Page</title>
        <style>
            .wrap {
                width: 100%;
                height: 700px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .footer {
                margin: 0;
            }
            .wrap_content {

                height: 600px;

                border-radius: 10px;
            }
            .content_head {
                display: flex;
                justify-content: center;
            }
            .content_head img{
                width: 60px;
                margin-top: 18px;
                margin-bottom: 12px;
                border-radius: 10px;
            }
            .content_head div{
                text-align: center;
            }
            .content_head h1{
                margin-bottom: 30px;
            }
            .quiz {
                font-size: 18px;
                height: 30px;
                display: flex;
                align-items: center;
                background-color: rgba(238, 222, 231, 0.8);
                margin: 0 30px 10px 30px;
                border-radius: 5px;
                padding: 4px 10px;
            }
            .quiz input {
                margin-right: 10px;
            }
            .submit_btn{
                background: #18ce18;
                color: white;
                border-style: outset;
                border-color: #0066A2;
                height: 40px;
                width: 100px;
                font: bold15px arial,sans-serif;
                text-shadow: none;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                margin: 10px 40px 0px 30px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="./header.jsp"></jsp:include>

            <div style="display: flex"> 
                <div style="margin-left: 30px;margin-top: 30px">
                <jsp:include page="sidebar.jsp"></jsp:include>
                </div>
                <div style="margin-left: 200px">
                    <div class="wrap">
                        <div class="wrap_content">
                            <div class="content_head">
                                <h1 style="font-size: 37px; margin-bottom: 30px">Edit Lesson</h1>
                            </div>
                            <div style="color: #0d4f4f ;margin: 24px 96px; font-size: 23px;">Lesson: ${lesson.name}.</div>
                        <form id="f" action="editLessonDetail" method="post">
                            <!--hidden field-->
                            <input name="lessonID" value="${lesson.id}" type="hidden" />
                            <input name="courseId" value="${lesson.courseID}" type="hidden"/>

                            <div style="    font-size: 23px;margin-top: 20px">Name:</div>
                            <input style="    height: 30px;
                                   width: 500px;
                                   margin-top: 10px;
                                   border-radius: 5px;
                                   border-color: #c4c4e3;
                                   font-size: 20px;
                                   " name="name" value="${lesson.name}" type="text"/><br> 
                            <div style="    font-size: 23px;margin-top: 20px">Type:</div>
                            <select style="height: 30px;
                                    width: 200px;
                                    margin-top: 10px;
                                    border-radius: 10px;
                                    font-size: 17px;" name="type" required>
                                <option <c:if test="${lesson.type == ''}">selected</c:if> value="default">Chọn Type</option>
                                <option <c:if test="${lesson.type == 'video'}">selected</c:if> value="video">Video</option>
                                <option <c:if test="${lesson.type == 'practice'}">selected</c:if> value="practice">Quiz</option>
                                <option <c:if test="${lesson.type == 'feedback'}">selected</c:if> value="feedback">Feedback</option>
                                </select><br> 
                                <div style="    font-size: 23px;margin-top: 20px">Link:</div>
                                <input style="    height: 30px;
                                   width: 500px;
                                   margin-top: 10px;
                                   border-radius: 5px;
                                   border-color: #c4c4e3;
                                   font-size: 20px;
                                   " name="link" value="${lesson.link}" type="text"/> <br> 
                            <div style="    font-size: 23px;margin-top: 20px">Chapter:</div>
                            <div class="content_dropdown_lesson">
                                <select style="height: 30px;
                                    width: 200px;
                                    margin-top: 10px;
                                    border-radius: 10px;
                                    font-size: 17px;" name="chapterId" required>
                                    <c:forEach items="${listChapter}" var="c">
                                        <option <c:if test="${c.id == lesson.chapterID}">selected</c:if> value="${c.id}">${c.name}</option>
                                    </c:forEach>
                                </select>
                            </div><br> 




                            <input style="margin-left: 202px;" class="submit_btn" type="submit" value="Edit" />
                        </form>
                    </div>
                </div>

            </div>
            <a>
                <img src="https://fullstack.edu.vn/static/media/fb-group-cards.4bd525b1b8baf7b1e5a2.png" style="width: 420px;height: 420px;margin-left: 100px;margin-top: 200px" >

            </a>
        </div>



        <jsp:include page="./footer.jsp"></jsp:include>  
            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script>
                function activeSuccessToast() {
                    toastr.success('${savedBlogStatus}', '', {
                        "toastClass": "toast-success"
                    });
                }

                if ('${savedBlogStatus}' === 'Chinh sua thanh cong') {
                    activeSuccessToast();
                }
        </script>
    </body>
</html>

