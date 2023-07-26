/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Authentication;

import Controller.registerEmailServlet;
import DAO.AccountDAO;
import Model.EmailSend;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author TIEN DAT
 */
public class passwordReset extends HttpServlet {

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
            out.println("<title>Servlet resetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet resetPassword at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String email = request.getParameter("email");
        String confirmationCode = request.getParameter("confirmationCode");
        HttpSession session = request.getSession();
        if (request.getParameter("sendCodeBtn") != null) {
            String randomCode = generateRandomCode();
            session.setAttribute("confirmationCode", randomCode);
            EmailSend e = new EmailSend();
            try {
                e.SendVerificationCode(email, randomCode);
            } catch (MessagingException ex) {
                Logger.getLogger(registerEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Loi gui email");
            }
            request.setAttribute("email", email);
            // Redirect back to the registration page
            request.getRequestDispatcher("passwordReset.jsp").forward(request, response);
//            response.sendRedirect("views/Authentication/resetPassword");


        } else if (request.getParameter("resetBt") != null) {
            String savedCode = (String) session.getAttribute("confirmationCode");
            if (confirmationCode.equals(savedCode)) {
                // Registration successful
                // Perform further actions (e.g., store user data, send welcome email)

                AccountDAO accountDAO = new AccountDAO();
                if (!accountDAO.checkUserEmail(email)) {
                    String errorMessage1 = "Email chưa được sử dụng";
                    request.setAttribute("errorMessage1", errorMessage1);
                     request.getRequestDispatcher("passwordReset.jsp").forward(request, response);
//                    response.sendRedirect("views/Authentication/resetPassword");
                } else {
                    response.sendRedirect("newPassword.jsp");// Redirect to success page
                }

            } else {
                request.setAttribute("email", email);
                String errorMessage2 = "Mã xác nhận không đúng ";
                request.setAttribute("errorMessage2", errorMessage2);
                request.getRequestDispatcher("passwordReset.jsp").forward(request, response);
//                response.sendRedirect("views/Authentication/resetPassword");
            }
        }

    }

    private String generateRandomCode() {
        // Generate a random confirmation code with 6 digits
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Generate a 6-digit code
        return String.valueOf(code);
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
