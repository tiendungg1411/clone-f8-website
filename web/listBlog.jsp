<%-- 
    Document   : listBlog
    Created on : 03/06/2023, 9:17:15 AM
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
        <link rel="stylesheet" type="text/css" href="./css/sidebar.css">
        <link rel="stylesheet" type="text/css" href="./css/listBlog.css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <!--Center-->
            <div class="container_wrap">
                <div style="position: absolute"><jsp:include page="sidebar.jsp"></jsp:include></div>
                <div class="container">
                    <div class="container_blog_wrap">
                        <h1>Bài viết nổi bật</h1>
                        <p>Tổng hợp các bài viết chia sẻ về kinh nghiệm tự học lập trình online và các kỹ thuật lập trình web.</p>
                    <c:set var="idPopup" value="1"></c:set>
                    <c:forEach items="${listBlog}" var="b">
                        <div class="container_blog">
                            <div class="container_blog_head">
                                <div class="container_blog_head_left">
                                    <img src="${b.userAvatar}" onclick="window.location = ''" alt="alt"/>
                                    <div onclick="window.location = ''">${b.userName}</div>
                                </div>
                                <div class="container_blog_head_right">
                                    <c:set var="flag" value="0"></c:set>
                                    <c:forEach items="${listSavedBlog}" var="i">
                                        <c:if test="${i.blogID == b.id}">
                                            <i onclick="window.location = 'savedBlog?id=${b.id}&savedBlogMode=1&page=${currentPage}'" class="a fa-solid fa-bookmark" style="color: #f05123;"></i>
                                            <c:set var="flag" value="1"></c:set>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${flag != 1}">
                                        <i onclick="window.location = 'savedBlog?id=${b.id}&savedBlogMode=2&page=${currentPage}'" class="a fa-regular fa-bookmark"></i>
                                    </c:if>
                                    <i onclick="showOptionPopup(${idPopup})" class="a fa-solid fa-ellipsis"></i>

                                </div>
                            </div>
                            <div class="container_blog_content">
                                <div class="container_blog_content_left">
                                    <h2 onclick="window.location = 'blogDetail?id=${b.id}'">${b.title}</h2>
                                    <div>${b.content}</div>
                                    <div class="container_blog_content_left_time">
                                        ${b.time}
                                        <span style="padding: 0 8px 0 8px">·</span>
                                        ${b.readMinute}
                                    </div>
                                </div>
                                <c:if test="${b.img != null}">
                                    <div class="container_blog_content_right">
                                        <img src="${b.img}" alt="alt"/>
                                    </div>
                                </c:if>        
                            </div>
                            <!--My option popup-->
                            <div id="${idPopup}" class="option_popup_wrap" style="display: none;">
                                <c:forEach items="${myListBlog}" var="mBlog">
                                    <c:if test="${mBlog.id == b.id}">
                                        <div onclick="window.location = 'editBlog?id=${b.id}'" class="option_popup_item">
                                            <i class="fa-solid fa-pen"></i>
                                            <div>Sửa bài viết</div>
                                        </div>
                                        <hr>
                                    </c:if>
                                </c:forEach>
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
                            <c:set var="idPopup" value="${idPopup+1}"></c:set>
                            </div>
                    </c:forEach>
                    <!--Pagination-->
                    <div class="paging_wrap">
                        <div class="paging_previous" style="display: <c:if test="${currentPage != 1}">none</c:if>; cursor: default">
                                <i class="fa-solid fa-angles-left" style="color: #d0d7de"></i>
                            </div>
                            <div onclick="window.location = 'listBlog?page=${currentPage-1}'" class="paging_previous hv" style="display: <c:if test="${currentPage == 1}">none</c:if>">
                                <i class="fa-solid fa-angles-left"></i>
                            </div>
                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <div onclick="window.location = 'listBlog?page=${i}'" class="paging_page hv <c:if test="${currentPage==i}">pagingActive</c:if>">${i}</div>
                        </c:forEach>
                        <div onclick="window.location = 'listBlog?page=${currentPage+1}'" class="paging_next hv" style="display: <c:if test="${currentPage == totalPage}">none</c:if>">
                                <i class="fa-solid fa-angles-right"></i>
                            </div>
                            <div class="paging_previous" style="display: <c:if test="${currentPage != totalPage}">none</c:if>">
                                <i class="fa-solid fa-angles-right" style="color: #d0d7de"></i>
                            </div>
                        </div>
                    </div>
                    <div class="container_ad">
                        <div class="container_ad_wrap">
                            <div class="container_ad_wrap_topictxt">CÁC CHỦ ĐỀ ĐƯỢC ĐỀ XUẤT</div>
                            <div class="container_ad_topic">
                                <div>Front-end / Mobile apps</div>
                                <div>Back-end / Devops</div>
                                <div>UI / UX / Design</div>
                                <div>Others</div>
                            </div>
                            <div class="container_ad_coursead">
                                <img src="https://files.fullstack.edu.vn/f8-prod/banners/26/63dc61f2a061e.png" alt="alt"/>
                                <img src="https://files.fullstack.edu.vn/f8-prod/banners/32/6421144f7b504.png" alt="alt"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>

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
        </script>
    </body>
</html>
