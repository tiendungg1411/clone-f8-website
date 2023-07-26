/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin

 */
public class LessonVM {
    private String quizID;
    private String quizName;
    private String chapterName;
    private String courseName;
    
    private String questionID;

    public LessonVM() {
    }

    public LessonVM(String quizID, String quizName, String chapterName, String courseName) {
        this.quizID = quizID;
        this.quizName = quizName;
        this.chapterName = chapterName;
        this.courseName = courseName;
    }

    public LessonVM(String quizID, String quizName, String chapterName, String courseName, String questionID) {
        this.quizID = quizID;
        this.quizName = quizName;
        this.chapterName = chapterName;
        this.courseName = courseName;
        this.questionID = questionID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
    

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "LessonVM{" + "quizID=" + quizID + ", quizName=" + quizName + ", chapterName=" + chapterName + ", courseName=" + courseName + '}';
    }
    
}
