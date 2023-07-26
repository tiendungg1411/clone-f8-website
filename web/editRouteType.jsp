<%-- 
    Document   : createRouteCourse
    Created on : Jun 5, 2023, 9:46:07 PM
    Author     : TIEN DAT
--%>

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
        <link rel="stylesheet" type="text/css" href="css/sidebar.css">
        <style>
            /*            .all{
                            border: 1px solid black;
                            width: 640px;
                            height: 600px;
                            background-color: #fdcfc9 ;
                            border-radius: 2%;
                            margin-top: 100px
                        }*/
            .h1{
                /*                font-family: 'Montserrat',Arial,Helvetica,sans-serif;*/
            }
            .avatar{
                border-radius: 10%;
                width: 45px;
                height: 45px;
                cursor: pointer ;
                margin-left: 280px;
                margin-top: 50px;
            }
            /*            .nhap{
                            background-color: #0000000d;
                            width:380px;
                            height: 49px;
                            margin-left: 128px;
                            border-radius: 24px;
                        }*/
            .form-input{
                border-color: black;
                font-size: 19px;
                /*                font-family: 'Montserrat',Arial,Helvetica,sans-serif;*/

                height: 26px;
                border-width: 1px;
                width:340px;
                /*                outline: none;*/
            }
            .nhap{
                margin-left:29px ;
                font-size: 25px;
                font-weight:600;
            }
            .submit{
                margin-left: 190px;
                width: 90px;
                height: 27px;
                margin-top: 15px;
                cursor: pointer;
                font-size: 19px;
            }
        </style>
    </head>
    <body >
        <jsp:include page="header.jsp"></jsp:include> 
            <form action="editRouteType" method="post" enctype="multipart/form-data">
                <div style="display: flex ;

                     height: 600px">
                    <div style="margin-left: 30px;margin-top: 30px">
                    <jsp:include page="sidebar.jsp"></jsp:include>
                    </div>
                    <div class="all" style=" color: #053f1b;margin-top: -50px;margin-left: 250px">
                        <h1 class="h1" style=";font-size: 40px;margin-top: 100px;margin-left: 150px;margin-bottom: 50px"> 
                            Edit lộ trình học</h1>  
                        <div class="khung" style="margin-top: 25px">
                            <div class="nhap" >
                                <div style="margin-bottom: 10px" >Tiêu đề lộ trình học
                                    <a style="color: red">*</a>
                                </div>
                                <input value="${routeType.id}"  name="routeTypeID" id="routeTypeID" type="hidden">
                                <input type="text" name="routeName" value="${routeType.name}" class="form-input" style="width: 400px ;"  maxlength="100" required> 
                            </div>

                            <br>
                            <div class="nhap" >
                                <div style="margin-bottom: 10px" >Ảnh lộ trình học
                                    <a style="color: red">*</a>
                                </div>
                                <!--                            <input type="text" name="image" class="form-input" style="width: 296px ; margin-left: 105px" required> -->
                                <input required="" id="image" type="file" value="${routeType.image}" name="image"/><br>${error7}
                        </div>

                        <br>
                        <div class="nhap" >
                            <div style="margin-bottom: 10px">Giới thiệu bao quát
                                <a style="color: red">*</a>
                            </div>
                            <textarea name="content1" style="width: 400px;height: 100px;font-size: 19px;"  maxlength="2000" required>${routeType.description1}</textarea>
                        </div>
                        <br>
                        <div class="nhap" >
                            <div style="margin-bottom: 10px" >Giới thiệu chi tiết
                                <a style="color: red">*</a>
                            </div>
                            <textarea name="content2" style="width: 400px;height: 100px;font-size: 19px;" maxlength="2000" required>${routeType.description2}</textarea>
                        </div>    <br>
<!--                        <div class="nhap" >
                            <div style="margin-bottom: 10px">An / Hien
                                <a style="color: red">*</a>
                            </div>
                            <input type="text" name="status" value="${routeType.status}" onblur="validateInput(this.value)" class="form-input" style="width: 400px ;" required> 
                        </div>-->
                    </div>
                    <input type="submit" Value="Submit" class="submit"  />
                </div>
                <a>
                    <img src="https://fullstack.edu.vn/static/media/fb-group-cards.4bd525b1b8baf7b1e5a2.png" style="width: 420px;height: 420px;margin-left: 200px;margin-top: 200px" >

                </a>
            </div>
        </form>
        <br><br><br><br>
        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            function validateInput(value) {
                var errorElement = document.getElementById("error");

                if (value !== "0" && value !== "1") {
                    errorElement.innerHTML = "Giá trị không hợp lệ. Vui lòng nhập lại.";
                    return false; // Prevent form submission
                } else {
                    errorElement.innerHTML = "";
                    return true; // Allow form submission
                }
            }
        </script>
    </body>
</html>
