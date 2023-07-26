/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.LessonDAO;
import DAO.LessonQuestionDAO;
import DAO.QuestionDAO;
import Model.Lesson;
import Model.LessonVM;
import Model.Question;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class editQuiz extends HttpServlet {
   
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
            out.println("<title>Servlet editQuiz</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editQuiz at " + request.getContextPath () + "</h1>");
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
        //Edit Quiz
        String quizID = request.getParameter("id");
        //get lesson by quizID
        LessonDAO l = new LessonDAO();
        Lesson lesson = l.getLessonByID(Integer.parseInt(quizID));
        request.setAttribute("lesson", lesson);
        
        QuestionDAO q = new QuestionDAO();
        //send list question by quizID
        ArrayList<Question> listTickQ = q.getAllDetailQuestionByQuizID(quizID);
        request.setAttribute("listTickQ", listTickQ);
        //send list question
        ArrayList<Question> listQ = q.getAllDetailQuestion();
        
        request.setAttribute("listQ", listQ);
        request.getRequestDispatcher("editQuiz.jsp").forward(request, response);
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
//        processRequest(request, response);
        String quizID = request.getParameter("lessonID");
        String listQuestionID[] = request.getParameterValues("questions");
        
        //get allQuestion
        LessonDAO l = new LessonDAO();
//        ArrayList<LessonVM> listLesson = l.getAllLessonVMByID(quizID);
//        request.setAttribute("listLesson", listLesson);
        QuestionDAO qu = new QuestionDAO();
        ArrayList<Question> listQues = qu.getAllDetailQuestionByQuizID(quizID);
        
        ArrayList<String> listQuestionIDRemove = new ArrayList<>();
        ArrayList<String> listQuestionIDAdd = new ArrayList<>();
        ArrayList<String> listTemp = new ArrayList<>();
        //listQuestionIDRemove
        for(Question ques : listQues) {
            boolean flag = false;
            for (String q : listQuestionID) {
                if(q.equals(ques.getId())) flag = true;
            }
            if(!flag) {
                listQuestionIDRemove.add(ques.getId());
            }
        }
        //listQuestionIDAdd
        for (String q : listQuestionID) {
            boolean flag = false;
            for(Question ques : listQues) {
                if(q.equals(ques.getId())) flag = true;
            }
            if(!flag) {
                listQuestionIDAdd.add(q);
            }
        }
        LessonQuestionDAO lq = new LessonQuestionDAO();
        //add to lessonQuestion
        for (String string : listQuestionIDAdd) {
            lq.addLessonQuestion(quizID, string);
        }
        //set lessonID to question
        for (String string : listQuestionIDRemove) {
            lq.removeLessonQuestion(quizID, string);
        }
        response.sendRedirect("listQuiz?editBlogStatus=Edit quiz successfully!");
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
