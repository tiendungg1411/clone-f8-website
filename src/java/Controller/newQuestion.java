/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AnswerDAO;
import DAO.CorrectAnswerDAO;
import DAO.CourseDetailDAO;
import DAO.LessonDAO;
import DAO.LessonQuestionDAO;
import DAO.QuestionDAO;
import Model.CourseDetail;
import Model.Lesson;
import Model.Question;
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
public class newQuestion extends HttpServlet {

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
            out.println("<title>Servlet newQuizLesson</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet newQuizLesson at " + request.getContextPath() + "</h1>");
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
        /* If the user is not logged in */
        if (session.getAttribute("account") == null) {
            response.sendRedirect("login");
            return;
        }


        CourseDetailDAO c = new CourseDetailDAO();
        LessonDAO l = new LessonDAO();
        String courseId = request.getParameter("course");

        ArrayList<CourseDetail> listCourses = c.getAllPublishedCourse();

        if (courseId!=null && !courseId.equals("default")) {
            ArrayList<Lesson> listLessons = l.getAllLessonTypePracticeByCourseID(courseId);
            request.setAttribute("courseID", courseId);
            request.setAttribute("listCourses", listCourses);
            request.setAttribute("listLessons", listLessons);
            request.getRequestDispatcher("newQuestion.jsp").forward(request, response);
            return;
        }

        request.setAttribute("listCourses", listCourses);
        request.getRequestDispatcher("newQuestion.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        /* If the user is not logged in */
        if (session.getAttribute("account") == null) {
            response.sendRedirect("login");
            return;
        }
        
        //Edit mode
        String mode = request.getParameter("mode");
        if(mode!=null && mode.equals("edit")){
            String quesID = request.getParameter("quesID");
            //Delete all answer and correctAns by quesID 
            AnswerDAO a = new AnswerDAO();
            CorrectAnswerDAO ca = new CorrectAnswerDAO();
            a.deleteAllAnswerByQuesID(quesID);
            ca.deleteCorrectAnswerByQuesID(quesID);
            //Update question
            QuestionDAO q = new QuestionDAO();
            String question = request.getParameter("question");
            String explain = request.getParameter("explain");
            q.updateQuestionByQuesID(quesID, question, explain);
            //Add new Answer and CorrectAns
            String answer[] = request.getParameterValues("answer");
            String correctAnswer = request.getParameter("correctAnswer");
            a.addAnswer(answer, quesID + "");
            ca.addCorrectAnswer(answer[Integer.parseInt(correctAnswer)-1], quesID);
            response.sendRedirect("listQuestion?editBlogStatus=Edit Quiz successfully!");
            return;
        }
        
        //Create
        CourseDetailDAO c = new CourseDetailDAO();

        ArrayList<CourseDetail> listCourses = c.getAllPublishedCourse();
        String correctAnswer = request.getParameter("correctAnswer");
        if (!correctAnswer.equals("default")) {
            //Add to DB
            String question = request.getParameter("question");
            String explain = request.getParameter("explain");
            String answer[] = request.getParameterValues("answer");
            correctAnswer = request.getParameter("correctAnswer");
            QuestionDAO q = new QuestionDAO();
            AnswerDAO a = new AnswerDAO();
            LessonQuestionDAO lq = new LessonQuestionDAO();
            CorrectAnswerDAO ca = new CorrectAnswerDAO();

            int size = 0;
            ArrayList<Question> listQ = q.getAllQuestion();
            for (Question question1 : listQ) {
                if (size < Integer.parseInt(question1.getId())) {
                    size = Integer.parseInt(question1.getId());
                }
            }
            q.addQuestion(size + 1 + "", question, explain);
            a.addAnswer(answer, (size + 1) + "");
            ca.addCorrectAnswer(answer[Integer.parseInt(correctAnswer)-1], (size+1)+"");
            response.sendRedirect("listQuestion?editBlogStatus=New Quiz successfully!");
        } else {
            request.setAttribute("listCourses", listCourses);
            request.setAttribute("newQuizLessonStatus", "New Quiz lesson failed!");
            request.getRequestDispatcher("newQuestion.jsp").forward(request, response);
        }
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
