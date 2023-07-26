/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LearnDAO;
import Model.Account;
import Model.Course;
import Model.CourseDetail;
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
@WebServlet(name = "CourseController", urlPatterns = {"/course"})
public class CourseController extends HttpServlet {

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
            out.println("<title>Servlet CourseController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseController at " + request.getContextPath() + "</h1>");
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
        
        LearnDAO la=new LearnDAO();
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            String courseID=request.getParameter("cid");
            la.deleteCourseByCID(courseID);
            request.setAttribute("savedBlogStatus", "Ðã xóa khóa học");
        }
        if (request.getParameter("stateID") != null && request.getParameter("stateID").equals("1")) {
            /* Update state of Blog */
            la.showCourse(request.getParameter("courseId"));
            request.setAttribute("savedBlogStatus1", "Course is Published");
        } else if (request.getParameter("stateID") != null && request.getParameter("stateID").equals("2")) {
            /* Click hide Blog */
            la.hideCourse(request.getParameter("courseId"));
            request.setAttribute("savedBlogStatus2", "Course is Hide");
        }
        
        
        ArrayList<CourseDetail> listCourse = la.getCourseList();
        
        
        request.setAttribute("listCourse", listCourse);
        request.getRequestDispatcher("CourseList.jsp").forward(request, response);
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
        LearnDAO la=new LearnDAO();
        Course cd=new Course();
       
//        if (request.getParameter("btn_edit") != null) {
//            String courseId = request.getParameter("cid");
//            request.getRequestDispatcher("editCourseServlet").forward(request, response);
//        }
        if (request.getParameter("btn_delete") != null) {
            String courseId = request.getParameter("btn_delete");
            cd=la.getCourseInfor(courseId);
            request.setAttribute("btn_delete", request.getParameter("btn_delete"));
            request.setAttribute("course", cd);            
        }
        
        
        doGet(request, response);
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
