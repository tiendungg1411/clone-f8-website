<%-- 
    Document   : lessonEdit
    Created on : Jun 18, 2023, 7:20:30 PM
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
        <link rel="stylesheet" href="css/lessonEdit.css"/>
        <link rel="stylesheet" href="css/course_list.css"/>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <link rel="stylesheet" href="path/to/unicons/css/unicons.css">
        <script src="path/to/unicons/js/unicons.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/unicons/css/unicons.css">
        <script src="${pageContext.request.contextPath}/resources/unicons/js/unicons.js"></script>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/unicons/css/unicons.css">
        <script src="<%= request.getContextPath() %>/resources/unicons/js/unicons.js"></script>

        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <!--link bootstrap-->
        <link href="./CommonLib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <style>
            .toast-success {
                background-color: green;
            }
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
               

            }
            .content_dropdown_lesson select {
                background-color: #4CAF50;
                border: none;
                border-radius: 6px;
                width: 210px;
                height: 40px;
                color: white;
                font-size: 17px;
                margin-left:  -65px;
                
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
    <body >
        <jsp:include page="header.jsp"></jsp:include>
            <form id="f" action="editLessonServlet" method="get">
                <div style="display: flex">
                    <div style="margin-top: 30px">
                    <jsp:include page="sidebar.jsp"></jsp:include>
                    </div>
                    <input name="courseId" value="${courseId}" hidden> 
                    <div style="margin-left: 120px;">
                        <h2 style="color:black;font-size: 30px;margin-right: 130px;    margin-bottom: -60px;">${listC.get(0).title}</h2>
                    <h2 style="color:black;font-size: 30px;margin-right: 130px">DANH SÁCH LESSON</h2>
                    <div class="add_btn">
                                <div style="display: flex; justify-content: end; ; margin-bottom: 10px">
                                    <div class="filter_instruction">FilterByCourse: </div>
                                    <div class="content_dropdown_lesson">
                                        <select onchange="document.getElementById('f').submit()" id="mySelect2" name="chapterId" required>
                                            <option selected value="default">Chọn Chương</option>
                                            <c:forEach items="${listChapter}" var="c">
                                                <option <c:if test="${c.id == chapterId}">selected</c:if> value="${c.id}">${c.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                    <div class="table-responsive shadow rounded m-5"  >
                         
                        <table id="table" class="  table table-center bg-white mb-0" style="font-size : 18px;">

                            <thead>
                                <tr style="background-color:#396cf0; color: #ffffff">
                                    <th class="border-bottom p-3" style="width: 50px" >ID</th>
                                    <th class="border-bottom p-3" style="width: 300px" >Tên Lesson</th>
                                    <th class="border-bottom p-3" style="width: 150px">Type</th>
                                    <th class="border-bottom p-3" style="width: 450px" >Link</th>
                                    <th class="border-bottom p-3" style="width: 100px" >Action</th>
                                </tr>
                            </thead>
                           
                            <tbody>
                                <c:forEach items="${listLesson}" var="l">
                                    <tr>
                                        <td class="p-3">${l.id}</td>
                                        <td class="p-3">${l.name}</td>
                                        <td class="p-3">${l.type}</td>
                                        <td class="p-3">${l.link}</td>

                                        <td style="display: flex
                                            "><button name="btn_edit" onclick="window.location = 'editLessonDetail?courseId=${courseId}&id=${l.id}'" type="button" style="text-decoration: none;color: black; width: 40px; cursor: pointer;font-size: 14px">Edit</button> 
                                            <div name="btn_delete" onclick="deleteQuiz(${courseId}, ${l.id})"   class="btn_delete" style="text-align: center;justify-content: center;margin-left: 10px; width: 50px; cursor: pointer;height: 25px;border:1px #807171 solid;border-radius: 2px;background-color: #F0F0F0 ;font-size: 14px">Xóa</div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        
                    </div><c:if test="${listLesson.isEmpty()==true}"><h2>Don't have any lesson</h2> </c:if>
                </div>
            </div>

        </form>





        <div class="footer">
            <jsp:include page="footer.jsp"></jsp:include>
            </div>
            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script>
                                                function activeSuccessToast() {
                                                    toastr.success('${editBlogStatus}', '', {
                                                        "toastClass": "toast-success"
                                                    });
                                                }
                                                function activeSuccessToast1() {
                                                    toastr.success('${editBlogStatus1}', '', {
                                                        "toastClass": "toast-success"
                                                    });
                                                }


                                                if ('${editBlogStatus}' === 'Edit Lesson successfully!') {
                                                    activeSuccessToast();
                                                }
                                                if ('${editBlogStatus1}' === 'Delete Lesson successfully!') {
                                                    activeSuccessToast1();
                                                }

                                                function deleteQuiz(cid, qid) {
                                                    if (confirm('Bạn có muốn xóa Lesson này không?')) {
                                                        window.location = 'editLessonServlet?mode=delete&courseId='+cid+'&id=' + qid;
                                                    }
                                                }

                                                function showExtraFields(n) {
                                                    var type = document.getElementById("type_" + n);
                                                    var urlInput = document.getElementById("url-input_" + n);

                                                    if (type.value === "video" || type.value === "feedback") {
                                                        urlInput.style.display = "block";
                                                    } else {
                                                        urlInput.style.display = "none";
                                                    }
                                                }
                                                function saveUrl(c, l) {
                                                    var url = document.getElementById("lessonURL_" + l).value;
                                                    window.location = "editLessonServlet?courseId=" + c + "&lessonId=" + l + "&url=" + url + "&mod=1";
                                                }
                                                function saveType(c, l) {
                                                    var type = document.getElementById("type_" + l).value;
                                                    window.location = "editLessonServlet?courseId=" + c + "&lessonId=" + l + "&type=" + type + "&mod=2";
                                                }

        </script>
    </body>
</html>
