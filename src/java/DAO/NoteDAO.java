/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import Model.Lesson;
import Model.Note;
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
public class NoteDAO extends DBContext {

    Connection con = null;
    
    public void insert(Note note) {
        try {
            String sql = "INSERT INTO `note`\n"
                    + "(`id`,\n"
                    + "`detail`,\n"
                    + "`date`,\n"
                    + "`lessonID`,\n"
                    + "`accountID`)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?);";
            con = DBContext.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, note.getId());
            stm.setString(2, note.getDetail());
            stm.setDate(3, note.getDate());
            stm.setInt(4, Integer.parseInt(note.getLessonID()));
            stm.setInt(5, Integer.parseInt(note.getAccountID()));
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

    public void update(Note note) {
        try {
            String sql = "UPDATE `note`\n"
                    + "SET\n"
                    + "`detail` = ?\n"
                    + "WHERE `id` = ?;";
            con = DBContext.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, note.getDetail());
            stm.setInt(2, note.getId());
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

    public int getTotalNote() {
        try {
            String sql = "SELECT max(id) as 'total' FROM note;";
            con = DBContext.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public ArrayList<Note> getAll(int selectedID, int accountID) {
        ArrayList<Note> list = new ArrayList<>();
        String sql = "SELECT * FROM note where lessonID = ? and accountID = ?\n"
                + "order by date asc";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, selectedID);
            pstm.setInt(2, accountID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Note(rs.getInt("id"),
                        rs.getString("detail"),
                        rs.getDate("date"),
                        String.valueOf(rs.getInt("lessonID")),
                        String.valueOf(rs.getInt("accountID"))));
            }
        } catch (Exception e) {
            System.out.println("getAllBlogByAccountID: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public void delete(int noteDelete) {
        try {
            String sql = "DELETE FROM `note`\n"
                    + "WHERE id =  ?";
            con = DBContext.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, noteDelete);
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
}
