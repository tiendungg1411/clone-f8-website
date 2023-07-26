/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import Model.NotificationDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class NotificationDetailsDAO extends DBContext {
    
    Connection con = null;

    public List<NotificationDetails> getAll() {
        ArrayList<NotificationDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM notification;";
        AccountDAO aDao = new AccountDAO();
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new NotificationDetails(rs.getInt("id"),
                        rs.getString("Content"),
                        rs.getString("url"),
                        aDao.getInforUser(String.valueOf(rs.getInt("fromAccount"))),
                        aDao.getInforUser(String.valueOf(rs.getInt("recieveAccount"))),
                        rs.getBoolean("isRead")));
            }
        } catch (Exception e) {
            System.out.println("NotificationDetailsDAO - getAll: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<NotificationDetails> getAll(String currentAccount) {
        ArrayList<NotificationDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM notification where recieveAccount = ?;";
        AccountDAO aDao = new AccountDAO();
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, currentAccount);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new NotificationDetails(rs.getInt("id"),
                        rs.getString("Content"),
                        rs.getString("url"),
                        aDao.getInforUser(String.valueOf(rs.getInt("fromAccount"))),
                        aDao.getInforUser(String.valueOf(rs.getInt("recieveAccount"))),
                        rs.getBoolean("isRead")));
            }
        } catch (Exception e) {
            System.out.println("NotificationDetailsDAO - getAllByAccountId: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public void updateStatusNotification(int id, boolean isRead) {
        try {
            String sql = "UPDATE `notification`\n"
                    + "SET\n"
                    + "`isRead` = ?\n"
                    + "WHERE `id` = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setBoolean(1, isRead);
            pstm.setInt(2, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("NotificationDetailsDAO - updateStatus: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
