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
import Model.Lesson;
import Model.Note;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
 * @author DELL
 */
@WebServlet(name = "feedbackLessonController", urlPatterns = {"/feedbackLessonController"})
public class feedbackLessonController extends HttpServlet {
    
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
            out.println("<title>Servlet feedbackLessonController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet feedbackLessonController at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        NoteDAO nDao = new NoteDAO();
        String courseID = request.getParameter("courseID");
        String selectedID=request.getParameter("lessonID");
        

        LessonDAO lDao = new LessonDAO();
        ArrayList<Lesson> lessons = lDao.getAllLesson(Integer.parseInt(courseID));

        int total = lDao.getTotalLesson(Integer.parseInt(courseID));

        ChapterDAO cDao = new ChapterDAO();
        ArrayList<Chapter> chapters = lDao.getAllChapterByCID(Integer.parseInt(courseID));

        Lesson lesson = lDao.getLessonByID(Integer.parseInt(selectedID));

        ArrayList<Note> notes = nDao.getAll(Integer.parseInt(selectedID), Integer.parseInt(acc.getId()));
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
        request.getRequestDispatcher("/views/course/feedbackLesson.jsp").forward(request, response);
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
//        processRequest(request, response);
        
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
