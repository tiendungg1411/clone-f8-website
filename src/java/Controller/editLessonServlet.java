/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LearnDAO;
import DAO.LessonDAO;
import Model.Account;
import Model.Chapter;
import Model.Course;
import Model.Lesson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
@WebServlet(name = "editLessonServlet", urlPatterns = {"/editLessonServlet"})
public class editLessonServlet extends HttpServlet {

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
            out.println("<title>Servlet editLessonServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editLessonServlet at " + request.getContextPath() + "</h1>");
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
        Account account = (Account) session.getAttribute("account");
        /* Check if user is logged in or not */
        if (session.getAttribute("account") == null) {
            request.getRequestDispatcher("login").forward(request, response);
        } else if (!"1".equals(account.getRoleID())) {
            /* If the user is not an administrator */
            request.getRequestDispatcher("home").forward(request, response);
        }
        String courseId = request.getParameter("courseId");
        String chapterId = request.getParameter("chapterId");
        String mode=request.getParameter("mode");
        String lessonId=request.getParameter("id");
        String editBlogStatus = request.getParameter("editBlogStatus");
        LearnDAO la = new LearnDAO();
        if(mode !=null && mode.equals("delete")){
            LessonDAO les = new LessonDAO();
            les.deleteLessonByLesID(lessonId);
            request.setAttribute("editBlogStatus1", "Delete Lesson successfully!");
        }
        if (chapterId != null && !chapterId.equals("")) {
            ArrayList<Chapter> listChapter1 = la.getAllChapter(courseId);
            ArrayList<Lesson> listLesson1 = la.getLessonByChapterId(chapterId);
            request.setAttribute("courseId", courseId);
            request.setAttribute("chapterId", chapterId);
            request.setAttribute("listChapter", listChapter1);
            request.setAttribute("listLesson", listLesson1);
        } else {
            ArrayList<Chapter> listChapter = la.getAllChapter(courseId);
            ArrayList<Lesson> listLesson = la.getLessonsByCourse(courseId);
            request.setAttribute("courseId", courseId);
            request.setAttribute("listChapter", listChapter);
            request.setAttribute("listLesson", listLesson);
        }

//        String lessonId=request.getParameter("lessonId");
//        String url=request.getParameter("url");
//        String type=request.getParameter("type");
//        if(request.getParameter("mod") != null && request.getParameter("mod").equals("1")){
//            la.updateURL(url, lessonId);
//            request.setAttribute("savedBlogStatus", "Chinh sua thanh cong");  
//        }
//        if(request.getParameter("mod") != null && request.getParameter("mod").equals("2")){
//            la.updateType(type, lessonId);
//            request.setAttribute("savedBlogStatus", "Chinh sua thanh cong");  
//        }
////        ArrayList<Course> listCourse1 = la.getCourse(courseId);
//        ArrayList<Chapter> listChapter = la.getAllChapter(courseId);
//        ArrayList<Lesson> listLesson = la.getLessonsByCourse(courseId);
//        request.setAttribute("listCourse", listCourse1);
//        request.setAttribute("courseId", courseId);
//        request.setAttribute("listChapter", listChapter);
//        request.setAttribute("listLesson", listLesson);
        ArrayList<Course> listC = la.getCourse(courseId);
        request.setAttribute("listC", listC);

        request.setAttribute("editBlogStatus", editBlogStatus);
        request.getRequestDispatcher("lessonEdit.jsp").forward(request, response);
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
