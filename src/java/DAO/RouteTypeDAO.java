/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DBContext.getConnection;
import Model.Account;
import Model.Blog;
import Model.RouteType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class RouteTypeDAO {

    public ArrayList<RouteType> getAllRouteType() {
        ArrayList<RouteType> listR = new ArrayList<>();
        String sql = "select * from RouteType";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listR.add(new RouteType(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println("getAllRouteType: " + e.getMessage());
        }
        return listR;
    }

    public ArrayList<RouteType> getAllRouteTypePublic() {
        ArrayList<RouteType> listR = new ArrayList<>();
        String sql = "select * from RouteType where status = 0";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listR.add(new RouteType(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println("getAllRouteType: " + e.getMessage());
        }
        return listR;
    }
    //Khai bao cac thanh phan xu ly DB
    Connection cnn;//Ket noi DB
    Statement stm;//Thuc thi cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;//Luu tru va xu ly du lieu
    //Route Type by name  exist

    public boolean checkRouteTypeByName(String name) {
        try {
            String strSelect = "select * from RouteType "
                    + "where name=? ";
            pstm = getConnection().prepareStatement(strSelect);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkRouteTypeByName:" + e.getMessage());
        }
        return false;
    }

    public RouteType getRouteTypeByID(String id) {
        String sql = "select * from RouteType where id = " + id;
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new RouteType(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("getRouteTypeByID: " + e.getMessage());
        }
        return null;
    }

    public void insertRouteType(RouteType routeType) {
        String sql = "insert into RouteType(id, name,image, description1, description2, status) Values(?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(1, routeType.getId());
            pstm.setString(2, routeType.getName());
            pstm.setString(3, routeType.getImage());
            pstm.setString(4, routeType.getDescription1());
            pstm.setString(5, routeType.getDescription2());
            pstm.setString(6, routeType.getStatus());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("insertRouteType: " + e.getMessage());
        }
    }

    public void editRouteTypeByID(RouteType routeType) {
        String sql = "update RouteType set name=?, image=?, description1=?, description2=?,status=?"
                + " where id = ?";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            pstm.setString(6, routeType.getId());
            pstm.setString(1, routeType.getName());
            pstm.setString(2, routeType.getImage());
            pstm.setString(3, routeType.getDescription1());
            pstm.setString(4, routeType.getDescription2());
            pstm.setString(5, routeType.getStatus());

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("editRouteTypeByID: " + e.getMessage());
        }
    }

    public void deleteRouteTypeByID(String routeTypeID) {
        try {
            String strDelete = "delete from routeType where id =? ";
            pstm = getConnection().prepareStatement(strDelete);
            pstm.setString(1, routeTypeID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteRouteTypeByID: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        RouteTypeDAO routeType = new RouteTypeDAO();
//        routeType.editRouteTypeByID(routeType);
    }

    public void updateStatusOfRouteType(String routeTypeID, String statusUpdate) {
        try {
            String strSelect = "";
            // Show route type
            if (statusUpdate.equals("1")) {
                strSelect = "UPDATE RouteType\n"
                        + "SET Status = 0\n"
                        + "WHERE Id = ?";
            } else { // Hide route type
                strSelect = "UPDATE RouteType\n"
                        + "SET Status = 1\n"
                        + "WHERE Id = ?";
            }

            PreparedStatement pstm = DBContext.getConnection().prepareStatement(strSelect);
            pstm.setString(1, routeTypeID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateStatusOfRouteType: " + e.getMessage());
        }
    }
}
