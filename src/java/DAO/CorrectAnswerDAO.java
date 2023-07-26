/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Answer;
import Model.CorrectAnswer;
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
public class CorrectAnswerDAO {

    Connection con = null;

    public ArrayList<CorrectAnswer> getAllCorrectAnswer() {
        ArrayList<CorrectAnswer> listC = new ArrayList<>();
        String sql = "select * from CorrectAnswer";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listC.add(new CorrectAnswer(rs.getString(1), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("getAllCorrectAnswer: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listC;
    }

    //get max answer id
    public int getMaxCorrectAnswerID() {
        int maxId = 0;
        CorrectAnswerDAO c = new CorrectAnswerDAO();
        ArrayList<CorrectAnswer> listC = c.getAllCorrectAnswer();
        for (CorrectAnswer answer : listC) {
            int id = Integer.parseInt(answer.getId());
            if(maxId < id) maxId = id;
        }
        return maxId;
    }

    //addCorrectAnswer
    public void addCorrectAnswer(String Content, String quesID) {
        int correctAnswerID = getMaxCorrectAnswerID() + 1;
        String sql = "insert into CorrectAnswer(id, content, quesID) values(?, ?, ?)";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, correctAnswerID + "");
            correctAnswerID++;
            pstm.setString(2, Content);
            pstm.setString(3, quesID);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addCorrectAnswer: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Delete CorrectAns by quesID
    public void deleteCorrectAnswerByQuesID(String quesID) {
        String sql = "delete from CorrectAnswer where quesID = "+quesID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteCorrectAnswerByQuesID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //getCorrectAnswerByQuesID
    public CorrectAnswer getCorrectAnswerByQuesID(String quesID) {
        String sql = "select * from CorrectAnswer where quesID = " + quesID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return (new CorrectAnswer(rs.getString(1), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("getCorrectAnswerByQuesID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
