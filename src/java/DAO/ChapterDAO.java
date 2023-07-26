/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Blog;
import Model.Chapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ChapterDAO extends DBContext {
    
    Connection con = null;
    
    public ArrayList<Chapter> getAll() {
        ArrayList<Chapter> list = new ArrayList<>();
        String sql = "SELECT * FROM chapter;";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Chapter(String.valueOf(rs.getInt("id")),
                        rs.getString("name")));
            }
        } catch (Exception e) {
            System.out.println("getAllBlogByAccountID: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
}
