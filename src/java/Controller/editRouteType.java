/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import static Controller.createRouteCourseServlet.copyImage;
import DAO.RouteTypeDAO;
import Model.RouteType;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author TIEN DAT
 */
@MultipartConfig(
        //        location = "C:\\Users\\Admin\\Downloads\\g4-Iteration_1_Submit\\g4-Iteration_1_Submit\\web\\image",
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class editRouteType extends HttpServlet {

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
            out.println("<title>Servlet editListRouteTypeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editListRouteTypeServlet at " + request.getContextPath() + "</h1>");
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
        String listRouteTypeID = request.getParameter("id");
        RouteTypeDAO routeTypeDAO = new RouteTypeDAO();

        RouteType routeType = routeTypeDAO.getRouteTypeByID(listRouteTypeID);
        request.setAttribute("routeType", routeType);
//        request.setAttribute("routeName", routeType.getName());
//        request.setAttribute("image", routeType.getImage());
//        request.setAttribute("content1", routeType.getDescription1());
//        request.setAttribute("content2", routeType.getDescription2());
        request.getRequestDispatcher("editRouteType.jsp").forward(request, response);

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

        String listRouteTypeID = request.getParameter("routeTypeID");
        RouteTypeDAO routeTypeDAO = new RouteTypeDAO();

        String routeName = request.getParameter("routeName");
//        String image = request.getParameter("image");
        String content1 = request.getParameter("content1");
        String content2 = request.getParameter("content2");
         String status = request.getParameter("status");
         
         //Xu ly anh
         Part filePart = null;
        try {  
            filePart = request.getPart("image");
            String fileName = filePart.getSubmittedFileName();

            // Save the file to disk
            // Get the ServletContext object
            ServletContext context = request.getServletContext();

            // Get the real path of the project
            String realPath = context.getRealPath("/");
            System.out.println(realPath);
            OutputStream out = new FileOutputStream(new File(realPath + "\\image\\" + fileName));
            InputStream fileContent = filePart.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            String srcS = realPath+"\\image\\"+fileName;
            String desS = realPath.replace("\\build\\web\\", "") + "\\web\\image\\"+fileName;
            copyImage(srcS, desS);
            out.flush();
            out.close();
        } catch (Exception e) {
//            request.setAttribute("error7", "Bạn chưa nhập ảnh");
//            request.getRequestDispatcher("createRouteCourse.jsp").forward(request, response);
            return;
        }
        String img = "./image/" + filePart.getSubmittedFileName();
         
        RouteType routeType = new RouteType(listRouteTypeID, routeName, img, content1, content2,status);
//         routeTypeDAO.insertRouteType(routeType);
        routeTypeDAO.editRouteTypeByID(routeType);
        response.sendRedirect("listRouteCourse?createRouteTypeStatus=edit routetype successfully");
    }

    public int getMaxRouteTypeID(ArrayList<RouteType> listR) {
        int max = 0;
        for (RouteType routeType : listR) {
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
