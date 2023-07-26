<%-- 
    Document   : newBlog
    Created on : 01/06/2023, 8:40:14 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="./CommonLib/ckeditor/ckeditor.js"></script>
        <link rel="stylesheet" type="text/css" href="./css/newBlog.css">
        <link rel="stylesheet" type="text/css" href="./css/header.css">
        <link rel="stylesheet" type="text/css" href="./css/footer.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div><jsp:include page="header.jsp"></jsp:include></div>
        <form id='myForm' action="newBlog" method="POST">
            <input id="blogID" name="blogID" value="${blog.id}" style="display: none;"/>
            <input id="saveDraft" name="saveDraft" value="" style="display: none;"/>
            <div class="title"><input id="title" value="${blog.title}" class="title_inp" placeholder="Tiêu đề" name="title" onkeyup="checkChange()"/></div>
            <div class="editor_wrap" ><textarea name="editor" id="editor" class="editor">${blog.content}</textarea></div>
            <div class="submit_btn"><input class="submit_btn_inp" type="submit" value="<c:if test="${blog==null}">Xuất bản</c:if><c:if test="${blog!=null}">Lưu và xuất bản</c:if>"></div>
        </form>
        
        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            checkChange();
            var edit = CKEDITOR.replace('editor');
            function sm() {
                document.getElementById('myForm').submit();
            }
            function checkChange() {
                var content = document.querySelector('.title_inp');
                var input_btn = document.querySelector('.submit_btn_inp');
                var event = null;
                if(content.value !== ''){
                    input_btn.style.opacity = '1';
                    input_btn.style.cursor = 'pointer';
                    input_btn.addEventListener('click', sm);
                }else{
                    input_btn.style.opacity = '0.4';
                    input_btn.style.cursor = 'default';
                    input_btn.removeEventListener('click', sm);
                }
            }
//            Get out the page Event

        </script>
    </body>
</html>

