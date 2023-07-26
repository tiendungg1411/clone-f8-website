/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.CourseDetailDAO;
import DAO.LessonDAO;
import Model.CourseDetail;
import Model.Lesson;
import Model.LessonVM;
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
public class listQuiz extends HttpServlet {
   
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
            out.println("<title>Servlet listQuiz</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listQuiz at " + request.getContextPath () + "</h1>");
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
        String editBlogStatus = request.getParameter("editBlogStatus");
        if(editBlogStatus != null) request.setAttribute("editBlogStatus", editBlogStatus);
        System.out.println("editBlogStatus = "+editBlogStatus);
        //Delete mode
        String quizID = request.getParameter("id");
        String courseID = request.getParameter("course");
        if(quizID != null && !quizID.equals("")){
            LessonDAO les = new LessonDAO();
            les.deleteLessonByLesID(quizID);
            request.setAttribute("editBlogStatus", "Delete quiz successfully!");
        }
        
        //send list course
        CourseDetailDAO c = new CourseDetailDAO();
        ArrayList<CourseDetail> listC = c.getAllProCourseAndFreeCourse();
        //send list lesson type practice (Quiz)
        LessonDAO l = new LessonDAO();
        ArrayList<LessonVM> listL = l.getAllLessonVMByType("practice");
        
        //Filter 
        if(courseID != null && !courseID.equals("default")){
            listL = l.getAllLessonVMByCourseIDAndType("practice" ,courseID);
            request.setAttribute("courseID", courseID);
        }
        
        request.setAttribute("listC", listC);
        request.setAttribute("listL", listL);
        request.getRequestDispatcher("listQuiz.jsp").forward(request, response);
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
