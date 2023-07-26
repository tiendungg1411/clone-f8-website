/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class QuestionEditVM {
    private String id;
    private String lessonID;
    private String courseID;

    public QuestionEditVM() {
    }

    public QuestionEditVM(String id, String lessonID, String courseID) {
        this.id = id;
        this.lessonID = lessonID;
        this.courseID = courseID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "QuestionEditVM{" + "id=" + id + ", lessonID=" + lessonID + ", courseID=" + courseID + '}';
    }
    
}
