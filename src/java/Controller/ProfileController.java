/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.createCourseServlet.copyImage;
import DAO.AccountDAO;
import DAO.BlogDAO;
import DAO.CourseDetailDAO;
import Model.Account;
import Model.AccountGoogle;
import Model.CommentBlog;
import Model.CourseAccount;
import Model.CourseDetail;
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
import java.util.ArrayList;

/**
 *
 * @author HP
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Check if user is logged in or not */
        HttpSession session = req.getSession();
        if (session.getAttribute("account") == null) {
            req.getRequestDispatcher("registerMainPage.jsp").forward(req, resp);
        }
        /* Get parameter*/
        String userId = req.getParameter("id");
        System.out.println(userId);
        // Get user
        if (userId == null) {
            Account account = (Account) session.getAttribute("account");
            userId = account.getId();

        }
        /* Get data */
        AccountDAO accountDAO = new AccountDAO();
        CourseDetailDAO courseDetailDAO = new CourseDetailDAO();
        BlogDAO blogDAO = new BlogDAO();
        Account inforUser = accountDAO.getInforUser(userId);
        /* Get course list registered of account by user id */
        ArrayList<CourseDetail> listCourseOfAccount = courseDetailDAO.getCourseRegisteredOfAccount(userId);
        System.out.println(listCourseOfAccount);
        /* Get list comment by userId */
        ArrayList<CommentBlog> listComment = blogDAO.getListCommentByUserId(userId);
        /* Set data to JSP */
        req.setAttribute("listCourseOfAccount", listCourseOfAccount);
        req.setAttribute("listComment", listComment);
        req.setAttribute("inforUser", inforUser);
        req.getRequestDispatcher("ViewProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Get userID */
        HttpSession session = req.getSession();
        if (session.getAttribute("account") == null) {
            req.getRequestDispatcher("registerMainPage.jsp").forward(req, resp);
        }
        Account account = (Account) session.getAttribute("account");
        AccountDAO accountDAO = new AccountDAO();
        /* Get parameter */
        String userId = req.getParameter("uid");
        String btnSave = req.getParameter("save");
        /* Check if user click save img */
        Part filePart = null;
        if (btnSave != null) {
            try {
                filePart = req.getPart("file");
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
                    /* Update cover image of user by user id */
                    accountDAO.updateCoverImg(userId, "./image/" + filePart.getSubmittedFileName());
                    doGet(req, resp);
                }
            } catch (Exception e) {
                doGet(req, resp);
            }

        } else {
            /* Back to JSP */
            doGet(req, resp);
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
