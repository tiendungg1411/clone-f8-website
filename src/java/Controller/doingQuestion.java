/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.AccountDoingQuestionDAO;
import DAO.AnswerDAO;
import DAO.CorrectAnswerDAO;
import DAO.LessonDAO;
import DAO.QuestionDAO;
import Model.Account;
import Model.AccountDoingQuestion;
import Model.Answer;
import Model.CorrectAnswer;
import Model.Lesson;
import Model.Question;
import Model.QuestionAndAnswerAndCorrectAnswerWrap;
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
public class doingQuestion extends HttpServlet {
   
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
            out.println("<title>Servlet doingQuizLesson</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet doingQuizLesson at " + request.getContextPath () + "</h1>");
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
        String page_raw = request.getParameter("page");
        Account account = (Account) session.getAttribute("account");
        String accountID = "";
        if(account!=null) accountID = account.getId();
        String courseID = request.getParameter("courseID");
        String lessonID = request.getParameter("lessonID");
        
        QuestionDAO q = new QuestionDAO();
        AnswerDAO a = new AnswerDAO();
        CorrectAnswerDAO c = new CorrectAnswerDAO();
        AccountDoingQuestionDAO adqdao = new AccountDoingQuestionDAO();
        LessonDAO l = new LessonDAO();
        
        //Mode answer
        String mode = request.getParameter("mode");
        if(account!=null && mode != null && mode.equals("answer")){
            String questionID = request.getParameter("questionID");
            String answer = request.getParameter("answer");
            
            String trueOrFalse = calTrueOrFalse(questionID, answer);
            
            if(!adqdao.checkQuestionIsAnsweredByAccount(accountID, questionID)){
                adqdao.insertAccountDoingQuestion(answer, trueOrFalse, accountID, questionID);
            }
        }
        
        //list QuestionAndAnswerAndCorrectAnswerWrap
        ArrayList<QuestionAndAnswerAndCorrectAnswerWrap> 
                lqac = new ArrayList<>();
        
        Lesson lesson = l.getLessonByID(Integer.parseInt(lessonID));
        ArrayList<Question> listQ = q.getAllQuestionByLessonID(lessonID);
        //get answer and correct answer by question
        for (Question question : listQ) {
            ArrayList<Answer> listAnswer = a.getAllAnswerByQuesID(question.getId());
            CorrectAnswer correctAnswer = c.getCorrectAnswerByQuesID(question.getId());
            AccountDoingQuestion adq = adqdao.getAccountDoingQuestionByAccountIDAndQuesID(accountID, question.getId());
            QuestionAndAnswerAndCorrectAnswerWrap qac = new QuestionAndAnswerAndCorrectAnswerWrap(question, listAnswer, correctAnswer, adq);
            lqac.add(qac);
        }
        
        //Xu ly phan trang
        int totalPage = lqac.size()/1;
        int currentPage = 1;
        if(page_raw!=null && !page_raw.equals("")){
            currentPage = Integer.parseInt(page_raw);
        }
        
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        
        ArrayList<QuestionAndAnswerAndCorrectAnswerWrap> lqacPage = new ArrayList<>();
        lqacPage.add(lqac.get(currentPage-1));
        request.setAttribute("lqac", lqacPage);
        request.setAttribute("lessonID", lessonID);
        request.setAttribute("lesson", lesson);
        request.setAttribute("courseID", courseID);
        request.getRequestDispatcher("doingQuestion.jsp").forward(request, response);
    } 
    public static String calTrueOrFalse(String questionID, String answer) {
        CorrectAnswerDAO c = new CorrectAnswerDAO();
        CorrectAnswer correctAnswer = c.getCorrectAnswerByQuesID(questionID);
        if(answer.equals(correctAnswer.getContent())) return "true";
        return "false";
    }
    public static void main(String[] args) {
        System.out.println(calTrueOrFalse("1", "10"));
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
