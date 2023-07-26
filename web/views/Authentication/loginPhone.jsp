<%-- 
    Document   : loginPhone
    Created on : May 26, 2023, 12:06:21 AM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <label class="form-input-label">Số điện thoại</label>
                        <a class="text-decoration-none" href="/g4/views/Authentication/loginEmail.jsp">
                            <label class="form-input-label">Đăng nhập với Email
                            </label>
                        </a>
                    </div>
                    <div class="input-group mb-3 mt-2">
                        <span class="input-group-text rounded-start-pill" id="basic-addon1">+ 84</span>
                        <input id="inputPhone" onkeydown="validateNumber(event)" onchange="validatePhone()" required
                               type="text" name="phone" class="form-control rounded-end-pill" placeholder="Số điện thoại"
                               aria-label="Username" aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control rounded-start-pill" placeholder="Nhập mã xác nhận"
                               aria-label="Nhập mã xác minh" aria-describedby="basic-addon2">
                        <span id="spanSend"
                              class="input-group-text rounded-end-pill" id="basic-addon2">
                            <button class="input-group-text" disabled onclick="sendVerificationCode()"
                                    id="btnSend" style="border: none">Gửi mã</button><br/>
                        </span>
                    </div>
                    <button id="btn-login" type="submit" disabled
                            style="color: #ffffff;" class="btn btn-info rounded-pill w-100">Đăng nhập</button>

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
                            //cho phep nhap only so
                            function validateNumber(event) {
                                var keyCode = event.keyCode || event.which;
                                var keyValue = String.fromCharCode(keyCode);
                                // Allow Backspace (keyCode 8) to work even at maximum length
                                if (keyCode === 8) {
                                    return;
                                }
                                // Only allow digits (0-9)
//                                var validDigits = /^[0-9]+$/;
//                                if (!validDigits.test(keyValue) || event.target.value.length >= 10) {
//                                    event.preventDefault();
//                                }
                            }
                            //kiem tra dien thoai du 10 so chua
                            function validatePhone() {
                                var input = document.getElementById('inputPhone');
                                var button = document.getElementById('btnSend');
                                var span = document.getElementById('spanSend');
                                button.style.backgroundColor = '#FF4500';
                                span.style.backgroundColor = '#FF4500';
                                button.disabled = input.value.length !== 9;
                            }
                            function sendVerificationCode() {
                                phoneInput.setNumber(document.getElementById("phone").value);
                                var formattedPhoneNumber = phoneInput.getNumber();
                                var phoneNumber = document.getElementsByName("phone").value;
                                var appVerifier = new firebase.auth.RecaptchaVerifier("registration-form-" + countRecaptcha);
                                console.log(formattedPhoneNumber);

                                firebase.auth().signInWithPhoneNumber(formattedPhoneNumber, appVerifier)
                                        .then(function (result) {
                                            confirmationResult = result; // Lưu kết quả xác thực vào biến confirmationResult

                                            console.log("Mã xác thực đã được gửi đi thành công");
                                        })
                                        .catch(function (error) {
                                            console.error("Lỗi gửi mã xác thực", error);
                                        });
                                add_child();
                            }
        </script>
        <script>
            function redirectToURL(url) {
                window.location.href = url;
            }
        </script>
    </body>

</html>
