/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Question {
    private String id;
    private String content;
    private String explain;
    private String lessonID;
    private String courseTitle;
    private String lessonName;
    

    public Question() {
    }
    public Question(String id, String content ,String explain) {
        this.id = id;
        this.content = content;
        this.explain = explain;
    }
    public Question(String id, String content, String explain, String lessonID) {
        this.id = id;
        this.content = content;
        this.explain = explain;
        this.lessonID = lessonID;
    }
    public Question(String id, String courseTitle, String lessonName, String content, String explain) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.lessonName = lessonName;
        this.explain = explain;
        this.content = content;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
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

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", content=" + content + ", explain=" + explain + ", lessonID=" + lessonID + '}';
    }

    
}
