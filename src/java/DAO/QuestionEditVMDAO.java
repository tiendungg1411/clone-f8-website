/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Question;
import Model.QuestionEditVM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class QuestionEditVMDAO {

    Connection con = null;

    public QuestionEditVM getQuestionEditVMByQuesID(String quesID) {
        String sql = "select q.id, l.id as lessonID, l.courseID from question q join lesson l on q.lessonID = l.id where q.id = "+quesID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new QuestionEditVM(rs.getString(1), rs.getString(2),
                                             rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("getQuestionEditVMByQuesID: " + e.getMessage());
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
