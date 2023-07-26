/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class CourseAccount {
    String id;
    String accountID;
    String courseID;
    String idState;

    public CourseAccount() {
    }

    public CourseAccount(String id, String accountID, String courseID) {
        this.id = id;
        this.accountID = accountID;
        this.courseID = courseID;
    }

    public CourseAccount(String id, String accountID, String courseID, String idState) {
        this.id = id;
        this.accountID = accountID;
        this.courseID = courseID;
        this.idState = idState;
    }
     
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getIdState() {
        return idState;
    }

    public void setIdState(String idState) {
        this.idState = idState;
    }

    @Override
    public String toString() {
        return "CourseAccount{" + "id=" + id + ", accountID=" + accountID + ", courseID=" + courseID + ", idState=" + idState + '}';
    }
    
    

  
    
    
}
