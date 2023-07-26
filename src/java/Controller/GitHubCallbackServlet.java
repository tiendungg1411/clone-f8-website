/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.AccountDAO;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
public class GitHubCallbackServlet extends HttpServlet {
   
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
            out.println("<title>Servlet GitHubCallbackServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GitHubCallbackServlet at " + request.getContextPath () + "</h1>");
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
//        processRequest(request, response);
        String clientId = "Iv1.3d21107577acf849";
        String clientSecret = "5021672d3d24f16beac0fd1a4d0e6da637cfa9a6";
        String redirectUri = "http://localhost:9999/g4/GitHubCallbackServlet"; // Thay thế bằng đường dẫn tới servlet GitHubCallbackServlet

        String code = request.getParameter("code");

        // Thực hiện trao đổi mã xác thực để lấy thông tin người dùng
        String tokenUrl = "https://github.com/login/oauth/access_token?" +
                "client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&code=" + code +
                "&redirect_uri=" + redirectUri;

        // Gửi yêu cầu POST để lấy mã truy cập
        URL url = new URL(tokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream responseStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
            StringBuilder responseData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseData.append(line);
            }
            reader.close();

            String responseString = responseData.toString();
            JSONObject jsonResponse = new JSONObject(responseString);
            String accessToken = jsonResponse.getString("access_token");

            // Sử dụng mã truy cập để lấy thông tin người dùng
            String userUrl = "https://api.github.com/user";
            String userAuthHeader = "token " + accessToken;

            URL userApiUrl = new URL(userUrl);
            HttpURLConnection userConnection = (HttpURLConnection) userApiUrl.openConnection();
            userConnection.setRequestProperty("Authorization", userAuthHeader);

            int userResponseCode = userConnection.getResponseCode();
            if (userResponseCode == HttpURLConnection.HTTP_OK) {
                InputStream userResponseStream = userConnection.getInputStream();
                BufferedReader userReader = new BufferedReader(new InputStreamReader(userResponseStream));
                StringBuilder userResponseData = new StringBuilder();
                String userLine;
                while ((userLine = userReader.readLine()) != null) {
                    userResponseData.append(userLine);
                }
                userReader.close();

                String userResponseString = userResponseData.toString();
                JSONObject userJsonResponse = new JSONObject(userResponseString);

                // Lấy thông tin từ JSON response
                int githubId = userJsonResponse.optInt("id");
                String name = userJsonResponse.getString("login");
                String avatarUrl = userJsonResponse.getString("avatar_url");
                
                String githubIdnew = Integer.toString(githubId);
                //add database
                AccountDAO aDao=new AccountDAO();
                Account acc = aDao.getAccountByGitId(githubIdnew);
                if (aDao.checkUserGithub(githubIdnew)) {
                    request.getSession().setAttribute("account", acc);
                    response.sendRedirect("home");
                } else {

//                    String username=generateRandomUsername();
//                    aDao.createGithub(username, githubIdnew,name, avatarUrl, "image/default_cover.png","2");
//                    Account acc1 = aDao.getAccountByGitId(githubIdnew);
//                    request.getSession().setAttribute("account", acc1);
//                    response.sendRedirect("home");
                response.sendRedirect("home");// Redirect to success page
                }
//                response.setContentType("text/html");
//                response.getWriter().println("<h1>Thông tin người dùng:</h1>");
//                response.getWriter().println("<p>GitHub ID: " + githubId + "</p>");
//                response.getWriter().println("<p>Tên người dùng: " + username + "</p>");
//                response.getWriter().println("<p>Link ảnh đại diện: <img src='" + avatarUrl + "'></p>");
            } else {
                // Xử lý lỗi khi không thể lấy thông tin người dùng
                response.getWriter().println("Lỗi: Không thể lấy thông tin người dùng từ GitHub.");
            }
        } else {
            // Xử lý lỗi khi không thể lấy mã truy cập
            response.getWriter().println("Lỗi: Không thể lấy mã truy cập từ GitHub.");
        }
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
