<%-- 
    Document   : blogSaved
    Created on : Jun 7, 2023, 1:09:59 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" type="text/css" href="./css/sidebar.css">
        <link rel="stylesheet" type="text/css" href="./css/listBlog.css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container_wrap">
                <div style="position: absolute"><jsp:include page="sidebar.jsp"></jsp:include></div>
                <div class="container">
                    <div class="container_blog_wrap">
                        <h1 style="font-weight: bold">Bài viết đã lưu </h1>
                        <br><br><br><br>
                        <div class="num_saved_blog" style="">
                            <h2>Bài viết (${size})</h2>
                    </div>                       
                    <br>

                    <c:set var="idPopup" value="1"></c:set>
                    <c:forEach items="${listBlog}" var="b">
                        <div class="container_blog">
                            <div class="container_blog_head_s">                                
                                <div class="container_blog_head_right_s" style="font-size: 16px;padding: 8px;cursor: pointer;margin-left:730px;margin-top: -20px;">
                                    <i onclick="showOptionPopup(${idPopup})" class="a fa-solid fa-ellipsis" style="position: relative;"></i>
                                </div>
                            </div>
                            <div class="container_blog_content">
                                <div class="container_blog_content_left">
                                    <h2 onclick="window.location = 'blogDetail?id=${b.id}'">${b.title}</h2>
                                    <div class="container_blog_content_left_time">
                                        <span style="color: #029e74">Ðã lưu ${b.time}</span>
                                        <span style="padding: 0 8px 0 8px">·</span>
                                        Tác giả: ${b.userName}
                                    </div>
                                </div>
                                <c:if test="${b.img != null}">
                                    <div class="container_blog_content_right">
                                        <img src="${b.img}" alt="alt"/>
                                    </div>
                                </c:if>        
                            </div>
                            <!--My option popup-->
                            <div id="${idPopup}" class="option_popup_wrap" style="display: none;position: absolute;top: 36px; right: 20px;">
                                <div class="option_popup_item">
                                    <div onclick="window.location = 'blogSaved?id=${b.id}&delete=1'">Xóa khỏi mục đã lưu</div>
                                </div>                          
                            </div>
                            <c:set var="idPopup" value="${idPopup+1}"></c:set>
                            </div>
                    </c:forEach>
                    <!--Pagination-->                    
                </div>
                <div class="container_ad">
                    <div class="container_ad_wrap">
                        <div class="container_ad_coursead">
                            <img src="https://files.fullstack.edu.vn/f8-prod/banners/26/63dc61f2a061e.png" alt="alt"/>
                            <img src="https://files.fullstack.edu.vn/f8-prod/banners/32/6421144f7b504.png" alt="alt"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <jsp:include page="footer.jsp"></jsp:include>
            </div>
            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script>
                                        function activeErrorToast() {
                                            toastr.error('${savedBlogStatus}');
                                        }
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
        </script>

    </body>
</html>
