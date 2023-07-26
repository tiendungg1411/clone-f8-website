<%-- 
    Document   : BlogManagement
    Created on : Jun 22, 2023, 7:44:14 PM
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
        <link rel="stylesheet" href="css/commentManagement.css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
    </head>
    <body style="background-image: url('image/anhnen.png')">
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <div class="content_top">
                    <h1>Quản lý Blog</h1>
                    <div class="toolbar">
                    <c:choose>
                        <c:when test="${selected == 0}">
                            <select id="filter" onchange="myFunction(this)">
                                <option value="0" selected="">Tất cả trạng thái</option>
                                <option value="1">Hiển thị</option>
                                <option value="2">Đã ẩn</option>
                            </select>  
                        </c:when>
                        <c:otherwise>
                            <select id="filter" onchange="myFunction(this)">
                                <c:choose>
                                    <c:when test="${selected == 1}">
                                        <option value="0">Tất cả trạng thái</option>
                                        <option value="1" selected="">Hiển thị</option>
                                        <option value="2">Đã ẩn</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">Tất cả trạng thái</option>
                                        <option value="1">Hiển thị</option>
                                        <option value="2" selected="">Đã ẩn</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>  
                        </c:otherwise>
                    </c:choose>
                    <form action="blogManagementServlet" method="post">
                        <input type="hidden" name="state" value="${selected}"">
                        <input type="hidden" name="page" value="${currentPage}">
                        <input type="text" name="txtSearch" value="${txtSearch}" placeholder="Tìm kiếm theo tên người dùng...">
                        <button type="submit" name="btn_search" class="btn">Search</button>
                    </form>
                </div>
            </div>
            <div class="table-wrapper">
                <table class="fl-table">
                    <thead>
                        <tr>
                            <th>NUMBER</th>
                            <th>USER NAME</th>
                            <th>FULL NAME</th>
                            <th>BLOG</th>
                            <th>WRITE TIME</th>
                            <th>State</th>
                        </tr>
                    </thead>

                    <tbody> 
                        <c:choose>
                            <c:when test="${blogList.size() == 0}">
                            <p style="margin-top: 50px; font-size: 32px; color: whitesmoke; position: absolute; text-shadow:black 0.1em 0.1em 0.2em">Không tìm thấy blog nào</p>
                        </c:when>
                        <c:otherwise>
                            <c:set var="number" value="1"></c:set>
                            <c:forEach items="${blogList}" var="cList">
                                <tr>
                                    <td style="color: red">${number}</td>
                                    <td onclick="window.location = 'profile?id=${cList.getUserID()}'" style="cursor: pointer; text-decoration: underline">${cList.getUserName()}</td>
                                    <td>${cList.getFullName()}</td>
                                    <td onclick="window.location = 'blogDetail?id=${cList.getId()}'" style="cursor: pointer; color: red; text-decoration: underline">${cList.getTitle()}</td>
                                    <td>${cList.getDate()}</td>
                                    <c:choose>
                                        <c:when test="${cList.getStateId() == 1}">
                                            <td><button onclick="window.location = 'blogManagementServlet?commentID=${cList.getId()}&stateID=2&page=${currentPage}&mod=2'">Hide</button></td>
                                        </c:when>
                                        <c:otherwise> 
                                            <td><button onclick="window.location = 'blogManagementServlet?commentID=${cList.getId()}&stateID=1&page=${currentPage}&mod=1'">Show</button></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                <c:set var="number" value="${number + 1}"></c:set>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <tbody>
                </table>
            </div>
            <div>
                <!--Pagination-->
                <div class="paging_wrap">
                    <div class="paging_previous" style="display: <c:if test="${currentPage != 1}">none</c:if>; cursor: default">
                            <i class="fa-solid fa-angles-left" style="color: #d0d7de"></i>
                        </div>
                        <div onclick="window.location = 'blogManagementServlet?page=${currentPage-1}'" class="paging_previous hv" style="display: <c:if test="${currentPage == 1}">none</c:if>">
                            <i class="fa-solid fa-angles-left"></i>
                        </div>
                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <div onclick="window.location = 'blogManagementServlet?page=${i}'" class="paging_page hv <c:if test="${currentPage==i}">pagingActive</c:if>">${i}</div>
                    </c:forEach>
                    <div onclick="window.location = 'blogManagementServlet?page=${currentPage+1}'" class="paging_next hv" style="display: <c:if test="${currentPage == totalPage}">none</c:if>">
                            <i class="fa-solid fa-angles-right"></i>
                        </div>
                        <div class="paging_previous" style="display: <c:if test="${currentPage != totalPage}">none</c:if>">
                            <i class="fa-solid fa-angles-right" style="color: #d0d7de"></i>
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top: 14%;">  <jsp:include page="footer.jsp"></jsp:include> </div>
            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script>
                        // Display toast
                        function activeShowToast() {
                            toastr.success('${commentStatus}');
                        }

                        if ('${commentStatus }' === 'Hiển thị blog thành công' || '${commentStatus}' === 'Ẩn blog thành công') {
                            activeShowToast();
                        }

                        // Filter
                        function myFunction(object) {
                            var value = object.value;
                            window.location = 'blogManagementServlet?stateFilter=' + value + '&page=${currentPage}&mod=3';
                        }
        </script>
    </body>
</html>
