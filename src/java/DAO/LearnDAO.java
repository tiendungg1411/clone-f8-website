/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import Model.Answer;
import Model.CourseLearnWhat;
import Model.CourseRequirement;
import Model.CourseDetail;
import Model.Course;
import Model.Chapter;
import Model.Lesson;
import Model.LessonVideo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class LearnDAO {

    Connection con = null;

    //Khai bao cac thanh phan xu ly DB
    Connection cnn;//Ket noi DB
    Statement stm;//Thuc thi cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;//Luu tru va xu ly du lieu

    public ArrayList<CourseDetail> getCourseList() {
        ArrayList<CourseDetail> listCourse = new ArrayList<>();
        String sql = "SELECT c.id, c.title, c.price, c.isPublished, cd.level FROM Course c LEFT JOIN CourseDetail cd ON c.id = cd.courseId";
        try {
            pstm = DBContext.getConnection().prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                listCourse.add(new CourseDetail(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4), rs.getString(5)));
            }
        } catch (Exception e) {
            System.out.println("getCourseList: " + e.getMessage());
        }
        return listCourse;
    }

    public ArrayList<Course> getCourse(String courseId) {
        ArrayList<Course> listCourse = new ArrayList<>();
        String sql = "select * from Course where id=?";
        try {
            pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(1, courseId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String numOfPeopleJoin = rs.getString(3);
                String price = rs.getString(4);
                String routeID = rs.getString(5);
                listCourse.add(new Course(id, title, numOfPeopleJoin, price, routeID));
            }
        } catch (Exception e) {
            System.out.println("getCourse: " + e.getMessage());
        }
        return listCourse;
    }

    public ArrayList<CourseDetail> getCourseDes(String courseId) {
        ArrayList<CourseDetail> listCourseDes = new ArrayList<>();
        String sql = "select * from CourseDetail where courseId=?";
        try {
            pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(1, courseId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                listCourseDes.add(new CourseDetail(String.valueOf(rs.getInt("id")),
                        rs.getString("level"),
                        Integer.parseInt(rs.getString("sumLesson")),
                        rs.getString("time"),
                        rs.getString("detailCourseDes"),
                        rs.getString("image"),
                        String.valueOf(rs.getInt("courseID"))));
            }
        } catch (Exception e) {
            System.out.println("getCourseDes: " + e.getMessage());
        }
        return listCourseDes;
    }

    public ArrayList<CourseLearnWhat> getAllTarget(String courseId) {
        ArrayList<CourseLearnWhat> listCourseLearnWhat = new ArrayList<>();
        String sql = "select * from CourseLearnWhat where courseId=?";
        try {
            pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(1, courseId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String courId = rs.getString(3);
                listCourseLearnWhat.add(new CourseLearnWhat(id, name, courId));
            }
        } catch (Exception e) {
            System.out.println("getAllTarget: " + e.getMessage());
        }
        return listCourseLearnWhat;
    }

    public ArrayList<CourseRequirement> getAllRequirement(String courseId) {
        ArrayList<CourseRequirement> listCourseRequirement = new ArrayList<>();
        String sql = "select * from CourseRequirement where courseId=?";
        try {
            pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(1, courseId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String courId = rs.getString(3);
                listCourseRequirement.add(new CourseRequirement(id, name, courId));
            }
        } catch (Exception e) {
            System.out.println("getAllRequirement: " + e.getMessage());
        }
        return listCourseRequirement;
    }

    public ArrayList<Chapter> getAllChapter(String courseId) {
        ArrayList<Chapter> listChapter = new ArrayList<>();
        String sql = "select id,name from chapter where courseID=?";
        try {
            pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(1, courseId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                listChapter.add(new Chapter(id, name));
            }
        } catch (Exception e) {
            System.out.println("getAllChapter: " + e.getMessage());
        }
        return listChapter;
    }

    public ArrayList<Lesson> getLessonsByCourse(String courseId) {
        ArrayList<Lesson> listLesson = new ArrayList<>();
        String sql = "select * from Lesson where courseId=?";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, courseId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String content = rs.getString(3);
                String link = rs.getString(4);
                String retry = rs.getString(5);
                String date = rs.getString(6);
                String numOfLikes = rs.getString(7);
                String type = rs.getString(8);
                String courID = rs.getString(9);
                String chapterID = rs.getString(10);
                listLesson.add(new Lesson(id, name, content, link, retry, date, numOfLikes, type, courID, chapterID));
            }
        } catch (SQLException e) {
            System.out.println("getAllChapter: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listLesson;
    }

    public static void main(String[] args) {
        LearnDAO la = new LearnDAO();
//        ArrayList<CourseDetail> listCourse = la.getCourseList();
//        System.out.println(listCourse);
//        LearnDAO la = new LearnDAO();
        String courseId = "1";
        ArrayList<Lesson> listL = la.getLessonByChapterId("1");
        System.out.println(listL);
//        Course inforCourse = la.getCourseInfor(courseId);
//        System.out.println(inforCourse.getTitle());
//        ArrayList<Course> listCourse = la.getCourse(courseId);
//        ArrayList<CourseDetail> listCourseDes = la.getCourseDes(courseId);
//        ArrayList<CourseLearnWhat> ListTarget= la.getAllTarget(courseId);
//        ArrayList<CourseRequirement> ListRequirement= la.getAllRequirement(courseId);
//        ArrayList<Chapter> listChapter= la.getAllChapter(courseId);
//        System.out.println(listCourse);
//        System.out.println(listCourseDes);
//        System.out.println(ListTarget);
//        System.out.println(ListRequirement);
//        System.out.println(listChapter);
    }

    public Course getCourseInfor(String courseId) {
        Course inforCourse = new Course();
        String sql = "select * from Course where id=?";
        try {
            pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(1, courseId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                inforCourse.setId(rs.getString(1));
                inforCourse.setTitle(rs.getString(2));
                inforCourse.setNumOfPeopleJoin(rs.getString(3));
                inforCourse.setPrice(rs.getString(4));
                inforCourse.setRouteID(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("getCourse: " + e.getMessage());
        }
        return inforCourse;
    }

    public void deleteCourseByCID(String courseID) {
        try {
            String strDelete = "delete from course where id = ? ";
            pstm = getConnection().prepareStatement(strDelete);
            pstm.setString(1, courseID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteCourseByCID: " + e.getMessage());
        }
    }

    public int getTotalLesson(String courseId) {
        try {
            String sql = "SELECT count(*) as 'total' FROM lesson where courseID = ?";
            pstm = getConnection().prepareStatement(sql);
            pstm.setString(1, courseId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("Total lesson: " + e.getMessage());
        }
        return -1;
    }

    public void updateURL(String url, String lessonId) {
        try {
            String strUpdate = "UPDATE Lesson\n"
                    + "SET link = ?\n"
                    + "WHERE id = ?;";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(strUpdate);
            pstm.setString(1, url);
            pstm.setString(2, lessonId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateURL: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateType(String type, String lessonId) {
        try {
            String strUpdate = "UPDATE Lesson\n"
                    + "SET type = ?\n"
                    + "WHERE id = ?;";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(strUpdate);
            pstm.setString(1, type);
            pstm.setString(2, lessonId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateURL: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateDes(String description, String cid) {
        try {
            String strUpdate = "UPDATE CourseDetail\n"
                    + "SET detailCourseDes = ?\n"
                    + "WHERE courseID = ?;";
            con = DBContext.getConnection();
            pstm = con.prepareStatement(strUpdate);
            pstm.setString(1, description);
            pstm.setString(2, cid);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateDes: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteAllTargetFromCID(String cid) {
        String sql = "delete from CourseLearnWhat where courseID = " + cid;
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteAllTargetFromCID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteAllRequirementFromCID(String cid) {
        String sql = "delete from CourseRequirement where courseID = " + cid;
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteAllTargetFromCID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getMaxChapterID() {
        int maxId = 0;
        LearnDAO a = new LearnDAO();
        ArrayList<Chapter> listA = a.getAllChapter();
        for (Chapter chapter : listA) {
            int id = Integer.parseInt(chapter.getId());
            if (maxId < id) {
                maxId = id;
            }
        }
        return maxId;
    }

    public void addChapter(String chap, String cid) {
        int answerID = getMaxChapterID() + 1;
        String sql = "insert into Chapter(id, name, courseID) values(?, ?, ?)";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, answerID + "");
            answerID++;
            pstm.setString(2, chap);
            pstm.setString(3, cid);
            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("addChapter: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private ArrayList<Chapter> getAllChapter() {
        ArrayList<Chapter> listA = new ArrayList<>();
        String sql = "select * from Chapter";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                listA.add(new Chapter(rs.getString(1), rs.getString(2)));
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

    public void addTargetByCID(String[] target, String cid) {
        String sql = "insert into CourseLearnWhat(content, courseID) values(?, ?)";
        try {
            for (String string : target) {
                con = DBContext.getConnection();
                pstm = con.prepareStatement(sql);
                pstm.setString(1, string);
                pstm.setString(2, cid);
                pstm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("addTargetByCID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addRequimentByCID(String[] requirement, String cid) {
        String sql = "insert into CourseRequirement(content, courseID) values(?, ?)";
        try {
            for (String string : requirement) {
                con = DBContext.getConnection();
                pstm = con.prepareStatement(sql);
                pstm.setString(1, string);
                pstm.setString(2, cid);
                pstm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("addTargetByCID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<String> getAllChapterName(String cid) {
        ArrayList<String> la = new ArrayList<>();
        String sql = "select name from chapter";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                la.add(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println("getAllChapterName: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return la;
    }

    public void deleteChapterByName(String chap, String cid) {
        String sql = "DELETE FROM Chapter WHERE name = ? AND courseID =?";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, chap);
            pstm.setString(2, cid);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteChapterByName: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Lesson> getLessonByCourseIDAndChapterID( String chapterID) {
        ArrayList<Lesson> listLesson = new ArrayList<>();
        String sql = "select * from Lesson where chapterID=?";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, chapterID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String content = rs.getString(3);
                String link = rs.getString(4);
                String retry = rs.getString(5);
                String date = rs.getString(6);
                String numOfLikes = rs.getString(7);
                String type = rs.getString(8);
                String courID = rs.getString(9);
                String chapterId = rs.getString(10);
                listLesson.add(new Lesson(id, name, content, link, retry, date, numOfLikes, type, courID, chapterId));
            }
        } catch (SQLException e) {
            System.out.println("getAllChapter: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listLesson;
    }

    public ArrayList<String> getAllLessonName(String chapterID) {
        ArrayList<String> la = new ArrayList<>();
        String sql = "select name from Lesson";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                la.add(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println("getAllChapterName: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return la;
    }
    
    public int getMaxLessonID() {
        int maxId = 0;
        LearnDAO a = new LearnDAO();
        ArrayList<Lesson> listA = a.getAllLesson();
        for (Lesson chapter : listA) {
            int id = Integer.parseInt(chapter.getId());
            if (maxId < id) {
                maxId = id;
            }
        }
        return maxId;
    }
    private ArrayList<Lesson> getAllLesson() {
        ArrayList<Lesson> listA = new ArrayList<>();
        String sql = "select * from Lesson";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                listA.add(new Lesson(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("getAllLesson: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listA;
    }
    public void addLesson(String less, String cid, String chapterID) {
        int answerID = getMaxLessonID()+ 1;
        String sql = "insert into Lesson(id, name, date, numOfLikes, type, courseID, chapterID) values(?, ?, NOW() ,?,?,?,?)";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, answerID + "");
            answerID++;
            pstm.setString(2, less);
            pstm.setString(3, "0");
            pstm.setString(4, "default");
            pstm.setString(5, cid);
            pstm.setString(6, chapterID);
            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("addLesson: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteLessonByName(String less, String chapterID) {
        String sql = "DELETE FROM Lesson WHERE name = ? AND chapterID =?";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, less);
            pstm.setString(2, chapterID);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteLessonByName: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Chapter> getChapterByChapterID(String chapterID) {
        ArrayList<Chapter> listA = new ArrayList<>();
        String sql = "select * from Chapter where id=?";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, chapterID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                listA.add(new Chapter(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("getAllLesson: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listA;
    }

    public ArrayList<Lesson> getLessonByChapterId(String chapterId) {
        ArrayList<Lesson> listA = new ArrayList<>();
        String sql = "select * from Lesson where chapterId=?";
        try {
            con = DBContext.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, chapterId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                listA.add(new Lesson(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println("getAllLesson: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listA;
    }

    public void showCourse(String parameter) {
        try {
            String strUpdate = "UPDATE Course \n"
                    + "SET isPublished = true\n"
                    + "where id = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strUpdate);
            pstm.setString(1, parameter);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("showCourse: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void hideCourse(String parameter) {
        try {
            String strUpdate = "UPDATE Course \n"
                    + "SET isPublished = false\n"
                    + "where id = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strUpdate);
            pstm.setString(1, parameter);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("hideCourse: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
