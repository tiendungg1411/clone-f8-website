/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import Model.SavedBlog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SavedBlogDAO {
    
    Connection con = null;

    public ArrayList<SavedBlog> getAllSavedBlogByAccID(String userID) {
        ArrayList<SavedBlog> listSavedBlog = new ArrayList<>();
        String sql = "select * from savedBlog where userID = " + userID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listSavedBlog.add(new SavedBlog(rs.getString(1), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("getAllSavedBlogByAccID: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SavedBlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listSavedBlog;
    }

    //getSavedBlogByID
    public SavedBlog getSavedBlogByBIDAndUID(String id, String uid) {
        String sql = "select * from savedBlog where blogID = " + id + " and userID = " + uid;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new SavedBlog(rs.getString(1), rs.getString(2),
                        rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("getSavedBlogByBIDAndUID: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<SavedBlog> getAllSavedBlog() {
        ArrayList<SavedBlog> listSavedBlog = new ArrayList<>();
        String sql = "select * from savedBlog ";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listSavedBlog.add(new SavedBlog(rs.getString(1), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("getAllSavedBlog: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listSavedBlog;
    }

    public int getMaxSavedBlogID() {
        int max = 0;
        ArrayList<SavedBlog> listSavedBlog = new ArrayList<>();
        String sql = "select * from savedBlog ";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listSavedBlog.add(new SavedBlog(rs.getString(1), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("getAllSavedBlog: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (SavedBlog savedBlog : listSavedBlog) {
            int id = Integer.parseInt(savedBlog.getId());
            if (max < id) {
                max = id;
            }
        }
        return max;
    }

    public void removeSavedBlogByBlogIDAndAccID(String blogID, String userID) {
        String sql = "delete from SavedBlog where blogID = ? and userID = ?";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, blogID);
            pstm.setString(2, userID);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("removeSavedBlogByBlogIDAndAccID: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SavedBlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addSavedBlogByBlogIDAndAccID(String blogID, String userID) {
        boolean flag = false;
        ArrayList<SavedBlog> listS = getAllSavedBlog();
        for (SavedBlog savedBlog : listS) {
            if (savedBlog.getUserID().equals(userID) && savedBlog.getBlogID().equals(blogID)) {
                flag = true;
            }
        }
        if (!flag) {
            String sql = "insert into SavedBlog(id, blogID, userID) Values(?, ?, ?)";
            try {
                con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setString(1, (getMaxSavedBlogID() + 1) + "");
                pstm.setString(2, blogID);
                pstm.setString(3, userID);
                pstm.executeUpdate();
            } catch (Exception e) {
                System.out.println("addSavedBlogByBlogIDAndAccID: " + e.getMessage());
            }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            return true;
        }
        return false;
    }

    public boolean addSavedBlogByBlogIDAndAccIDAndTime(String blogID, String userID, String time) {
        boolean flag = false;
        ArrayList<SavedBlog> listS = getAllSavedBlog();
        for (SavedBlog savedBlog : listS) {
            if (savedBlog.getUserID().equals(userID) && savedBlog.getBlogID().equals(blogID)) {
                flag = true;
            }
        }
        if (!flag) {
            String sql = "insert into SavedBlog(id, blogID, userID, time) Values(?, ?, ?, ?)";
            try {
                con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setString(1, (getMaxSavedBlogID() + 1) + "");
                pstm.setString(2, blogID);
                pstm.setString(3, userID);
                pstm.setString(4, time);
                pstm.executeUpdate();
            } catch (Exception e) {
                System.out.println("addSavedBlogByBlogIDAndAccIDAndTime: " + e.getMessage());
            }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SavedBlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
       ArrayList<SavedBlog> s = new SavedBlogDAO().getAllSavedBlog();
        System.out.println(s);
        
    }

}
