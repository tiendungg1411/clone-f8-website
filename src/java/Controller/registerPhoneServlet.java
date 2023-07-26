/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Account;
import java.net.URLDecoder;
import java.util.Random;

/**
 *
 * @author DELL
 */
@WebServlet(name = "registerPhoneServlet", urlPatterns = {"/registerPhone"})
public class registerPhoneServlet extends HttpServlet {

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
            out.println("<title>Servlet registerPhoneServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registerPhoneServlet at " + request.getContextPath() + "</h1>");
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
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        AccountDAO accountDao = new AccountDAO();

        if (request.getParameter("checkPhone") != null) {
            if (accountDao.checkUserPhone(phone)) {
                String errorMessage1 = "Phone đã tồn tại. Vui lòng sử dụng phone khác!";
                request.setAttribute("errorMessage1", errorMessage1);
                request.setAttribute("phone", phone);
                request.setAttribute("name", name);
                request.getRequestDispatcher("registerPhone.jsp").forward(request, response);
            } else {
                String errorMessage2 = "Phone chưa được sử dụng!";
                request.setAttribute("errorMessage2", errorMessage2);
                request.setAttribute("phone", phone);
                request.setAttribute("name", name);
                request.getRequestDispatcher("registerPhone.jsp").forward(request, response);
            }
        }
        if (request.getParameter("register")!=null) {

            //add to database
            String username = generateRandomUsername();
            accountDao.createPhone(username, phone, name, "image/default_cover.png", "2");
            response.sendRedirect("home");
        }

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
        processRequest(request, response);
    }

    public static String generateRandomUsername() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 6;
        Random random = new Random();
        StringBuilder username = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            username.append(randomChar);
        }

        return username.toString();
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
