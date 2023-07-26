/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class LessonQuestion {

    private String id;
    private String lessonID;
    private String questionID;

    public LessonQuestion() {
    }

    public LessonQuestion(String lessonID, String questionID) {
        this.lessonID = lessonID;
        this.questionID = questionID;
    }

    public LessonQuestion(String id, String lessonID, String questionID) {
        this.id = id;
        this.lessonID = lessonID;
        this.questionID = questionID;
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

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    @Override
    public String toString() {
        return "LessonQuestion{" + "id=" + id + ", lessonID=" + lessonID + ", questionID=" + questionID + '}';
    }

}
