<%-- 
    Document   : editQuizLesson
    Created on : 17/06/2023, 11:01:44 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/newQuizLesson.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="./header.jsp"></jsp:include>
        <form action="newQuizLesson" method="post">
                <div class="wrap">
                    <div class="content">
                        <div class="content_head">
                            <h1>Edit Quiz cho bài học</h1>  
                        </div>
                        <div class="content_dropdown">
                            <div class="content_dropdown_course">
                                <select name="course" required>
                                    <option selected>Choose</option>
                                    <option value="saab">Saab</option>
                                    <option value="vw">VW</option>
                                    <option value="audi" selected>Audi</option>
                                </select>
                            </div>
                            <div class="content_dropdown_lesson">
                                <select name="course" required>
                                    <option selected>Choose</option>
                                    <option value="saab">Saab</option>
                                    <option value="vw">VW</option>
                                    <option value="audi" selected>Audi</option>
                                </select>
                            </div>
                        </div>
                        <div class="content_bot">
                            <div class="content_bot_question">
                                <div>Câu hỏi: </div> 
                                <textarea name="question" required></textarea>
                            </div><br>
                            <div class="content_bot_answer">
                                <div>Đáp án: </div>
                                <input type="text" name="answer" required class="answer"/><br>
                                <input type="text" name="answer" required class="answer"/><br>
                                <input type="text" name="answer" required class="answer"/><br>
                                <input type="text" name="answer" required class="answer"/><br>
                            </div>
                            <div class="submit_wrap">
                                <input type="submit" value="Edit" class="submit_btn"/>
                            </div>

                        </div>
                    </div>
                </div>
            </form>

        <jsp:include page="./footer.jsp"></jsp:include>  
    </body>
</html>

