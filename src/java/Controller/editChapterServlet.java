/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LearnDAO;
import Model.Account;
import Model.Chapter;
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
@WebServlet(name = "editChapterServlet", urlPatterns = {"/editChapterServlet"})
public class editChapterServlet extends HttpServlet {

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
            out.println("<title>Servlet editChapterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editChapterServlet at " + request.getContextPath() + "</h1>");
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
        String cid = request.getParameter("cid");
        String chapterID = request.getParameter("chapterID");
//        String[] lesson=request.getParameterValues("lesson");
        LearnDAO la = new LearnDAO();

        ArrayList<Lesson> listLesson = la.getLessonByCourseIDAndChapterID(chapterID);
        ArrayList<Chapter> listC=la.getChapterByChapterID(chapterID);
        System.out.println(listLesson);
        request.setAttribute("cid", cid);
        request.setAttribute("listC", listC);
        request.setAttribute("chapterID", chapterID);
        request.setAttribute("listLesson", listLesson);
        request.getRequestDispatcher("chapterEdit.jsp").forward(request, response);
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
        Account account = (Account) session.getAttribute("account");
        /* Check if user is logged in or not */
        if (session.getAttribute("account") == null) {
            request.getRequestDispatcher("login").forward(request, response);
        } else if (!"1".equals(account.getRoleID())) {
            /* If the user is not an administrator */
            request.getRequestDispatcher("home").forward(request, response);
        }
        String cid = request.getParameter("cid");
        String chapterID = request.getParameter("chapterID");
        
        String[] lesson = request.getParameterValues("lesson");
        LearnDAO la = new LearnDAO();

        ArrayList<String> listLessonName1 = la.getAllLessonName(chapterID);
        if (lesson != null) {
            for (String less : lesson) {
                if (listLessonName1.contains(less)) {
                } else {
                    la.addLesson(less,cid,chapterID);
                }
            }
        }
        ArrayList<String> listLessonName = la.getAllLessonName(chapterID);
        for (String less : listLessonName) {
            if (contains(lesson, less)) {
            }else{
                la.deleteLessonByName(less, chapterID);
            }
        }
        ArrayList<Chapter> listC=la.getChapterByChapterID(chapterID);
        
        ArrayList<Lesson> listLesson = la.getLessonByCourseIDAndChapterID(chapterID);
        request.setAttribute("cid", cid);
        request.setAttribute("listC", listC);
        request.setAttribute("savedBlogStatus", "Chinh sua thanh cong");        
        request.setAttribute("chapterID", chapterID);
        request.setAttribute("listLesson", listLesson);
        request.getRequestDispatcher("chapterEdit.jsp").forward(request, response);
        
        
    }

    public static boolean contains(String[] array, String element) {
        for (String num : array) {
            if (num.equals(element)) {
                return true;
            }
        }
        return false;
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
