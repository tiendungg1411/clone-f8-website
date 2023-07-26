<%-- 
    Document   : videoCourse
    Created on : Jun 10, 2023, 5:06:18 PM
    Author     : dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="../../css/learning.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" rel="stylesheet" />
    </head>
    <body>
        <div>
            <div class="sticky-top d-flex justify-content-between align-items-center ps-3 pe-3"
                 style="background-color: #29303b; height: 50px;">
                <div class="d-flex gap-3 align-items-center">
                    <i class="fa-solid fa-regular fa-arrow-left" style="color: #ffffff;" onclick="goHome()"></i>
                    <img style="width: 30px; height: 30px; border-radius: 8px; margin-left: 20px;"
                         src="https://fullstack.edu.vn/static/media/f8-icon.18cd71cfcfa33566a22b.png" alt="F8" onclick="goHome()">
                    <p class="mb-0 text-white" style="font-weight: 700; font-size: 14px;">
                        <!--                        HTML CSS từ zero đến Hero-->
                    </p>
                </div>
                <div class="d-flex gap-4 align-items-center text-white" style="font-weight: 300; font-size: 14px;">
                    <div>
                        ${total} Bài học
                    </div>
                    <!-- Click to see all notes -->
                    <div class="cursor-pointer" data-bs-toggle="modal" data-bs-target="#myModalNotes">
                        <i class="fa-solid fa-notes-medical"></i>
                        Ghi chú
                    </div>
                    <div>
                        <i class="fa-solid fa-question" style="color: #ffffff;"></i>
                        Hướng dẫn
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-between gap-1" style="height: calc(100vh - 100px);">
                <div class="w-100 overflow-x-hidden overflow-y-scroll position-relative">
                    <!-- Each video wrapper by id  -->
                    <div style="min-height: calc(100vh - 160px);">
                        <!-- Video wrapper -->
                        <div class="" style="height: 520px;">
                            <iframe width="100%" height="100%" src="${lesson.link}"
                                    frameborder="0" allowfullscreen></iframe>
                        </div>

                        <!-- Content wrapper  -->
                        <div class="py-4 px-5 row">
                            <div class="col-10">
                                <h4>${lesson.name}</h4>
                                <p class="mb-0" style="font-size: 13px;">
                                    Cập nhật ${lesson.date}
                                </p>
                            </div>
                        </div>

                        <!-- Other content wrapper -->
                        <div class="py-4 px-5">
                            ${lesson.content}
                        </div>
                        <p class="text-center mb-0"> · Powered by F8
                        </p>
                    </div>

                    <div onclick="window.location = 'qaOfLesson?id=${lesson.getId()}&name=${lesson.getName()}'" class="cursor-pointer d-flex position-sticky gap-2 align-items-center p-2 px-3 bg-white shadow-lg rounded-pill"
                         style="color: #f05123; bottom: 14px; left: auto; right: 20px; margin-left: auto; width: fit-content;">
                        <i class="fa-brands fa-rocketchat" style="color: #f05123"></i>
                        <p class="mb-0">Hỏi đáp</p>
                    </div>
                </div>

                <!-- Accordition nav right -->
                <div id="accordionExample" class="show list__course__wrapper overflow-y-scroll text-black"
                     style="width: 330px;">
                    <div class="p-3 sticky-top bg-white" style="font-weight: bold;">
                        Nội dung khóa học
                    </div>

                    <!-- List of Chapter -->
                    <div class="accordion">
                        <div class="">
                            <c:forEach items="${chapters}" var="c">
                                <h4 class="accordion-header" id="heading${c.id}">
                                    <div style="background-color: #dedfe060; color: black;" class="accordion-button"
                                         type="button" data-bs-toggle="collapse" data-bs-target="#collapse${c.id}"
                                         aria-expanded="true" aria-controls="collapse${c.id}">
                                        <div class="d-flex flex-column">
                                            <div>${c.name}</div>
                                            <p class="mb-0" style="font-size: 13px; font-weight: 300;">
                                                <!--                                            5/6 | 19:39-->
                                            </p>
                                        </div>
                                    </div>
                                </h4>
                                <c:forEach items="${lessons}" var="l">
                                    <c:if test="${l.chapterID == c.id}">
                                        <div id="collapse${c.id}" class="accordion-collapse collapse show" aria-labelledby="heading${c.id}"
                                             data-bs-parent="#accordionExample">
                                            <!-- List of lesson here  -->
                                            <c:if test="${l.type == 'video'}">
                                                <!-- Lesson video, not choose => hover effect and cursor effect: course__hover cursor-pointer  -->
                                                <li onclick="viewLesson(${l.id})"
                                                    class="accordion-body
                                                    <c:if test="${selectedID == l.id}">course__selected</c:if>
                                                    <c:if test="${selectedID != l.id}">course__hover cursor-pointer </c:if>
                                                        d-flex justify-content-between align-items-center gap-2">
                                                        <div style="font-size: 13px; font-weight: 300;">
                                                            <p class="mb-1">${l.name}</p>
                                                        <div style="font-size: 12px;">
                                                            <i class="fa-solid fa-circle-play" style="margin-right: 4px;"></i>
                                                            03:15
                                                        </div>
                                                    </div>
                                                    <i class="fa-solid fa-circle-check" style="color: #5db85c;"></i>
                                                </li>
                                                <form id="frm-submit-${l.id}" action="videoCourse" method="post">
                                                    <input type="hidden" name="action" value="video"/>
                                                    <input type="hidden" name="lessonID" value="${l.id}"/>
                                                </form>
                                            </c:if>
                                            <c:if test="${l.type == 'practice'}">
                                                <!-- Lesson coming soon, if else to use, different id to show left detail -->
                                                <li onclick="window.location = 'doingQuestion?courseID=${cid}&lessonID=${l.id}'"
                                                    class="accordion-body course__hover cursor-pointer
                                                    d-flex justify-content-between align-items-center gap-2">
                                                    <div style="font-size: 13px; font-weight: 300;">
                                                        <p class="mb-1">${l.name}</p>
                                                        <div style="font-size: 12px;">
                                                            <i class="fa-regular fa-file-audio" style="margin-right: 4px;"></i>
                                                            00:00
                                                        </div>
                                                    </div>
                                                    <i class="fa-solid fa-circle-check" style="color: #5db85c;"></i>
                                                </li>
                                            </c:if>
                                            <c:if test="${l.type == 'feedback'}">
                                                <!-- Lesson coming soon, if else to use, different id to show left detail -->
                                                <li onclick="window.location = 'feedbackLessonController?courseID=${cid}&lessonID=${l.id}'"
                                                    class="accordion-body course__hover cursor-pointer
                                                    d-flex justify-content-between align-items-center gap-2">
                                                    <div style="font-size: 13px; font-weight: 300;">
                                                        <p class="mb-1">${l.name}</p>
                                                        <div style="font-size: 12px;">
                                                            <i class="fa fa-commenting-o" style="margin-right: 4px;"></i>
                                                            1:00
                                                        </div>
                                                    </div>
                                                    <i class="fa-solid fa-circle-check" style="color: #5db85c;"></i>
                                                </li>
                                                <form id="frm-submit-${l.id}" action="videoCourse" method="post">
                                                    <input type="hidden" name="action" value="video"/>
                                                    <input type="hidden" name="lessonID" value="${l.id}"/>
                                                </form>
                                            </c:if>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>

                        </div>
                    </div>

                </div>
            </div>
            <div class="fixed-bottom d-flex justify-content-between align-items-center"
                 style="background-color: #f0f0f0; height: 50px;">
                <div class="d-flex gap-3 justify-content-center w-100"  onclick="previous()">
                    <div class="px-3 py-2" style="color: #404040;">
                        <i class="fa-solid fa-chevron-left" style="margin-right: 8px;"></i>
                        Bài trước
                    </div>
                    <form id="frm-submit-pre" action="videoCourse" method="post">
                        <input type="hidden" name="action" value="pre"/>
                        <input type="hidden" name="lessonID" value="${lesson.id}"/>
                    </form>
                    <div class="px-3 py-2 rounded-2" style="border: 1px solid #f05123; color: #f05123;" onclick="next()">Bài tiếp theo
                        <i class="fa-solid fa-chevron-right" style="margin-left: 8px;"></i>
                    </div>
                    <form id="frm-submit-next" action="videoCourse" method="post">
                        <input type="hidden" name="action" value="next"/>
                        <input type="hidden" name="lessonID" value="${lesson.id}"/>
                    </form>
                </div>
                <div data-bs-toggle="collapse" data-bs-target="#accordionExample" aria-controls="accordionExample"
                     aria-expanded="false" aria-label="Toggle navigation"
                     class="d-flex justify-content-end gap-1 align-items-center px-3 hover__effect" id="filter__navbar"
                     style="width: 30%; cursor: pointer;">
                    <div style="color: #1c1d1f; font-weight: 400; font-size: 16px; font-weight: 700; margin-right: 8px;">
                        ${lesson.id}. ${lesson.name}
                    </div>
                    <i class="fa-solid fa-bars"></i>
                </div>
            </div>

        </div>
        <!-- Modal right to see all notes  -->
        <div class="modal right fade" id="myModalNotes" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="p-3 d-flex justify-content-between align-items-center">
                        <h4 class="modal-title" id="myModalLabel2">Ghi chú của tôi</h4>
                        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" id="noteList">
                        <c:forEach var="n" items="${notes}">
                            <!-- List of notes wrapper  -->
                            <div id="note_wrapper" class="mt-4 mb-5 px-3">
                                <div class="d-flex justify-content-between gap-3 align-items-center">
                                    <div class="d-flex gap-2">
                                        <div class="text-white rounded-pill mb-0 px-2"
                                             style="background-color: #f05123; font-size: 13px; font-weight: 600; height: 24px;">
                                            ${n.date}
                                        </div>
                                        <div style="color: #f05123;">
                                            <span style="color: #1c1d1f; font-size: 14px; font-weight: 600;">

                                            </span>
                                        </div>
                                    </div>
                                    <div class="d-flex gap-2">
                                        <!-- Custom delete button here  -->
                                        <div onclick="deleteNote(${n.id})">
                                            <i class="fa-solid fa-trash"></i>
                                        </div>
                                        <form id="frm-submit-delete-${n.id}" action="videoCourse" method="post">
                                            <input type="hidden" name="action" value="deleteNote"/>
                                            <input type="hidden" name="noteID" value="${n.id}"/>
                                        </form>
                                    </div>
                                </div>
                                <div id="note_1" class="p-3 mt-2 mb-3 rounded-2"
                                     style="background-color: #f7f8fa; font-weight: 300;">
                                    ${n.detail}
                                </div>
                                <!--                                <button class="btn btn-success">Chỉnh sửa</button>
                                                                <button class="btn btn-info">Lưu</button>-->
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal right to see all notes  -->
        <div class="modal right fade" id="createNote" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="p-3 d-flex justify-content-between align-items-center">
                        <h4 class="modal-title" id="myModalLabel2">Ghi chú của tôi</h4>
                        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" id="noteList">
                        <!-- List of notes wrapper  -->
                        <div id="note_wrapper" class="mt-4 mb-5 px-3 text-center">
                            <div class="d-flex justify-content-between gap-3 align-items-center">
                                <div class="d-flex gap-2">
                                    <div style="color: #f05123;">
                                        Tạo ghi chú mới
                                        <span style="color: #1c1d1f; font-size: 14px; font-weight: 600;">

                                        </span>
                                    </div>
                                </div>
                            </div>
                            <form action="videoCourse" method="post">
                                <input type="hidden" value="${lesson.id}" name="lessonID"/>
                                <input type="hidden" value="createNote" name="action"/>
                                <textarea class="form-control" placeholder="Ghi chú" name="newNote" id="floatingTextarea2" style="height: 100px"></textarea>
                                <button class="btn btn-success mt-3" type="submit">Thêm mới</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            const note = document.getElementById('note_wrapper');

            note.addEventListener('click', (event) => {
                const button = event.target;
                const li = button.parentNode;
                const paragraph = li.children[1];
                console.log("LI: ", li.children[1]);
                if (button.textContent === 'Chỉnh sửa') {
                    paragraph.contentEditable = true;
                    paragraph.style.backgroundColor = "#dddbdb";
                    paragraph.focus();
                    // Focus vào kết thúc đoạn văn bản
                    const range = document.createRange();
                    range.selectNodeContents(paragraph);
                    range.collapse(false);
                    const selection = window.getSelection();
                    selection.removeAllRanges();
                    selection.addRange(range);
                }
                if (button.textContent === 'Lưu') {
                    paragraph.contentEditable = false;
                    paragraph.style.backgroundColor = "#f7f8fa";
                    // Submit content of editable here 
                }
            });

            function viewLesson(id) {
                document.getElementById('frm-submit-' + id).submit();
            }

            function next() {
                document.getElementById('frm-submit-next').submit();
            }

            function previous() {
                document.getElementById('frm-submit-pre').submit();
            }

            function goHome() {
                window.location.href = "home";
            }

            function deleteNote(id) {
                document.getElementById('frm-submit-delete-' + id).submit();
            }
        </script>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    </body>
</html>
