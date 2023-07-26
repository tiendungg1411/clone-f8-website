/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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
public class QuestionDAO {

    Connection con = null;

    public ArrayList<Question> getAllQuestion() {
        ArrayList<Question> listQ = new ArrayList<>();
        String sql = "select * from Question";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listQ.add(new Question(rs.getString(1), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("getAllQuestion: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listQ;
    }

    public Question getQuestionByID(String id) {
        String sql = "select * from Question where id = " + id;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Question(rs.getString(1), rs.getString(2),
                        rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("getQuestionByID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    //Get all Question by Detail
    public ArrayList<Question> getAllDetailQuestion() {
        ArrayList<Question> listQ = new ArrayList<>();
        String sql = "select q.id, q.content, q.explain from question q";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listQ.add(new Question(rs.getString(1), rs.getString(2),
                                            rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println("getAllQuestion: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listQ;
    }

    //getAllDetailQuestionByQuizID
    public ArrayList<Question> getAllDetailQuestionByQuizID(String quizID) {
        ArrayList<Question> listQ = new ArrayList<>();
        String sql = "select q.id, c.title, l.name, q.content, q.explain  from Question q join lessonQuestion lq on q.id = lq.questionID join lesson l on lq.lessonID = l.id join course c on l.courseid = c.id where l.type = 'practice' and lq.lessonID = "+quizID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listQ.add(new Question(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            System.out.println("getAllDetailQuestionByQuizID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listQ;
    }

    //getAllDetailQuestionByLessonID
    public ArrayList<Question> getAllDetailQuestionByLessonID(String lessonID) {
        ArrayList<Question> listQ = new ArrayList<>();
        String sql = "select q.id, c.title, l.name, q.content, q.explain  from Question q join lessonQuestion lq on q.id = lq.questionID join lesson l on lq.lessonID = l.id join course c on l.courseid = c.id where l.type = 'practice' and q.lessonID = " + lessonID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listQ.add(new Question(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            System.out.println("getAllQuestion: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listQ;
    }

    //addQuestion
    public void addQuestion(String id, String content, String explain) {
        String sql = "insert into question(id, content, `explain`) values(?, ?, ?)";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.setString(2, content);
            pstm.setString(3, explain);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addQuestion: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //updateQuestionByQuesID
    public void updateQuestionByQuesID(String quesID, String content, String explain) {
        String sql = "Update Question set content = ?, `explain` = ? where id = ?";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, content);
            pstm.setString(2, explain);
            pstm.setString(3, quesID);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateQuestionByQuesID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Question> getAllQuestionByLessonID(String lessonID) {
        ArrayList<Question> listQ = new ArrayList<>();
        String sql = "select q.id, q.content, q.explain, lq.lessonID from Question q join  lessonQuestion lq on q.id = lq.questionID where lq.lessonID = "+lessonID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listQ.add(new Question(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println("getAllQuestionByLessonID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listQ;
    }

    //deleteQuestionByID
    public void deleteQuestionByID(String quesID) {
        String sql = "delete from Question where id = " + quesID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteQuestionByID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        QuestionDAO q = new QuestionDAO();
        ArrayList<Question> listQ = q.getAllQuestion();
        for (Question question : listQ) {
            System.out.println(question);
        }
    }
}
