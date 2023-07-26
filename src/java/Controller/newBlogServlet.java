/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.BlogDAO;
import DAO.NotificationDAO;
import Model.Account;
import Model.Blog;
import Model.Notification;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class newBlogServlet extends HttpServlet {
   
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
            out.println("<title>Servlet newBlogServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet newBlogServlet at " + request.getContextPath () + "</h1>");
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
        HttpSession sesson = request.getSession();
        if(sesson.getAttribute("account") != null){
            NotificationDAO n = new NotificationDAO();
            Account account = (Account) sesson.getAttribute("account");
            ArrayList<Notification> listN = n.getAllNotificationByAccountID(account.getId());
            sesson.setAttribute("notificationList", listN);
            request.setAttribute("newBl", "abc");
            request.getRequestDispatcher("newBlog.jsp").forward(request, response);
        }else{
            response.sendRedirect("login");
        }
        
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
        String blogID = request.getParameter("blogID");
        //Edit Blog
        if(!blogID.equals("")){
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BlogDAO blogDao = new BlogDAO();

            HttpSession session = request.getSession();

            Account account = (Account) session.getAttribute("account");
            String title = request.getParameter("title");
            String content = request.getParameter("editor");
            String userID = account.getId();
            String userAvatar = account.getAvatar();
            String userName = account.getUserName();
            Blog blog = new Blog(blogID, title, null, content, 0, sdf.format(currentDate), userID, userAvatar, userName);
            blogDao.editBlogByID(blog);
//            request.setAttribute("editBlogStatus", "Cập nhật thành công");
//            request.getRequestDispatcher("/home").forward(request, response);
            response.sendRedirect("blogDetail?id="+blogID+"&newBlogStatus=Edit Blog successfully!");
        }else{
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BlogDAO blogDao = new BlogDAO();

            HttpSession session = request.getSession();

            Account account = (Account) session.getAttribute("account");
            String title = request.getParameter("title");
            String content = request.getParameter("editor");
            String userID = account.getId();
            ArrayList<Blog> listB = blogDao.getAllBlog();
            String id = (getMaxBlogID(listB)+1)+"";
            String userAvatar = account.getAvatar();
            String userName = account.getUserName();

            Blog blog = new Blog(id, title, null, content, 0, sdf.format(currentDate), userID, userAvatar, userName);
            blogDao.insertBlog(blog);
            response.sendRedirect("blogDetail?id="+id+"&newBlogStatus=New Blog successfully!");
        }
    }
    public int getMaxBlogID(ArrayList<Blog> listBlog){
        int max = 0;
        for (Blog blog : listBlog) {
            int id = Integer.parseInt(blog.getId());
            if(max < id) max = id;
        }
        return max;
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
