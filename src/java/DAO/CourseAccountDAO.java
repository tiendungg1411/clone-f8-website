/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import Model.CourseAccount;
import Model.CourseDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class CourseAccountDAO extends DBContext {
    
    Connection con = null;

    public void registCourse(int id, int accountID, int courseID, String date, int stateId) {
        try {
            String sql = "INSERT INTO `courseaccount`\n"
                    + "(`id`,\n"
                    + "`accountID`,\n"
                    + "`courseID`,"
                    + "timeRegistration,"
                    + "idState)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?,\n"
                    + "?,"
                    + "?,"
                    + "?);";
            con = DBContext.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, accountID);
            stm.setInt(3, courseID);
            stm.setString(4, date);
            stm.setInt(5, stateId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getMaxID() {
        try {
            String sql = "SELECT max(id) as 'total' FROM courseaccount;";
            con = DBContext.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("getAllCourse: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public ArrayList<CourseAccount> getAll(int accountID) {
        ArrayList<CourseAccount> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM courseaccount where accountID = ?;";
            con = DBContext.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, accountID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                list.add(new CourseAccount(String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getInt("accountID")),
                        String.valueOf(rs.getInt("courseID"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public boolean isRegisterCourse(int courseID, int accountID) {
        try {
            String sql = "SELECT * FROM courseaccount where accountID = ? and courseID = ?";
            con = DBContext.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
