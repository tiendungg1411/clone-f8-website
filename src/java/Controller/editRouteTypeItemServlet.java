/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CourseDetailDAO;
import DAO.ListRouteTypeItemDAO;
import DAO.RouteTypeDAO;
import DAO.RouteTypeItemAndCourseDAO;
import DAO.RouteTypeItemDAO;
import Model.Course;
import Model.CourseDetail;
import Model.RouteType;
import Model.RouteTypeItem;
import Model.listRouteTypeItem;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author TIEN DAT
 */
public class editRouteTypeItemServlet extends HttpServlet {

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
            out.println("<title>Servlet editRouteTypeItem</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editRouteTypeItem at " + request.getContextPath() + "</h1>");
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
        //delete mode
        String listRouteTypeID = request.getParameter("id");
        RouteTypeDAO routeTypeDao = new RouteTypeDAO();
        RouteTypeItemDAO routeTypeItemDAO = new RouteTypeItemDAO();
        CourseDetailDAO courseDetailDao = new CourseDetailDAO();
        //Get routeTypeItem by id:
        String rtid = routeTypeItemDAO.getRouteTypeIDByRouteTypeItemID(listRouteTypeID);
        RouteType rt = routeTypeDao.getRouteTypeByID(rtid);
        //Get list Course by RouteTypeItemID
        ArrayList<CourseDetail> listCourse = courseDetailDao.getlistCourseByRouteTypeItemID(listRouteTypeID);

//        ListRouteTypeItemDAO routeTypeItemDAO = new ListRouteTypeItemDAO();
        ArrayList<CourseDetail> listCourseDetail = courseDetailDao.getAllPublishedCourse();
        ArrayList<RouteType> routeTypeList = routeTypeDao.getAllRouteType();
        request.setAttribute("listCourseDetail", listCourseDetail);
        request.setAttribute("routeTypeList", routeTypeList);
        RouteTypeItem routeTypeItem = routeTypeItemDAO.getRouteTypeItemByID(listRouteTypeID);
        request.setAttribute("routeTypeItem", routeTypeItem);

        request.setAttribute("routeType", rt);
        request.setAttribute("listCourse", listCourse);
        request.getRequestDispatcher("editRouteCourseItem.jsp").forward(request, response);
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
        String routeTypeItemID = request.getParameter("routeTypeID");
        String routeTypeID = request.getParameter("routeTypeName");
        String courseName = request.getParameter("courseName");
        String des = request.getParameter("content");
        String[] listTick = request.getParameterValues("courseChecked");

        RouteTypeItemDAO routeTypeItemDAO = new RouteTypeItemDAO();
        //Edit routeTypeID courseName des
        routeTypeItemDAO.EditRouteTypeItem(routeTypeID, courseName, des, routeTypeItemID);

        //Edit listTick
        //Get list Course by RouteTypeItemID
        CourseDetailDAO courseDetailDao = new CourseDetailDAO();
        ArrayList<CourseDetail> listCourse = courseDetailDao.getlistCourseByRouteTypeItemID(routeTypeItemID);

        //
        ArrayList<String> listCourseIDRemove = new ArrayList<>();
        ArrayList<String> listCourseIDAdd = new ArrayList<>();
        ArrayList<String> listTemp = new ArrayList<>();
        RouteTypeItemAndCourseDAO rtiacd = new RouteTypeItemAndCourseDAO();

        if (listTick != null && listTick.length > 0) {
            //listQuestionIDRemove
            for (CourseDetail course : listCourse) {
                boolean flag = false;
                for (String q : listTick) {
                    if (q.equals(course.getId())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    listCourseIDRemove.add(course.getId());
                }
            }
            //listQuestionIDAdd
            for (String q : listTick) {
                boolean flag = false;
                for (CourseDetail course : listCourse) {
                    if (q.equals(course.getId())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    listCourseIDAdd.add(q);
                }
            }
            //add to RouteTypeItemAndCourse
            for (String string : listCourseIDAdd) {
                rtiacd.addRouteTypeItemAndCourse(routeTypeItemID, string);
            }
            //set lessonID to question
            for (String string : listCourseIDRemove) {
                rtiacd.removeRouteTypeItemAndCourse(routeTypeItemID, string);
            }
        }else{
            for (CourseDetail course : listCourse) {
                rtiacd.removeRouteTypeItemAndCourse(routeTypeItemID, course.getId());
            }
        }
        response.sendRedirect("listRouteCourseItem?createRouteTypeStatus=edit routetype successfully");
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
