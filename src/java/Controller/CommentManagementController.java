/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.BlogDAO;
import Model.Account;
import Model.Blog;
import Model.CommentBlog;
import Model.CommentManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CommentManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        BlogDAO blogDAO = new BlogDAO();
        String page_raw = req.getParameter("page");
        int page = 1;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        }
        /* Check if user is logged in or not */
        if (session.getAttribute("account") == null) {
            req.getRequestDispatcher("registerMainPage.jsp").forward(req, resp);
        } else if (!"1".equals(account.getRoleID())) {
            /* If the user is not an administrator */
            req.getRequestDispatcher("home").forward(req, resp);
        }
        /* If click show comment */
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("1")) {
            /* Update state of comment */
            blogDAO.showComment(req.getParameter("commentID"), req.getParameter("stateID"));
            req.setAttribute("commentStatus", "Hiển thị bình luận thành công");
        } else if (req.getParameter("mod") != null && req.getParameter("mod").equals("2")) {
            /* Click hide comment */
            blogDAO.hideComment(req.getParameter("commentID"), req.getParameter("stateID"));
            req.setAttribute("commentStatus", "Ẩn bình luận thành công");
        }
        /* Comment list */
        ArrayList<CommentManagement> commentList = null;

        /* Filter by state */
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("3") && !req.getParameter("stateFilter").equals("0")) {
            String stateFilter = req.getParameter("stateFilter");
            System.out.println(stateFilter);
            /* Filter comment list by state */
            commentList = blogDAO.getCommentListByState(stateFilter);
            req.setAttribute("selected", stateFilter);
        } else {
            /* Get comment list and information of user comment and blog ID */
            commentList = blogDAO.getCommentListAndInformation();
            req.setAttribute("selected", "0");
        }
        System.out.println(commentList);
        int totalPage = commentList.size() / 5;
        if (commentList.size() % 5 != 0) {
            totalPage++;
        }
        ArrayList<CommentManagement> listPage = null;
        if (commentList.size() / 5 != 0 && page == totalPage && !(commentList.size() <= 5)) {
            listPage = new ArrayList<>(commentList.subList((page - 1) * 5, commentList.size()));
        } else {
            if (!(commentList.size() <= 5)) {
                listPage = new ArrayList<>(commentList.subList((page - 1) * 5, page * 5));
            }
        }
        if (commentList.size() <= 5) {
            listPage = commentList;
        }
        for (CommentManagement comment : listPage) {
            String content = comment.getContent();
            /* Check length of content */
            if (content.length() >= 20) {
                content = content.substring(0, 10) + "...";
            }
            comment.setContent(content);
        }
        /* Set data to JSP */
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("commentList", listPage);
        req.getRequestDispatcher("CommentManagement.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlogDAO blogDAO = new BlogDAO();
        /* Setting to receive parameters is Vietnamese */
        req.setCharacterEncoding("UTF-8");
        /* Get parameter */
        String txtSearch = req.getParameter("txtSearch");
        String page_raw = req.getParameter("page");
        String state = req.getParameter("state");
        int page = 1;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        }
        ArrayList<CommentManagement> commentList = null;
        /* Search all comment list by username */
        if (state.equals("0")) {
            commentList = blogDAO.getAllCommentList(txtSearch);
        } else {
            /* Search comment list by username and state id */
            commentList = blogDAO.getCommentListByUserName(txtSearch, state);
        }
        /* Pagination */
        int totalPage = commentList.size() / 5;
        if (commentList.size() % 5 != 0) {
            totalPage++;
        }
        ArrayList<CommentManagement> listPage = null;
        if (commentList.size() / 5 != 0 && page == totalPage && !(commentList.size() <= 5)) {
            listPage = new ArrayList<>(commentList.subList((page - 1) * 5, commentList.size()));
        } else {
            if (!(commentList.size() <= 5)) {
                listPage = new ArrayList<>(commentList.subList((page - 1) * 5, page * 5));
            }
        }
        if (commentList.size() <= 5) {
            listPage = commentList;
        }
        for (CommentManagement comment : listPage) {
            String content = comment.getContent();
            /* Check length of content */
            if (content.length() >= 20) {
                content = content.substring(0, 10) + "...";
            }
            comment.setContent(content);
        }
        /* Set data to JSP */
        req.setAttribute("selected", state);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("commentList", listPage);
        req.setAttribute("txtSearch", txtSearch);
        req.getRequestDispatcher("CommentManagement.jsp").forward(req, resp);

    }

}
