<%-- 
    Document   : front-end-development
    Created on : May 31, 2023, 12:05:02 AM
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
                        <div style="width: 840px">
                            <h1 style="font-size: 28px;font-weight: 800">${routeType.name}</h1 <br> <br> <br>
                            <a style="font-size: 1.5rem">${routeType.description2}</a><br><br>
<!--                            <p style="font-size: 1.5rem">Tại Việt Nam,
                                <strong>
                                    <a href="https://fullstack.edu.vn/external-url?continue=https%3A%2F%2Fbit.ly%2F3p40c2D" style="color: red">
                                        lương trung bình 
                                    </a>
                                </strong> 
                                cho lập trình viên front-end vào khoảng
                                <strong style="font-weight: 700">
                                    16.000.000đ 
                                </strong>

                                / tháng.</p>         <br>
                            <p style="font-size: 1.5rem">Dưới đây là các khóa học F8 đã tạo ra dành cho bất cứ ai theo đuổi sự nghiệp trở thành một lập trình viên Front-end.</p><br>
                            <div style="font-size: 1.5rem;border-left: 3px solid red;padding: 5px 0 5px 20px">Các khóa học có thể chưa đầy đủ, F8 vẫn đang nỗ lực hoàn thiện trong thời gian sớm nhất.</div>-->
                        <c:set var="count" value="1"></c:set>
                        <c:forEach items="${listLearningPathWrap}" var="l">
                            <div style="margin-top: 70px">
                                <h1 style="font-size: 25px;font-weight: 800">
                                    ${count}. ${l.routeTypeItem.name}<c:set var="count" value="${count+1}"></c:set>
                                    </h1>  <br> <br>
                                    <a style="font-size: 1.4rem;line-height: 1.6">
                                    ${l.routeTypeItem.description}
                                </a> 
                                <c:forEach items="${l.courses}" var="c">
                                    <div style="border: 2px solid #e8e8e8;height: 180px ;border-radius: 15px;margin-top: 20px">
                                        <div style="display: flex">
                                            <img src="${c.image}" style="width: 220px ; height: 120px ; border-radius: 15px;margin: 20px" >
                                            <div style="margin: 20px 10px 10px">
                                                <h1 style="font-size: 20px;font-weight: 700">${c.title}</h1> 
                                                <p style="color: red;font-size: 1.6rem;font-weight: 600;margin-top: 10px"><c:if test="${c.price<=0}">Miễn phí</c:if><c:if test="${c.price>0}">${c.price}</c:if></p><br>
                                                <a style="font-size: 1.4rem;line-height: 1.6;">
                                                    ${c.detailCourseDes}
                                                </a> <br>
                                                <button style="background-color: #f05123;color: #ffffff;width: 120px;height: 36px;border-radius: 30px;font-size: 13px;text-align: center;margin-top: 20px;border-width: 0px;font-weight: 600;cursor: pointer;margin-top: 10px">
                                                    Xem khóa học 
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </c:forEach>


                        <!--                        <div style="margin-top: 60px">
                                                    <h1 style="font-size: 25px;font-weight: 800">
                                                        2. HTML và CSS
                                                    </h1>  <br><br>
                                                    <a style="font-size: 1.4rem;line-height: 1.6;">
                                                        Để học web Front-end chúng ta luôn bắt đầu với ngôn ngữ HTML và CSS, đây là 2 ngôn ngữ có mặt trong mọi website trên internet. Trong khóa học này F8 sẽ chia sẻ từ những kiến thức cơ bản nhất. Sau khóa học này bạn sẽ tự làm được 2 giao diện websites là The Band và Shopee.
                                                    </a> 
                                                    <div style="border: 2px solid #e8e8e8;height: 360px ;border-radius: 15px;margin-top: 20px">
                                                        <div style="display: flex">
                                                            <img src="https://files.fullstack.edu.vn/f8-prod/courses/15/62f13d2424a47.png" style="width: 220px ; height: 120px ; border-radius: 15px;margin: 20px" >
                                                            <img src="https://fullstack.edu.vn/static/media/crown_icon.3e4800f7485935ab6ea312a7080a85fe.svg" style="width: 22px;height: 22px">
                                                            <div style="margin: 20px 10px 10px">
                                                                <h1 style="font-size: 20px;font-weight: 700">HTML CSS Pro</h1> 
                                                                <div style="margin-top: 10px;font-size: 1.6rem">
                                                                    <span style="text-decoration: line-through;margin-right: 8px">2.499.000đ</span>
                                                                    <span style="color: red;font-weight: 600">1.299.000đ</span>
                                                                </div> <br>
                                                                <a style="font-size: 1.4rem;line-height: 1.6;">
                                                                    Từ cơ bản tới chuyên sâu, thực hành 8 dự án, hàng trăm bài tập, trang hỏi đáp riêng, cấp chứng chỉ sau khóa học và mua một lần học mãi mãi.
                                                                </a> <br>
                                                                <button style="background-color: #f05123;color: #ffffff;width: 120px;height: 36px;border-radius: 30px;font-size: 13px;text-align: center;margin-top: 20px;border-width: 0px;font-weight: 600;cursor: pointer;margin-top: 10px">
                                                                    Xem khóa học 
                                                                </button>
                                                            </div>
                                                        </div>
                                                        <div style="font-size: 1.4rem;font-weight: 600;margin-left: 24px">Khóa học liên quan</div>
                                                        <div style="display: flex;margin-left: 20px">
                                                            <img src="https://files.fullstack.edu.vn/f8-prod/courses/2.png" style="width: 126px ; height: 71px ; border-radius: 15px;margin: 20px" >
                                                            <div style="margin-top: 20px">
                                                                <h1 style="font-size: 14px;font-weight: 700">
                                                                    HTML CSS từ Zero đến Hero
                                                                </h1> 
                                                                <p style="color: red;font-size: 12px;font-weight: 600;margin-top: 5px;margin-bottom: 8px">Miễn phí</p>
                                                                <a style="font-size: 12px">
                                                                    Trong khóa này chúng ta sẽ cùng nhau xây dựng giao diện 2 trang web là The Band & Shopee.
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div style="border: 2px solid #e8e8e8;height: 180px ;border-radius: 15px;margin-top: 17px">
                                                        <div style="display: flex">
                                                            <img src="https://files.fullstack.edu.vn/f8-prod/courses/3.png" style="width: 220px ; height: 120px ; border-radius: 15px;margin: 20px" >
                                                            <div style="margin: 20px 10px 10px">
                                                                <h1 style="font-size: 20px;font-weight: 700">Responsive Với Grid System</h1> 
                                                                <p style="color: red;font-size: 1.6rem;font-weight: 600;margin-top: 10px">Miễn phí</p><br>
                                                                <a style="font-size: 1.4rem;line-height: 1.6;">
                                                                    Trong khóa này chúng ta sẽ học về cách xây dựng giao diện web responsive với Grid System, tương tự Bootstrap 4.
                                                                </a> <br>
                                                                <button style="background-color: #f05123;color: #ffffff;width: 120px;height: 36px;border-radius: 30px;font-size: 13px;text-align: center;margin-top: 20px;border-width: 0px;font-weight: 600;cursor: pointer;margin-top: 10px">
                                                                    Xem khóa học 
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                        
                        
                                                <div style="margin-top: 60px">
                                                    <h1 style="font-size: 25px;font-weight: 800">
                                                        3. JavaScript
                                                    </h1>  <br><br>
                                                    <a style="font-size: 1.4rem;line-height: 1.6;">
                                                        Với HTML, CSS bạn mới chỉ xây dựng được các websites tĩnh, chỉ bao gồm phần giao diện và gần như chưa có xử lý tương tác gì. Để thêm nhiều chức năng phong phú và tăng tính tương tác cho website bạn cần học Javascript.
                                                    </a> 
                                                    <div style="border: 2px solid #e8e8e8;height: 200px ;border-radius: 15px;margin-top: 17px">
                                                        <div style="display: flex">
                                                            <img src="https://files.fullstack.edu.vn/f8-prod/courses/1.png" style="width: 220px ; height: 120px ; border-radius: 15px;margin: 20px" >
                                                            <div style="margin: 20px 10px 10px">
                                                                <h1 style="font-size: 20px;font-weight: 700">Lập Trình JavaScript Cơ Bản</h1> 
                                                                <p style="color: red;font-size: 1.6rem;font-weight: 600;margin-top: 10px">Miễn phí</p><br>
                                                                <a style="font-size: 1.4rem;line-height: 1.6;">
                                                                    Học Javascript cơ bản phù hợp cho người chưa từng học lập trình. Với hơn 100 bài học và có bài tập thực hành sau mỗi bài học.
                                                                </a> <br>
                                                                <button style="background-color: #f05123;color: #ffffff;width: 120px;height: 36px;border-radius: 30px;font-size: 13px;text-align: center;margin-top: 20px;border-width: 0px;font-weight: 600;cursor: pointer;margin-top: 10px">
                                                                    Xem khóa học 
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div style="border: 2px solid #e8e8e8;height: 200px ;border-radius: 15px;margin-top: 17px">
                                                        <div style="display: flex">
                                                            <img src="https://files.fullstack.edu.vn/f8-prod/courses/12.png" style="width: 220px ; height: 120px ; border-radius: 15px;margin: 20px" >
                                                            <div style="margin: 20px 10px 10px">
                                                                <h1 style="font-size: 20px;font-weight: 700">Lập Trình JavaScript Nâng Cao</h1> 
                                                                <p style="color: red;font-size: 1.6rem;font-weight: 600;margin-top: 10px">Miễn phí</p><br>
                                                                <a style="font-size: 1.4rem;line-height: 1.6;">
                                                                    Hiểu sâu hơn về cách Javascript hoạt động, tìm hiểu về IIFE, closure, reference types, this keyword, bind, call, apply, prototype, ...
                                                                </a> <br>
                                                                <button style="background-color: #f05123;color: #ffffff;width: 120px;height: 36px;border-radius: 30px;font-size: 13px;text-align: center;margin-top: 20px;border-width: 0px;font-weight: 600;cursor: pointer;margin-top: 10px">
                                                                    Xem khóa học 
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                        
                                                <div style="margin-top: 60px">
                                                    <h1 style="font-size: 25px;font-weight: 800">
                                                        4. Sử dụng Ubuntu/Linux
                                                    </h1>  <br><br>
                                                    <a style="font-size: 1.4rem;line-height: 1.6;">
                                                        Cách làm việc với hệ điều hành Ubuntu/Linux qua Windows Terminal & WSL. Khi đi làm, nhiều trường hợp bạn cần nắm vững các dòng lệnh cơ bản của Ubuntu/Linux.
                                                    </a> 
                                                    <div style="border: 2px solid #e8e8e8;height: 200px ;border-radius: 15px;margin-top: 17px">
                                                        <div style="display: flex">
                                                            <img src="https://files.fullstack.edu.vn/f8-prod/courses/14/624faac11d109.png" style="width: 220px ; height: 120px ; border-radius: 15px;margin: 20px" >
                                                            <div style="margin: 20px 10px 10px">
                                                                <h1 style="font-size: 20px;font-weight: 700">Làm việc với Terminal & Ubuntu</h1> 
                                                                <p style="color: red;font-size: 1.6rem;font-weight: 600;margin-top: 10px">Miễn phí</p><br>
                                                                <a style="font-size: 1.4rem;line-height: 1.6;">
                                                                    Sở hữu một Terminal hiện đại, mạnh mẽ trong tùy biến và học cách làm việc với Ubuntu là một bước quan trọng trên con đường trở thành một Web Developer.
                                                                </a> <br>
                                                                <button style="background-color: #f05123;color: #ffffff;width: 120px;height: 36px;border-radius: 30px;font-size: 13px;text-align: center;margin-top: 20px;border-width: 0px;font-weight: 600;cursor: pointer;margin-top: 10px">
                                                                    Xem khóa học 
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                        
                                                <div style="margin-top: 60px">
                                                    <h1 style="font-size: 25px;font-weight: 800">
                                                        5. Libraries and Frameworks
                                                    </h1>  <br><br>
                                                    <a style="font-size: 1.4rem;line-height: 1.6;">
                                                        Một websites hay ứng dụng hiện đại rất phức tạp, chỉ sử dụng HTML, CSS, Javascript theo cách code thuần (tự code từ đầu tới cuối) sẽ rất khó khăn. Vì vậy các Libraries, Frameworks ra đời nhằm đơn giản hóa, tiết kiệm chi phí và thời gian để hoàn thành một sản phẩm website hoặc ứng dụng mobile.
                                                    </a> 
                                                    <div style="border: 2px solid #e8e8e8;height: 200px ;border-radius: 15px;margin-top: 17px">
                                                        <div style="display: flex">
                                                            <img src="https://files.fullstack.edu.vn/f8-prod/courses/13/13.png" style="width: 220px ; height: 120px ; border-radius: 15px;margin: 20px" >
                                                            <div style="margin: 20px 10px 10px">
                                                                <h1 style="font-size: 20px;font-weight: 700">Xây Dựng Website với ReactJS</h1> 
                                                                <p style="color: red;font-size: 1.6rem;font-weight: 600;margin-top: 10px">Miễn phí</p><br>
                                                                <a style="font-size: 1.4rem;line-height: 1.6;">
                                                                    Khóa học ReactJS từ cơ bản tới nâng cao, kết quả của khóa học này là bạn có thể làm hầu hết các dự án thường gặp với ReactJS. Cuối khóa học này bạn sẽ sở hữu một dự án...
                                                                </a> <br>
                                                                <button style="background-color: #f05123;color: #ffffff;width: 120px;height: 36px;border-radius: 30px;font-size: 13px;text-align: center;margin-top: 20px;border-width: 0px;font-weight: 600;cursor: pointer;margin-top: 10px">
                                                                    Xem khóa học 
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                        
                                                <div style="margin-top: 60px">
                                                    <h1 style="font-size: 25px;font-weight: 800">
                                                        Các khóa học Pro tại F8
                                                        <img src="https://fullstack.edu.vn/static/media/crown_icon.3e4800f7485935ab6ea312a7080a85fe.svg" style="width: 22px;height: 22px">
                                                    </h1>
                                                    <br><br>
                                                    <a style="font-size: 1.4rem;line-height: 1.6;">
                                                        Các khóa học Pro được thiết kế đầy đủ chi tiết, bài bản. Với đa dạng các loại bài học và bài tập thực hành đi kèm, code luôn ở trang web. Cuối khóa học sẽ được thực hành từ 8 - 10 dự án thực chiến với cấp độ từ dễ đến khó.
                                                    </a> 
                                                    <div style="display: flex">
                                                        <div >
                                                            <img src="https://files.fullstack.edu.vn/f8-prod/courses/15/62f13d2424a47.png" style="width: 220px ; height: 120px ; border-radius: 15px;margin: 20px" >
                                                            <h1 style="font-size: 20px;font-weight: 700;margin-left: 20px">HTML CSS Pro</h1> 
                                                            <div style="margin-top: 10px;font-size: 1.6rem;margin-left: 20px">
                                                                <span style="text-decoration: line-through;margin-right: 8px">2.499.000đ</span>
                                                                <span style="color: red;font-weight: 600">1.299.000đ</span>
                                                            </div>
                                                        </div>
                                                        <div>
                                                            <img src="https://files.fullstack.edu.vn/f8-prod/courses/19/62f13cb607b4b.png" style="width: 220px ; height: 120px ; border-radius: 15px;margin: 20px" >
                                                            <h1 style="font-size: 20px;font-weight: 700;margin-left: 20px">JavaScript Pro</h1> 
                                                            <div style="margin-top: 10px;font-size: 1.6rem;margin-left: 20px">
                                                                <span style="color: red;font-weight: 600">Dự kiến: 05/2023</span>
                                                            </div>
                                                        </div>
                                                        <div>
                                                            <img src="https://files.fullstack.edu.vn/f8-prod/courses/20/62f13dded314e.png" style="width: 220px ; height: 120px ; border-radius: 15px;margin: 20px" >
                                                            <h1 style="font-size: 20px;font-weight: 700;margin-left: 20px">ReactJS Pro</h1> 
                                                            <div style="margin-top: 10px;font-size: 1.6rem;margin-left: 20px">
                                                                <span style="color: red;font-weight: 600">Dự kiến: 08/2023</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                        -->


                    </div>
                </div>
            </div>
        </form>
        <jsp:include page="footer.jsp"></jsp:include> 
    </body>
</html>
