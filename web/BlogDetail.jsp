<%-- 
    Document   : BlogDetail
    Created on : Jun 7, 2023, 7:14:11 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${blog.getTitle()}</title>
        <link rel="stylesheet" href="css/header.css"/>
        <link rel="stylesheet" href="css/sidebar.css"/>
        <link rel="stylesheet" href="css/footer.css"/>
        <link rel="stylesheet" href="css/blogdetail.css"/>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <!--Include header-->
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <div class="content_left">
                    <h4 onclick="window.location = 'profile?id=${blog.getUserID()}'"style="cursor: pointer">${blog.getUserName()}</h4> <br>
                <hr>
                <c:set var="flag" value="0"></c:set>
                <c:forEach items="${listLikedBlog}" var="i">
                    <c:if test="${i.blogID == blog.id}">
                        <i onclick="window.location = 'blogDetail?id=${blog.id}&likeBlogMode=1&mod=2'" class='bx bxs-heart' style="color: red; cursor: pointer"></i>
                        <c:set var="flag" value="1"></c:set>
                    </c:if>
                </c:forEach>
                <c:if test="${flag != 1}">
                    <i onclick="window.location = 'blogDetail?id=${blog.id}&likeBlogMode=2&mod=2'" class='bx bx-heart' style="cursor: pointer"" ></i>
                </c:if>
                <i style="margin-left: 20px; cursor: pointer" onclick="showComment()"class='bx bxs-message-rounded'></i> <br>

                <div style="display: flex"><h6> ${blog.getNumOfLikes()} </h6>   <h6 style="margin-left: 40px"> ${numOfComment}</h6> </div>
            </div>
            <div class="content_right">
                <h3>${blog.getTitle()}</h3>
                <div class="user">
                    <div class="avt_user" style="display: flex">
                        <c:choose>
                            <c:when test="${blog.getUserAvatar() == null}">
                                <div>
                                    <img onclick="window.location = 'profile?id=${blog.getUserID()}'" style="cursor: pointer"class="avt" src ="./image/f8_logo.png"> <br>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div>
                                    <img onclick="window.location = 'profile?id=${blog.getUserID()}'" style="cursor: pointer"class="avt" src ="${blog.getUserAvatar()}"> <br>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div> 
                            <h4 onclick="window.location = 'profile?id=${blog.getUserID()}'" style="cursor: pointer">${blog.getUserName()}</h4> <br>
                            <div class="time" style="display: flex">
                                <p class="edit">${blog.getTime()}</p> 
                                <span style="padding: 0 8px 0 8px">·</span>
                                <p class="read">${blog.getReadMinute()}</p>
                            </div>
                        </div>
                    </div>
                    <div class="option">
                        <c:set var="flag" value="0"></c:set>
                        <c:forEach items="${listSavedBlog}" var="i">
                            <c:if test="${i.blogID == blog.id}">
                                <i onclick="window.location = 'blogDetail?id=${blog.getId()}&savedBlogMode=1&mod=1'" class="a fa-solid fa-bookmark" style="color: #f05123; cursor: pointer"></i>
                                <c:set var="flag" value="1"></c:set>
                            </c:if>
                        </c:forEach>
                        <c:if test="${flag != 1}">
                            <i onclick="window.location = 'blogDetail?id=${blog.getId()}&savedBlogMode=2&mod=1'" class="a fa-regular fa-bookmark" style="cursor: pointer"></i>
                        </c:if>
                        <c:set var="idPopup" value="1"></c:set>
                        <i style="cursor: pointer" onclick="showOptionPopup(${idPopup})" class="a fa-solid fa-ellipsis"></i>
                    </div>
                    <div id="${idPopup}" class="option_popup_wrap" style="display: none;">
                        <c:if test="${blog.getUserID() == sessionScope.account.getId()}">
                            <div onclick="window.location = 'editBlog?id=${blog.id}'" class="option_popup_item">
                                <i class="fa-solid fa-pen"></i>
                                <div>Sửa bài viết</div>
                            </div>
                            <hr>
                        </c:if>
                        <div class="option_popup_item">
                            <i class="fa-brands fa-facebook"></i>
                            <div>Chia sẻ lên Facebook</div>
                        </div>
                        <div class="option_popup_item">
                            <i class="fa-brands fa-twitter"></i>
                            <div>Chia sẻ lên Twitter</div>
                        </div>
                        <div class="option_popup_item">
                            <i class="fa-solid fa-envelope"></i>
                            <div>Chia sẻ tới Email</div>
                        </div>
                        <div class="option_popup_item">
                            <i class="fa-solid fa-link"></i>
                            <div>Sao chép liên kết</div>
                        </div>
                        <c:set var="checkReportArticle" value="1"></c:set>
                        <c:forEach items="${myListBlog}" var="mBlog">
                            <c:if test="${mBlog.id == b.id}">
                                <c:set var="checkReportArticle" value="2"></c:set>
                            </c:if>
                        </c:forEach>
                        <c:if test="${checkReportArticle == 1}">
                            <div class="option_popup_item">
                                <i class="fa-sharp fa-solid fa-flag"></i>
                                <div>Báo cáo bài viết</div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="main">
                    <p>${blog.getContent()}</p> 
                </div>
                <hr style="margin-top: 30px;">

                <div id="comment">
                    <div class="list_com">
                        <c:set var="idDelete" value="2"></c:set>
                            <!--Loop to get list comment of blog-->
                        <c:forEach items="${listComment}" var="comment">
                            <!--Loop to get list account-->
                            <c:forEach items="${listAccount}" var="acc">
                                <!--Avatar user-->
                                <c:choose>
                                    <c:when test="${acc.getAvatar() == null}">
                                        <div>
                                            <img onclick="window.location = 'profile?id=${acc.getId()}'" style="cursor: pointer"class="avt_com" src ="./image/f8_logo.png"> <br>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${acc.getId() == comment.getUserID()}">
                                            <div>
                                                <img onclick="window.location = 'profile?id=${acc.getId()}'" style="cursor: pointer"class="avt_com" src ="${acc.getAvatar()}"> <br>
                                            </div>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${acc.getId() == comment.getUserID()}">
                                    <div class="content_com">
                                        <h4 onclick="window.location = 'profile?id=${acc.getId()}'" style="cursor: pointer">${acc.getFirstAndLastName()}</h4>
                                        <span style="padding: 0 8px 0 8px">·</span>
                                        <p style="color: grey">${comment.getTime()}</p>
                                        <div style="position: absolute; margin-left: 200px">
                                            <i onclick="showOptionDelete(${idDelete})" class="a fa-solid fa-ellipsis" style="color: grey; cursor: pointer; "></i>
                                        </div>
                                        <div id="${idDelete}" class="popup">
                                            <div class="popup_item" onclick="window.location = 'blogDetail?id=${comment.getId()}&mod=3'">Xóa ${comment.getId()}</div>
                                        </div>
                                    </div>
                                    <p class="content" style="word-wrap:break-word;width: 650px">${comment.getContent()}</p>
                                </c:if>
                            </c:forEach>
                            <c:set var="idDelete" value="${idDelete+1}"></c:set>
                        </c:forEach>
                    </div>

                    <form action="blogDetail" method="post">
                        <c:choose>
                            <c:when test="${sessionScope.account.getAvatar() == null}">
                                <div>
                                    <img class="avt" src ="./image/f8_logo.png"> 
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div>
                                    <img class="avt" src ="${sessionScope.account.getAvatar()}">
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <input type="text" name="id" value="${blog.id}" style="display: none">
                        <textarea maxlength="200" class="comment" name="comment" rows="7" cols="60" required="" placeholder="Thêm bình luận..."></textarea>
                        <button type="submit" name="btnComment">Bình luận</button>
                    </form>
                </div>
            </div>
        </div>
        <!--Include sidebar-->
        <jsp:include page="sidebar.jsp"></jsp:include>
        <div style="margin-top: 50%">   <jsp:include page="footer.jsp"></jsp:include> </div>

            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script>
                                                function activeErrorToast() {
                                                    toastr.error('${savedBlogStatus}');
                                                }
                                                function activeSuccessToast() {
                                                    toastr.success('${savedBlogStatus}');
                                                }
                                                if ('${savedBlogStatus}' === 'Thêm vào mục đã lưu')
                                                    activeSuccessToast();
                                                if ('${savedBlogStatus}' === 'Xóa khỏi mục đã lưu')
                                                    activeErrorToast();
                                                function showOptionPopup(idPopup) {
                                                    var popOption = document.getElementById(idPopup);
                                                    if (popOption.style.display === "none") {
                                                        popOption.style.display = "block";
                                                    } else {
                                                        popOption.style.display = "none";
                                                    }
                                                }

//                                                function showOptionDelete(idDelete) {
//                                                    var popDelete = document.getElementById(idDelete);
//                                                    if (popDelete.style.display === "none") {
//                                                        popDelete.style.display = "block";
//                                                    } else {
//                                                        popDelete.style.display = "none";
//                                                    }
//
//                                                }

                                                function showComment() {
                                                    var popComment = document.querySelector("#comment");
                                                    if (popComment.style.display === "none") {
                                                        popComment.style.display = "block";
                                                    } else {
                                                        popComment.style.display = "none";
                                                    }
                                                }



//                                Toast cua dai
                                                function activeSuccessToastDAI(abc) {
                                                    toastr.success(abc);
                                                }
                                                if ('${newBlogStatus}' !== '') {
                                                    activeSuccessToastDAI('${newBlogStatus}');
                                                }
        </script>
    </body>
</html>
