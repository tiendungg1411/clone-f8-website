<%-- 
    Document   : chapterEdit
    Created on : Jun 25, 2023, 5:30:30 PM
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
        <link rel="stylesheet" href="css/chapterEdit.css"/>

        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <style>
            .content_bot_lesson div, .content_bot_lesson input{
    margin-bottom: 10px;
}
.content_bot_lesson input {
    width: 550px;
    height: 26px;
    font-size: 15px;
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
    font-size: 15px;
}
.wrap {
    display: flex;
    justify-content: center;
    align-items: center;
    
    min-height: 700px;
}
.content {
    width: 800px;
    min-height: 600px;
 
    border-radius: 2%;
    margin-top: 60px;
    margin-bottom: 50px;
    position: relative;
    text-align: center;
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
    margin-right: 680px;
    margin-bottom: 30px;
    margin-top: 15px;
}
        </style>
    </head>
    <body>
        <jsp:include page="/header.jsp"></jsp:include>
            <form action="editChapterServlet" method="post">
                <div style="display: flex">
                     <div style="margin-left: 30px;margin-top: 30px">
                    <jsp:include page="sidebar.jsp"></jsp:include>
                    </div>
                    <div style="margin-left: 30px">
                         <div class="wrap">
                    <div class="container content">      
                        <button type="button" style="margin-top: -50px;font-size: 17px"  class="btn_back" onclick="window.location.href='editCourseServlet?courseId=${cid}'">Trở lại</button> 
                        <input name="cid" value="${cid}" type="hidden">
                    <input name="chapterID" value="${chapterID}" type="hidden">
                    <h1 style="margin-bottom: 15px;font-size: 30px">${listC.get(0).name}</h1>
                    <div class="content_bot_lesson" style="font-size: 15px">
                        <h2 style="text-align: left; padding-left: 80px; padding-bottom: 20px;">Bài học : </h2>
                        <c:if test="${listLesson.isEmpty()}">
                            <input type="text" name="lesson" required class="lesson"/><br>
                        </c:if>
                        <c:if test="${!listLesson.isEmpty()}">
                            <c:forEach items="${listLesson}" var="a">
                                <input type="text" name="lesson" value="${a.name}" required class="lesson"/><br>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="add_remove_btn">
                        <img onclick="add()" style="width: 30px; cursor: pointer;" src="./image/plus.png" alt="alt"/>
                        <img onclick="remove()" style="width: 29px; cursor: pointer; margin-left: 10px;" src="./image/minus.png" alt="alt"/><br>
                    </div>
                    <input type="submit" value="Edit" class="submit_btn"/>
                </div>
                </div>
                 
                    </div>
                     <a>
                    <img src="https://fullstack.edu.vn/static/media/fb-group-cards.4bd525b1b8baf7b1e5a2.png" style="width: 420px;height: 420px;margin-left: 100px;margin-top: 100px" >

                </a>
                </div>
              
        </form>




                    <div class="footer" style="margin-top: -200px">
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <script>    
                    function activeSuccessToast() {
                    toastr.success('${savedBlogStatus}');
                }
                if ('${savedBlogStatus}' === 'Chinh sua thanh cong')
                    activeSuccessToast();
                    function add() {
                        const contain = document.querySelector(".content_bot_lesson");
                        const inputTag = document.createElement('input');
                        //style="width: 750px; height: 40px; margin-bottom: 10px" type="text" name="answer" value=""
                        inputTag.style.width = '550px';
                        inputTag.style.height = '26px';
                        inputTag.style.marginBottom = '10px';
                        inputTag.type = "text";
                        inputTag.name = "lesson";
                        inputTag.className = "lesson";
                        contain.appendChild(inputTag);

                    }
                    function remove() {
                        const select = document.querySelector(".content_bot_lesson");
                        var numOfInputTag = document.getElementsByClassName("lesson");

                        if (numOfInputTag.length > 1) {
                            select.removeChild(select.lastChild);
                        }
                    }
        </script>
    </body>
</html>
