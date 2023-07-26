/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.BlogDAO;
import Model.Account;
import Model.Blog;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author HP
 */
public class MyBlogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Check if user is logged in or not */
        HttpSession session = req.getSession();
        if (session.getAttribute("account") == null) {
            req.getRequestDispatcher("registerMainPage.jsp").forward(req, resp);
        }
        /* Get userId */
        Account account = (Account) session.getAttribute("account");
        String userId = account.getId();
        BlogDAO blogDAO = new BlogDAO();
        /* If user delete blog */
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("1")) {
            String blogId = req.getParameter("id");
            /* Delete blog from commentBlog table */
            blogDAO.deleteBlogFromCommentBlogTable(blogId);
            /* Delete blog from commentDetail table */
            blogDAO.deleteBlogFromCommentDetailTable(blogId);
            /* delete blog from savedBlog table */
            blogDAO.deleteBlogFromSavedBlogTable(blogId);
            /* Delete blog by id */
            blogDAO.deleteBlogById(blogId);
        }

        String[] empty = {"<p>", "</p>", "<em>", "</em>", "<strong>", "</strong>", "<u>", "</u>",
            "<h1>", "</h1>", "<h2>", "</h2>", "<h3>", "</h3>", "<h4>", "</h4>",
            "<h5>", "</h5>", "<h6>", "</h6>"};
        /* Get list blog of account */

        ArrayList<Blog> listBlog = blogDAO.getAllBlogByAccountID(userId);
        /* Sort list blog by time */
        Collections.sort(listBlog, new Comparator<Blog>() {
            @Override
            public int compare(Blog b1, Blog b2) {
                String dbf = b1.getTime();
                String dat = getCurrentTime();
                int second1 = getSecond(dbf, dat);
                dbf = b2.getTime();
                int second2 = getSecond(dbf, dat);
                return second1 - second2;
            }
        });
        for (Blog blog : listBlog) {
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
                content = content.substring(0, 150) + "...";
            }
            blog.setContent(content);
            //get time 
            String dbf = blog.getDate();
            String dat = getCurrentTime();
            blog.setTime(getTime(dbf, dat));
        }
        /* Get number of my blog */
        int numOfMyBlog = listBlog.size();
        /* Set data to JSP */
        req.setAttribute("numOfMyBlog", numOfMyBlog);
        req.setAttribute("listBlog", listBlog);
        req.getRequestDispatcher("MyBlog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    public String calculateMinute(String src) {
        int readMinute = 1;
        if ((src.length() / 1000) > 1) {
            readMinute = (src.length() / 1000);
        }
        return readMinute + " phút đọc";
    }

    public String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date d = new Date();
        return sdf.format(d);
    }

    public int getSecond(String dbf, String dat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
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

}
