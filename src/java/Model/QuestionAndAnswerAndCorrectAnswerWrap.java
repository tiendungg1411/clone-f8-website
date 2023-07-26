/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class QuestionAndAnswerAndCorrectAnswerWrap {
    private Question question;
    private ArrayList<Answer> answer;
    private CorrectAnswer correctAnswer;
    private AccountDoingQuestion accountDoingQuestion;

    public QuestionAndAnswerAndCorrectAnswerWrap() {
    }

    public QuestionAndAnswerAndCorrectAnswerWrap(Question question, ArrayList<Answer> answer, CorrectAnswer correctAnswer, AccountDoingQuestion accountDoingQuestion) {
        this.question = question;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
        this.accountDoingQuestion = accountDoingQuestion;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Answer> answer) {
        this.answer = answer;
    }

    public CorrectAnswer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(CorrectAnswer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public AccountDoingQuestion getAccountDoingQuestion() {
        return accountDoingQuestion;
    }

    public void setAccountDoingQuestion(AccountDoingQuestion accountDoingQuestion) {
        this.accountDoingQuestion = accountDoingQuestion;
    }

    @Override
    public String toString() {
        return "QuestionAndAnswerAndCorrectAnswerWrap{" + "question=" + question + ", answer=" + answer + ", correctAnswer=" + correctAnswer + ", accountDoingQuestion=" + accountDoingQuestion + '}';
    }

    
}
