<%-- 
    Document   : changepassword
    Created on : May 17, 2023, 7:44:12 PM
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
  <script src="path/to/bootstrap.min.js"></script>
        <style>
            .input{
                width: 250px;
            }

        </style>
    </head>

    <body style="font-family: 'Montserrat',Arial,Helvetica,sans-serif;">
        
      
       <jsp:include page="header.jsp"></jsp:include> 
       
         <form action="passwordChange" method="post">
             <div style="display: flex">
                 <div style="margin-top: 50px ; ">
                      <jsp:include page="OptionSetting.jsp"></jsp:include>
                 </div>
             <div style="margin-top: 50px;margin-left: 90px">
            <h1 style="font-size: 40px">Đổi mật khẩu</h1>
            <br>
            <br>
            <div style="font-size: 20px" > Mật khẩu hiện tại*</div>  
            <input class="input" type="password" name="oldPassword" style="margin-top: 7px" minlength="8" required>  <br>
            <div style="font-size: 15px; font-weight: 700;color: red" >
            ${error1} 
            </div>
         

            <br>
            <div style="font-size: 20px">Mật khẩu mới*</div> 
            <input class="input" type="password" name="newPassword"style="margin-top: 7px" minlength="8" required>  <br>
            <div style="font-weight: normal;font-size: 13px;margin-top: 3px ;font-family: 'Montserrat',Arial,Helvetica,sans-serif" >Gợi ý: Mật khẩu cần có ít nhất 8 kí tự
            </div>

            <br>
            <div style="font-size: 20px">Xác nhận mật khẩu mới*</div> 
            <input class="input" type="password" name="confirmNewPassword" style="margin-top: 7px" minlength="8" required> <br>
            <div style="font-size: 15px; font-weight: 700;color: red">
            ${error2} 
            </div>
            <br>
            <input style="cursor: pointer;font-size: 17px" type="submit" name="passwordChange" value="Đổi mật khẩu" >
 
            <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script>
            function logout() {
                window.location = 'logout';
            }
            let password = document.getElementById('password');
            if(password.type == 'text'){
                password.type = 'password';
            }else{
                password.type = 'text';
            }
        </script>
          
        </div>
           </div>
         </form>
           
              <jsp:include page="footer.jsp"></jsp:include>
    </body>


</html>
