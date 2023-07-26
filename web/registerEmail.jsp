<%-- 
    Document   : registerEmail
    Created on : May 19, 2023, 8:03:13 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                height: 740px;
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
                margin-top:130px;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
                font-size: 14px;
            }
        </style>
    </head>
    <body style="background-image: url('./image/anhnen.png') ;background-size: 100%">


        <div style="display: flex ; justify-content: center;align-items: center;height: 600px">
            <div class="all">
                <img onclick="returnHome()" src="./image/f8_logo.png" alt="Avatar"  class="avatar" />
                <h1 class="h1">Đăng kí tài khoản F8</h1>

                <div class="khung" >
                    <h5 style="margin-left: 125px;font-family: 'Montserrat',Arial,Helvetica,sans-serif">Tên của bạn? </h5>

                    <form action="registerEmail" method="post">
                        <div class="form-group" style="text-align: center ">
                            <input type="text" class="form-input" placeholder="    Họ và tên của bạn" id="username" value="${username}"  name="username" > 
                        </div>

                        <div class="mid_register" style="display: inline-flex">                        
                            <h5 style="margin-left: 125px;font-family: 'Montserrat',Arial,Helvetica,sans-serif">Email </h5>
                            <h5 style="cursor: pointer;margin-left: 230px;font-family: 'Montserrat',Arial,Helvetica,sans-serif" onclick="register_phone_ridect()">Đăng ký với SÐT</h5>                                                
                        </div>


                        <div class="form-group" style="text-align: center ">
                            <div style="text-align: center ;position: relative">
                                <input type="text" class="form-input" placeholder="    Ðịa chỉ email" value="${email}" id="email" name="email" style="margin-bottom: 10px;" > 
                                <div class="send_code_email" style="display: flex;justify-content: center;align-items: center;">
                                    <input type="submit" name="checkEmail" style="cursor: pointer; margin-top: 2px;margin-left: 48px" value="Check Email"> 
                                </div>
                            </div>
                            <div style="display: flex;justify-content: center; color: red;font-weight:bold ">
                                ${errorMessage1}                          
                            </div>  
                            <div style="display: flex;justify-content: center; color: green;font-weight:bold ">
                                    ${errorMessage2}                    
                            </div>  

                            <br> 
                            <input type="password" class="form-input" placeholder="    Mật khẩu" value="${password}" id="password" name="password"> 
                            <h6 class="FormInput_help__KZbU7" style="    font-weight: normal;text-align:left;margin: 8px 0 8px 130px ;font-family: 'Montserrat',Arial,Helvetica,sans-serif">Gợi ý: Mật khẩu cần có ít nhất 6 kí tự và 1 kí tự đặc biệt</h6>
                        </div>
                        <br> 
                        <div class="form-group" style="text-align: center ;position: relative">
                            <input type="password" class="form-input" placeholder="    Nhập mã xác nhận" id="confirmationCode" name="confirmationCode">
                            <div class="send_code_email" style="display: flex;justify-content: center;align-items: center;">
                                <input type="submit" name="sendCodeBtn" style="cursor: pointer; margin-top: 2px;margin-left: 48px;" value="Gửi mã"> 
                            </div>
                            <br>
                        </div>
                        <div style="display: flex;justify-content: center; color: red;font-weight:bold ">
                            ${errorMessage}                            
                        </div>                        
                        <div style="text-align: center">
                            <input style="cursor: pointer" value="Đăng kí" class="submit" type="submit" name="registerBt" />
                        </div>
                    </form>

                    <br>
                </div>

                <div class="Login_dontHaveAcc" style="display: flex;justify-content: center; ">
                    <h4 style="font-weight: normal;padding-right: 3px">Bạn đã có tài khoản? </h4>
                    <h4 onclick="ridect_login()" style="cursor: pointer;color: #f05123">  Đăng nhập</h4>
                </div>

                <div class="footer_register">
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
            function register_phone_ridect() {
                window.location.href = "/g4/registerPhone.jsp";
            }
            function returnHome() {
                window.location.href = "/g4/home";
            }
            function ridect_login() {
                window.location.href = "/g4/views/Authentication/loginMainPage.jsp";
            }
        </script>
        <script type="module">
            // Import the functions you need from the SDKs you need
            import { initializeApp } from "https://www.gstatic.com/firebasejs/9.22.0/firebase-app.js";
            import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.22.0/firebase-analytics.js";
            // TODO: Add SDKs for Firebase products that you want to use
            // https://firebase.google.com/docs/web/setup#available-libraries

            // Your web app's Firebase configuration
            // For Firebase JS SDK v7.20.0 and later, measurementId is optional
            const firebaseConfig = {
                apiKey: "AIzaSyCBxmSsivZUsvHuILRElwTROat0MFkG8DA",
                authDomain: "f8demo-9e6de.firebaseapp.com",
                projectId: "f8demo-9e6de",
                storageBucket: "f8demo-9e6de.appspot.com",
                messagingSenderId: "1062836074174",
                appId: "1:1062836074174:web:3d4d996ca52634bcfad0ef",
                measurementId: "G-9D2RS9QDST"
            };

            // Initialize Firebase
            const app = initializeApp(firebaseConfig);
            const analytics = getAnalytics(app);
        </script>

    </body>
</html>

