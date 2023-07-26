/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class CommentBlog {
    private String id;
    private String content;
    private String time;
    private String userID;
    private String blogID;
    private String stateID;
    public CommentBlog() {
    }

    public CommentBlog(String id, String content, String time, String userID, String blogID) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.userID = userID;
        this.blogID = blogID;
    }

    public CommentBlog(String id, String content, String time, String userID, String blogID, String stateID) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.userID = userID;
        this.blogID = blogID;
        this.stateID = stateID;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBlogID() {
        return blogID;
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    @Override
    public String toString() {
        return "CommentBlog{" + "id=" + id + ", content=" + content + ", time=" + time + ", userID=" + userID + ", blogID=" + blogID + ", stateID=" + stateID + '}';
    }

    
    
    
    
}
