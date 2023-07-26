<%-- 
    Document   : QAOfLesson
    Created on : Jun 21, 2023, 10:51:45 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/header.css"/>
        <link rel="stylesheet" href="css/sidebar.css"/>
        <link rel="stylesheet" href="css/footer.css"/>
        <link rel="stylesheet" href="css/qaLesson.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container" style="position: absolute">
                <div class="content_top">
                    <h1><span style="color: red; font-size: 40px">Q&A:</span> <span class="lessonName"onclick="window.location = 'videoCourse?selectedID=${lessonId}&mod=1'">${lessonName}</span> </h1>
                <h3>${numOfListQA} hỏi đáp</h3>
                <hr style="margin-top: 10px; width: 80%">
            </div>
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div class="content_bot">
                    <div class="listComment">
                    <c:set var="idInputRep" value="1"></c:set>
                        <!--Loop to get list Q&A of lesson-->
                    <c:forEach items="${listQA}" var="QA">
                        <!--Loop to get list account-->
                        <c:forEach items="${listAccount}" var="acc">
                            <form action="qaOfLesson" method="post">
                                <div class="parentComment">
                                    <div class="introduceParentComment">
                                        <!--Choose avatar of user-->
                                        <c:choose>
                                            <c:when test="${acc.getAvatar() == null}">
                                                <img onclick="window.location = 'profile?id=${QA.getUserID()}'" src="./image/f8_logo.png">
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${QA.getUserID() == acc.getId()}">
                                                    <img onclick="window.location = 'profile?id=${QA.getUserID()}'" src="${acc.getAvatar()}">
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:if test="${QA.getUserID() == acc.getId()}">
                                            <div style="margin: 10px 0 0 10px; display: flex">
                                                <h4 style="font-size: 14px" >${acc.getFirstAndLastName()}</h4>
                                                <span style="padding: 0 10px 0 10px; font-size: 14px">·</span>
                                                <p style="font-size: 12px; color: grey">${QA.getDate()}</p>
                                            </div>
                                        </div>
                                        <div class="parentContentComment">
                                            <p style="font-size: 14px">${QA.getContent()}</p>
                                            <p id="rep" onclick="myFunction(${idInputRep})" >Trả lời</p>
                                        </div>
                                        <!--Loop to get list rep Q&A of lesson-->
                                        <c:forEach items="${listRepQA}" var="rep">
                                            <!--Loop to get list Account rep-->
                                            <c:forEach items="${listAccount}" var="accRep">
                                                <!--Check the reply of each QA-->
                                                <c:if test="${rep.getParentID() == QA.getId() && rep.getUserID() == accRep.getId()}">
                                                    <div class="childComment">
                                                        <div class="introduceChildComment">
                                                            <!--Choose avatar of user-->
                                                            <c:choose>
                                                                <c:when test="${accRep.getAvatar() == null}">
                                                                    <img onclick="window.location = 'profile?id=${rep.getUserID()}'" src="./image/f8_logo.png">
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:if test="${rep.getUserID() == accRep.getId()}">
                                                                        <img onclick="window.location = 'profile?id=${rep.getUserID()}'" src="${accRep.getAvatar()}">
                                                                    </c:if>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <div style="margin: 10px 0 0 10px; display: flex">
                                                                <h4>${accRep.getFirstAndLastName()}</h4>
                                                                <span style="padding: 0 8px 0 8px">·</span>
                                                                <p style="color: grey">${rep.getDate()}</p>
                                                            </div>
                                                        </div>
                                                        <div class="childContentComment">
                                                            <p>${rep.getContent()}</p>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                        <div class="replyComment" id="${idInputRep}" style="display: none">
                                            <c:choose>
                                                <c:when test="${sessionScope.account.getAvatar() == null}">
                                                    <img class="avt" src ="./image/f8_logo.png"> 
                                                </c:when>
                                                <c:otherwise>
                                                    <img class="avt" src ="${sessionScope.account.getAvatar()}">
                                                </c:otherwise>
                                            </c:choose>
                                            <input type="hidden" name="lessonID" value="${lessonId}">
                                            <input type="hidden" name="lessonName" value="${lessonName}">
                                            <input type="hidden" name="parentID" value="${QA.getId()}">
                                            <textarea maxlength="200" class="comment" name="repContent" rows="5" cols="40" required="" placeholder="Phản hồi..."></textarea>
                                            <button type="submit" name="btnRep" style="height: 32px">Bình luận</button>
                                        </div>
                                    </c:if>
                                </div>
                            </form>
                        </c:forEach>
                        <c:set var="idInputRep" value="${idInputRep+1}"></c:set>
                    </c:forEach>

                </div>
                <div class="postComment">
                    <form action="qaOfLesson" method="post">
                        <c:choose>
                            <c:when test="${sessionScope.account.getAvatar() == null}">
                                <img class="avt" src ="./image/f8_logo.png"> 
                            </c:when>
                            <c:otherwise>
                                <img class="avt" src ="${sessionScope.account.getAvatar()}">
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="lessonID" value="${lessonId}">
                        <input type="hidden" name="lessonName" value="${lessonName}">
                        <textarea maxlength="200" class="comment" name="comment" rows="7" cols="60" required="" placeholder="Bạn có thắc mắc gì trong bài học này?"></textarea>
                        <button type="submit" name="btnComment">Bình luận</button>
                    </form>
                </div>
            </div>
            <c:if test="${listQA.size() != 0}">
                <div style="margin-top: 150px; margin-left: -200px"> <jsp:include page="footer.jsp"></jsp:include> </div>
            </c:if>
            <c:if test="${listQA.size() == 0}">
                <div style="margin-top: 150px;"> <jsp:include page="footer.jsp"></jsp:include> </div>
            </c:if>
        </div>
        <script>
            function myFunction(idInputRep) {
                var input = document.getElementById(idInputRep);
                if (input.style.display === "none") {
                    input.style.display = "block";
                } else {
                    input.style.display = "none";
                }
            }
        </script>
    </body>
</html>
