/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.AnswerDAO;
import DAO.CorrectAnswerDAO;
import DAO.CourseDetailDAO;
import DAO.LessonDAO;
import DAO.QuestionDAO;
import DAO.QuestionEditVMDAO;
import Model.Answer;
import Model.CorrectAnswer;
import Model.CourseDetail;
import Model.Lesson;
import Model.Question;
import Model.QuestionAndAnswerAndCorrectAnswerWrap;
import Model.QuestionEditVM;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class listQuestion extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet listQuizLesson</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listQuizLesson at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        /* If the user is not logged in */
        if (session.getAttribute("account") == null) {
            response.sendRedirect("login");
            return;
        }
        String editBlogStatus = request.getParameter("editBlogStatus");
        if (editBlogStatus != null) {
            request.setAttribute("editBlogStatus", editBlogStatus);
        }
        QuestionDAO q = new QuestionDAO();
        AnswerDAO a = new AnswerDAO();
        CorrectAnswerDAO c = new CorrectAnswerDAO();
        LessonDAO l = new LessonDAO();
        CourseDetailDAO cd = new CourseDetailDAO();
        QuestionEditVMDAO qevm = new QuestionEditVMDAO();
        String mode = request.getParameter("mode");
        //Edit mode
        if(mode!=null && mode.equals("edit")){
            //Send list Courses
            ArrayList<CourseDetail> listCourses = cd.getAllPublishedCourse();
            request.setAttribute("listCourses", listCourses);
            
            //Send list Lessons
            ArrayList<Lesson> listLessons = l.getAllLessons();
            request.setAttribute("listLessons", listLessons);
            
            String id = request.getParameter("id");
            //Send current question following by lessonID and courseID
            QuestionEditVM qe = qevm.getQuestionEditVMByQuesID(id);
            request.setAttribute("qe", qe);
            
            Question question = q.getQuestionByID(id);
            ArrayList<Answer> listAnswer = a.getAllAnswerByQuesID(id);
            CorrectAnswer correctAnswer = c.getCorrectAnswerByQuesID(id);
            QuestionAndAnswerAndCorrectAnswerWrap qac = new QuestionAndAnswerAndCorrectAnswerWrap(question, listAnswer, correctAnswer, null);
            
            request.setAttribute("mode", "edit");
            request.setAttribute("qac", qac);
            request.getRequestDispatcher("newQuestion.jsp").forward(request, response);
            return;
        }
        
        //Delete mode
        if(mode!=null && mode.equals("delete")){
            String id = request.getParameter("id");
            q.deleteQuestionByID(id);
            request.setAttribute("editBlogStatus", "Delete Quiz successfully!");
        }
        //Load all lesson
        ArrayList<Lesson> listLessons = l.getAllLessons();
        request.setAttribute("listLessons", listLessons);
        
        
        ArrayList<Question> listQ = q.getAllDetailQuestion();
        //Filter
        String lessonID = request.getParameter("lesson");
        if(lessonID!=null && !lessonID.equals("default")){
            listQ = q.getAllDetailQuestionByLessonID(lessonID);
            request.setAttribute("lessonID", lessonID);
        }
        request.setAttribute("listQ", listQ);
        request.getRequestDispatcher("listQuestion.jsp").forward(request, response);
    } 
    
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
