/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class LessonQuestionDAO {
    Connection con = null;
    
    public void addLessonQuestion(String lessonID, String questionID) {
        String sql = "insert into LessonQuestion(lessonID, questionID) values(?, ?)";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, lessonID);
            pstm.setString(2, questionID);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addLessonQuestion: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void removeLessonQuestion(String lessonID, String questionID) {
        String sql = "Delete from LessonQuestion where lessonID = ? and questionID = ?";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, lessonID);
            pstm.setString(2, questionID);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("removeLessonQuestion: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
