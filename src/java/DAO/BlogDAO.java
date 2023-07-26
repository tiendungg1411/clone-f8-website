/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import Model.Blog;
import Model.CommentBlog;
import Model.CommentManagement;
import Model.LikedBlog;
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
public class BlogDAO {

    Connection con = null;

    public ArrayList<Blog> getAllBlogByAccountID(String accID) {
        ArrayList<Blog> listB = new ArrayList<>();
        String sql = "select b.id, b.title, b.topic, b.content, b.numOfLikes, b.date, "
                + "b.userID, a.avatar, a.userName "
                + "from Blog b join Account a on b.userID = a.id where userID = " + accID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listB.add(new Blog(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9))
                );
            }
        } catch (Exception e) {
            System.out.println("getAllBlogByAccountID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listB;
    }

    public ArrayList<Blog> getAllBlog() {
        ArrayList<Blog> listB = new ArrayList<>();
        String sql = "select b.id, b.title, b.topic, b.content, b.numOfLikes, b.date, "
                + "b.userID, a.avatar, a.userName "
                + "from Blog b join Account a on b.userID = a.id where b.stateId=1";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listB.add(new Blog(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9))
                );
            }
        } catch (Exception e) {
            System.out.println("getAllBlog: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listB;
    }

    public void insertBlog(Blog blog) {
        String sql = "insert into Blog(id, title, topic,stateID, content, numOfLikes, date, userID) Values(?, ?, ?,1, ?, ?, ?, ?);";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, blog.getId());
            pstm.setString(2, blog.getTitle());
            pstm.setString(3, blog.getTopic());
            pstm.setString(4, blog.getContent());
            pstm.setString(5, blog.getNumOfLikes() + "");
            pstm.setString(6, blog.getDate());
            pstm.setString(7, blog.getUserID());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("insertBlog: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void editBlogByID(Blog blog) {
        String sql = "update Blog set title=?, topic=?, content=?, numOfLikes=?, date=?, userID=? "
                + " where id = ?";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, blog.getTitle());
            pstm.setString(2, blog.getTopic());
            pstm.setString(3, blog.getContent());
            pstm.setString(4, blog.getNumOfLikes() + "");
            pstm.setString(5, blog.getDate());
            pstm.setString(6, blog.getUserID());
            pstm.setString(7, blog.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("editBlogByID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Blog getBlogById(String blogID) {
        String sql = "select b.id, b.title, b.topic, b.content, b.numOfLikes, b.date, "
                + "b.userID, a.avatar, a.userName "
                + "from Blog b join Account a on b.userID = a.id where b.id = " + blogID;
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Blog(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9));
            }
        } catch (Exception e) {
            System.out.println("getBlogById: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BlogDAO b = new BlogDAO();
        ArrayList<Blog> b1 = new ArrayList<>();
        b1 = b.getAllBlogList("a");
        System.out.println(b1);
    }

    public ArrayList<Blog> getAllSavedBlogByUserID(String userID) {
        ArrayList<Blog> listB = new ArrayList<>();
        String sql = "select b.id, b.title, b.topic, b.content, b.numOfLikes, sb.time, b.userID, a.avatar, a.firstAndLastName \n"
                + " from Blog b join Account a join SavedBlog sb  on b.id = sb.blogID and b.userID = a.id "
                + "where b.stateId=1 and sb.userID = " + userID + "  order by b.id asc";
        try {
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listB.add(new Blog(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9))
                );
            }
        } catch (Exception e) {
            System.out.println("getAllSavedBlogByUserID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listB;
    }

    /* Delete blog from commentBlog table */
    public void deleteBlogFromCommentBlogTable(String blogId) {
        try {
            String strDelete = "delete from commentblog where blogID = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strDelete);
            pstm.setString(1, blogId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteBlogFromCommentBlogTable: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* delete blog from savedBlog table */
    public void deleteBlogFromSavedBlogTable(String blogId) {
        try {
            String strDelete = "delete from savedblog where blogID = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strDelete);
            pstm.setString(1, blogId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteBlogFromSavedBlogTable: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* Delete blog by blogId */
    public void deleteBlogById(String blogId) {
        try {
            String strDelete = "delete from blog where id = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strDelete);
            pstm.setString(1, blogId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteBlogById: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* Unlike blog by blog id and user id*/
    public void unLikeBlog(String blogID, String userID) {
        try {
            String strDelete = "delete from LikeOfBlog\n"
                    + "where userID = ? and blogID = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strDelete);
            pstm.setString(1, userID);
            pstm.setString(2, blogID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("unLikeBlog: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /* Like blog by blog id and user id*/
    public void likeBlog(String blogID, String userID) {
        try {
            String strInsert = "insert into LikeOfBlog\n"
                    + "(userID, blogID)\n"
                    + "Values(?,?)";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strInsert);
            pstm.setString(1, userID);
            pstm.setString(2, blogID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("likeBlog: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* Get list blog liked by user ID */
    public ArrayList<LikedBlog> getListBlogLikedByUserID(String id) {
        ArrayList<LikedBlog> listLiked = new ArrayList<LikedBlog>();
        try {
            String strSelect = "select id, userID, blogID \n"
                    + " from LikeOfBlog\n"
                    + "where userID = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listLiked.add(new LikedBlog(String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getInt("blogID")), String.valueOf(rs.getInt("userID"))));
            }

        } catch (Exception e) {
            System.out.println("getListBlogLikedByUserID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listLiked;
    }

    /* Get the number of likes of the blog by blog ID */
    public int getNumOfLikeOfBlog(String blogID) {
        int numOfLike = 0;
        try {
            String strSelect = "select count(*) AS numOfLike\n"
                    + "from LikeOfBlog\n"
                    + "where blogID = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, blogID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                numOfLike = rs.getInt("numOfLike");
            }
        } catch (Exception e) {
            System.out.println("getNumOfLikeOfBlog: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return numOfLike;
    }

    /* Add new comment */
    public void addComment(String blogID, String userID, String content, String time, String stateID) {
        try {
            String strInsert = "insert into CommentDetail\n"
                    + "(content, date, userID, blogID, stateID)\n"
                    + "Values\n"
                    + "(?, ?, ?, ?, ?)";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strInsert);
            pstm.setString(1, content);
            pstm.setString(2, time);
            pstm.setString(3, userID);
            pstm.setString(4, blogID);
            pstm.setString(5, stateID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addComment: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* Get list comment of the blog by blog ID */
    public ArrayList<CommentBlog> getListComment(String blogID) {
        ArrayList<CommentBlog> listComment = new ArrayList<CommentBlog>();
        try {
            String strSelect = "select * from CommentDetail\n"
                    + "where blogID = ? and stateID = 1";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, blogID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listComment.add(new CommentBlog(String.valueOf(rs.getInt("id")),
                        rs.getString("content"), rs.getString("date"),
                        String.valueOf(rs.getInt("userID")), String.valueOf(rs.getInt("blogID"))));
            }
        } catch (Exception e) {
            System.out.println("getListComment: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listComment;
    }

    /* Delete blog from commentDetail table */
    public void deleteBlogFromCommentDetailTable(String blogId) {
        try {
            String strDelete = "delete from commentDetail \n"
                    + "where blogId = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strDelete);
            pstm.setString(1, blogId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteBlogFromCommentDetailTable: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* Get comment list and information of user comment and blog ID */
    public ArrayList<CommentManagement> getCommentListAndInformation() {
        ArrayList<CommentManagement> commentList = new ArrayList<CommentManagement>();
        try {
            String strSelect = "SELECT acc.id as userID, acc.userName, acc.firstAndLastName, cd.content, cd.date,  cd.stateId, b.id as blogID, cd.id as commentID\n"
                    + "FROM\n"
                    + "Account acc JOIN CommentDetail cd\n"
                    + "ON acc.id = cd.userId\n"
                    + "JOIN Blog b\n"
                    + "ON cd.blogId = b.id";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                commentList.add(new CommentManagement(String.valueOf(rs.getInt("userID")),
                        rs.getString("userName"), rs.getString("firstAndLastName"),
                        rs.getString("content"), rs.getString("date"),
                        String.valueOf(rs.getInt("stateId")), String.valueOf(rs.getInt("blogID")),
                        String.valueOf(rs.getInt("commentID"))));
            }
        } catch (Exception e) {
            System.out.println("getCommentListAndInformation: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return commentList;
    }

    /* Update state of comment (Show) */
    public void showComment(String commentID, String stateID) {
        try {
            String strUpdate = "UPDATE CommentDetail \n"
                    + "SET stateID = ?\n"
                    + "where id = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strUpdate);
            pstm.setString(1, stateID);
            pstm.setString(2, commentID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("showComment: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* Update state of comment (Hide) */
    public void hideComment(String commentID, String stateID) {
        try {
            String strUpdate = "UPDATE CommentDetail \n"
                    + "SET stateID = ?\n"
                    + "where id = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strUpdate);
            pstm.setString(1, stateID);
            pstm.setString(2, commentID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("showComment: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /* Get comment list and information of user comment by username */
    public ArrayList<CommentManagement> getCommentListByUserName(String txtSearch, String state) {
        ArrayList<CommentManagement> commentList = new ArrayList<CommentManagement>();
        try {
            String strSelect = "SELECT acc.id as userID, acc.userName, acc.firstAndLastName, cd.content, cd.date,  cd.stateId, b.id as blogID, cd.id as commentID\n"
                    + "FROM\n"
                    + "Account acc JOIN CommentDetail cd\n"
                    + "ON acc.id = cd.userId\n"
                    + "JOIN Blog b\n"
                    + "ON cd.blogId = b.id\n"
                    + "WHERE acc.userName like ? and cd.stateID = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, "%" + txtSearch + "%");
            pstm.setString(2, state);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                commentList.add(new CommentManagement(String.valueOf(rs.getInt("userID")),
                        rs.getString("userName"), rs.getString("firstAndLastName"),
                        rs.getString("content"), rs.getString("date"),
                        String.valueOf(rs.getInt("stateId")), String.valueOf(rs.getInt("blogID")),
                        String.valueOf(rs.getInt("commentID"))));
            }
        } catch (Exception e) {
            System.out.println("getCommentListByUserName: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return commentList;
    }

    /* Get comment list by state id */
    public ArrayList<CommentManagement> getCommentListByState(String stateFilter) {
        ArrayList<CommentManagement> commentList = new ArrayList<CommentManagement>();
        try {
            String strSelect = "SELECT acc.id as userID, acc.userName, acc.firstAndLastName, cd.content, cd.date,  cd.stateId, b.id as blogID, cd.id as commentID\n"
                    + "FROM\n"
                    + "Account acc JOIN CommentDetail cd\n"
                    + "ON acc.id = cd.userId\n"
                    + "JOIN Blog b\n"
                    + "ON cd.blogId = b.id\n"
                    + "where cd.stateId = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, stateFilter);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                commentList.add(new CommentManagement(String.valueOf(rs.getInt("userID")),
                        rs.getString("userName"), rs.getString("firstAndLastName"),
                        rs.getString("content"), rs.getString("date"),
                        String.valueOf(rs.getInt("stateId")), String.valueOf(rs.getInt("blogID")),
                        String.valueOf(rs.getInt("commentID"))));
            }
        } catch (Exception e) {
            System.out.println("getCommentListByState: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return commentList;
    }

    /* Get all comment list by username */
    public ArrayList<CommentManagement> getAllCommentList(String txtSearch) {
        ArrayList<CommentManagement> commentList = new ArrayList<CommentManagement>();
        try {
            String strSelect = "SELECT acc.id as userID, acc.userName, acc.firstAndLastName, cd.content, cd.date,  cd.stateId, b.id as blogID, cd.id as commentID\n"
                    + "FROM\n"
                    + "Account acc JOIN CommentDetail cd\n"
                    + "ON acc.id = cd.userId\n"
                    + "JOIN Blog b\n"
                    + "ON cd.blogId = b.id\n"
                    + "WHERE acc.userName like ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, "%" + txtSearch + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                commentList.add(new CommentManagement(String.valueOf(rs.getInt("userID")),
                        rs.getString("userName"), rs.getString("firstAndLastName"),
                        rs.getString("content"), rs.getString("date"),
                        String.valueOf(rs.getInt("stateId")), String.valueOf(rs.getInt("blogID")),
                        String.valueOf(rs.getInt("commentID"))));
            }
        } catch (Exception e) {
            System.out.println("getAllCommentList: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return commentList;
    }

    /* Get a list of the last 3 user comments by userId */
    public ArrayList<CommentBlog> getListCommentByUserId(String userId) {
        ArrayList<CommentBlog> listComment = new ArrayList<CommentBlog>();
        try {
            String strSelect = "SELECT *\n"
                    + "FROM CommentDetail\n"
                    + "WHERE UserID = ?\n"
                    + "AND StateID = 1\n"
                    + "ORDER BY DATE DESC\n"
                    + "LIMIT 3";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, userId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listComment.add(new CommentBlog(String.valueOf(rs.getInt("id")),
                        rs.getString("content"), rs.getString("date"),
                        String.valueOf(rs.getInt("userID")), String.valueOf(rs.getInt("blogID"))));
            }
        } catch (Exception ex) {
            System.out.println("getListCommentByUserId: " + ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listComment;
    }

    public ArrayList<Blog> getBlogByState(String stateFilter) {
        ArrayList<Blog> blogList = new ArrayList<Blog>();
        try {
            String strSelect = "select b.id, b.title, b.stateId, b.content, b.date, b.userID, "
                    + "a.userName, a.firstAndLastName from Blog b "
                    + "join Account a on b.userID=a.id  where stateId=?;";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, stateFilter);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                blogList.add(new Blog(rs.getString(1), rs.getString(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            } 
        } catch (Exception e) {
            System.out.println("getBlogListByState: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return blogList;
    }

    public ArrayList<Blog> getBlogListAndInformation() {
        ArrayList<Blog> blogList = new ArrayList<Blog>();
        try {
            String strSelect = "select b.id, b.title, b.stateId, b.content, b.date, b.userID, "
                    + "a.userName, a.firstAndLastName from Blog b "
                    + "join Account a on b.userID=a.id";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                blogList.add(new Blog(rs.getString(1), rs.getString(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (Exception e) {
            System.out.println("getCommentListAndInformation: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return blogList;
    }

    public void showBlog(String parameter, String parameter0) {
        try {
            String strUpdate = "UPDATE Blog \n"
                    + "SET stateID = ?\n"
                    + "where id = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strUpdate);
            pstm.setString(1, parameter0);
            pstm.setString(2, parameter);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("showBlog: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void hideBlog(String parameter, String parameter0) {
        try {
            String strUpdate = "UPDATE Blog \n"
                    + "SET stateID = ?\n"
                    + "where id = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strUpdate);
            pstm.setString(1, parameter0);
            pstm.setString(2, parameter);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("showComment: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Blog> getAllBlogList(String txtSearch) {
        ArrayList<Blog> blogList = new ArrayList<Blog>();
        try {
            String strSelect = "select b.id, b.title, b.stateId, b.content, b.date, b.userID, a.userName, a.firstAndLastName"
                    + " from Blog b join Account a on b.userID=a.id  where a.userName like ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, "%" + txtSearch + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                blogList.add(new Blog(rs.getString(1), rs.getString(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (Exception e) {
            System.out.println("getAllCommentList: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return blogList;
    }

    public ArrayList<Blog> getBlogListByUserName(String txtSearch, String state) {
        ArrayList<Blog> blogList = new ArrayList<Blog>();
        try {
            String strSelect = "select b.id, b.title, b.stateId, b.content, b.date, b.userID, a.userName, a.firstAndLastName"
                    + " from Blog b join Account a on b.userID=a.id  where a.userName like ? and b.stateId = ?";
            con = DBContext.getConnection();
            PreparedStatement pstm = con.prepareStatement(strSelect);
            pstm.setString(1, "%" + txtSearch + "%");
            pstm.setString(2, state);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                blogList.add(new Blog(rs.getString(1), rs.getString(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (Exception e) {
            System.out.println("getBlogListByUserName: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return blogList;
    }
}
