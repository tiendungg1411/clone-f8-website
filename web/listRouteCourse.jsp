<%-- 
    Document   : CourseList
    Created on : Jun 10, 2023, 11:55:29 AM
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
        <link rel="stylesheet" href="css/course_list.css"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="path/to/unicons/css/unicons.css">
        <script src="path/to/unicons/js/unicons.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/unicons/css/unicons.css">
        <script src="${pageContext.request.contextPath}/resources/unicons/js/unicons.js"></script>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/unicons/css/unicons.css">
        <script src="<%= request.getContextPath() %>/resources/unicons/js/unicons.js"></script>

        <style>
            .table1, th, td {
                border: 1px solid #dee2e6;
                vertical-align: top;
                text-align: left;
                padding: 8px;
                border-collapse: collapse;
            }

            .mb-0 tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            .mb-0 tr:hover {
                background-color: #ddd;
            }

            thead tr th {
                text-align: center;
            }

            .add_btn {

                width: 183px;
                height: 33px;
                background: #4CAF50;
                font-size: 17px;
                margin-bottom: 10px;
                border-radius: 4px;
                border: none;
                cursor: pointer;
                font-size: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <form action="listRouteCourse" method="post" id="form">
                <div style="display: flex">
                    <div style="margin-left: 10px;margin-top: 30px">
                    <jsp:include page="sidebar.jsp"></jsp:include>
                    </div>
                    <div>
                        <h2 style="color:black;font-size: 30px">DANH SÁCH Khóa Học</h2>
                        <div class="table-responsive shadow rounded m-5">
                            <table id="table" class="  table table-center bg-white mb-0" style="font-size : 18px">
                                <p class="add_btn" style="display: flex; justify-content: center; align-items: center;">
                                    <a onclick="window.location = 'createRouteCourse'" style="color:white;text-decoration:none">Tạo lộ trình học</a>
                                </p>

                                <thead>
                                    <tr style="background-color:#396cf0; color: #ffffff">
                                        <th class="border-bottom p-3" style="min-width: 120px;">ID</th>
                                        <th class="border-bottom p-3" style="min-width: 120px;">Tên lộ Trình</th>
                                        <th class="border-bottom p-3" style="min-width: 120px;">Ảnh</th>
                                        <th class="border-bottom p-3" style="min-width: ">Giới thiệu về lộ trình</th>
                                        <th class="border-bottom p-3" style="min-width: 120px;">Ẩn / hiện</th>
                                        <th class="border-bottom p-3" style="min-width: 170px;">Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listRouteType}" var="i">  
                                    <tr>
                                        <td class="p-3">${i.id}</td>
                                        <td class="p-3">${i.name}</td>
                                        <td class="p-3">${i.image}</td>
                                        <td style="line-height: 1.4" class="p-3">${i.description1}</td>
                                        <c:choose>
                                            <c:when test="${i.status != 0}">
                                                <td class="p-3"><button onclick="window.location = 'listRouteCourse?routeTypeID=${i.id}&mod=1'" type="button">Show</button></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="p-3"><button onclick="window.location = 'listRouteCourse?routeTypeID=${i.id}&mod=2'" type="button">Hide</button></td>
                                            </c:otherwise>
                                        </c:choose>

                                        <td style="display: flex
                                            "><button name="btn_edit" onclick="window.location = 'editRouteType?id=${i.id}'" value="${i.id}" type="button" style="text-decoration: none;color: black; width: 70px; cursor: pointer;font-size: 14px">Chỉnh sửa</button> 
                                            <div name="btn_delete" onclick="deleteRouteCourse(${i.id})" value="${i.id}"    class="btn_delete" style="text-align: center;justify-content: center;margin-left: 10px; width: 50px; cursor: pointer;height: 25px;border:1px #807171 solid;border-radius: 2px;background-color: #F0F0F0 ;font-size: 14px">Xóa</div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>


            </div>
        </form>


        <div class="footer">
            <jsp:include page="footer.jsp"></jsp:include>
            </div>
            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script>
                                                    function deleteRouteCourse(id) {
                                                        if (confirm("Bạn có đồng ý xóa không ?")) {
                                                            window.location = "listRouteCourse?id=" + id;
                                                        }
                                                    }

                                                    function  createRouteType() {
                                                        window.location = 'CreateRouteCourse';
                                                    }
                                                    function activeErrorToast() {
                                                        toastr.error('${savedBlogStatus}');
                                                    }
                                                    function activeSuccessToast(abc) {
                                                        toastr.success(abc);
                                                    }
                                                    if ('${editBlogStatus}' !== '')
                                                        activeSuccessToast('${editBlogStatus}');
                                                    if ('${createRouteTypeStatus}' !== '')
                                                        activeSuccessToast('${createRouteTypeStatus}');
                                                    if ('${createRouteCourseItemStatus}' !== '')
                                                        activeSuccessToast('${createRouteCourseItemStatus}');
                                                    if ('${changePasswordStatus}' !== '')
                                                        activeSuccessToast('${changePasswordStatus}');
                                                    if ('${ResetPasswordStatus}' !== '')
                                                        activeSuccessToast('${ResetPasswordStatus}');

        </script>        
    </body>
</html>
