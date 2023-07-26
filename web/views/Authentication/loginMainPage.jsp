<%-- 
    Document   : login
    Created on : May 25, 2023, 11:10:30 PM
    Author     : dell
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/login.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <div class="back"
             style="background-image: url(https://accounts.fullstack.edu.vn/static/media/f8_bg_auth_1920.b517075e98f3051de678.png);">
            <div class="div-center d-flex flex-column align-items-center justify-content-between">
                <div class="d-flex flex-column align-items-center">
                    <img src="https://accounts.fullstack.edu.vn/assets/icon/f8_icon.png"
                         style="width: 44px; height: 44px; object-fit: cover; border-radius: 12px;" />
                    <div class="mt-4 mb-4 login-head-title">Đăng nhập vào F8</div>
                </div>
                <div>
                    <div onclick="redirectToURL('/g4/views/Authentication/loginEmail.jsp')"
                        class="login-btn-wrapper gap-3 rounded-pill border px-4 py-2 mt-3 text-center">
                        <img class="img-login-btn"
                             src="https://accounts.fullstack.edu.vn/assets/images/signin/personal-18px.svg"
                             alt="Đăng nhập với personal">
                        <p class="mb-0 txt-login-btn">
                            Sử dụng email / số điện thoại
                        </p>
                    </div>
                    <div onclick="redirectToURL('GoogleRegisterServlet')"
                        class="login-btn-wrapper gap-3 rounded-pill border px-4 py-2 mt-3 text-center">
                        <img class="img-login-btn"
                             src="https://accounts.fullstack.edu.vn/assets/images/signin/google-18px.svg"
                             alt="Đăng nhập với google">
                        <p class="mb-0 txt-login-btn">
                            Tiếp tục với google
                        </p>
                    </div>
                        <div onclick="redirectToURL('https://www.facebook.com/v12.0/dialog/oauth?client_id=1278529913078590&redirect_uri=http://localhost:9999/g4/facebookCallBack&state=xyz')" class="login-btn-wrapper gap-3 rounded-pill border px-4 py-2 mt-3 text-center">
                        <img class="img-login-btn"
                             src="https://accounts.fullstack.edu.vn/assets/images/signin/facebook-18px.svg"
                             alt="Đăng nhập với facebook">
                        <p class="mb-0 txt-login-btn">
                            Tiếp tục với Facebook
                        </p>
                    </div>
                    <div onclick="redirectToURL('http://localhost:${port}/g4/loginGitHub')"
                        class="login-btn-wrapper gap-3 rounded-pill border px-4 py-2 mt-3 text-center">
                        <img class="img-login-btn"
                             src="https://accounts.fullstack.edu.vn/assets/images/signin/github-18px.svg"
                             alt="Đăng nhập với github">
                        <p class="mb-0 txt-login-btn">
                            Tiếp tục với Github
                        </p>
                    </div>
                </div>
                <div class="new-user-signup">
                    Bạn chưa có tài khoản? <a style="font-weight: 500; color: #f05123;" href="/g4/registerMainPage.jsp">Đăng ký</a>
                </div>
                <div class="text-center" style="color: #666; font-size: 12px; font-weight: 300; width: 376px;">Việc bạn tiếp
                    tục sử dụng trang
                    web này đồng nghĩa bạn đồng ý với <a href="https://fullstack.edu.vn/terms" style="color: #666;">Điều
                        khoản sử dụng</a> của
                    chúng tôi.
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
        <script>
            function redirectToURL(url) {
                window.location.href = url;
            }
        </script>
    </body>
</html>