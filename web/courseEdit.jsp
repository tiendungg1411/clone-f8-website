<%-- 
    Document   : courseEdit
    Created on : Jun 18, 2023, 4:04:37 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/header.css"/>
        <link rel="stylesheet" href="css/footer.css"/>
        <link rel="stylesheet" href="css/courseEdit.css"/>
        <script src="js/courseEdit.js"></script>

        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <style>
            .content_bot_des textarea {
                margin-top: 15px;
                width: 550px;
                height: 50px;
                margin-right: 246px;
                font-size: 15px;
            }
            .content_bot_chapter div, .content_bot_chapter input{
                margin-top: 15px;
                margin-bottom: 10px;
            }
            .content_bot_chapter input {
                margin-top: 15px;
                width: 466px;
                font-size: 15px;
                height: 26px;
            }
            .content_bot_target div, .content_bot_target input{
                margin-bottom: 10px;       font-size: 15px;
            }
            .content_bot_target input {
                width: 550px;
                height: 26px; font-size: 15px;
            }
            .content_bot_requiment div, .content_bot_requiment input{
                margin-bottom: 10px; font-size: 15px;
            }
            .content_bot_requiment input {
                width: 550px;
                height: 26px; font-size: 15px;
            }
            .submit_btn{
                background: #4CAF50;
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
                margin-right: 40px;
                margin-bottom: 50px;
                margin-top: 20px;
            }
            .btn_back{
                background: #4CAF50;
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
                margin-left: 100px;
                margin-top: 30px;

            }

            .wrap {
                display: flex;
                justify-content: center;
                align-items: center;

                min-height: 1200px;
            }
            .content {
                width: 800px;
                min-height: 1000px;
                border-radius: 2%;
                margin-top: -90px;
                margin-bottom: 50px;
                position: relative;
                text-align: center;
                margin-right: 100px;
            }
        </style>
    </head>
    <body >
        <jsp:include page="/header.jsp"></jsp:include>
            <form action="editCourseServlet" method="post">
                <input name="cid" value="${listCourse.get(0).id}" type="hidden">
            <div style="display: flex">
                <div style="margin-left: 30px;margin-top: 30px">
                    <jsp:include page="sidebar.jsp"></jsp:include>
                    </div>
                    <button type="button"  class="btn_back" onclick="window.location.href = 'course'">Back</button> 
                    <div style="margin-left: 100px">
                        <div class="wrap">
                            <div class="container content" >

                                <h1 style="padding-top: 120px; padding-bottom: 30px;font-size: 30px">Chỉnh sửa khóa học: ${listCourse.get(0).title}</h1>
                            <div class="content_bot_des">
                                <h2 style="text-align: left;;font-size: 20px">Description: </h2>

                                <c:if test="${listCourseDes.isEmpty()}">
                                    <textarea name="description" style="width: 778px" required></textarea>
                                </c:if>
                                <c:if test="${!listCourseDes.isEmpty()}">
                                    <textarea name="description" style="width: 800px" required>${listCourseDes.get(0).detailCourseDes}</textarea>
                                </c:if>
                            </div><br>

                            <div class="content_bot_target">
                                <h2 style="text-align: left;font-size: 20px ">Target: </h2>
                                <div style="margin-right: 200px;margin-top: 15px;">
                                    <c:if test="${targetStr.isEmpty()}">
                                        <textarea  name="target" style="width: 800px;font-size: 15px;    height: 200px;"></textarea>
                                    </c:if>
                                    <c:if test="${!targetStr.isEmpty()}">                            
                                        <textarea name="target" style="width: 800px;font-size: 15px;    height: 200px;">${targetStr}</textarea>                            
                                    </c:if>
                                </div>

                            </div>


                            <div class="content_bot_chapter">

                                <h2 style="text-align: left;font-size: 20px ">Chapter: </h2>
                                <div style="margin-right: 244px">
                                    <c:if test="${listChapter.isEmpty()}">
                                        <input type="text" style="margin-right: 244px" name="chapter" required class="chapter"/><br>
                                    </c:if>
                                    <c:if test="${!listChapter.isEmpty()}">
                                        <c:forEach items="${listChapter}" var="a">
                                            <input type="text"  name="chapter" value="${a.name}" required class="chapter"/>
                                            <button type="button" style="font-size: 15px;" onclick="redirectToURL1(${listCourseDes.get(0).courseID},${a.id})">Edit Chapter</button>
                                            <br>
                                        </c:forEach>
                                    </c:if>
                                </div>

                            </div>
                            <div class="add_remove_btn">
                                <img onclick="add2()" style="width: 30px; cursor: pointer;" src="./image/plus.png" alt="alt"/>
                                <img onclick="remove2()" style="width: 29px; cursor: pointer; margin-left: 10px;" src="./image/minus.png" alt="alt"/><br>
                            </div>

                            <div class="content_bot_requiment">
                                <h2 style="text-align: left;font-size: 20px ">Requirement: </h2> 
                                <div style="margin-right: 244px;margin-top: 15px">
                                       <c:if test="${requiStr.isEmpty()}">
                                    <textarea name="requiment" style="width: 800px;font-size: 15px;    height: 200px;"></textarea>
                                </c:if>
                                <c:if test="${!requiStr.isEmpty()}">                            
                                    <textarea name="requiment" style="width: 800px; font-size: 15px;   height: 200px;">${requiStr}</textarea>                            
                                </c:if>
                                </div>
                             
                            </div>



                            <input  type="submit" value="Edit" class="submit_btn"/>
                        </div>
                    </div>


                </div>
            </div>

        </form>


        <div class="footer">
            <jsp:include page="footer.jsp"></jsp:include>
            </div>
            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script>
                                    function activeErrorToast() {
                                        toastr.error('${savedBlogStatus}');
                                    }
                                    function activeSuccessToast() {
                                        toastr.success('${savedBlogStatus}');
                                    }
                                    if ('${savedBlogStatus}' === 'Chinh sua thanh cong')
                                        activeSuccessToast();
                                    if ('${savedBlogStatus}' === 'Xóa khỏi mục đã lưu')
                                        activeErrorToast();
                                    function redirectToURL1(n, p) {
                                        window.location = 'editChapterServlet?cid=' + n + '&chapterID=' + p;
                                    }


        </script>
    </body>
</html>
