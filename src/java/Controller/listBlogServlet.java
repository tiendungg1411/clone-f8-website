/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.BlogDAO;
import DAO.SavedBlogDAO;
import Model.Account;
import Model.Blog;
import Model.SavedBlog;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class listBlogServlet extends HttpServlet {

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
            out.println("<title>Servlet listBlogServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listBlogServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String page_raw = request.getParameter("page");
        String savedBlogStatus = (String) request.getAttribute("savedBlogStatus");
        System.out.println(savedBlogStatus);
        int page = 1;
        if(page_raw != null){
            page = Integer.parseInt(page_raw);
        }
        String[] empty = {"<p>", "</p>", "<em>", "</em>", "<strong>", "</strong>", "<u>", "</u>",
            "<h1>", "</h1>", "<h2>", "</h2>", "<h3>", "</h3>", "<h4>", "</h4>",
            "<h5>", "</h5>", "<h6>", "</h6>"};
        BlogDAO blogDao = new BlogDAO();
        ArrayList<Blog> allListBlog = blogDao.getAllBlog();
        Collections.sort(allListBlog, new Comparator<Blog>(){
            @Override
            public int compare(Blog o1, Blog o2) {
                String dbf = o1.getDate();
                String dat = getCurrentTime();
                int second1 = getSecond(dbf, dat);
                dbf = o2.getDate();
                int second2 = getSecond(dbf, dat);
                return second1-second2;
            }
        });
        int totalPage = allListBlog.size()/5;
        if(allListBlog.size()%5!=0) {totalPage++;}
        ArrayList<Blog> listBlog = null;
        if(allListBlog.size()/5!=0 && page == totalPage  && !(allListBlog.size()<=5)){
            listBlog = new ArrayList<>(allListBlog.subList((page-1)*5, allListBlog.size()));
        }else {
            if(!(allListBlog.size()<=5)) listBlog = new ArrayList<>(allListBlog.subList((page-1)*5, page*5));
        }
        if(allListBlog.size()<=5) listBlog = allListBlog;
        for (Blog blog : listBlog) {
            //get link of first image
            String imgLink = blog.getContent();
            if (blog.getContent().contains("<img")) {
                imgLink = imgLink.substring(imgLink.indexOf("http"), imgLink.indexOf("style") - 2);
                blog.setImg(imgLink);
            }
            //get content to display
            String content = blog.getContent();
            for (String string : empty) {
                content = content.replaceAll(string, "");
            }
            //Set readMinute
            String readMinute = calculateMinute(content);
            blog.setReadMinute(readMinute);
            if (content.contains("<img")) {
                content = content.substring(0, blog.getContent().indexOf("<img"));
            }
            content = content.replace("<img alt=\"", "");
            if (content.length() >= 150) {
                content = content.substring(0, 150)+"...";
            }
            blog.setContent(content);
            //get time 
            String dbf = blog.getDate();
            String dat = getCurrentTime();
            blog.setTime(getTime(dbf, dat));
        }
        //Saved Blog
        Account account = (Account) session.getAttribute("account");
        if(account!=null){
            SavedBlogDAO savedBlogDao = new SavedBlogDAO();
            ArrayList<SavedBlog> listSavedBlog = savedBlogDao.getAllSavedBlogByAccID(account.getId());
            request.setAttribute("listSavedBlog", listSavedBlog);
        }
        //My list Blog
        if(account!=null){
            ArrayList<Blog> myListBlog = blogDao.getAllBlogByAccountID(account.getId());
            request.setAttribute("myListBlog", myListBlog);
        }
        
        request.setAttribute("savedBlogStatus", savedBlogStatus);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("mode", "Blog");
        request.setAttribute("hoc", "hoc");
        request.setAttribute("listBlog", listBlog);
        request.getRequestDispatcher("listBlog.jsp").forward(request, response);
    }
    public String calculateMinute(String src) {
        int readMinute = 1;
        if((src.length()/1000)>1) readMinute = (src.length()/1000);
        return readMinute+" phút đọc";
    }
    public String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        return sdf.format(d);
    }
    public int getSecond(String dbf, String dat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        Date d1 = null;
        long duration = 0;
        int second = 0;
        try {
            d = sdf.parse(dbf);
            d1 = sdf.parse(dat);
        } catch (Exception e) {
        }
        if (d != null && d1 != null) {
            duration = d1.getTime() - d.getTime();
        }

        // Print out the duration in seconds
        second = (int) duration / 1000;
        return second;
    }
    public String getTime(String dbf, String dat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        Date d1 = null;
        long duration = 0;
        int second = 0;
        String result = "";
        try {
            d = sdf.parse(dbf);
            d1 = sdf.parse(dat);
        } catch (Exception e) {
        }
        if (d != null && d1 != null) {
            duration = d1.getTime() - d.getTime();
        }

        // Print out the duration in seconds
        second = (int) duration / 1000;
        result = "vài giây trước";
        if (second > 60) {
            result = second / 60 + " phút trước";
        }
        if (second > 3600) {
            result = second / 3600 + " giờ trước";
        }
        if (second > 86400) {
            result = second / 86400 + " ngày trước";
        }
        return result;
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
