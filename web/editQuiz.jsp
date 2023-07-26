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
        <title>JSP Page</title>
        <style>
            .wrap {
                width: 100%;
                height: 900px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .footer {
                margin: 0;
            }
            .wrap_content {
               
                height: 800px;
               
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
                height: 50px;
                display: flex;
                align-items: center;
                background-color: rgba(238, 222, 231, 0.8);
                margin: 0 30px 10px 30px;
                border-radius: 5px;
                padding: 4px 10px;
                line-height: 25px;
            }
            .quiz input {
                margin-right: 10px;
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
                        <h1 style="font-size: 37px">Edit Quiz</h1>
                    </div>
                    <div style="color: #0d4f4f ;margin: 14px 24px; font-size: 23px;">Thêm, xóa câu hỏi cho câu đố ${lesson.name}.</div>
                <form id="f" action="editQuiz" method="post">
                    <!--hidden field-->
                    <input name="lessonID" value="${lesson.id}" type="hidden" />
                    <c:forEach items="${listQ}" var="q">
                        <c:set var="flag" value="0"></c:set>
                            <div class="quiz">
                            <c:forEach items="${listTickQ}" var="tq">
                                <c:if test="${q.id == tq.id}">
                                    <c:set var="flag" value="1"></c:set>
                                </c:if>
                            </c:forEach>
                            <input <c:if test="${flag == 1}">checked</c:if> type="checkbox" value="${q.id}" name="questions" />
                            <div >${q.content}</div>
                        </div>

                    </c:forEach>
                    <input class="submit_btn" type="submit" value="Edit" />
                </form>
            </div>
        </div>
 
                    </div>
 <a>
                    <img src="https://fullstack.edu.vn/static/media/fb-group-cards.4bd525b1b8baf7b1e5a2.png" style="width: 420px;height: 420px;margin-left: 100px;margin-top: 200px" >

                </a>
        </div>
        
           

        <jsp:include page="./footer.jsp"></jsp:include>  
    </script>
</body>
</html>

