/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.CourseDetailDAO;
import Model.Account;
import Model.CourseDetail;
import Model.CourseState;
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
public class MyOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CourseDetailDAO courseDetailDAO = new CourseDetailDAO();
        /* If the user is not logged in */
        if (session.getAttribute("account") == null) {
            resp.sendRedirect("login");
        }
        /* Get user id from session */
        Account account = (Account) session.getAttribute("account");
        String userID = account.getId();
        /* If the user unsubscribes the course */
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("1")) {
            String courseId = req.getParameter("id");
            String stateId = req.getParameter("stateId");
            courseDetailDAO.unSubscribesCourse(courseId, userID, stateId);
            req.setAttribute("unSubcribesStatus", "Hủy đăng ký thành công");
        }
        /* Filter by state */
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("2") && !req.getParameter("stateFilter").equals("0")) {
            String stateFilter = req.getParameter("stateFilter");
            ArrayList<CourseDetail> listCourse = courseDetailDAO.getCourseByState(account.getId(), stateFilter);
            req.setAttribute("selected", stateFilter);
            req.setAttribute("listCourse", listCourse);
        } else {
            /* Get the list of registered courses of the account */
            ArrayList<CourseDetail> listCourse = courseDetailDAO.getCourseOfAccount(userID);
            req.setAttribute("selected", "0");
            req.setAttribute("listCourse", listCourse);
        }

        /* Get state of course */
        ArrayList<CourseState> stateList = courseDetailDAO.getStateOfCourse();
        /* Set data to JSP */
        req.setAttribute("stateList", stateList);
        req.getRequestDispatcher("MyOrder.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        CourseDetailDAO courseDetailDAO = new CourseDetailDAO();
        /* Setting to receive parameters is Vietnamese */
        req.setCharacterEncoding("UTF-8");
        /* Get parameter */
        String state = req.getParameter("state");
        String txtSearch = req.getParameter("txtSearch");
        System.out.println(state);
        System.out.println(txtSearch);
        ArrayList<CourseDetail> courseList = null;
        /* Search all course by title */
        if (state.equals("0")) {
            courseList = courseDetailDAO.courseSearchAllByTitle(account.getId(), txtSearch);
        } else {
            /* Course search by title and state id */
            courseList = courseDetailDAO.courseSearchByTitle(account.getId(), txtSearch, state);
        }
        /* Get state of course */
        ArrayList<CourseState> stateList = courseDetailDAO.getStateOfCourse();
        /* Set data to JSP */
        req.setAttribute("selected", state);
        req.setAttribute("txtSearch", txtSearch);
        req.setAttribute("stateList", stateList);
        req.setAttribute("listCourse", courseList);
        req.getRequestDispatcher("MyOrder.jsp").forward(req, resp);
    }

}
