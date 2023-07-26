/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CourseDetailDAO;
import Model.CourseDetail;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
//String projectPath = System.getProperty("user.dir");
//@MultipartConfig(
//    location = System.getProperty("user.dir") + "/uploads"
//)
@MultipartConfig(
        //        location = "C:\\Users\\Admin\\Downloads\\g4-Iteration_1_Submit\\g4-Iteration_1_Submit\\web\\image",
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class createCourseServlet extends HttpServlet {

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
            out.println("<title>Servlet createCourseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createCourseServlet at " + request.getContextPath() + "</h1>");
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
        if (request.getSession().getAttribute("account") == null) {
            request.getRequestDispatcher("home").forward(request, response);
        }
        request.setAttribute("createc", "abc");
        request.getRequestDispatcher("createCourse.jsp").forward(request, response);
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
//        String imageLocation = getServletContext().getInitParameter("ImageLocation");
//        processRequest(request, response);
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String routeType = request.getParameter("routeType");
        String level = request.getParameter("level");
        String detailCourseDes = request.getParameter("detailCourseDes");
        int pricee = 0;
        request.setAttribute("createc", "abc");
        if (title.equals("")) {
            request.setAttribute("error1", "Bạn chưa nhập tiêu đề");
            request.getRequestDispatcher("createCourse.jsp").forward(request, response);
            return;
        }
        if (price.equals("")) {
            request.setAttribute("error2", "Bạn chưa nhập giá");
            request.getRequestDispatcher("createCourse.jsp").forward(request, response);
            return;
        }
        try {
            pricee = Integer.parseInt(price);
        } catch (Exception e) {
            request.setAttribute("error3", "Giá không phải là 1 chuỗi");
            request.getRequestDispatcher("createCourse.jsp").forward(request, response);
            return;
        }
        if (routeType == null) {
            request.setAttribute("error4", "Bạn chưa nhập lộ trình");
            request.getRequestDispatcher("createCourse.jsp").forward(request, response);
            return;
        }
        if (level.equals("")) {
            request.setAttribute("error5", "Bạn chưa nhập mức độ");
            request.getRequestDispatcher("createCourse.jsp").forward(request, response);
            return;
        }
        if (detailCourseDes.equals("")) {
            request.setAttribute("error6", "Bạn chưa nhập chi tiết khóa học");
            request.getRequestDispatcher("createCourse.jsp").forward(request, response);
            return;
        }
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
            request.setAttribute("createc", "abc");
            request.setAttribute("error7", "Bạn chưa nhập ảnh");
            request.getRequestDispatcher("createCourse.jsp").forward(request, response);
            return;
        }
        CourseDetailDAO c = new CourseDetailDAO();
        ArrayList<CourseDetail> listCourse = c.getAllProCourseAndFreeCourse();
        for (CourseDetail courseDetail : listCourse) {
            if (title.equalsIgnoreCase(courseDetail.getTitle())) {
                request.setAttribute("error8", "Khóa học đã tồn tại");
                request.getRequestDispatcher("createCourse.jsp").forward(request, response);
                return;
            }
        }
        CourseDetail course = new CourseDetail(0 + "", title, pricee, 0, routeType, 0 + "", level, 0, 0 + "", detailCourseDes, "./image/" + filePart.getSubmittedFileName(), null);
        c.insertCourse(course);
        c.insertCourseDetail(course);
        response.sendRedirect("home");
    }
    public static void copyImage(String srcS, String destS) {
        System.out.println("");
        Path sourcePath = Paths.get(srcS);
        Path destPath = Paths.get(destS);

        // Copy the image file
        try {
            Files.copy(sourcePath, destPath);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Image copied successfully!");
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
