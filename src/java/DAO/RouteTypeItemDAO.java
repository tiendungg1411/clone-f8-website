/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Course;
import Model.CourseDetail;
import Model.RouteType;
import Model.RouteTypeItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class RouteTypeItemDAO {
    public ArrayList<RouteTypeItem> getAllRouteTypeItem() {
        ArrayList<RouteTypeItem> listR = new ArrayList<>();
        String sql = "select * from RouteTypeItems";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listR.add(new RouteTypeItem(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println("getAllRouteTypeItem: " + e.getMessage());
        }
        return listR;
    }
    //EditRouteTypeItem
        public void EditRouteTypeItem(String routeTypeID, String courseName, String des, String routeTypeItemID) {
        ArrayList<RouteTypeItem> listR = new ArrayList<>();
        String sql = "Update RouteTypeItems set name = ?, description = ?, routeTypeID = ? where id = ?";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(1, courseName);
            pstm.setString(2, des);
            pstm.setString(3, routeTypeID);
            pstm.setString(4, routeTypeItemID);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("EditRouteTypeItem: " + e.getMessage());
        }
    }
    //deleteRoutyTypeItemById
     public void deleteRoutyTypeItemById(String routeTypeItemID) {
        String sql = "delete from routeTypeItems where id = "+routeTypeItemID;
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteRoutyTypeItemById: " + e.getMessage());
        }
    }  
    
    //getRouteTypeIDByRouteTypeItemID
        public String getRouteTypeIDByRouteTypeItemID(String routeTypeItemID) {
        String sql = "select routeTypeID from RouteTypeItems where id = "+routeTypeItemID;
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("getRouteTypeIDByRouteTypeItemID: " + e.getMessage());
        }
        return null;
    }
    //getCourseByRouteTypeItemID
    public ArrayList<CourseDetail> getCourseDetailByRouteTypeItemID(String routeTypeID) {
        ArrayList<CourseDetail> listC = new ArrayList<>();
        String sql = "select c.id, c.title,c.price, c.numOfPeopleJoin, c.routeID, cd.id as courseDetailID, cd.level, cd.sumLesson, cd.time, cd.detailCourseDes, \n" +
"cd.image, cd.courseID from routetypeItems rti join RouteTypeItemAndCourse rtiac on rti.id = rtiac.RouteTypeItemID \n" +
"join Course c on rtiac.CourseID =  c.id join CourseDetail cd on c.id = cd.courseID where rti.id =  "+routeTypeID+";";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listC.add(new CourseDetail(rs.getString(1), rs.getString(2),
                        Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        Integer.parseInt(rs.getString(8)), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12)));
            }
        } catch (Exception e) {
            System.out.println("getCourseDetailByRouteTypeItemID: " + e.getMessage());
        }
        return listC;
    }
    
           public RouteTypeItem getRouteTypeItemByID(String id) {
        String sql = "select * from RouteTypeItems where id = "+id;
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new RouteTypeItem(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("getRouteTypeByID: " + e.getMessage());
        }
        return null;
    }
    
    public ArrayList<RouteTypeItem> getAllRouteTypeItemByRouteTypeID(String routeTypeID) {
        ArrayList<RouteTypeItem> listR = new ArrayList<>();
        String sql = "select rti.id, rti.name, rti.description, rti.routeTypeID from routetype rt join routetypeItems rti on rt.id = rti.routeTypeID where rt.id = "+routeTypeID+";";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listR.add(new RouteTypeItem(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println("getAllRouteTypeItemByRouteTypeID: " + e.getMessage());
        }
        return listR;
    }
    public void insertRouteTypeItem(RouteTypeItem routeTypeItem){
        String sql = "insert into RouteTypeItems(id, name, description, routeTypeID) Values(?, ?, ?, ?);";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(1, routeTypeItem.getId());
            pstm.setString(2, routeTypeItem.getName());
            pstm.setString(3, routeTypeItem.getDescription());
            pstm.setString(4, routeTypeItem.getRouteTypeID());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("insertRouteTypeItem: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        RouteTypeItemDAO r = new RouteTypeItemDAO();
        ArrayList<CourseDetail> list = r.getCourseDetailByRouteTypeItemID("1");
        for (CourseDetail courseDetail : list) {
            System.out.println(courseDetail);
        }
        ArrayList<RouteTypeItem> list1 = r.getAllRouteTypeItemByRouteTypeID("2");
        for (RouteTypeItem courseDetail : list1) {
            System.out.println(courseDetail);
        }
    }
}
