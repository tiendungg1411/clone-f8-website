/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ListRouteTypeItemDAO;
import DAO.RouteTypeItemDAO;
import Model.RouteType;
        import Model.listRouteTypeItem;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author TIEN DAT
 */
public class listRouteTypeItemServlet extends HttpServlet {

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
            out.println("<title>Servlet listRouteCoureItem</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listRouteCoureItem at " + request.getContextPath() + "</h1>");
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
        String createRouteCourseItemStatus = request.getParameter("createRouteTypeStatus");
        if (createRouteCourseItemStatus != null) {
            request.setAttribute("createRouteCourseItemStatus", createRouteCourseItemStatus);
        }
//               processRequest(request, response);
        String id = request.getParameter("id");
        String mode = request.getParameter("mode");
         ListRouteTypeItemDAO listRouteTypeItemDao = new ListRouteTypeItemDAO();
        if(mode!=null && mode.equals("delete")){
            RouteTypeItemDAO rtidao = new RouteTypeItemDAO();
            rtidao.deleteRoutyTypeItemById(id);
        }
        // Filter 
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("1") && !request.getParameter("stateFilter").equals("0")) {
            String stateFilter = request.getParameter("stateFilter");
            ArrayList<listRouteTypeItem> listRouteTypeItem = listRouteTypeItemDao.getRouteTypeItemDetailByID(stateFilter);
            request.setAttribute("listRouteTypeItem", listRouteTypeItem);
            request.setAttribute("selected", stateFilter);
        } else {

            ArrayList<listRouteTypeItem> listRouteTypeItem = listRouteTypeItemDao.getAllRouteTypeItemDetail();
            request.setAttribute("listRouteTypeItem", listRouteTypeItem);
            request.setAttribute("selected", "0");
        }

        // Get list route type
        ArrayList<RouteType> listRouteType = listRouteTypeItemDao.getListRouteType();

        request.setAttribute("listRouteType", listRouteType);
        request.getRequestDispatcher("listRouteCourseItem.jsp").forward(request, response);
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
        String id = request.getParameter("id");
          doGet(request, response);
    
    }
    public int getMaxBlogID(ArrayList<listRouteTypeItem> listRouteTypeItem) {
        int max = 0;
//        for (Blog blog : listBlog) {
//            int id = Integer.parseInt(blog.getId());
//            if (max < id) {
//                max = id;
//            }
//        }
for (listRouteTypeItem routeTypeItem : listRouteTypeItem) {
            int id = Integer.parseInt(routeTypeItem.getId());
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
