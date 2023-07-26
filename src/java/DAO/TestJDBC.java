/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import Model.Blog;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class TestJDBC {

    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> listA = new ArrayList<>();
        String sql = "select * from Account";
        try {
            PreparedStatement pstm = DBContext.getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listA.add(new Account(rs.getString(1),
                        rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13)));
            }
        } catch (Exception e) {
            System.out.println("getListUser: " + e.getMessage());
        }
        return listA;
    }

    public static void main(String[] args) {
//        String projectPath = System.getProperty("user.dir");
//        System.out.println("Project path: " + projectPath);
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(sdf.format(date));
//          Blog b = new Blog("1", "", "", "", 0, "2020-11-01", "1", "dsa", "dassa");
//        Path sourcePath = Paths.get("D:\\Semester05\\SWP391\\G4_SWP391\\g4\\g4\\build\\web\\image\\cs.png");
//        Path destPath = Paths.get("D:\\Semester05\\SWP391\\G4_SWP391\\g4\\g4\\web\\image");
//
//        // Copy the image file
//        try {
//            Files.copy(sourcePath, destPath);
//            System.out.println("aaaa");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        System.out.println("Image copied successfully!");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date d = new Date();
//        Date d1 = null;
//        long duration = 0;
//        try {
//           d = sdf.parse("2023-06-03 08:54:37");
//           d1 = sdf.parse("2023-06-03 09:55:37");
//        } catch (Exception e) {
//        }
//        if(d != null && d1 != null)
//            duration = d1.getTime() - d.getTime();
//        
//            // Print out the duration in seconds
//        System.out.println(duration / 1000 + " seconds");
        System.out.println(sdf.format(d));
    }
}
