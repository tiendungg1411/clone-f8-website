<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="notification.css" type="text/css" />
        <link href="${pageContext.request.contextPath}/css/notification.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/notification.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>

    <body>
        <section class="back d-flex flex-column align-items-center">
            <div class="notification-table-wrapper">
                <h3>
                    Thông báo
                    </h6>
                    <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active rounded-pill" id="pills-home-tab" data-bs-toggle="pill"
                                    data-bs-target="#pills-home" type="button" role="tab" aria-controls="pills-home"
                                    aria-selected="true">Tất cả</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link rounded-pill" id="pills-profile-tab" data-bs-toggle="pill"
                                    data-bs-target="#pills-profile" type="button" role="tab" aria-controls="pills-profile"
                                    aria-selected="false">Chưa đọc</button>
                        </li>
                    </ul>
                    <div class="tab-content" id="pills-tabContent">
                        <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                             aria-labelledby="pills-home-tab">
                            <h6>
                                Mới
                            </h6>
                            <c:forEach items="${requestScope.list}" var="noti">
                                <form id="frm-all"
                                      action="notification" method="post">
                                    <input type="hidden" value="${noti.id}" name="id"/>
                                    <div class="d-flex flex-column gap-3" onclick="submitForm('frm-all')">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="d-flex gap-2 align-items-center">
                                                <img src="${noti.fromAccount.avatar}"
                                                     class="rounded-circle" style="width: 50px; height: 50px; object-fit: cover;" />
                                                <div>
                                                    <p class="mb-0">
                                                        <span style="font-weight: bold;">${noti.fromAccount.userName}</span>
                                                        ${noti.content}
                                                    </p>
                                                    <p class="mb-0" style="color: #005eff;">23 phút trước</p>
                                                </div>
                                            </div>
                                            <i class="fa-solid fa-circle" style="color: #ffffff;"></i>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </div>
                        <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                            <c:forEach items="${requestScope.list}" var="noti">
                                <c:if test="${noti.isRead eq false}">
                                    <form action="notification" method="post" id="frm-notRead">
                                        <input type="hidden" value="${noti.id}" name="id"/>
                                        <div class="d-flex flex-column gap-3" onclick="submitForm('frm-notRead')">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <div class="d-flex gap-2 align-items-center">
                                                    <img src="${noti.fromAccount.avatar}"
                                                         class="rounded-circle" style="width: 50px; height: 50px; object-fit: cover;" />
                                                    <div>
                                                        <p class="mb-0">
                                                            <span style="font-weight: bold;">${noti.fromAccount.userName}</span>
                                                            ${noti.content}
                                                        </p>
                                                        <p class="mb-0" style="color: #005eff;">10 phút trước</p>
                                                    </div>
                                                </div>
                                                <i class="fa-solid fa-circle" style="color: #ffffff;"></i>
                                            </div>
                                        </div>
                                    </form>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
            </div>
        </section>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
        <script>
            function submitForm(formId) {
                // Get the form element
                var form = document.getElementById(formId);

                // Submit the form
                form.submit();
            }
        </script>
        <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    </body>

</html>
