<%-- 
    Document   : registerPhone
    Created on : May 19, 2023, 8:03:01 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

        <title>JSP Page</title>
        <style>
            .khung{
                margin-top:  60px;
            }
            .footer_register{
                text-align: center;
                margin-top: 120px;
                color: #18171791;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
                font-size: 13px
            }
            .all{
                border: 1px solid black;
                width: 640px;
                height: 700px;
                background-color: #ffffff ;
                border-radius: 2%;
                margin-top: 100px
            }
            .form-input{
                width:380px;
                height: 44px;
                border-color: #00000000;
                font-size: 17px;
                border-radius: 24px;
                background-color: #0000000d;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
            }
            .send_code_phone{
                position: absolute;
                left: 362px;
                width:100px;
                height: 42px;
                border-radius:24px;
                border-style: solid;
                border-color: #00000000;
                border-width: 2px;
                font-size: 13px ;
                top: 2px;
                font-weight: 700;
                background-color: #ccc;
                justify-content: center;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
                color: #3a3636a3;
            }
            .send_code_email input[type="submit"]{
                position: absolute;
                left: 362px;
                width:100px;
                height: 42px;
                border-radius:24px;
                border-style: solid;
                border-color: #00000000;
                border-width: 2px;
                font-size: 13px ;
                top: 2px;
                font-weight: 700;
                background-color: #ccc;
                justify-content: center;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
                color: #3a3636a3;
            }
            .submit{
                width:380px;
                height: 40px;
                font-size: 20px;
                border-radius: 24px;
                background-color: #68eedfab;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
                font-weight: 700;
                color: white;
                border-color: #00000000;
            }
            .avatar{
                position: absolute;
                border-radius: 10%;
                width: 45px;
                height: 45px;
                margin-left: 300px;
                margin-top: 40px;
            }
            .h1{
                text-align:  center;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
                margin-top: 115px;
            }
            .cuoi{
                margin-left: 550px;
                margin-top:110px;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
                font-size: 14px;
            }
        </style>
    </head>

    <body style="background-image: url('./image/anhnen.png') ;background-size: 100%">
        <div style="display: flex ; justify-content: center;align-items: center;height: 600px">
            <div class="all">
                <img onclick="returnHome()" src="./image/f8_logo.png" alt="Avatar"  class="avatar" />
                <h1 class="h1"> 
                    Đăng kí tài khoản F8</h1>
                <form action="registerPhone" method="get">
                    <div class="khung" >
                        <h5 style="margin-left: 125px;font-family: 'Montserrat',Arial,Helvetica,sans-serif">Tên của bạn? </h5>
                        <div class="form-group" style="text-align: center ">

                            <input type="text" class="form-input" value="${name}" placeholder="    Họ và tên của bạn" id="name" name="name" > 
                        </div>
                        <div class="mid_register" style="display: inline-flex">                        
                            <h5 style="margin-left: 125px;font-family: 'Montserrat',Arial,Helvetica,sans-serif">Số điện thoại </h5>                       
                            <h5 style="cursor: pointer;margin-left: 170px;font-family: 'Montserrat',Arial,Helvetica,sans-serif" onclick="register_email_ridect()">Đăng ký với Email</h5>                                               
                        </div>

                        <div class="form-group" style="text-align: center ;position: relative">
                            <input id="phone" name="phone" value="${phone}" type="tel" class="form-input" placeholder="    Số điện thoại" style="margin-bottom: 10px;" >   
                            <div class="send_code_email" style="display: flex;justify-content: center;align-items: center;margin-left: 48px; margin-bottom: 2px">
                         <input type="submit" name="checkPhone" style="margin-top: cursor: pointer;margin-left: 48px;" value="Check Phone"> 
                            </div>
                        </div>  
                        <div style="display: flex;justify-content: center; color: red;font-weight:bold ">
                            ${errorMessage1}                          
                        </div>  
                        <div style="display: flex;justify-content: center; color: green;font-weight:bold ">
                            ${errorMessage2}                    
                        </div>  
                        <br> 
                        <div class="form-group" style="text-align: center ;position: relative">                        
                            <input id="" type="password" class="form-input" placeholder="    Nhập mã xác nhận" name="authetication_code">                      
                            <div class="send_code_phone" style="margin-left: 48px">                            
                                <div onclick="sendVerificationCode()" style="margin-top: 12px;cursor: pointer;"> Gửi mã</div>
                            </div>
                            <!--<button onclick="test()">test</button>-->
                            <p id="statusMessage"></p>
                            <br>
                        </div>
                        <br> 
                        <div style="display: flex;    justify-content: center;" id="registration-form">
                            <div id="registration-form-0"></div>
                        </div>
                        <div style="display: flex;justify-content: center; color: red;font-weight:bold ">
                            ${errorMessage}                            
                        </div>  
                        <div style="text-align: center;">
                            <button style="cursor: pointer;" class="submit" type="submit" name="register" />Đăng kí
                        </div>
                    </div>
                </form>
                <div class="Login_dontHaveAcc" style="display: flex;justify-content: center">
                    <h4 style="font-weight: normal;padding-right: 3px">Bạn đã có tài khoản? </h4>
                    <h4 onclick="ridect_login()" style="cursor: pointer;color: #f05123">  Đăng nhập</h4>
                </div>
                <div class="footer_register" >
                    <div  >Việc bạn tiếp tục sử dụng trang web này đồng nghĩa bạn đồng ý với </div> 
                    <div ><a href="https://fullstack.edu.vn/terms" style="color: #18171791"> Ðiều khoản sử dụng</a> của chúng tôi</div>
                </div>
            </div>
        </div>
        <div class="cuoi">
            <a href="https://fullstack.edu.vn/about-us" style="text-decoration: none;color: white">Giới thiệu về F8 </a> <span>|</span>
            <a href="https://www.youtube.com/c/F8VNOfficial " style="text-decoration: none;color: white"> F8 trên Youtube </a> <span>|</span>
            <a href="https://www.facebook.com/groups/f8official " style="text-decoration: none;color: white" > F8 trên Facebook</a>

        </div>
        <script>
            function register_email_ridect() {
                window.location.href = "/g4/registerEmail.jsp";
            }
            function returnHome() {
                window.location.href = "/g4/home";
            }
            function ridect_login() {
                window.location.href = "/g4/views/Authentication/loginMainPage.jsp";
            }
            //intenational phone
            const phoneInputField = document.querySelector("#phone");
            const phoneInput = window.intlTelInput(phoneInputField, {
                utilsScript:
                        "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
                initialCountry: "vn"
            });
        </script>

        <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-auth.js"></script>
        <script src="js/registerPhone.js"></script>
    </body>
</html>

