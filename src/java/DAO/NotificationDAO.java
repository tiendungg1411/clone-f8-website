/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CourseDetail;
import Model.Notification;
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
public class NotificationDAO {
    
    Connection con = null;
    
    public ArrayList<Notification> getAllNotificationByAccountID(String accountID) {
        ArrayList<Notification> listN = new ArrayList<>();
        String sql = "select * from notification n join account a on n.fromAccount = a.id where recieveAccount = "+accountID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listN.add(new Notification(rs.getInt(1), rs.getString(2),
                                            rs.getString(3), rs.getString(4), 
                                            rs.getString(5), rs.getBoolean(6), 
                                            rs.getString(20), rs.getString(16)));
            }
        } catch (Exception e) {
            System.out.println("getAllNotificationByAccountID: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listN;
    }
    public static void main(String[] args) {
        NotificationDAO n = new NotificationDAO();
        ArrayList<Notification> listN = n.getAllNotificationByAccountID("3");
        for (Notification notification : listN) {
            System.out.println(notification);
        }
    }
}
