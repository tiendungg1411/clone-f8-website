<%-- 
    Document   : generalManagerment
    Created on : Jun 12, 2023, 5:53:37 PM
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-9LFz6Y0bOyD2kOUzcBv8ymPdVvkf7LT9wAjT6sS3lVX0eZK/oxI8j/4x39rHJNQHR7PXBj47bLskoM/VfGYSNw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <style>
            .all{
                border: 1px solid black;
                width: 1068px;
                height: 600px;
                background-color: #ffffff ;
                border-radius: 2%;
                margin-top: 100px;
            }
            .avatar{

                border-radius: 10%;
                width: 45px;
                height: 45px;
                cursor: pointer ;
                margin-top: 20px;
                margin-left: 500px;
            }
            .container {
                display: flex;
                justify-content: space-between;
            }

            .item {
                width: 25%;
                height: 400px;
                background-color: #b6c2bb8f;
                margin-right: 23px;
                border: 2px solid none;
                padding: 20px;
                transition: transform 0.2s ease-in-out;
                cursor: pointer;
            }

            .item:last-child {
                margin-right: 20px;
            }
            .item:first-child {
                margin-left: 20px;
            }


            .item:hover,
            .item:focus {
                transform: translateY(-10px);
            }
        </style>
    </head>
    <body style="background-image: url(https://accounts.fullstack.edu.vn/static/media/f8_bg_auth_1920.b517075e98f3051de678.png);">
        <jsp:include page="header.jsp"></jsp:include> 
            <form>
                <div style="display: flex ;
                     justify-content: center;
                     align-items: center;
                     height: 600px">
                    <div class="all" >
                        <div >
                            <a href="/g4/home" >
                                <img src="https://accounts.fullstack.edu.vn/assets/icon/f8_icon.png" alt="Avatar"  class="avatar" >
                            </a>
                            <h1 class="h1" style="text-align: center;font-size: 35px;margin-top: 10px">Quản lí chung</h1>  
                        </div> <br><br>
                        <div class="container">
                            <div class="item" style="font-size: 15px" onclick="course()">
                                <h2 style="text-align: center;margin-top: 3px">Khóa học</h2><br>
                                <p>
                                    Tạo một khóa học là quá trình thiết kế và triển khai một chương trình giảng dạy về một chủ đề cụ thể. Khóa học có thể được thiết kế để đào tạo người học về các kỹ năng cụ thể, kiến thức mới hoặc để cung cấp một sự giới thiệu về một lĩnh vực hay chủ đề nào đó.
                                </p>
                                <i class="fas fa-arrow-right" style="margin-left: 57px;margin-top: 60px;font-size: 30px;color: #8d8181"></i>
                            </div>
                            <div class="item" style="font-size: 15px" onclick="window.location = 'listQuiz'">
                                <h2 style="text-align: center;margin-top: 3px">Câu đố</h2><br>
                                <p>
                                    Câu đố là một câu hoặc tình huống gợi ý, thường chứa một vấn đề khó khăn hoặc một sự mơ hồ, yêu cầu người nghe hoặc đọc suy nghĩ và tìm ra lời giải hoặc câu trả lời thông qua việc suy luận logic, trí tưởng tượng hoặc kiến thức cụ thể. 
                                </p>
                                <i class="fas fa-arrow-right" style="margin-left: 57px;margin-top: 60px;font-size: 30px;color: #8d8181"></i>
                            </div>
                            <div class="item" style="font-size: 15px" onclick="window.location = 'listQuestion'">
                                <h2 style="text-align: center;margin-top: 3px">Câu hỏi</h2><br>
                                <p>
                                    Tạo câu hỏi là một kỹ năng rất quan trọng trong việc giao tiếp và học tập. Để tạo ra những câu hỏi hiệu quả, chúng ta cần phải có kiến thức đầy đủ về chủ đề cần hỏi, đặt câu hỏi dễ hiểu và cần thiết để thu thập thông tin hoặc giải quyết vấn đề.
                                </p>
                                <i class="fas fa-arrow-right" style="margin-left: 57px;margin-top: 60px;font-size: 30px;color: #8d8181"></i>
                            </div>
                            <div class="item" style="font-size: 15px" onclick="listRouteCourse()">
                                <h2 style="text-align: center;margin-top: 3px">Lộ trình học</h2><br>
                                <p>
                                    Một lộ trình học là một kế hoạch cụ thể để đạt được mục tiêu học tập. Nó bao gồm các bước và chỉ dẫn cụ thể về cách tiến hành học tập, bao gồm những kiến thức và kỹ năng mà bạn cần phải học để đạt được mục tiêu của mình.
                                </p>
                                <i class="fas fa-arrow-right" style="margin-left: 57px;margin-top: 70px;font-size: 30px;color: #8d8181"></i>

                            </div>
                            <div class="item" style="font-size: 15px" onclick="listRouteCourseItem()">
                                <h2 style="text-align: center;line-height: 1.1">Mục lục của lộ trình học</h2><br>
                                <p>
                                    Mục lục của một lộ trình học là danh sách các chủ đề và nội dung được bao gồm trong lộ trình học. Nó cung cấp cho người học một cái nhìn tổng quan về các chủ đề phải học và giúp họ dễ dàng tìm kiếm thông tin cần thiết để học tập.
                                </p>    
                                <i class="fas fa-arrow-right" style="margin-left: 57px;margin-top: 50px;font-size: 30px;color: #8d8181"></i>

                            </div>
                        </div>
                    </div>
                </div>
                <script>
            function course() {
                window.location = "course";
            }
            function listRouteCourseItem() {
                window.location.href = "listRouteCourseItem";
            }
            function listRouteCourse() {
                window.location = "listRouteCourse";
            }
        </script>
            </form>
            <br><br><br><br>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
