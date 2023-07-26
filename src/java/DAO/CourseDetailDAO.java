/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Course;
import Model.CourseDetail;
import Model.CourseState;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class CourseDetailDAO {

    Connection con = null;

    public ArrayList<CourseDetail> getAllPublishedCourse() {
        ArrayList<CourseDetail> listC = new ArrayList<>();
        String sql = "select c.id as cid, title, price, numOfPeopleJoin, routeID, cd.id as courseDetailID, level, "
                + "cd.time, sumLesson, detailCourseDes, image, courseID from Course c join "
                + "CourseDetail cd on c.id = cd.courseID where c.price = 0 and isPublished = true";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listC.add(new CourseDetail(rs.getString(1), rs.getString(2),
                        Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        Integer.parseInt(rs.getString(8)), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12)));
            }
        } catch (Exception e) {
            System.out.println("getAllCourse: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listC;
    }

    //getlistCourseByRouteTypeItemID
    public ArrayList<CourseDetail> getlistCourseByRouteTypeItemID(String routeTypeItemID) {
        ArrayList<CourseDetail> listC = new ArrayList<>();
        String sql = "select c.id as cid, title, price, numOfPeopleJoin, routeID, cd.id as courseDetailID, level, \n"
                + "cd.time, sumLesson, detailCourseDes, image, cd.courseID from Course c join \n"
                + "CourseDetail cd on c.id = cd.courseID join RouteTypeItemAndCourse rtiac on rtiac.CourseID = c.id \n"
                + "join RouteTypeItems rti on rtiac.routeTypeItemID = rti.id\n"
                + "where c.price = 0 and isPublished = true and rti.id = " + routeTypeItemID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listC.add(new CourseDetail(rs.getString(1), rs.getString(2),
                        Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        Integer.parseInt(rs.getString(8)), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12)));
            }
        } catch (Exception e) {
            System.out.println("getlistCourseByRouteTypeItemID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listC;
    }

    public ArrayList<Course> getAllCourse() {
        ArrayList<Course> listC = new ArrayList<>();
        String sql = "select * from Course";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listC.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            System.out.println("getAllCourse: " + e.getMessage());
        }
        return listC;
    }

    public ArrayList<CourseDetail> getAllProCourseAndFreeCourse() {
        ArrayList<CourseDetail> listC = new ArrayList<>();
        String sql = "select c.id as cid, title, price, numOfPeopleJoin, routeID, cd.id as courseDetailID, level, "
                + "cd.time, sumLesson, detailCourseDes, image, courseID from Course c join CourseDetail cd on c.id = cd.courseID";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listC.add(new CourseDetail(rs.getString(1), rs.getString(2),
                        Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        Integer.parseInt(rs.getString(8)), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12)));
            }
        } catch (Exception e) {
            System.out.println("getAllProCourseAndFreeCourse: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listC;
    }

    public ArrayList<CourseDetail> getAllCourseByTitle(String txt) {
        ArrayList<CourseDetail> listC = new ArrayList<>();
        String sql = "select c.id as cid, title, price, numOfPeopleJoin, routeID, cd.id as courseDetailID, level, "
                + "cd.time, sumLesson, detailCourseDes, image, courseID from Course c join CourseDetail cd on c.id = cd.courseID where c.title like ?";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, "%" + txt + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listC.add(new CourseDetail(rs.getString(1), rs.getString(2),
                        Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        Integer.parseInt(rs.getString(8)), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12)));
            }
        } catch (Exception e) {
            System.out.println("getAllCourseByTitle: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listC;
    }

    public ArrayList<CourseDetail> getAllPublishedProCourse() {
        ArrayList<CourseDetail> listC = new ArrayList<>();
        String sql = "select c.id as cid, title, price, numOfPeopleJoin, routeID, cd.id as courseDetailID, level, "
                + "cd.time, sumLesson, detailCourseDes, image, courseID from Course c join CourseDetail cd on c.id = cd.courseID where c.price > 0 and isPublished = true";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listC.add(new CourseDetail(rs.getString(1), rs.getString(2),
                        Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        Integer.parseInt(rs.getString(8)), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12)));
            }
        } catch (Exception e) {
            System.out.println("getAllProCourse: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listC;
    }

    public ArrayList<CourseDetail> getAllCourseMinus0() {
        ArrayList<CourseDetail> listC = new ArrayList<>();
        String sql = "select c.id as cid, title, price, numOfPeopleJoin, routeID, cd.id as courseDetailID, level, "
                + "cd.time, sumLesson, detailCourseDes, image, courseID from Course c join CourseDetail cd on c.id = cd.courseID";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listC.add(new CourseDetail(rs.getString(1), rs.getString(2),
                        Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        Integer.parseInt(rs.getString(8)), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12)));
            }
        } catch (Exception e) {
            System.out.println("getAllCourseMinus0: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listC;
    }

    public int getMaxCourseID() {
        int id = 0;
        ArrayList<CourseDetail> listC = getAllCourseMinus0();
        for (CourseDetail courseDetail : listC) {
            int idd = Integer.parseInt(courseDetail.getId());
            if (idd > id) {
                id = idd;
            }
        }
        return id;
    }

    public int getMaxCourseDetailID() {
        int id = 0;
        ArrayList<CourseDetail> listC = getAllCourseMinus0();
        for (CourseDetail courseDetail : listC) {
            int idd = Integer.parseInt(courseDetail.getCourseDetailID());
            if (idd > id) {
                id = idd;
            }
        }
        return id;
    }

    public void insertCourse(CourseDetail courseD) {
        String sql = "insert into Course(id, title, numOfPeopleJoin, price, routeID) Values(?, ?, ?, ?, ?);";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, (getMaxCourseID() + 1) + "");
            pstm.setString(2, courseD.getTitle());
            pstm.setString(3, courseD.getNumOfPeopleJoin() + "");
            pstm.setString(4, courseD.getPrice() + "");
            pstm.setString(5, courseD.getRouteID());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("insertCourse: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertCourseDetail(CourseDetail courseD) {
        String sql = "insert into CourseDetail(id, level, sumLesson, time, detailCourseDes, image, courseID) Values(?, ?, ?, ?, ?, ?, ?);";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, (getMaxCourseDetailID() + 1) + "");
            pstm.setString(2, courseD.getLevel());
            pstm.setString(3, 0 + "");
            pstm.setString(4, 0 + "");
            pstm.setString(5, courseD.getDetailCourseDes());
            pstm.setString(6, courseD.getImage());
            pstm.setString(7, (getMaxCourseID() + 1) + "");
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("insertCourseDetail: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        CourseDetailDAO c = new CourseDetailDAO();
        ArrayList<CourseDetail> l = c.getAllProCourseAndFreeCourse();
        System.out.println(l);
    }

    /* Get course of account by user id */
    public ArrayList<CourseDetail> getCourseOfAccount(String userId) {
        ArrayList<CourseDetail> listCourse = new ArrayList<CourseDetail>();
        try {
            String strSelect = "select * from\n"
                    + " CourseAccount ca join CourseDetail cd\n"
                    + " on ca.courseId = cd.courseId\n"
                    + " join Course c\n"
                    + " on c.id = cd.courseId\n"
                    + "where ca.accountId = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, userId);
            ResultSet rs = pstm.executeQuery();
            /* Loop to get course of account */
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(12));
                String courseDetailID = String.valueOf(rs.getInt(3));
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String timeRegistration = date.format(rs.getDate(4));
                String stateId = String.valueOf(rs.getInt(5));
                String courseID = String.valueOf(rs.getInt(6));
                String level = rs.getString(7);
                int sumLesson = Integer.valueOf(rs.getString(8));
                String time = rs.getString(9);
                String detailCourseDes = rs.getString(10);
                String image = rs.getString(11);
                String title = rs.getString(14);
                int numOfPeopleJoin = Integer.valueOf(rs.getString(15));
                int price = Integer.valueOf(rs.getString(16));
                String routeID = String.valueOf(rs.getInt(17));
                listCourse.add(new CourseDetail(id, title, price, numOfPeopleJoin,
                        routeID, courseDetailID, level, sumLesson, time, detailCourseDes,
                        image, courseID, timeRegistration, stateId));
            }
        } catch (Exception e) {
            System.out.println("getCourseOfAccount: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCourse;
    }

    //select c.id, c.title, c.numOfPeopleJoin, cd.timeRegistration from course c join courseaccount ca on c.id = ca.courseid where c.isPublished = true and ca.accountID = 1;
    //Get my course
    public ArrayList<CourseDetail> getAllPublishedCourseByUserId(String userId) {
        ArrayList<CourseDetail> listC = new ArrayList<>();
        String sql = "select c.id, c.title, cd.image, ca.timeRegistration "
                + "from course c join courseaccount ca on c.id = ca.courseid\n"
                + "join courseDetail cd on c.id = cd.courseId where c.isPublished = true and ca.accountID = " + userId + ";";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listC.add(new CourseDetail(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println("getAllPublishedCourseByUserId: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listC;
    }

    /* Unsubscribes course of user by course id and user id and update state id */
    public void unSubscribesCourse(String courseId, String userID, String stateId) {
        try {
            String strDelete = "UPDATE courseAccount\n"
                    + "SET idState = ? \n"
                    + "WHERE accountID = ? and CourseID = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strDelete);
            pstm.setString(1, stateId);
            pstm.setString(2, userID);
            pstm.setString(3, courseId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("unSubscribesCourse: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* Get state of course */
    public ArrayList<CourseState> getStateOfCourse() {
        ArrayList<CourseState> listState = new ArrayList<CourseState>();
        try {
            String strSelect = "select * from State";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listState.add(new CourseState(String.valueOf(rs.getInt(1)), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("getStateOfCourse: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listState;
    }

    // Check out the user who signed up for this course
    public boolean checkUserRegiteredCourse(String id, String courseID, int stateID) {
        try {
            String strSelect = "   select * from courseAccount\n"
                    + "   where accountID = ? and courseID = ? and idState = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.setString(2, courseID);
            pstm.setInt(3, stateID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUserRegiteredCourse");
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /* Course search by title and userID */
    public ArrayList<CourseDetail> courseSearchByTitle(String accID, String txtSearch, String state) {
        ArrayList<CourseDetail> courseList = new ArrayList<CourseDetail>();
        try {
            String strSelect = "select * from CourseAccount ca join CourseDetail cd\n"
                    + "on ca.courseId = cd.courseId join Course c\n"
                    + " on c.id = cd.courseId\n"
                    + " where ca.accountId = ? and ca.idState = ? and c.title like ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, accID);
            pstm.setString(2, state);
            pstm.setString(3, "%" + txtSearch + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(12));
                String courseDetailID = String.valueOf(rs.getInt(3));
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String timeRegistration = date.format(rs.getDate(4));
                String stateId = String.valueOf(rs.getInt(5));
                String courseID = String.valueOf(rs.getInt(6));
                String level = rs.getString(7);
                int sumLesson = Integer.valueOf(rs.getString(8));
                String time = rs.getString(9);
                String detailCourseDes = rs.getString(10);
                String image = rs.getString(11);
                String title = rs.getString(14);
                int numOfPeopleJoin = Integer.valueOf(rs.getString(15));
                int price = Integer.valueOf(rs.getString(16));
                String routeID = String.valueOf(rs.getInt(17));
                courseList.add(new CourseDetail(id, title, price, numOfPeopleJoin,
                        routeID, courseDetailID, level, sumLesson, time, detailCourseDes,
                        image, courseID, timeRegistration, stateId));
            }
        } catch (Exception e) {
            System.out.println("courseSearchByTitle: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courseList;
    }

    /* Filter course by status and userID */
    public ArrayList<CourseDetail> getCourseByState(String accID, String stateFilter) {
        ArrayList<CourseDetail> listCourse = new ArrayList<CourseDetail>();
        try {
            String strSelect = "select * from CourseAccount ca join CourseDetail cd\n"
                    + "on ca.courseId = cd.courseId join Course c\n"
                    + "on c.id = cd.courseId\n"
                    + "where ca.accountId = ? and ca.idState = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, accID);
            pstm.setString(2, stateFilter);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(12));
                String courseDetailID = String.valueOf(rs.getInt(3));
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String timeRegistration = date.format(rs.getDate(4));
                String stateId = String.valueOf(rs.getInt(5));
                String courseID = String.valueOf(rs.getInt(6));
                String level = rs.getString(7);
                int sumLesson = Integer.valueOf(rs.getString(8));
                String time = rs.getString(9);
                String detailCourseDes = rs.getString(10);
                String image = rs.getString(11);
                String title = rs.getString(14);
                int numOfPeopleJoin = Integer.valueOf(rs.getString(15));
                int price = Integer.valueOf(rs.getString(16));
                String routeID = String.valueOf(rs.getInt(17));
                listCourse.add(new CourseDetail(id, title, price, numOfPeopleJoin,
                        routeID, courseDetailID, level, sumLesson, time, detailCourseDes,
                        image, courseID, timeRegistration, stateId));
            }
        } catch (Exception e) {
            System.out.println("getCourseByState: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCourse;
    }

    public ArrayList<CourseDetail> courseSearchAllByTitle(String userId, String txtSearch) {
        ArrayList<CourseDetail> courseList = new ArrayList<CourseDetail>();
        try {
            String strSelect = "select * from CourseAccount ca join CourseDetail cd\n"
                    + "on ca.courseId = cd.courseId join Course c\n"
                    + " on c.id = cd.courseId\n"
                    + " where ca.accountId = ? and c.title like ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, userId);
            pstm.setString(2, "%" + txtSearch + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(12));
                String courseDetailID = String.valueOf(rs.getInt(3));
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String timeRegistration = date.format(rs.getDate(4));
                String stateId = String.valueOf(rs.getInt(5));
                String courseID = String.valueOf(rs.getInt(6));
                String level = rs.getString(7);
                int sumLesson = Integer.valueOf(rs.getString(8));
                String time = rs.getString(9);
                String detailCourseDes = rs.getString(10);
                String image = rs.getString(11);
                String title = rs.getString(14);
                int numOfPeopleJoin = Integer.valueOf(rs.getString(15));
                int price = Integer.valueOf(rs.getString(16));
                String routeID = String.valueOf(rs.getInt(17));
                courseList.add(new CourseDetail(id, title, price, numOfPeopleJoin,
                        routeID, courseDetailID, level, sumLesson, time, detailCourseDes,
                        image, courseID, timeRegistration, stateId));
            }
        } catch (Exception e) {
            System.out.println("courseSearchAllByTitle: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courseList;
    }
     /* Get course list registered of account by user id */
    public ArrayList<CourseDetail> getCourseRegisteredOfAccount(String userId) {
        ArrayList<CourseDetail> listCourse = new ArrayList<CourseDetail>();
        try {
            String strSelect = "select * from\n"
                    + " CourseAccount ca join CourseDetail cd\n"
                    + " on ca.courseId = cd.courseId\n"
                    + " join Course c\n"
                    + " on c.id = cd.courseId\n"
                    + "where ca.accountId = ?"
                    + "and ca.idState = 1";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, userId);
            ResultSet rs = pstm.executeQuery();
            /* Loop to get course of account */
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(12));
                String courseDetailID = String.valueOf(rs.getInt(3));
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String timeRegistration = date.format(rs.getDate(4));
                String stateId = String.valueOf(rs.getInt(5));
                String courseID = String.valueOf(rs.getInt(6));
                String level = rs.getString(7);
                int sumLesson = Integer.valueOf(rs.getString(8));
                String time = rs.getString(9);
                String detailCourseDes = rs.getString(10);
                String image = rs.getString(11);
                String title = rs.getString(14);
                int numOfPeopleJoin = Integer.valueOf(rs.getString(15));
                int price = Integer.valueOf(rs.getString(16));
                String routeID = String.valueOf(rs.getInt(17));
                listCourse.add(new CourseDetail(id, title, price, numOfPeopleJoin,
                        routeID, courseDetailID, level, sumLesson, time, detailCourseDes,
                        image, courseID, timeRegistration, stateId));
            }
        } catch (Exception e) {
            System.out.println("getCourseOfAccount: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCourse;
    }

}
