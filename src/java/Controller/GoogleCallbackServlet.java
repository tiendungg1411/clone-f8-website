/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import static Controller.FacebookRegisterServlet.generateRandomUsername;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONObject;
import DAO.AccountDAO;
import Model.Account;
import java.util.Random;

/**
 *
 * @author DELL
 */
public class GoogleCallbackServlet extends HttpServlet {

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
            out.println("<title>Servlet GoogleCallbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GoogleCallbackServlet at " + request.getContextPath() + "</h1>");
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
        String code = request.getParameter("code");
        if (code != null) {
            try {
                // Gửi yêu cầu để nhận mã thông báo truy cập từ Google
                String clientId = "724595458253-mjhvmnbttrbhr4l7ip00h8f9c51dap3l.apps.googleusercontent.com";
                String clientSecret = "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";
                String redirectUri = "http://localhost:9999/g4/GoogleCallbackServlet";
                String tokenEndpoint = "https://accounts.google.com/o/oauth2/token";
                String tokenPayload = "code=" + URLEncoder.encode(code, "UTF-8")
                        + "&client_id=" + URLEncoder.encode(clientId, "UTF-8")
                        + "&client_secret=" + URLEncoder.encode(clientSecret, "UTF-8")
                        + "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8")
                        + "&grant_type=authorization_code";

                URL tokenUrl = new URL(tokenEndpoint);
                HttpURLConnection connection = (HttpURLConnection) tokenUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // Gửi payload lấy mã thông báo truy cập
                PrintWriter out = new PrintWriter(connection.getOutputStream());
                out.print(tokenPayload);
                out.flush();

                // Đọc phản hồi từ Google
                String responseBody;
                Scanner scanner = new Scanner(connection.getInputStream());
                if (scanner.hasNext()) {
                    responseBody = scanner.useDelimiter("\\A").next();
                } else {
                    responseBody = "";
                }
                scanner.close();

                // Xử lý phản hồi để nhận mã thông báo truy cập
                String accessToken = "";
                String refreshToken = "";
                int expiresIn = 0;

                // Parse JSON response
                JSONObject json = new JSONObject(responseBody);
                if (json.has("access_token")) {
                    accessToken = json.getString("access_token");
                }
                if (json.has("refresh_token")) {
                    refreshToken = json.getString("refresh_token");
                }
                if (json.has("expires_in")) {
                    expiresIn = json.getInt("expires_in");
                }

                // Gửi yêu cầu để lấy thông tin người dùng từ Google
                String userInfoEndpoint = "https://www.googleapis.com/oauth2/v2/userinfo";
                String userInfoUrl = userInfoEndpoint + "?access_token=" + accessToken;

                URL url = new URL(userInfoUrl);
                HttpURLConnection userInfoConnection = (HttpURLConnection) url.openConnection();
                userInfoConnection.setRequestMethod("GET");

                // Đọc phản hồi từ yêu cầu lấy thông tin người dùng
                String userInfoResponse;
                Scanner userInfoScanner = new Scanner(userInfoConnection.getInputStream());
                if (userInfoScanner.hasNext()) {
                    userInfoResponse = userInfoScanner.useDelimiter("\\A").next();
                } else {
                    userInfoResponse = "";
                }
                userInfoScanner.close();

                // Xử lý phản hồi lấy thông tin người dùng
                String userId = "";
                String name = "";
                String userAvatarUrl = "";

                // Parse JSON response
                JSONObject userInfoJson = new JSONObject(userInfoResponse);
                if (userInfoJson.has("id")) {
                    userId = userInfoJson.getString("id");
                }
                if (userInfoJson.has("name")) {
                    name = userInfoJson.getString("name");
                }
                if (userInfoJson.has("picture")) {
                    userAvatarUrl = userInfoJson.getString("picture");
                }

                //add database
                AccountDAO aDao = new AccountDAO();
                Account acc = aDao.getAccountByGgId(userId);
                if (aDao.checkUserGoogle(userId)) {
                    request.getSession().setAttribute("account", acc);
                    response.sendRedirect("home");
                } else {
                    String username = generateRandomUsername();
                    aDao.createGoogle(username, userId, name, userAvatarUrl, "image/default_cover.png", "2");
                    Account acc1 = aDao.getAccountByGgId(userId);
                    request.getSession().setAttribute("account", acc1);
                    response.sendRedirect("home");
//                    aDao.createGoogle(userName, userId, userAvatarUrl, "image/default_cover.png", "2");
//                    response.sendRedirect("home");// Redirect to success page
                }

                // In ra màn hình thông tin người dùng
//                response.setContentType("text/html;charset=UTF-8");
//                PrintWriter writer = response.getWriter();
//                writer.println("<html>");
//                writer.println("<body>");
//                writer.println("<h1>Thông tin người dùng</h1>");
//                writer.println("<p>Tên: " + userName + "</p>");
//                writer.println("<p>ID: " + userId + "</p>");
//                writer.println("<img src=\"" + userAvatarUrl + "\" alt=\"Avatar\">");
//                writer.println("</body>");
//                writer.println("</html>");
            } catch (Exception e) {
                // Xử lý lỗi
                e.printStackTrace();
                response.sendRedirect("login.jsp");
            }
        } else {
            // Xử lý trường hợp không có mã xác thực
            response.sendRedirect("login.jsp");
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
