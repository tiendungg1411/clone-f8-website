/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class LikedBlog {
    private String id;
    private String blogID;
    private String userID;

    public LikedBlog() {
    }

    public LikedBlog(String id, String blogID, String userID) {
        this.id = id;
        this.blogID = blogID;
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogID() {
        return blogID;
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "LikedBlog{" + "id=" + id + ", blogID=" + blogID + ", userID=" + userID + '}';
    }
    
    
}
