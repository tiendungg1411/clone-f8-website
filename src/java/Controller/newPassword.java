/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.AccountDAO;
import Model.Account;
import Model.passwordEncryption;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author TIEN DAT
 */
public class newPassword extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet newPassword</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet newPassword at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       AccountDAO a = new AccountDAO();
        // Lấy thông tin cần thiết từ form
        
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        String newPasswordEncrypt = passwordEncryption.encoder(confirmNewPassword);

//            1. ten nguoi dung gui tu jsp ve .
        Account username =(Account)request.getSession().getAttribute("account");
    //    String password = username.getPassword();

//            2. check oldpassword trung password co ten gui ve tu jsp

            if(newPassword.length()<8){
                request.setAttribute("error1", "Mật khẩu cần có ít nhất 8 kí tự");
                request.getRequestDispatcher("newPassword.jsp").forward(request, response);
            }
            else{
            //3. check new password == confirmNewPassword 
            if(!newPassword.equals(confirmNewPassword)) {
                request.setAttribute("error2", "Xác nhận mật khẩu sai");
                request.getRequestDispatcher("newPassword.jsp").forward(request, response);
            }
            else {
                //4. goi ham update va changepassword
                  a.update(new Account(username.getUserName(), newPasswordEncrypt));
//                  request.getRequestDispatcher("home").forward(request, response);
                    //response.sendRedirect("home");
                    response.sendRedirect("home?ResetPasswordStatus=Reset password successfully");
            }
        }
        
    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
