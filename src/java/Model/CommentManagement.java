/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class CommentManagement {
    private String userID;
    private String userName;
    private String fullName;
    private String content;
    private String time;
    private String stateID;
    private String blogID;
    private String commentID;

    public CommentManagement() {
    }

    public CommentManagement(String userID, String userName, String fullName, String content, String time, String stateID, String blogID, String commentID) {
        this.userID = userID;
        this.userName = userName;
        this.fullName = fullName;
        this.content = content;
        this.time = time;
        this.stateID = stateID;
        this.blogID = blogID;
        this.commentID = commentID;
    }

   

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public String getBlogID() {
        return blogID;
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    @Override
    public String toString() {
        return "CommentManagement{" + "userID=" + userID + ", userName=" + userName + ", fullName=" + fullName + ", content=" + content + ", time=" + time + ", stateID=" + stateID + ", blogID=" + blogID + ", commentID=" + commentID + '}';
    }
    
    
    
    
    
}
