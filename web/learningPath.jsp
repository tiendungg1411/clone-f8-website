<%-- 
    Document   : learningPaths
    Created on : May 30, 2023, 5:33:02 PM
    Author     : TIEN DAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/header.css">
        <link rel="stylesheet" type="text/css" href="css/footer.css">
        <link rel="stylesheet" type="text/css" href="css/sidebar.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include> 
            <form>
                <div style="display: flex">
                    <div>
                    <jsp:include page="sidebar.jsp"></jsp:include>
                    </div>
                    <div style="margin-left: 50px ; margin-top: 20px;font-family: 'Segoe UI',Roboto,Helvetica,Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol'">
                        <h1 style="font-size: 30px;font-weight: 800;">Lộ trình học</h1> <br>
                        <a style="font-size:16px">Để bắt đầu một cách thuận lợi, bạn nên tập trung vào một lộ trình học. Ví dụ: Để đi làm với vị trí "Lập trình viên Front-end"</a>
                        <p style="font-size:16px">bạn nên tập trung vào lộ trình "Front-end".</p>
                        <div style="margin-top: 60px;width: 950px;display: grid;
                             grid-template-columns: 1fr 1fr; /* Chia thành 2 cột bằng nhau */grid-gap: 23px">
                        <c:forEach items="${listRouteType}" var="i">

                            <div style=";height: 250px;border-color: black;border-radius: 16px;border:2px solid #e8e8e8;">
                                <div style="display: flex">
                                    <div style="height: 100px">
                                        <h1 style="margin-top: 25px;margin-left: 20px;font-weight: 800;">${i.name}</h1> <br>
                                        <p style="width: 290px;margin-left: 20px;font-size: 15px">
                                            ${i.description1}
                                        </p>
                                    </div>
                                    <a >
                                        <img src="${i.image}" style="border-radius: 50% ; width: 110px;height: 110px;border-color: orange;border:4px solid orangered;margin-left: 11px;margin-top: 20px;cursor: pointer">    
                                    </a>
                                </div>
                                <div onclick="window.location = 'detailLearnPath?id=${i.id}'">
                                    <button style="background-color: #f05123;;width: 110px;height: 36px;margin-top: 54px;border-radius: 30px;font-size: 15px;text-align: center;margin-left: 20px;border-width: 0px;font-weight: 600;cursor: pointer" > 
                                        <a href="detailLearnPath?id=${i.id} " style="color: #ffffff;text-decoration: none;">
                                            Xem chi tiét
                                        </a>
                                    </button>
                                </div>

                            </div>
                        </c:forEach>
                        </div>
                        <!--                            
                                                    <div style="width: 450px;height: 250px;border-color: black;border-radius: 16px;border:2px solid #e8e8e8 ; margin-left: 23px">
                                                        <div style="display: flex">
                                                            <div style="height: 100px">
                                                                <h1 style="margin-top: 25px;margin-left: 20px;font-weight: 800;">Lộ trình học Back-end</h1> <br>
                                                                <p style="width: 290px;margin-left: 20px;font-size: 13px">
                                                                    Trái với Front-end thì lập trình viên Back-end là người làm việc với dữ liệu, công việc thường nặng tính logic hơn. Chúng ta sẽ cùng tìm hiểu thêm về lộ trình học Back-end nhé.
                                                                </p>
                                                            </div>
                                                            <a >
                                                                <img src="https://files.fullstack.edu.vn/f8-prod/learning-paths/3/63b4641535b16.png" style="border-radius: 50% ; width: 110px;height: 110px;border-color: orange;border:4px solid orangered;margin-left: 11px;margin-top: 20px;cursor: pointer">    
                                                            </a>
                                                        </div>
                                                        <div>
                                                            <button style="background-color: #f05123;color: #ffffff;width: 105px;height: 36px;margin-top: 70px;border-radius: 30px;font-size: 13px;text-align: center;margin-left: 20px;border-width: 0px;font-weight: 600;cursor: pointer">
                                                                Xem chi tiết
                                                            </button>
                                                        </div>
                                                    </div>-->
                

                    <div style="display: flex;margin-top: 70px">
                        <div style="width: 400px;height: 250px;margin-top: 100px">
                            <h1 style="font-size: 25px;font-weight: 800;">Tham gia cộng đồng học viên F8</h1>
                            <h1 style="font-size: 25px;font-weight: 800;margin-top: 15px;margin-bottom: 10px">trên Facebook</h1> <br>
                            <a style="font-size: 16px">Hàng nghìn người khác đang học lộ trình giống như bạn. Hãy tham gia hỏi đáp, chia sẻ và hỗ trợ nhau trong quá trình học nhé.</a>
                            <div>
                                <!--                    <div style="width: 140px;height: 36px;margin-top: 50px;display: flex;border-radius: 30px;justify-content: center;font-size: 13px;text-align: center;font-weight: 600;border: 2px solid black" onclick="joinFb()" >
                                <span>Tham gia nhóm</span>
                                
                                                        </div>-->
                                <div style="width: 140px;height: 36px;display: flex;
                                     justify-content: center;
                                     align-items: center;border: 2px solid black;margin-top: 50px;border-radius: 30px;font-size: 15px;font-weight: 600;cursor: pointer" onclick="joinFb()">
                                    Tham gia nhóm
                                </div>
                            </div>
                        </div>
                        <a>
                            <img src="https://fullstack.edu.vn/static/media/fb-group-cards.4bd525b1b8baf7b1e5a2.png" style="width: 420px;height: 420px;margin-left: 500px" >

                        </a>
                    </div>
                </div>
            </div>
        </form>
        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            function joinFb() {
                window.location.href = "https://www.facebook.com/groups/f8official";
            }
        </script>
    </body>
</html>
