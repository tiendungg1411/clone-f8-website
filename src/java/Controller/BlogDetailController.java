/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.BlogDAO;
import DAO.SavedBlogDAO;
import Model.Account;
import Model.Blog;
import Model.CommentBlog;
import Model.LikedBlog;
import Model.SavedBlog;
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
public class BlogDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Check if user is logged in or not */
        String newBlogStatus = req.getParameter("newBlogStatus");
        if (newBlogStatus != null) {
            req.setAttribute("newBlogStatus", newBlogStatus);
        }

        HttpSession session = req.getSession();
        if (session.getAttribute("account") == null) {
            req.getRequestDispatcher("registerMainPage.jsp").forward(req, resp);
        }
        /* Get account from session */
        Account account = (Account) session.getAttribute("account");
        SavedBlogDAO savedBlogDao = new SavedBlogDAO();
        BlogDAO blogDAO = new BlogDAO();
        /* Get parameter save mode */
        String savedBlogMode = req.getParameter("savedBlogMode");
        String[] empty = {"<p>", "</p>", "<em>", "</em>", "<strong>", "</strong>", "<u>", "</u>",
            "<h1>", "</h1>", "<h2>", "</h2>", "<h3>", "</h3>", "<h4>", "</h4>",
            "<h5>", "</h5>", "<h6>", "</h6>"};
        /* If user click save or unsave(Save from blog detail) */
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("1")) {
            if (savedBlogMode.equals("1")) {
                //Remove
                System.out.println("Remove");
                savedBlogDao.removeSavedBlogByBlogIDAndAccID(req.getParameter("id"), account.getId());
                req.setAttribute("savedBlogStatus", "Xóa khỏi mục đã lưu");
                req.setAttribute("id", req.getParameter("id"));
            } else {
                //Add
                System.out.println("Add");
                String time = getCurrentTime();
                boolean flag = savedBlogDao.addSavedBlogByBlogIDAndAccIDAndTime(req.getParameter("id"), account.getId(), time);
                if (flag) {
                    req.setAttribute("savedBlogStatus", "Thêm vào mục đã lưu");
                }
                req.setAttribute("id", req.getParameter("id"));
            }
        }

        /* Get Comment of Blog */
        ArrayList<CommentBlog> listComment = blogDAO.getListComment(req.getParameter("id"));
        int numOfComment = listComment.size();
        /* Sort comment by time */
        Collections.sort(listComment, new Comparator<CommentBlog>() {
            @Override
            public int compare(CommentBlog c1, CommentBlog c2) {
                String dbf = c1.getTime();
                String dat = getCurrentTime();
                int second1 = getSecond(dbf, dat);
                dbf = c2.getTime();
                int second2 = getSecond(dbf, dat);
                return second1 - second2;
            }
        });
        /* Get time */
        for (CommentBlog commentBlog : listComment) {
            String dbf = commentBlog.getTime();
            String dat = getCurrentTime();
            commentBlog.setTime(getTime(dbf, dat));
        }
        
        /* Get list account */
        AccountDAO accountDAO = new AccountDAO();
        ArrayList<Account> listAccount = accountDAO.getListAccount();

        /* User click like blog */
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("2")) {
            /* Unlike */
            if (req.getParameter("likeBlogMode").equals("1")) {
                System.out.println("remove");
                blogDAO.unLikeBlog(req.getParameter("id"), account.getId());
                req.setAttribute("id", req.getParameter("id"));
            } else {
                /* Like */
                System.out.println("Like");
                blogDAO.likeBlog(req.getParameter("id"), account.getId());
                req.setAttribute("id", req.getParameter("id"));
            }
        }
        /* Get blog detail by blogId */
        Blog blog = blogDAO.getBlogById(req.getParameter("id"));
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
        //get time 
        String dbf = blog.getDate();
        String dat = getCurrentTime();
        blog.setTime(getTime(dbf, dat));
        /* Get list blog saved */
        savedBlogDao = new SavedBlogDAO();
        ArrayList<SavedBlog> listSavedBlog = savedBlogDao.getAllSavedBlogByAccID(account.getId());
        String savedBlogStatus = (String) req.getAttribute("savedBlogStatus");

        /* Get list blog liked */
        ArrayList<LikedBlog> listLikedBlog = blogDAO.getListBlogLikedByUserID(account.getId());
        /* Get the number of likes of the blog */
        int numOfLike = blogDAO.getNumOfLikeOfBlog(req.getParameter("id"));
        blog.setNumOfLikes(numOfLike);
        /* Set data to jsp */
        req.setAttribute("listComment", listComment);
        req.setAttribute("numOfComment", numOfComment);
        req.setAttribute("listAccount", listAccount);
        req.setAttribute("listSavedBlog", listSavedBlog);
        req.setAttribute("listLikedBlog", listLikedBlog);
        req.setAttribute("savedBlogStatus", savedBlogStatus);
        req.setAttribute("blog", blog);
        req.getRequestDispatcher("BlogDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        BlogDAO blogDAO = new BlogDAO();
        /* Comment in the blog */
        if (req.getParameter("btnComment") != null) {
            /* Get parameter */
            String content = req.getParameter("comment");
            String blogID = req.getParameter("id");
            String userID = account.getId();
            String stateID = "1";
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            blogDAO.addComment(blogID, userID, content, sdf.format(currentDate), stateID);
//            req.setAttribute("id", blogID);
            doGet(req, resp);
        }
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
