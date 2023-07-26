/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class RepQAOfLesson {
    private String id;
    private String content;
    private String date;
    private String userID;
    private String lessonID;
    private String parentID;

    public RepQAOfLesson() {
    }

    public RepQAOfLesson(String id, String content, String date, String userID, String lessonID, String parentID) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.userID = userID;
        this.lessonID = lessonID;
        this.parentID = parentID;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    @Override
    public String toString() {
        return "RepQAOfLesson{" + "id=" + id + ", content=" + content + ", date=" + date + ", userID=" + userID + ", lessonID=" + lessonID + ", parentID=" + parentID + '}';
    }
    
    
}
