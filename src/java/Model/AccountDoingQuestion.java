/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class AccountDoingQuestion {
    private String id;
    private String answer;
    private String trueOrFalse;
    private String accountID;
    private String quesID;

    public AccountDoingQuestion() {
    }

    public AccountDoingQuestion(String id, String answer, String trueOrFalse, String accountID, String quesID) {
        this.id = id;
        this.answer = answer;
        this.trueOrFalse = trueOrFalse;
        this.accountID = accountID;
        this.quesID = quesID;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(String trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getQuesID() {
        return quesID;
    }

    public void setQuesID(String quesID) {
        this.quesID = quesID;
    }

    @Override
    public String toString() {
        return "AccountDoingQuestion{" + "id=" + id + ", answer=" + answer + ", trueOrFalse=" + trueOrFalse + ", accountID=" + accountID + ", quesID=" + quesID + '}';
    }
    
}
