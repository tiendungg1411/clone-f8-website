<%-- 
    Document   : MyOrder
    Created on : Jun 17, 2023, 2:05:33 PM
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
        <link rel="stylesheet" href="css/optionSetting.css"/>
        <link rel="stylesheet" href="css/footer.css"/>
        <link rel="stylesheet" href="css/myorder.css"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <style>
            .message_popup_show{
                background-color: white;
                position: absolute;
                width: 25%;
                height: 25%;
                text-align: center;
                border-radius: 10px;
                margin-left: 14%;
                border: 1px solid;
                padding: 10px;
                box-shadow: 5px 10px orange;
            }

            .btn_yes{
                background-color: orange;
                border: 1px solid rgba(0,0,0,.15);
                border-radius: 30px;
                color: white;
                cursor: pointer;
                font-size: 1.5rem;
                text-decoration: none;
                padding: 20px 20px;
                transition: background-color .1s,border-color .1s,color .1s,fill .1s;
                opacity: 1;
                position: absolute;
                margin-top: 40px;
                margin-left: -120px;
            }

            .btn_no{
                background-color: orange;
                border: 1px solid rgba(0,0,0,.15);
                border-radius: 30px;
                color: white;
                cursor: pointer;
                font-size: 1.5rem;
                padding: 20px 32px;
                transition: background-color .1s,border-color .1s,color .1s,fill .1s;
                margin-top: 40px;
                position: absolute;
                opacity: 1;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="content" style="display: flex">
                <!--Content left-->
                <div style="margin-top: 40px">
                <jsp:include page="OptionSetting.jsp"></jsp:include>
                </div>
                <!--Content right-->
                <div class="content_right" >

                    <h1>Các đơn hàng của tôi</h1>
                    <div class="toolbar" style="display: flex">
                    <c:choose>
                        <c:when test="${selected == 0}">
                            <select id="filter" onchange="myFunction(this)">
                                <option value="0" selected="">Tất cả trạng thái</option>
                                <c:forEach items="${stateList}" var="state">
                                    <option value="${state.getId()}">${state.getState()}</option>
                                </c:forEach>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select id="filter" onchange="myFunction(this)">
                                <option value="0">Tất cả trạng thái</option>
                                <c:forEach items="${stateList}" var="state">
                                    <c:choose>
                                        <c:when test="${selected == state.getId()}">
                                            <option value="${state.getId()}" selected="">${state.getState()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${state.getId()}">${state.getState()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </c:otherwise>
                    </c:choose>
                    <form action="myorder" method="post">
                        <input type="text" name="state" value="${selected}" style="display: none">
                        <input type="text" name="txtSearch" value="${txtSearch}" placeholder="Tìm kiếm theo tên khóa học...">
                        <button type="submit" name="btn_search" class="btn">Search</button>
                    </form>


                    <div class="table-wrapper">
                        <table class="fl-table">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Khóa học </th>
                                    <th>Ngày đăng ký </th>
                                    <th>Giá niêm yết </th>
                                    <th>Giá bán </th>
                                    <th>Trạng thái </th>
                                    <th>Hủy</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${listCourse.size() == 0}">
                                    <p style="margin-top: 50px; font-size: 24px; color: red; position: absolute">Không tìm thấy đơn hàng nào</p>
                                </c:when>
                                <c:otherwise>

                                    <c:set var="idOrder" value="1"></c:set>
                                    <c:forEach items="${listCourse}" var="item">
                                        <tr>
                                            <td>${idOrder}</td>
                                            <td>${item.getTitle()}</td>
                                            <td>${item.getTimeRegistration()}</td>
                                            <td>${item.getPrice()}</td>
                                            <td>${item.getPrice()}</td>
                                            <c:forEach items="${stateList}" var="state">
                                                <c:if test="${item.getStateId() == state.getId()}">
                                                    <td>${state.getState()}</td>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${item.getStateId() != 3}">
                                                <td><button onclick="showPopup(${idOrder})">Hủy</button></td>
                                            <div class="message_popup_show" id="${idOrder}" style="display: none">
                                                <p style="font-size: 18px; margin-top: 20px; font-style: italic ">Bạn có chắc chắn muốn hủy đơn hàng này không?</p>
                                                <a class="btn_yes" onclick="window.location='myorder?id=${item.getId()}&stateId=3&mod=1'"> <i class='bx bx-check'> Đồng ý</i></a>
                                                <a class="btn_no" onclick="window.location='myorder'"> <i class='bx bx-x'></i> Hủy </a>
                                            </div>
                                        </c:if>
                                        </tr>
                                        <c:set var="idOrder" value="${idOrder+1}"></c:set>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                            </tbody>
                        </table>
                    </div>
                    <div class="ads">
                        <jsp:include page="Slider.jsp"></jsp:include>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>
            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script>
//                                                    Display toast
                                                    function activeSuccessToast() {
                                                        toastr.success('${unSubcribesStatus}');
                                                    }
                                                    if ('${unSubcribesStatus}' === 'Hủy đăng ký thành công')
                                                        activeSuccessToast();

                                                    function myFunction(object) {
                                                        var value = object.value;
                                                        window.location = 'myorder?stateFilter=' + value + '&mod=2';
                                                    }
                                                    function showPopup(idOrder) {
                                                        var option = document.getElementById(idOrder);
                                                        if (option.style.display === "none") {
                                                            option.style.display = "block";
                                                        } else {
                                                            option.style.display = "none";
                                                        }
                                                    }
        </script>
    </body>
</html>
