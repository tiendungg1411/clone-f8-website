/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.BlogDAO;
import Model.Account;
import Model.Blog;
import Model.CommentManagement;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
@WebServlet(name = "blogManagementServlet", urlPatterns = {"/blogManagementServlet"})
public class blogManagementServlet extends HttpServlet {

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
            out.println("<title>Servlet blogManagementServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet blogManagementServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        BlogDAO blogDAO = new BlogDAO();
        String page_raw = request.getParameter("page");
        int page = 1;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        }
        /* Check if user is logged in or not */
        if (session.getAttribute("account") == null) {
            request.getRequestDispatcher("registerMainPage.jsp").forward(request, response);
        } else if (!"1".equals(account.getRoleID())) {
            /* If the user is not an administrator */
            request.getRequestDispatcher("home").forward(request, response);
        }

        /* If click show Blog */        //chua sua
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            /* Update state of Blog */
            blogDAO.showBlog(request.getParameter("commentID"), request.getParameter("stateID"));
            request.setAttribute("commentStatus", "Hiển thị blog thành công");
        } else if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) {
            /* Click hide Blog */
            blogDAO.hideBlog(request.getParameter("commentID"), request.getParameter("stateID"));
            request.setAttribute("commentStatus", "Ẩn blog thành công");
        }

        /* Blog list */
        ArrayList<Blog> blogList = null;

        /* Filter by state */
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("3") && !request.getParameter("stateFilter").equals("0")) {
            String stateFilter = request.getParameter("stateFilter");
            System.out.println(stateFilter);
            /* Filter blog list by state */
            blogList = blogDAO.getBlogByState(stateFilter);
            request.setAttribute("selected", stateFilter);
        } else {
            /* Get blog list and information of user blog and blog ID */
            blogList = blogDAO.getBlogListAndInformation();
            request.setAttribute("selected", "0");
        }
        System.out.println(blogList);
        int totalPage = blogList.size() / 5;
        if (blogList.size() % 5 != 0) {
            totalPage++;
        }
        ArrayList<Blog> listPage = null;
        if (blogList.size() / 5 != 0 && page == totalPage && !(blogList.size() <= 5)) {
            listPage = new ArrayList<>(blogList.subList((page - 1) * 5, blogList.size()));
        } else {
            if (!(blogList.size() <= 5)) {
                listPage = new ArrayList<>(blogList.subList((page - 1) * 5, page * 5));
            }
        }
        if (blogList.size() <= 5) {
            listPage = blogList;
        }
        for (Blog blog : listPage) {
            String content = blog.getContent();
            /* Check length of content */
            if (content.length() >= 20) {
                content = content.substring(0, 10) + "...";
            }
            blog.setContent(content);
        }
        /* Set data to JSP */
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("blogList", listPage);
        request.getRequestDispatcher("BlogManagement.jsp").forward(request, response);
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
        BlogDAO blogDAO = new BlogDAO();
        /* Setting to receive parameters is Vietnamese */
        request.setCharacterEncoding("UTF-8");
        /* Get parameter */
        String txtSearch = request.getParameter("txtSearch");
        String page_raw = request.getParameter("page");
        String state = request.getParameter("state");
        int page = 1;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        }
        ArrayList<Blog> blogList = null;
        /* Search all blog list by username */
        if (state.equals("0")) {
            blogList = blogDAO.getAllBlogList(txtSearch);
        } else {
            /* Search blog list by username and state id */
            blogList = blogDAO.getBlogListByUserName(txtSearch, state);
        }
        /* Pagination */
        int totalPage = blogList.size() / 5;
        if (blogList.size() % 5 != 0) {
            totalPage++;
        }
        ArrayList<Blog> listPage = null;
        if (blogList.size() / 5 != 0 && page == totalPage && !(blogList.size() <= 5)) {
            listPage = new ArrayList<>(blogList.subList((page - 1) * 5, blogList.size()));
        } else {
            if (!(blogList.size() <= 5)) {
                listPage = new ArrayList<>(blogList.subList((page - 1) * 5, page * 5));
            }
        }
        if (blogList.size() <= 5) {
            listPage = blogList;
        }
        for (Blog comment : listPage) {
            String content = comment.getContent();
            /* Check length of content */
            if (content.length() >= 20) {
                content = content.substring(0, 10) + "...";
            }
            comment.setContent(content);
        }
        /* Set data to JSP */
        request.setAttribute("selected", state);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("blogList", listPage);
        request.setAttribute("txtSearch", txtSearch);
        request.getRequestDispatcher("BlogManagement.jsp").forward(request, response);
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
