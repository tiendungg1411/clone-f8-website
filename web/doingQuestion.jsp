<%-- 
    Document   : doingQuizLesson
    Created on : 17/06/2023, 2:19:46 PM
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
        <link rel="stylesheet" type="text/css" href="./css/listBlog.css">
        <title>JSP Page</title>
        <style>
            body{
                position: relative;
            }
            .quiz_wrap{
                text-align: center;
                padding-top: 72px;
               
                min-height: 600px;
                padding-bottom: 100px;
            }
            .quiz_wrap h2{
                font-size: 28px;
            }
            .quiz_wrap_wrap {
                text-align: start;
                margin-left: 400px;
                margin-right: 300px;
            }
            .quiz_question_content {
                font-size: 16px;
                margin-top: 30px;
                font-weight: 600;
            }
            .quiz_question_cdad {
                font-size: 16px;
                margin-top: 20px;
                margin-bottom: 30px;
            }
            .quiz_answer {
                display: block;
                margin: 10px 0;
                width: 320px;
                height: 40px;
                background-color: #e0e0e0;
                border: none;
                border-radius: 10px;
                cursor: pointer;
            }
            .quiz_explain {
                margin-top: 50px;
                font-size: 18px;
                color: #f05123;
                font-weight: 500;
            }
            .back_btn {
                position: absolute;
                top: 138px;
                left: 124px;
                font-size: 16px;
                font-weight: 700;
                color: #f05123;
                cursor: pointer;
                text-decoration: none;
            }
            .back_btn:hover{
                font-size: 18px;
                transition: .3s ease;
                left: 120px;
            }
            .activeTrue {
                background-color: rgba(92, 182, 120, 0.6);
            }
            .activeFalse {
                background-color: rgba(210, 131, 146, 0.6);
            }
            #ft{
                margin-top: 0;
            }
        </style>
    </head>
    <body>
        <jsp:include page="./header.jsp"></jsp:include>
        <div style="display: flex ">
            <div style="margin-left: 30px;margin-top: 30px">
                    <jsp:include page="sidebar.jsp"></jsp:include>
                    </div>
                    <div>
                        <a class="back_btn" value="" href="videoCourse?courseID=${courseID}" style="margin-top:-50px;margin-left: 30px">< Quay về bài học</a>
                    <div class="quiz_wrap" style="margin-left: -150px">
            <div class="quiz_wrap_wrap">
                <h2 class="">${lesson.name}</h2>
                <c:forEach items="${lqac}" var="q">
                    <div class="quiz_question_content">Câu hỏi: ${q.question.content}</div>
                    <div class="quiz_question_cdad">Chọn đáp án đúng: </div>
                    <c:set var="flag" value="false"></c:set>
                    <c:forEach items="${q.answer}" var="a">
                        <button onclick="window.location = 'doingQuestion?mode=answer&answer=${a.content}&questionID=${q.question.id}&lessonID=${lessonID}&courseID=${courseID}&page=${currentPage}'" class="quiz_answer
                                <c:if test="${q.accountDoingQuestion != null && a.content == q.accountDoingQuestion.answer && q.accountDoingQuestion.trueOrFalse == 'true'}">activeTrue <c:set var="flag" value="true"></c:set> </c:if>
                                <c:if test="${q.accountDoingQuestion != null && a.content == q.accountDoingQuestion.answer && q.accountDoingQuestion.trueOrFalse == 'false'}">activeFalse <c:set var="flag" value="true"></c:set> </c:if> " type="button">${a.content}
                                    </button>
                    </c:forEach>
                    <div style="display: <c:if test="${!flag && !flag1}">none</c:if>" class="quiz_explain">Giải thích: ${q.question.explain}</div>
                </c:forEach>
            </div>
        </div>

        <!--Pagination-->
        <div class="paging_wrap" style="margin-top: -150px;margin-bottom: 70px;margin-left: 300px">
            <div class="paging_previous" style="display: <c:if test="${currentPage != 1}">none</c:if>; cursor: default">
                    <i class="fa-solid fa-angles-left" style="color: #d0d7de"></i>
                </div>
                <div onclick="window.location = 'doingQuestion?page=${currentPage-1}&courseID=${courseID}&lessonID=${lessonID}'" class="paging_previous hv" style="display: <c:if test="${currentPage == 1}">none</c:if>">
                    <i class="fa-solid fa-angles-left"></i>
                </div>
            <c:forEach begin="1" end="${totalPage}" var="i">
                <div onclick="window.location = 'doingQuestion?page=${i}&courseID=${courseID}&lessonID=${lessonID}'" class="paging_page hv <c:if test="${currentPage==i}">pagingActive</c:if>">${i}</div>
            </c:forEach>
            <div onclick="window.location = 'doingQuestion?page=${currentPage+1}&courseID=${courseID}&lessonID=${lessonID}'" class="paging_next hv" style="display: <c:if test="${currentPage == totalPage}">none</c:if>">
                    <i class="fa-solid fa-angles-right"></i>
                </div>
                <div class="paging_previous" style="display: <c:if test="${currentPage != totalPage}">none</c:if>">
                    <i class="fa-solid fa-angles-right" style="color: #d0d7de"></i>
                </div>
            </div>  
                    </div>
                    <a>
                    <img src="https://fullstack.edu.vn/static/media/fb-group-cards.4bd525b1b8baf7b1e5a2.png" style="width: 420px;height: 420px;margin-top: 100px" >

                </a>
        </div>
      
        <jsp:include page="./footer.jsp"></jsp:include>
        <script src="./CommonLib/bootstrap/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
