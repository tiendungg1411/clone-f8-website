/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Answer;
import Model.Question;
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
public class AnswerDAO {

    Connection con = null;

    public ArrayList<Answer> getAllAnswer() {
        ArrayList<Answer> listA = new ArrayList<>();
        String sql = "select * from Answer";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listA.add(new Answer(rs.getString(1), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("getAllAnswer: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listA;
    }

    //get max answer id
    public int getMaxAnswerID() {
        int maxId = 0;
        AnswerDAO a = new AnswerDAO();
        ArrayList<Answer> listA = a.getAllAnswer();
        for (Answer answer : listA) {
            int id = Integer.parseInt(answer.getId());
            if(maxId < id) maxId = id;
        }
        return maxId;
    }

    //addAnswer
    public void addAnswer(String answer[], String quesID) {
        int answerID = getMaxAnswerID() + 1;
        String sql = "insert into Answer(id, content, quesID) values(?, ?, ?)";
        try {
            for (String string : answer) {
                con = DBContext.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setString(1, answerID + "");
                answerID++;
                pstm.setString(2, string);
                pstm.setString(3, quesID);
                pstm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("addAnswer: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Delete all Answer by QuesID
    public void deleteAllAnswerByQuesID(String quesID) {
        String sql = "delete from Answer where quesID = " + quesID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteAllAnswerByQuesID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //getAllAnswerByQuesID
    public ArrayList<Answer> getAllAnswerByQuesID(String quesID) {
        ArrayList<Answer> listA = new ArrayList<>();
        String sql = "select * from Answer where quesID = " + quesID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listA.add(new Answer(rs.getString(1), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("getAllAnswerByQuesID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listA;
    }
}
