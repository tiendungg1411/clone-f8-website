<%-- 
    Document   : createRouteCourseItem
    Created on : Jun 5, 2023, 11:11:10 PM
    Author     : TIEN DAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/optionSetting.css"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" type="text/css" href="css/header.css">
        <link rel="stylesheet" type="text/css" href="css/footer.css">
        <link rel="stylesheet" href="path/to/bootstrap.min.css">
        <style>
/*            .all{
                border: 1px solid black;
                width: 797px;
                height: 850px;
                background-color: #fdcfc9 ;
                border-radius: 2%;
                margin-top: 60px
            }*/
            .avatar{
                border-radius: 10%;
                width: 45px;
                height: 45px;
                cursor: pointer ;
                margin-left: 357px;
                margin-top: 35px;
            }
            .container {
                display: grid;
                grid-template-columns: 1fr 1fr 1fr; /* Chia thành 3 cột bằng nhau */
                grid-gap: 23px; /* Khoảng cách giữa các ô */
                margin-left: 15px;
                text-align: center;
                padding: 63px;
            }

            .item {
/*                width: 33.33%;*/
                height: 137px;
                background-color: #eee;
/*                border: 2px solid none;*/
                padding: 17px;
                /*                transition: transform 0.2s ease-in-out;*/
                cursor: pointer;
                text-align: center;
                font-size: 14px;
                width: 179px;
                border-radius: 10px;
            }
            .routeTypeName{
                margin-left: 203px;
                height: 27px;
                width: 190px;
                font-size: 15px;
            }
            .nhap{
                margin-left:40px ;
                font-size: 24px;
                font-weight:600;
            }
            .error-msg{
                margin-left: 40px;
                font-size: 19px;
                color: red;
                font-weight: 700;
            }
            .submit{
                margin-left: 347px;
                width: 100px;
                height: 32px;
/*                margin-top: 15px;*/
                cursor: pointer;
                font-size: 19px;
            }
            .h1{
                text-align: center;
                font-size: 35px;
                margin-top: 15px;
            }

        </style>
    </head>
    <body >
          <jsp:include page="header.jsp"></jsp:include> 
        <form action="createRouteCourseItem" method="post">
            <div style="display: flex ;">
                    <div style="margin-left: 30px;margin-top: 30px">
                    <jsp:include page="sidebar.jsp"></jsp:include>
                    </div>
                    <div style="margin-left: 300px">
                        <div style="display: flex ;
                 justify-content: center;align-items: center;margin-top: 30px
               
                ">
                <div class="all" style=" color: #053f1b">
                    <div>
<!--                        <a href="/g4/home" >
                            <img src="https://accounts.fullstack.edu.vn/assets/icon/f8_icon.png" alt="Avatar"  class="avatar" >
                        </a>-->
                        <h1 class="h1"> 
                            Thêm mục lục lộ trình học</h1>  
                    </div>  
                    <div class="nhap" style="margin-top: 35px;display: flex">Lộ trình học   
                        <a style="color: red">*</a>
                        <select name="routeTypeName" class="routeTypeName" required >
                            <option disabled selected value="">Chọn một giá trị</option>
                            <c:forEach items="${routeTypeList}" var="r">
                                <option value="${r.id}">
                                    ${r.name}
                                </option>
                            </c:forEach>
                        </select>

                    </div>
                    <br>
                    <div class="khung" >
                        <div class="nhap" style="display: flex">
                            <div >Tiêu đề mục lục
                                <a style="color: red">*</a>
                            </div>
                            <input name="courseName" type="text" class="form-input" style="width: 379px ; margin-left: 154px;height: 25px" maxlength="100" required> 
                        </div>

                        <br>
                        <div class="nhap" style="display: flex">
                            <div >Giới thiệu về mục lục 
                                <a style="color: red">*</a>
                            </div>
                            <textarea name="content" cols="49" rows="5" style="margin-left: 91px" maxlength="2000" required></textarea>
                        </div>

                        <br>
                        <div class="nhap">Các khóa học:</div>
                        <div  class="container">
                            <c:forEach items="${listCourseDetail}" var="i">
                                <a  class="item">
                                    <input type="checkbox" style="cursor: pointer;" name="courseTitle" value="${i.id}" />${i.title}
                                    <img src="${i.image}" style="width: 119px ; height: 74px ; border-radius: 10px;" >
                                </a>
                            </c:forEach>
                        </div>
                        <p id="error-msg"></p>
                    </div>
                    <input type="submit" Value="Submit" class="submit" />
                </div>
            </div> 
                    </div>
                </div>
           
        </form>
        <script>
            const form = document.querySelector('form');
            const checkboxes = form.querySelectorAll('input[type="checkbox"]');
            const errorMsg = document.getElementById('error-msg');

            form.addEventListener('submit', function (event) {
                event.preventDefault(); // Prevents the form from submitting if no checkbox is checked

                const checkedBoxes = Array.from(checkboxes).filter(function (checkbox) {
                    return checkbox.checked;
                });

                if (checkedBoxes.length === 0) {
                    errorMsg.textContent = 'Hãy chọn ít nhất một khóa học';
                } else {
                    errorMsg.textContent = '';
                    form.submit(); // Submits the form if at least one checkbox is checked
                }
            });
        </script>
         <br><br><br><br>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
