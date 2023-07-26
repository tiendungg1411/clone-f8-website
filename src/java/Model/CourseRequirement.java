/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */
public class CourseRequirement {
    private String id;
    private String content;
    private String courseID;

    public CourseRequirement() {
    }

    public CourseRequirement(String id, String content, String courseID) {
        this.id = id;
        this.content = content;
        this.courseID = courseID;
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

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "CourseRequirement{" + "id=" + id + ", content=" + content + ", courseID=" + courseID + '}';
    }
    
}
