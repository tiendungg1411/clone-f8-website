/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class SavedBlog {
    private String id;
    private String blogID;
    private String userID;
    private String time;
    
    public SavedBlog() {
    }

    public SavedBlog(String id, String blogID, String userID) {
        this.id = id;
        this.blogID = blogID;
        this.userID = userID;
    }

    public SavedBlog(String id, String blogID, String userID, String time) {
        this.id = id;
        this.blogID = blogID;
        this.userID = userID;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SavedBlog{" + "id=" + id + ", blogID=" + blogID + ", userID=" + userID + ", time=" + time + '}';
    }

    
}
