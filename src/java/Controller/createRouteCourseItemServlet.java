/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CourseDetailDAO;
import DAO.RouteTypeDAO;
import DAO.RouteTypeItemAndCourseDAO;
import DAO.RouteTypeItemDAO;
import Model.CourseDetail;
import Model.RouteType;
import Model.RouteTypeItem;
import Model.RouteTypeItemAndCourse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class createRouteCourseItemServlet extends HttpServlet {

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
            out.println("<title>Servlet createRouteCourseItemServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createRouteCourseItemServlet at " + request.getContextPath() + "</h1>");
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
        CourseDetailDAO courseDetailDao = new CourseDetailDAO();
        RouteTypeDAO routeTypeDao = new RouteTypeDAO();
        ArrayList<CourseDetail> listCourseDetail = courseDetailDao.getAllPublishedCourse();
        ArrayList<RouteType> routeTypeList = routeTypeDao.getAllRouteType();
        request.setAttribute("listCourseDetail", listCourseDetail);
        request.setAttribute("routeTypeList", routeTypeList);
        request.getRequestDispatcher("createRouteCourseItem.jsp").forward(request, response);
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
        RouteTypeItemDAO routeTypeItemDao = new RouteTypeItemDAO();
        RouteTypeItemAndCourseDAO routeTypeItemAndCourseDao = new RouteTypeItemAndCourseDAO();
        ArrayList<RouteTypeItem> listR = routeTypeItemDao.getAllRouteTypeItem();
        ArrayList<RouteTypeItemAndCourse> listRAC = routeTypeItemAndCourseDao.getAllRouteTypeItemAndCourse();
        String RACID = getMaxRouteTypeItemAndCourseID(listRAC) + 1 + "";
        String id = getMaxRouteTypeItemID(listR) + 1 + "";
        String routeTypeID = request.getParameter("routeTypeName");
        String routeTypeItemName = request.getParameter("courseName");
        //check if routeTypeItem is existed
        
        String content = request.getParameter("content");
        String[] listTick = request.getParameterValues("courseTitle");
        //Insert to DB
        //Insert into RouteTypeItem table
        RouteTypeItem routeTypeItem = new RouteTypeItem(id, routeTypeItemName, content, routeTypeID);
        routeTypeItemDao.insertRouteTypeItem(routeTypeItem);
        //Insert into RouteTypeItemAndCourse table
        routeTypeItemAndCourseDao.insertRouteTypeItemAndCourse(RACID, id, listTick);
        response.sendRedirect("listRouteCourseItem?createRouteTypeStatus=Create route course item successfully");
    }

    public int getMaxRouteTypeItemID(ArrayList<RouteTypeItem> listR) {
        int max = 0;
        for (RouteTypeItem routeType : listR) {
            int id = Integer.parseInt(routeType.getId());
            if (max < id) {
                max = id;
            }
        }
        return max;
    }

    public int getMaxRouteTypeItemAndCourseID(ArrayList<RouteTypeItemAndCourse> listR) {
        int max = 0;
        for (RouteTypeItemAndCourse routeType : listR) {
            int id = Integer.parseInt(routeType.getId());
            if (max < id) {
                max = id;
            }
        }
        return max;
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
