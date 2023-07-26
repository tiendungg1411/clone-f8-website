/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import Model.Account;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author HP
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class SettingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Check if user is logged in or not */
        HttpSession session = req.getSession();
        if (session.getAttribute("account") == null) {
            req.getRequestDispatcher("registerMainPage.jsp").forward(req, resp);
        }

        Account accountSes = (Account) session.getAttribute("account");
        /* Get parameter*/
        String userId = accountSes.getId();
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getInforUser(userId);

        /* Set data to JSP */
        req.setAttribute("account", account);
        req.getRequestDispatcher("AccountSettings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        String userId = req.getParameter("uid");
        String name = "";
        String bio = "";
        String username = "";
        String email = "";
        String phoneNumber = "";
        Part filePart = null;
        /* If user save data */
        if (req.getParameter("btnSave") != null) {
            try {
                name = req.getParameter("full_name");
                username = req.getParameter("username");
                bio = req.getParameter("bio");
                email = req.getParameter("email");
                phoneNumber = req.getParameter("phone_number");
                filePart = req.getPart("file");
                /* Not change avatar */
                if (filePart == null) {
                    doGet(req, resp);
                } else {
                    String fileName = filePart.getSubmittedFileName();

                    // Save the file to disk
                    // Get the ServletContext object
                    ServletContext context = req.getServletContext();

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
                    String srcS = realPath + "\\image\\" + fileName;
                    String desS = realPath.replace("\\build\\web\\", "") + "\\web\\image\\" + fileName;
                    copyImage(srcS, desS);
                    out.flush();
                    out.close();
                    /* Update */
                    accountDAO.updateInforUser(userId, name, bio, "./image/" + filePart.getSubmittedFileName(), username, email, phoneNumber);
                    /* Update to session */
                    Account account = accountDAO.getInforUser(userId);
                    req.getSession().setAttribute("account", account);
                    doGet(req, resp);
                }
            } catch (Exception e) {
                accountDAO.updateNameAndBio(userId, name, bio, username, email, phoneNumber);
                /* Update to session */
                Account account = accountDAO.getInforUser(userId);
                req.getSession().setAttribute("account", account);
                doGet(req, resp);
            }

        }
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
}
