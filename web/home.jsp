<%-- 
    Document   : home
    Created on : 14/05/2023, 8:22:47 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="./css/base.css">
        <link rel="stylesheet" type="text/css" href="./css/main.css">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <link rel="stylesheet" href="./font/fontawesome-free-6.4.0-web/fontawesome-free-6.4.0-web/css/all.min.css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <title>Document</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="center.jsp"></jsp:include>
        <jsp:include page="footer.jsp"></jsp:include>
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <script>
                     function activeErrorToast() {
                toastr.error('${savedBlogStatus}');
            }
            function activeSuccessToast(abc) {
                toastr.success(abc);
            }
            if ('${editBlogStatus}' !== '') activeSuccessToast('${editBlogStatus}');
            if ('${createRouteTypeStatus}' !== '') activeSuccessToast('${createRouteTypeStatus}');
            if ('${createRouteCourseItemStatus}' !== '') activeSuccessToast('${createRouteCourseItemStatus}');
            if ('${changePasswordStatus}' !== '') activeSuccessToast('${changePasswordStatus}');
            if ('${ResetPasswordStatus}' !== '') activeSuccessToast('${ResetPasswordStatus}');
           
        </script>
    </body>
</html>
