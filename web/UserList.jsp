<%-- 
    Document   : UserList
    Created on : May 19, 2023, 9:05:32 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
        <link rel="stylesheet" href="css/header.css"/>
        <link rel="stylesheet" href="css/footer.css"/>
        <link rel="stylesheet" href="css/user_list.css"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <style>
            /*Paging*/
            .paging_wrap {
                margin-top: -360px;
                padding: 10px;
                color: white;
            }
            .paging_wrap div{
                padding: 6px 12px;
                margin-right: 4px;
                font-size: 16px;
                font-weight: 600;
            }
            .paging_wrap .hv:hover {
                cursor: pointer;
                border: 1px solid #d0d7de;
                border-radius: 6px;
            }
            .paging_wrap {
                display: flex;
                justify-content: center;

            }
            .pagingActive {
                background-color: #f05123;
                color: white;
                border-radius: 6px;
                cursor: default;
            }
        </style>
    </head>
    <body style="background-image: url('image/anhnen.png')">
        <jsp:include page="header.jsp"></jsp:include>
            <h2 style="text-shadow:black 0.1em 0.1em 0.2em">DANH SÁCH NGƯỜI DÙNG</h2>
            <div class="toolbar">
            <c:choose>
                <c:when test="${actorId == 0}">
                    <select id="filter" onchange="myFunction(this)">
                        <option value="0" selected="">All</option>
                        <c:forEach items="${listActor}" var="item">
                            <option value="${item.getId()}">${item.getActor()}</option>
                        </c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                    <select id="filter" onchange="myFunction(this)">
                        <option value="0">All</option>
                        <c:forEach items="${listActor}" var="item">
                            <c:choose>
                                <c:when test="${item.getId() == actorId}">
                                    <option value="${item.getId()}" selected="">${item.getActor()}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.getId()}">${item.getActor()}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </c:otherwise>
            </c:choose>

            <form action="account" method="post">
                <input type="hidden" name="page" value="${currentPage}">
                <input type="hidden" name="actor" value="${actorId}">
                <input type="text" name="txtSearch" value="${txtSearch}" placeholder="Tìm kiếm theo tên người dùng...">
                <button type="submit">Search</button>
            </form>
        </div>
        <div class="table-wrapper">

            <table class="fl-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>USER NAME</th>
                        <th>FULL NAME</th>
                        <th>EMAIL</th>
                        <th>DATE JOIN</th>
                        <th>ROLE</th>
                        <th>BUTTON</th>
                    </tr>
                </thead>

                <tbody>      
                    <c:choose>
                        <c:when test="${listAccount.size() == 0}">
                        <p style="margin-top: 50px; font-size: 32px; color: whitesmoke; position: absolute; text-shadow:black 0.1em 0.1em 0.2em">Không tìm thấy người dùng nào</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${listAccount}" var="itemAccount">   
                            <form action="account">
                                <tr>
                                    <td id="${itemAccount.getId()}">${itemAccount.getId()}</td> 
                                <input type="text" name="uid" value="${itemAccount.getId()}" readonly="" style="display: none">
                                <input type="hidden" name="page" value="${currentPage}">
                                <td onclick="window.location='profile?id=${itemAccount.getId()}'" style="text-decoration: underline; cursor: pointer">${itemAccount.getUserName()}</td>
                                <td>${itemAccount.getFirstAndLastName()}</td>
                                <td>${itemAccount.getMail()}</td>
                                <td>${itemAccount.getRegistrationDate()}</td>
                                <td>
                                    <select name="role">
                                        <c:forEach items="${listActor}" var="itemActor"> 
                                            <c:choose>
                                                <c:when test="${itemAccount.getRoleID() == itemActor.getId()}">
                                                    <option selected value="${itemActor.getId()}">${itemActor.getActor()}</option>                       
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${itemActor.getId()}">${itemActor.getActor()}</option>                       
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td><button name="btn_update" type="submit" style="text-decoration: none;color: black; width: 40px; cursor: pointer">Save</button>  </td>
                                </tr>
                            </form>
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
                    <div onclick="window.location = 'account?page=${currentPage-1}'" class="paging_previous hv" style="display: <c:if test="${currentPage == 1}">none</c:if>">
                        <i class="fa-solid fa-angles-left"></i>
                    </div>
                <c:forEach begin="1" end="${totalPage}" var="i">
                    <div onclick="window.location = 'account?page=${i}'" class="paging_page hv <c:if test="${currentPage==i}">pagingActive</c:if>">${i}</div>
                </c:forEach>
                <div onclick="window.location = 'account?page=${currentPage+1}'" class="paging_next hv" style="display: <c:if test="${currentPage == totalPage}">none</c:if>">
                        <i class="fa-solid fa-angles-right"></i>
                    </div>
                    <div class="paging_previous" style="display: <c:if test="${currentPage != totalPage}">none</c:if>">
                        <i class="fa-solid fa-angles-right" style="color: #d0d7de"></i>
                    </div>
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script>
                    // Display toast
                    function activeSuccessToast() {
                        toastr.success('${updateStatus}');
                    }
                    if ('${updateStatus}' === 'Cập nhật vai trò của người dùng thành công')
                        activeSuccessToast();

                    function myFunction(object) {
                        var value = object.value;
                        window.location = 'account?actor=' + value + '&page=${currentPage}&mod=2';
                    }
        </script>
    </body>
</html>
