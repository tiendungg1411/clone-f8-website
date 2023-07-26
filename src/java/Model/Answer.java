/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Answer {
    private String id;
    private String content;
    private String quesID;

    public Answer() {
    }

    public Answer(String id, String content, String quesID) {
        this.id = id;
        this.content = content;
        this.quesID = quesID;
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

    public String getQuesID() {
        return quesID;
    }

    public void setQuesID(String quesID) {
        this.quesID = quesID;
    }

    @Override
    public String toString() {
        return "Answer{" + "id=" + id + ", content=" + content + ", quesID=" + quesID + '}';
    }
    
}
