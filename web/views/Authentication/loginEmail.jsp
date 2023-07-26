<%-- 
    Document   : login
    Created on : May 25, 2023, 11:10:30 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
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
                <a href="#" class="back-to-login-btn">
                    <i onclick="redirectToURL('/g4/views/Authentication/loginMainPage.jsp')"
                       class="fa-solid fa-chevron-left" style="color: #000000;"></i>
                </a>
                <div class="d-flex flex-column align-items-center">
                    <img src="https://accounts.fullstack.edu.vn/assets/icon/f8_icon.png"
                         style="width: 44px; height: 44px; object-fit: cover; border-radius: 12px;" />
                    <div class="mt-4 mb-4 login-head-title">Đăng nhập vào F8</div>
                </div>
                <div style="width: 320px;">
                    <div class="d-flex justify-content-between align-items-center">
                        <label class="form-input-label">Email</label>
                        <a class="text-decoration-none" href="/g4/views/Authentication/loginPhone.jsp">
                            <label class="form-input-label">Đăng nhập với SĐT
                            </label>
                        </a>
                    </div>
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="input-group mb-3 mt-2">
                            <input type="email" name="email" required
                                   class="form-control rounded-pill" placeholder="Địa chỉ email"
                                   aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group mb-4 mt-2">
                            <input type="password" name="pwd" required
                                   class="form-control rounded-pill" placeholder="Mật khẩu" aria-label="Username"
                                   aria-describedby="basic-addon1">
                        </div>
                        <input type="hidden" name="action" value="emailLogin"/>
                        <div id="msgFail" style="display: none; color: red">
                            <p>Email hoặc mật khẩu không chính xác</p>
                            <script>
                                var isFail = ${isLoginFail};
                                if (isFail) {
                                    document.getElementById("msgFail").style.display = "block";
                                }
                            </script>
                        </div>
                        <button type="submit" style="color: #ffffff;" class="btn btn-info rounded-pill w-100">Đăng nhập</button>
                    </form>
                </div>
                <div class="new-user-signup text-center">
                    <div>
                        Bạn chưa có tài khoản? <a style="font-weight: 500; color: #f05123;" href="/g4/registerMainPage.jsp">Đăng ký</a>
                    </div>
                    <a style="font-weight: 500; color: #f05123;cursor: pointer"href="/g4/passwordReset.jsp">Quên mật khẩu</a>
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
