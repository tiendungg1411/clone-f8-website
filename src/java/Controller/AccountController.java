/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import Model.Account;
import Model.Setting;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class AccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        /* Check if user is logged in or not */
        if (session.getAttribute("account") == null) {
            req.getRequestDispatcher("registerMainPage.jsp").forward(req, resp);
        } else if (!"1".equals(account.getRoleID())) {
            /* If the user is not an administrator */
            req.getRequestDispatcher("home").forward(req, resp);
        }
        String page_raw = req.getParameter("page");
        int page = 1;
        if (page_raw != null) {
            page = Integer.parseInt(page_raw);
        }

        AccountDAO accountDAO = new AccountDAO();

        /* Update actor */
        if (req.getParameter("btn_update") != null) {
            /* Get user id and role id to update */
            String userId = req.getParameter("uid");
            String roleId = req.getParameter("role");
            /* Update actor by user id */
            accountDAO.updateActor(userId, roleId);
            req.setAttribute("updateStatus", "Cập nhật vai trò của người dùng thành công");
        }

        /* Get list account */
        ArrayList<Account> accounts = accountDAO.getListAccount();
        /* Get list actor */
        ArrayList<Setting> listActor = accountDAO.getListActor();
        String actorId = "0";
        /* If choose to watch each type of actor */
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("2")) {
            actorId = req.getParameter("actor");
            /* If select all */
            if ("0".equals(actorId)) {
                accounts = accountDAO.getListAccount();
            } else {
                /* Get list account by actor id user selected */
                accounts = accountDAO.getListAccountByActorId(actorId);
            }
        }
        int totalPage = accounts.size() / 5;
        if (accounts.size() % 5 != 0) {
            totalPage++;
        }
        ArrayList<Account> listAccount = null;
        if (accounts.size() / 5 != 0 && page == totalPage && !(accounts.size() <= 5)) {
            listAccount = new ArrayList<>(accounts.subList((page - 1) * 5, accounts.size()));
        } else {
            if (!(accounts.size() <= 5)) {
                listAccount = new ArrayList<>(accounts.subList((page - 1) * 5, page * 5));
            }
        }
        if (accounts.size() <= 5) {
            listAccount = accounts;
        }

        /* Set data to JSP */
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("actorId", actorId);
        req.setAttribute("listAccount", listAccount);
        req.setAttribute("listActor", listActor);
        req.getRequestDispatcher("UserList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        /* Setting to receive parameters is Vietnamese */
        req.setCharacterEncoding("UTF-8");
        /* Get parameters */
        String actorID = req.getParameter("actor");
        String txtSearch = req.getParameter("txtSearch");
        ArrayList<Account> listAccount = null;
        /* Search in "All" status */
        if (actorID.equals("0")) {
            listAccount = accountDAO.getAccountByUserName(txtSearch);
        } else {
            /* Search by role */
            listAccount = accountDAO.getAccountByUserNameAndActorID(actorID, txtSearch);
        }
        /* Get list actor */
        ArrayList<Setting> listActor = accountDAO.getListActor();
        /* Set data to JSP */
        req.setAttribute("actorId", actorID);
        req.setAttribute("listAccount", listAccount);
        req.setAttribute("listActor", listActor);
        req.setAttribute("txtSearch", txtSearch);
        req.getRequestDispatcher("UserList.jsp").forward(req, resp);
    }

}
