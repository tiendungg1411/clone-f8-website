/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import Model.EmailSend;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import Model.passwordEncryption;

/**
 *
 * @author DELL
 */
@WebServlet(name = "registerEmailServlet", urlPatterns = {"/registerEmail"})
public class registerEmailServlet extends HttpServlet {

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
            out.println("<title>Servlet registerEmailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registerEmailServlet at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String confirmationCode = request.getParameter("confirmationCode");
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        if(request.getParameter("checkEmail")!=null){
            if (accountDAO.checkUserEmail(email)) {
                    String errorMessage1 = "Email đã tồn tại. Vui lòng sử dụng email khác!";
                    request.setAttribute("errorMessage1", errorMessage1);
                    request.setAttribute("email", email);
                    request.setAttribute("username", name);
                    request.setAttribute("password", pass);
                    request.getRequestDispatcher("registerEmail.jsp").forward(request, response);
                }else{
                String errorMessage2 = "Email chưa được sử dụng!";
                    request.setAttribute("errorMessage2", errorMessage2);
                    request.setAttribute("email", email);
                    request.setAttribute("username", name);
                    request.setAttribute("password", pass);
                    request.getRequestDispatcher("registerEmail.jsp").forward(request, response);
            }
        }
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
            request.setAttribute("username", name);
            request.setAttribute("password", pass);

            // Redirect back to the registration page
            request.getRequestDispatcher("registerEmail.jsp").forward(request, response);
        } else if (request.getParameter("registerBt") != null) {
            
            
            String savedCode = (String) session.getAttribute("confirmationCode");
            if (confirmationCode.equals(savedCode)) {
                // Registration successful
                // Perform further actions (e.g., store user data, send welcome email)

                
                if (accountDAO.checkUserEmail(email)) {
                    String errorMessage = "Email đã tồn tại. Vui lòng sử dụng email khác!";
                    request.setAttribute("errorMessage", errorMessage);
                    request.setAttribute("email", email);
                    request.setAttribute("username", name);
                    request.setAttribute("password", pass);
                    request.getRequestDispatcher("registerEmail.jsp").forward(request, response);
                } else {
                    //ma hoa mat khau de luu tru
                    String encrypted = passwordEncryption.encoder(pass);
                    //add to databse
                    String username = generateRandomUsername();
                    accountDAO.createEmail(username, encrypted, email, name, "image/default_cover.png", "2");

                    response.sendRedirect("home");// Redirect to success page
                }

            } else {
                request.setAttribute("email", email);
                request.setAttribute("username", name);
                request.setAttribute("password", pass);
                String errorMessage = "Mã xác nhận không đúng. Xin thử lại!";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("registerEmail.jsp").forward(request, response);
            }
        }

    }

    private String generateRandomCode() {
        // Generate a random confirmation code with 6 digits
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Generate a 6-digit code
        return String.valueOf(code);
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
