/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Authentication;

import static Controller.Authentication.loginGithubCallBackController.generateRandomUsername;
import DAO.AccountDAO;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import org.json.JSONObject;

/**
 *
 * @author dell
 */
public class loginFacebookController extends HttpServlet {

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
            out.println("<title>Servlet loginFacebookController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginFacebookController at " + request.getContextPath() + "</h1>");
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
        String appId = "1278529913078590"; // ID ứng dụng của bạn
        String appSecret = "f04d92ccff81f5cade3dcf90e2ece8af"; // App Secret của bạn
        String redirectUri = "http://localhost:9999/g4/loginFacebook"; // URL của trang callback
        String code = request.getParameter("code"); // Lấy mã code từ Facebook

        // Gửi yêu cầu để lấy mã truy cập từ mã code
        String tokenUrl = "https://graph.facebook.com/v12.0/oauth/access_token?client_id=" + appId
                + "&redirect_uri=" + redirectUri
                + "&client_secret=" + appSecret
                + "&code=" + code;
        URL url = new URL(tokenUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Đọc dữ liệu JSON chứa mã truy cập
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        reader.close();

        // Phân tích dữ liệu JSON và lấy mã truy cập
        JSONObject json = new JSONObject(responseBuilder.toString());
        String accessToken = json.getString("access_token");

        // Gửi yêu cầu để lấy thông tin người dùng từ Graph API
        String apiUrl = "https://graph.facebook.com/me?fields=id,name,picture&access_token=" + accessToken;
        URL userUrl = new URL(apiUrl);
        HttpURLConnection userConn = (HttpURLConnection) userUrl.openConnection();
        userConn.setRequestMethod("GET");

        // Đọc dữ liệu JSON chứa thông tin người dùng
        BufferedReader userReader = new BufferedReader(new InputStreamReader(userConn.getInputStream()));
        StringBuilder userResponse = new StringBuilder();
        String userLine;
        while ((userLine = userReader.readLine()) != null) {
            userResponse.append(userLine);
        }
        userReader.close();

        // Phân tích dữ liệu JSON và lấy ID, tên người dùng và link ảnh đại diện
        JSONObject userJson = new JSONObject(userResponse.toString());
        String userId = userJson.getString("id");
        String name = userJson.getString("name");
        String pictureUrl = userJson.getJSONObject("picture").getJSONObject("data").getString("url");

        AccountDAO aDAO = new AccountDAO();
        Account acc = aDAO.getAccountByFbId(userId);
        if (acc != null) {
            request.getSession().setAttribute("account", acc);
            response.sendRedirect("home");
        } else {
//            String username = generateRandomUsername();
//            aDAO.createFacebook(username, userId, name, pictureUrl, "image/default_cover.png", "2");
//            Account acc1 = aDAO.getAccountByGitId(userId);
//            request.getSession().setAttribute("account", acc1);
//            response.sendRedirect("home");
            request.getRequestDispatcher("registerMainPage.jsp").forward(request, response);
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
