<%-- 
    Document   : createCourse
    Created on : 20/05/2023, 10:10:36 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="./css/createCourse.css">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <title>JSP Page</title>
        
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        
        <div style="display: flex">
            <div style="margin-left: 30px;margin-top: 30px">
                    <jsp:include page="sidebar.jsp"></jsp:include>
                    </div>
                    <div style="margin-left: 250px">
                        <div class="create_course">
                <div class="create_course_course">
                    <h2 style="color: white;">${error8}</h2>
                <h2 style="margin-top: -120px;font-size: 37px">Thêm khóa học</h2>
                <form id="myform" action="createCourse" method="post" enctype="multipart/form-data">
                    <div style="font-size:23px" >Tiêu đề: </div><input id="title" type="text" name="title"/><br>${error1}
                    <div style="font-size:23px">Giá: </div><input id="price" type="text" name="price"/><br>${error2}${error3}
                    <div style="font-size:23px">Loại lộ trình: </div>
                    <select name="routeType">
                        <option value="1">Front End</option>
                        <option value="2">Back End</option>
                    </select><br>${error4}
                    <div style="font-size:23px">Trình độ: </div>
                    <select name="level">
                        <option value="Easy">Dễ</option>
                        <option value="Medium">Bình thường</option>
                        <option value="Hard">Khó</option>
                        <option value="Super Hard">Siêu khó</option>
                    </select><br>${error5}
                    <div style="font-size:23px">Ảnh: </div><input id="image" type="file" name="image"/><br>${error7}
                    <div style="font-size:23px">Mô tả khóa học: </div><input id="detailCourseDes" type="text" name="detailCourseDes"/><br>${error6}
                    <div class="submit">
                        <input type="submit" value="Add"/>
                        <input type="button" value="Reset" onclick="reset()"/>
                    </div><br>
                </form>
            </div>
        </div> 
                    </div>
                  <a>
                    <img src="https://fullstack.edu.vn/static/media/fb-group-cards.4bd525b1b8baf7b1e5a2.png" style="width: 420px;height: 420px;margin-left: 200px;margin-top: 200px" >

                </a>
        </div>
        
           
        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            function reset() {
                document.getElementById("myform").reset();
            }
        </script>
    </body>
</html>
