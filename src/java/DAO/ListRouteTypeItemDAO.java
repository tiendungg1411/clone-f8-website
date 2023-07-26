/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Controller.listRouteTypeItemServlet;
import Model.RouteType;
import Model.RouteTypeItem;
import Model.listRouteTypeItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author TIEN DAT
 */
public class ListRouteTypeItemDAO {
    Connection connect = null;
    public ArrayList<listRouteTypeItem> getAllRouteTypeItemDetail() {
        ArrayList<listRouteTypeItem> listL = new ArrayList<>();
        String sql = "select rti.id,rt.name,rti.name,rti.description,c.title from RouteTypeItems rti join RouteType rt on rti.routeTypeID = rt.id \n"
                + "join RouteTypeItemAndCourse rtiac on rtiac.RouteTypeItemID = rti.id\n"
                + "join course c on  c.id=rtiac.CourseID order by rti.id";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listL.add(new listRouteTypeItem(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            System.out.println("getAllRouteTypeItemDetail: " + e.getMessage());
        }
        return listL;
    }

    public listRouteTypeItem getListRouteCourseItemByID(String RouteCourseItemID) {
        String sql = "select rti.id,rt.name,rti.name,rti.description,c.title from RouteTypeItems rti join RouteType rt on rti.routeTypeID = rt.id \n"
                + "join RouteTypeItemAndCourse rtiac on rtiac.RouteTypeItemID = rti.id\n"
                + "join course c on c.id=rtiac.id where rti.id = " + RouteCourseItemID;
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new listRouteTypeItem(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("getBlogById: " + e.getMessage());
        }
        return null;
    }
    
public ArrayList<RouteType> getListRouteType() {
        ArrayList<RouteType> list = new ArrayList<RouteType>();
        try {
            String strSelect = "SELECT * FROM RouteType";
            connect = DBContext.getConnection();
            PreparedStatement pstm = connect.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new RouteType(String.valueOf(rs.getInt("id")), rs.getString("name"),
                        rs.getString("image"), rs.getString("description1"),
                        rs.getString("description2"), String.valueOf(rs.getInt("status"))));

            }
        } catch (Exception e) {
            System.out.println("getListRouteType: " + e.getMessage());
        }

        return list;
    }

    public ArrayList<listRouteTypeItem> getRouteTypeItemDetailByID(String stateFilter) {
        ArrayList<listRouteTypeItem> list = new ArrayList<listRouteTypeItem>();
        try {
            String strSelect = "select rti.id,rt.name,rti.name,rti.description,c.title from RouteTypeItems rti join RouteType rt on rti.routeTypeID = rt.id \n"
                    + "join RouteTypeItemAndCourse rtiac on rtiac.RouteTypeItemID = rti.id\n"
                    + "join course c on  c.id=rtiac.CourseID where rt.id = ? order by rti.id";
            connect = DBContext.getConnection();
            PreparedStatement pstm = connect.prepareStatement(strSelect);
            pstm.setString(1, stateFilter);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                                list.add(new listRouteTypeItem(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            System.out.println("getRouteTypeItemDetailByID: " + e.getMessage());
        }
        return list;
    }

//    public void editlistRouteTypeItemByID(listRouteTypeItem listRouteTypeItem) {
//        String sql = "update listRouteTypeItems set title=?, topic=?, content=?, numOfLikes=?, date=?, userID=? "
//                + " where id = ?";
//        try {
//            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
//            pstm.setString(1, blog.getTitle());
//            pstm.setString(2, blog.getTopic());
//            pstm.setString(3, blog.getContent());
//            pstm.setString(4, blog.getNumOfLikes() + "");
//            pstm.setString(5, blog.getDate());
//            pstm.setString(6, blog.getUserID());
//            pstm.setString(7, blog.getId());
//            pstm.executeUpdate();
//        } catch (Exception e) {
//            System.out.println("editBlogByID: " + e.getMessage());
//        }
//    }
    
}
