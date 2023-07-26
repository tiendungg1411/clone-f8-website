/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.AccountDoingQuestion;
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
public class AccountDoingQuestionDAO {
    Connection con = null;
    public ArrayList<AccountDoingQuestion> getAllAccountDoingQuestion() {
        ArrayList<AccountDoingQuestion> listADQ = new ArrayList<>();
        String sql = "select * from AccountDoingQuestion";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listADQ.add(new AccountDoingQuestion(rs.getString(1), rs.getString(2), 
                                        rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            System.out.println("getAllAccountDoingQuestion: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listADQ;
    }
    public ArrayList<AccountDoingQuestion> getAllAccountDoingQuestionByAccountID(String accountID) {
        ArrayList<AccountDoingQuestion> listADQ = new ArrayList<>();
        String sql = "select * from AccountDoingQuestion where accountID = "+accountID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listADQ.add(new AccountDoingQuestion(rs.getString(1), rs.getString(2), 
                                        rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            System.out.println("getAllAccountDoingQuestion: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listADQ;
    }
    //getAccountDoingQuestionByAccountIDAndQuesID
    public AccountDoingQuestion getAccountDoingQuestionByAccountIDAndQuesID(String accountID, String quesID) {
        String sql = "select * from AccountDoingQuestion where accountID = "+accountID+" and quesID = "+quesID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new AccountDoingQuestion(rs.getString(1), rs.getString(2), 
                                        rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("getAllAccountDoingQuestion: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public static void main(String[] args) {
        AccountDoingQuestionDAO a = new AccountDoingQuestionDAO();
        System.out.println(a.getAccountDoingQuestionByAccountIDAndQuesID("1", "1"));
    }
    public boolean checkQuestionIsAnsweredByAccount(String accountID, String questionID) {
        ArrayList<AccountDoingQuestion> listADQ = new ArrayList<>();
        String sql = "select * from AccountDoingQuestion where accountID = "+accountID+" and quesID = "+questionID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkQuestionIsAnsweredByAccount: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public int getMaxID(){
        AccountDoingQuestionDAO a = new AccountDoingQuestionDAO();
        ArrayList<AccountDoingQuestion> listA = a.getAllAccountDoingQuestion();
        int id = 0;
        for (AccountDoingQuestion accountDoingQuestion : listA) {
            int ida = Integer.parseInt(accountDoingQuestion.getId());
            if(id < ida) id = ida;
        }
        return id+1;
    }
    public void insertAccountDoingQuestion(String answer, String trueOrFalse, String accountID, String questionID) {
        ArrayList<AccountDoingQuestion> listADQ = new ArrayList<>();
        String sql = "insert into AccountDoingQuestion(id, answer, trueOrFalse, accountID, quesID) Values(?, ?, ?, ?, ?)";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, getMaxID()+"");
            pstm.setString(2, answer);
            pstm.setString(3, trueOrFalse);
            pstm.setString(4, accountID);
            pstm.setString(5, questionID);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("insertAccountDoingQuestion: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
