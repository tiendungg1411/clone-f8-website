/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Time;

/**
 *
 * @author DELL
 */
public class Lesson {
    String id; 
    String name;
    String content;
    String link;
    Time duration;
    String retry;
    String date;
    String numOfLikes;
    String type;
    String  courseID;
    String chapterID;

    public Lesson() {
    }

    public Lesson(String id, String name) {
        this.id = id;
        this.name = name;
    }
    

    public Lesson(String id, String name, String content, String link, String retry, String date, String numOfLikes, String type, String courseID, String chapterID) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.link = link;
        this.retry = retry;
        this.date = date;
        this.numOfLikes = numOfLikes;
        this.type = type;
        this.courseID = courseID;
        this.chapterID = chapterID;
    }

    public Lesson(String id, String name, String content, String link, Time duration, String retry, String date, String numOfLikes, String type, String courseID, String chapterID) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.link = link;
        this.duration = duration;
        this.retry = retry;
        this.date = date;
        this.numOfLikes = numOfLikes;
        this.type = type;
        this.courseID = courseID;
        this.chapterID = chapterID;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRetry() {
        return retry;
    }

    public void setRetry(String retry) {
        this.retry = retry;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(String numOfLikes) {
        this.numOfLikes = numOfLikes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChapterID() {
        return chapterID;
    }

    public void setChapterID(String chapterID) {
        this.chapterID = chapterID;
    }

    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", name=" + name + ", content=" + content + ", link=" + link + ", retry=" + retry + ", date=" + date + ", numOfLikes=" + numOfLikes + ", type=" + type + ", courseID=" + courseID + ", chapterID=" + chapterID + '}';
    }

    
    
}
