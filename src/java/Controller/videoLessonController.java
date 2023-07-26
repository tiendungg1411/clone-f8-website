/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ChapterDAO;
import DAO.CourseAccountDAO;
import DAO.CourseDetailDAO;
import DAO.LessonDAO;
import DAO.NoteDAO;
import Model.Account;
import Model.Chapter;
import Model.CourseDetail;
import Model.Lesson;
import Model.Note;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class videoLessonController extends HttpServlet {

    String courseID = "1";
    int selectedID = 1;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VideoCourse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VideoCourse at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        NoteDAO nDao = new NoteDAO();
        String cID = request.getParameter("courseID");
        if (cID != null) {
            courseID = cID;
            LessonDAO lDao1 = new LessonDAO();
            selectedID = lDao1.getMinLessonIdByCourseID(courseID);
        }

        LessonDAO lDao = new LessonDAO();
        ArrayList<Lesson> lessons = lDao.getAllLesson(Integer.parseInt(courseID));

        int total = lDao.getTotalLesson(Integer.parseInt(courseID));

        ChapterDAO cDao = new ChapterDAO();
        ArrayList<Chapter> chapters = lDao.getAllChapterByCID(Integer.parseInt(courseID));

        Lesson lesson = lDao.getLessonByID(selectedID);

        ArrayList<Note> notes = nDao.getAll(selectedID, Integer.parseInt(acc.getId()));
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            request.setAttribute("selectedID", request.getParameter("selectedID"));
        } else {
            request.setAttribute("selectedID", selectedID);
        }
        request.setAttribute("cid", courseID);

        request.setAttribute("total", total);
        request.setAttribute("notes", notes);
        request.setAttribute("lesson", lesson);
        request.setAttribute("lessons", lessons);
        request.setAttribute("chapters", chapters);
        request.getRequestDispatcher("/views/course/videoLesson.jsp").forward(request, response);
//        HttpSession session = request.getSession();
//        Account acc = (Account) session.getAttribute("account");
//        response.getWriter().print(acc.getId());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        String action = request.getParameter("action");
        LessonDAO lDao = new LessonDAO();
        CourseAccountDAO caDao = new CourseAccountDAO();
        CourseDetailDAO cdao = new CourseDetailDAO();
        NoteDAO nDao = new NoteDAO();

        switch (action) {
            case "register":
                if (acc == null) {
                    response.sendRedirect("login");
                } else {
                    courseID = request.getParameter("courseID");
                    int id = caDao.getMaxID();
                    int stateId = 1;
                    // Check out the user who signed up for this course
                    boolean check = cdao.checkUserRegiteredCourse(acc.getId(), courseID, stateId);
                    // If the user has registered for this course
                    if (check) {
                        doGet(request, response);
                        break;
                    }
                    // Add time registration
                    java.util.Date currentDate = new java.util.Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    // Add state id
                    caDao.registCourse(++id, Integer.parseInt(acc.getId()), Integer.parseInt(courseID), sdf.format(currentDate), stateId);
                    doGet(request, response);
                }
                break;
            case "view":
                courseID = request.getParameter("courseID");
                if (acc == null) {
                    response.sendRedirect("courseDetail?courseID=" + courseID);
                } else {
                    courseID = request.getParameter("courseID");
                    boolean isRegister = caDao.isRegisterCourse(Integer.parseInt(courseID), Integer.parseInt(acc.getId()));
                    if (isRegister) {
                        doGet(request, response);
                    } else {
                        response.sendRedirect("courseDetail?courseID=" + courseID);
                    }
                }
                break;
            case "video":
                selectedID = Integer.parseInt(request.getParameter("lessonID"));
                doGet(request, response);
                break;
            case "next":
                int total = lDao.getTotalLesson(Integer.parseInt(courseID));
                if (selectedID < total) {
                    selectedID += 1;
                } else {
                    selectedID = total;
                }
                doGet(request, response);
                break;
            case "pre":
                if (selectedID > 1) {
                    selectedID -= 1;
                } else {
                    selectedID = 1;
                }
                doGet(request, response);
                break;
            case "createNote":
                String newNote = request.getParameter("newNote");
                String lessonNote = request.getParameter("lessonID");

                int totalNote = nDao.getTotalNote();
                ++totalNote;

                Note note = new Note();
                note.setId(++totalNote);
                note.setLessonID(String.valueOf(selectedID));
                note.setAccountID(acc.getId());
                note.setDate(Date.valueOf(LocalDate.now()));
                note.setDetail(newNote);

                nDao.insert(note);
                doGet(request, response);
                break;
            case "updateNote":
                String updateNote = request.getParameter("note");
                String noteID = request.getParameter("noteID");

                Note noteUpdate = new Note();
                noteUpdate.setId(Integer.parseInt(noteID));
                noteUpdate.setDetail(updateNote);

                nDao.update(noteUpdate);
                doGet(request, response);
                break;
            case "deleteNote":
                int noteDelete = Integer.parseInt(request.getParameter("noteID"));

                nDao.delete(noteDelete);
                doGet(request, response);
                break;
        }
    }

    public static void main(String[] args) {
        NoteDAO nDao = new NoteDAO();
        Note note = new Note();
        note.setId(1);
        note.setLessonID("1");
        note.setAccountID("4");
        note.setDate(Date.valueOf("2023-6-16"));
        note.setDetail("heloooooooooooooooo");

        nDao.insert(note);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
