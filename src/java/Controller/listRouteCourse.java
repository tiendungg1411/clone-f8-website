/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.RouteTypeDAO;
import Model.Account;
import Model.RouteType;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author TIEN DAT
 */
public class listRouteCourse extends HttpServlet {

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
            out.println("<title>Servlet listRouteCourse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listRouteCourse at " + request.getContextPath() + "</h1>");
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
//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute("account");
//        /* Check if user is logged in or not */
//        if (session.getAttribute("account") == null) {
//            request.getRequestDispatcher("login").forward(request, response);
//        } else if (!"1".equals(account.getRoleID())) {
//            /* If the user is not an administrator */
//            request.getRequestDispatcher("home").forward(request, response);
//        }
        String createRouteTypeStatus = request.getParameter("createRouteTypeStatus");
        if (createRouteTypeStatus != null) {
            request.setAttribute("createRouteTypeStatus", createRouteTypeStatus);
        }
        
        RouteTypeDAO rta = new RouteTypeDAO();
        // Show route type
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            rta.updateStatusOfRouteType(request.getParameter("routeTypeID"), request.getParameter("mod"));
        } else if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) { // Hide route type
            rta.updateStatusOfRouteType(request.getParameter("routeTypeID"), request.getParameter("mod"));
        }

        String routeTypeID = request.getParameter("id");
        rta.deleteRouteTypeByID(routeTypeID);
        ArrayList<RouteType> listRouteType = rta.getAllRouteType();
        request.setAttribute("listRouteType", listRouteType);

        request.getRequestDispatcher("listRouteCourse.jsp").forward(request, response);

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
        RouteTypeDAO rta = new RouteTypeDAO();
        RouteType rt = new RouteType();
        if (request.getParameter("btn_delete") != null) {
            String rtid = request.getParameter("id");
            rt = rta.getRouteTypeByID(rtid);
            request.setAttribute("btn_delete", request.getParameter("btn_delete"));
            request.setAttribute("listRouteCourse", rt);

        }
        doGet(request, response);
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
