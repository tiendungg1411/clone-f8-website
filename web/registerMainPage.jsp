<%-- 
    Document   : registerMainPage
    Created on : May 26, 2023, 6:35:34 PM
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
            .footer{
                text-align: center;
                margin-top: 100px;
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
            .ma{
                position: absolute;
                left: 362px;
                width:146px;
                height: 42px;
                border-radius:24px;
                border-style: solid;
                border-color: #00000000;
                border-width: 2px;
                font-size: 17px ;
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
            .Login_body {
                align-items: center;
                display: flex;
                flex-direction: column;
                margin-top: 8px;
            }
            .Login_mainStep {
                margin-top: 10px;
            }
            .SigninButton_title{
                color: #35414c;
                cursor: pointer;
                font-size: 0.9rem;
                font-weight: 600;
                line-height: 40px;
                outline: 0;
                text-align: center;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
            }
            .SigninButton_wrapper {
                --height: 44px;
                background-color: #fff;
                border: 2px solid #dce0e3;
                border-radius: 44px;
                display: inline-block;
                height: var(--height);
                line-height: var(--height);
                padding-left: 16px;
                position: relative;
                text-align: center;
                width: 320px;
                width:380px;
                height: 49px;
                margin-left: 128px;
                border-radius: 24px;
                margin-top: 15px;
            }
            .SigninButton_icon {
                left: 16px;
                position: absolute;
                top: 50%;
                -webkit-transform: translateY(-50%);
                transform: translateY(-50%);
            }
        </style>
    </head>
    <body style="background-image: url('./image/anhnen.png') ;background-size: 100%">
        <div style="display: flex ; justify-content: center;align-items: center;height: 600px">
            <div class="all">
                <img onclick="returnHome()" src="./image/f8_logo.png" alt="Avatar"  class="avatar" />
                <h1 class="h1"> 
                    Đăng kí tài khoản F8</h1>
                <div style="display: flex;justify-content: center; color: red;font-weight:bold">
                    ${error}
                </div>
                

                <div class="khung" >
                    <div class="Login_body">
                        <div class="Login_mainStep">
                            <div class="SigninButton_wrapper">
                                <img  class="SigninButton_icon" src="./image/personal-18px.svg" alt="Đăng nhập với personal">
                                <span class="SigninButton_title" onclick="window.location.href = 'registerPhone.jsp';">Sử dụng email / số điện thoại</span>
                            </div>
                            <div class="SigninButton_wrapper">
                                <img class="SigninButton_icon" src="./image/google-18px-gg.svg" alt="Đăng nhập với google">
                                <span class="SigninButton_title" type="submit" onclick="loginWithGoogle()">    Tiếp tục với Google</span>
                            </div>
                            <div class="SigninButton_wrapper">
                                <img class="SigninButton_icon" src="./image/facebook-18px-fb.svg" alt="Đăng nhập với facebook">
                                <span onclick="loginWithFacebook()" class="SigninButton_title">Tiếp tục với Facebook</span>
                            </div> 
                            <div class="SigninButton_wrapper">
                                <img class="SigninButton_icon" src="./image/github-18px-gh.svg" alt="Đăng nhập với github">
                                <span class="SigninButton_title" onclick="loginWithGithub()">Tiếp tục với Github</span>
                            </div>
                        </div>
                        <div class="Login_dontHaveAcc" style="display: flex;justify-content: center; ">
                            <h4 style="font-weight: normal;padding-right: 3px">Bạn đã có tài khoản? </h4>
                            <h4 onclick="ridect_login()" style="cursor: pointer;color: #f05123">  Đăng nhập</h4>
                        </div>
                    </div>

                </div>
                <div class="footer">
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
            function ridect_login() {
                window.location.href = "/g4/views/Authentication/loginMainPage.jsp";
            }
            function returnHome(){
                window.location.href = "/g4/home";
            }
            function loginWithFacebook() {
                // Redirect the user to the Facebook login page
                window.location.href = "https://www.facebook.com/v12.0/dialog/oauth?client_id=1278529913078590&redirect_uri=http://localhost:9999/g4/facebookCallBack&state=xyz";
            }
            function loginWithGoogle() {
                // Redirect the user to the Google login page
                window.location.href = 'GoogleRegisterServlet';
            }
            function loginWithGithub() {
                // Redirect the user to the Github login page
                window.location.href = 'http://localhost:9999/g4/loginGitHub';
            }
            
        </script>
    </body>
</html>
