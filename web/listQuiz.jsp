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
                display: flex;
                justify-content: space-between;
                margin-right: 1150px;
              
            }
            .content_dropdown_lesson select {
                background-color: #4CAF50;
                border: none;
                border-radius: 6px;
                width: 210px;
                height: 40px;
                margin-left: 20px;
                color: white;
                 font-size: 17px;
                 margin-left: 60px;
            }
            .filter_instruction {
                font-size: 20px;
                color: white;
                margin-top: 14px;
            }
            .add_btn input {
                width: 60px;
                height: 40px;
                background: #4CAF50;
                color: white;
                margin-bottom: 10px;
                border-radius: 8px;
                border: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <form id="f" action="listQuiz" method="get">
                <div style="display: flex">
                    <div style="margin-left: 30px;margin-top: 30px">
                    <jsp:include page="sidebar.jsp"></jsp:include>
                    </div>
                    <div>
                         <h2 style="color:black;font-size: 30px;margin-right: 130px">DANH SÁCH QUIZ</h2>
                <div class="table-responsive shadow rounded m-5">
                    <table id="table" class="  table table-center bg-white mb-0" style="font-size : 18px;margin-left: 200px">
                       
                        <thead>
                            <tr style="background-color:#396cf0; color: #ffffff">
                                <th class="border-bottom p-3" style="min-width: 120px;">ID</th>
                                <th class="border-bottom p-3" style="min-width: 120px;">Tên Quiz</th>
                                <th class="border-bottom p-3" style="min-width: 120px;">Tên Chapter</th>
                                <th class="border-bottom p-3" style="min-width: ">Tên khóa học</th>
                                <th class="border-bottom p-3" style="min-width: 120px;">Action</th>
                            </tr>
                        </thead>
                          <div class="add_btn">
                            <div style="display: flex; justify-content: end; width: 100%; margin-bottom: 10px">
                                <div class="filter_instruction">FilterByCourse: </div>
                                <div class="content_dropdown_lesson">
                                    <select onchange="document.getElementById('f').submit()" id="mySelect2" name="course" required>
                                        <option selected value="default">Chọn khóa học</option>
                                        <c:forEach items="${listC}" var="c">
                                            <option <c:if test="${c.id == courseID}">selected</c:if> value="${c.id}">${c.title}</option>
                                        </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                        <tbody>
                       <c:forEach items="${listL}" var="l">
                            <tr>
                                <td class="p-3">${l.quizID}</td>
                                <td class="p-3">${l.quizName}</td>
                                <td class="p-3">${l.chapterName}</td>
                                <td class="p-3">${l.courseName}</td>

                                <td style="display: flex
                                    "><button name="btn_edit" onclick="window.location = 'editQuiz?id=${l.quizID}'" type="button" style="text-decoration: none;color: black; width: 40px; cursor: pointer;font-size: 14px">Edit</button> 
                                    <div name="btn_delete" onclick="deleteQuiz(${l.quizID})"   class="btn_delete" style="text-align: center;justify-content: center;margin-left: 10px; width: 50px; cursor: pointer;height: 25px;border:1px #807171 solid;border-radius: 2px;background-color: #F0F0F0 ;font-size: 14px">Xóa</div>
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
                                        
            function deleteQuiz(qid) {
                if(confirm('Bạn có muốn xóa quiz này không?')){
                    window.location = 'listQuiz?mode=delete&id='+qid;
                }
            }
            function activeErrorToast() {
                    toastr.error('${savedBlogStatus}');
            }
            function activeSuccessToast(abc) {
                toastr.success(abc);
            }
            if ('${editBlogStatus}' !== '')
                activeSuccessToast('${editBlogStatus}');
        </script>        
    </body>
</html>
