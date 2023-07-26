/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import DAO.LessonDAO;
import Model.Account;
import Model.CommentBlog;
import Model.QAOfLesson;
import Model.RepQAOfLesson;
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
public class QAOfLessonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        LessonDAO lessonDAO = new LessonDAO();
        AccountDAO accountDAO = new AccountDAO();
        /* Check user is logged in or not */
        if (session.getAttribute("account") == null) {
            req.getRequestDispatcher("registerMainPage.jsp").forward(req, resp);
        }
        Account account = (Account) session.getAttribute("account");
        
        String lessonId;
        String lessonName;
        /* Get data from the session */
        if (req.getParameter("id") != null) {
            lessonId = req.getParameter("id");
            lessonName = req.getParameter("name");
        } else {
            /* Get parameter */
            lessonId = (String) req.getSession().getAttribute("id");
            lessonName = (String) req.getSession().getAttribute("name");
        }
        /* Get list Q&A of lesson */
        ArrayList<QAOfLesson> listQA = lessonDAO.getListQAOfLesson(lessonId);
        /* Sort comment by time */
        Collections.sort(listQA, new Comparator<QAOfLesson>() {
            @Override
            public int compare(QAOfLesson q1, QAOfLesson q2) {
                String dbf = q1.getDate();
                String dat = getCurrentTime();
                int second1 = getSecond(dbf, dat);
                dbf = q2.getDate();
                int second2 = getSecond(dbf, dat);
                return second1 - second2;
            }
        });
        /* Get time */
        for (QAOfLesson qa : listQA) {
            String dbf = qa.getDate();
            String dat = getCurrentTime();
            qa.setDate(getTime(dbf, dat));
        }
        
        /* Get list rep Q&A of lesson */
        ArrayList<RepQAOfLesson> listRepQA = lessonDAO.getListRepQAOfLesson(lessonId);
         /* Get time */
        for (RepQAOfLesson rep : listRepQA) {
            String dbf = rep.getDate();
            String dat = getCurrentTime();
            rep.setDate(getTime(dbf, dat));
        }
        int numOfListQA = listQA.size() + listRepQA.size();
        /* Get list account */
        ArrayList<Account> listAccount = accountDAO.getListAccount();
        
        /* Set data to JSP */
        req.setAttribute("account", account);
        req.setAttribute("numOfListQA", numOfListQA);
        req.setAttribute("listQA", listQA);
        req.setAttribute("listRepQA", listRepQA);
        req.setAttribute("listAccount", listAccount);
        req.setAttribute("lessonName", lessonName);
        req.setAttribute("lessonId", lessonId);
        req.getRequestDispatcher("QAOfLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        LessonDAO lessonDAO = new LessonDAO();
        /* Get userID */
        String userID = account.getId();
        /* Get parameter */
        String lessonID = req.getParameter("lessonID");
        String lessonName = req.getParameter("lessonName");
        /* Comment */
        if (req.getParameter("btnComment") != null) {
            String contentComment = req.getParameter("comment");
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            lessonDAO.addQAOfLesson(lessonID, contentComment, sdf.format(currentDate), userID);

        }

        /* Rep comment */
        if (req.getParameter("btnRep") != null) {
            String contentRep = req.getParameter("repContent");
            String parentID = req.getParameter("parentID");
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            lessonDAO.addRepQAOfLesson(lessonID, contentRep, sdf.format(currentDate), userID, parentID);
        }
        /* Set data */
        req.getSession().setAttribute("id", lessonID);
        req.getSession().setAttribute("name", lessonName);
        /* Back to jsp */
        doGet(req, resp);
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
