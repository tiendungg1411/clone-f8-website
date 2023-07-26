<%-- 
    Document   : reset
    Created on : May 14, 2023, 11:19:40 PM
    Author     : TIEN DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>

            .footer{
                text-align: center;
                margin-top: 130px;
                color: #18171791;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
                font-size: 13px
            }
            .all{
                border: 1px solid black;
                width: 640px;
                height: 600px;
                background-color: #ffffff ;
                border-radius: 2%;
                margin-top: 100px
            }
            .nhap{
                background-color: #0000000d;
                width:380px;
                height: 49px;
                margin-left: 128px;
                border-radius: 24px;
            }
            .form-input{
                border-color: #00000000;
                font-size: 17px;
                background-color: #00000000;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
                border-width: 0px;
                margin-top: 12px;
                margin-left: 16px;
                width:340px;
                outline: none;
            }
            .ma  {
                position: absolute;
                left: 362px;
                width:146px;
                height: 45px;
                border-radius:24px;
                border-style: solid;
                border-color: #00000000;
                border-width: 2px;
                top: 2px;
                background-color: #ccc;
                 cursor: not-allowed;
                display: flex;
                justify-content: center;
                align-items: center;
                font-weight: 700;
        
            }
            .guima {
                background-color: #ccc;
                border-width: 0px;
                font-size: 17px;
                font-weight: 700;
                color: #3a3636a3;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;

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
                margin-top: -15px;
                cursor: not-allowed;
            }

            .h1{

                font-family: 'Montserrat',Arial,Helvetica,sans-serif;

            }
            .avatar{

                border-radius: 10%;
                width: 45px;
                height: 45px;
                cursor: pointer ;
                
                margin-left: 280px;
            }
            .cuoi{
                margin-left: 600px;
                margin-top:61px;
                font-family: 'Montserrat',Arial,Helvetica,sans-serif;
                font-size: 14px;
            }
            
        </style>
    </head>
    <body style="background-image: url(https://accounts.fullstack.edu.vn/static/media/f8_bg_auth_1920.b517075e98f3051de678.png);">
        <form action="passwordReset" method="post">
            <div style="display: flex ;
                 justify-content: center;
                 align-items: center;
                 height: 600px">
                 
                <div class="all">
                    <div style="font-size: 30px;margin-left: 30px;margin-top: 20px;font-weight: 900;cursor: pointer" onclick="back()"><</div>
                    <div >
                    
                     <a href="/g4/home" >
                            <img src="https://accounts.fullstack.edu.vn/assets/icon/f8_icon.png" alt="Avatar"  class="avatar" >
                        </a>
                        <h1 class="h1" style="text-align: center"> 
                            Lấy lại mật khẩu</h1>  
                    </div>
                    <div class="khung" >
                        <h4 style="margin-left: 140px;
                            font-family: 'Montserrat',Arial,Helvetica,sans-serif ">Email</h4>
                        <div class="form-group" >
                            <div class="nhap">
                                <input type="text" class="form-input" placeholder="Nhập địa chỉ email" value="${email}" id="email" name="email"> 
                            </div>

                        </div>
                   
                        <div style="display: flex; color: red ; border-color: red;margin-top: 5px ; margin-left: 148px">
                            ${errorMessage1}                            
                        </div>
                             <br> 
                        <div class="form-group" style="
                             position: relative">
                            <div class="nhap">
                                <input type="password" class="form-input" placeholder="Nhập mã xác nhận" id="confirmationCode" name="confirmationCode">
                            </div>
                            <div class="ma" id="submit-button" type="submit" >
                                <input class="guima" id="span" type="submit" name="sendCodeBtn" value="Gửi mã"
                                       >
                            </div>

                         
                        </div>

                        <div style="display: flex; color: red ; border-color: red;margin-bottom:  10px ; margin-left: 148px">
                            ${errorMessage2}                            
                        </div>
                        <br>
                        <div style="text-align: center ">
                            <input value="Xác nhận" class="submit" type="submit" name="resetBt" id="xac_nhan" onclick="newPassword()" />
                        </div>
                    </div>
                    <script>
                        const emailInput = document.getElementById('email');
                        const submitButton = document.getElementById('submit-button');
                        const submitSpan = document.getElementById('span');

                        const inputBox = document.getElementById("confirmationCode");
                        const xac_nhan = document.getElementById("xac_nhan");

                        emailInput.addEventListener('input', () => {
                            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                            if (emailRegex.test(emailInput.value)) {
                                submitButton.style.cursor = 'pointer';
                                submitSpan.style.cursor = 'pointer';
                                submitButton.style.backgroundColor = "#f05123";
                                submitSpan.style.backgroundColor = "#f05123";
                                submitSpan.style.color = "#fff";
                            } else {
                                submitButton.style.cursor = 'not-allowed';
                            }
                        });

                        inputBox.addEventListener("input", function () {
                            if (inputBox.value.length >= 6) {
                                xac_nhan.style.cursor = "pointer";
                            } else {
                                xac_nhan.style.cursor = "not-allowed";
                            }
                        });

                        function newPassword() {
                            window.location = "newPassword.jsp";
                        }
                        function back() {
                        window.location.href="/g4/views/Authentication/loginEmail.jsp";
                         }
                        
                    </script>


                    <div class="footer">
                        <div  >Việc bạn tiếp tục sử dụng trang web này đồng nghĩa bạn đồng ý với </div> 
                        <div ><a href="https://fullstack.edu.vn/terms" style="color: #18171791"> Ðiều khoản sử dụng</a> của chúng tôi</div>

                    </div>
                </div>
            </div>
            <div class="cuoi">
                <a href="https://fullstack.edu.vn/about-us" style="text-decoration: none;
                   color: white">Giới thiệu về F8 | </a> 
                <a href="https://www.youtube.com/c/F8VNOfficial " style="text-decoration: none;
                   color: white"> F8 trên Youtube |</a>
                <a href="https://www.facebook.com/groups/f8official " style="text-decoration: none;
                   color: white" > F8 trên Facebook</a>

            </div>


        </form>
    </body>
</html>
