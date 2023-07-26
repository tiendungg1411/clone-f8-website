/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Time;

/**
 *
 * @author dell
 */
public class LessonVideo {

    private String id;
    private String name;
    private String content;
    private String link;
    private Time duration;
    private String retry;
    private String date;
    private String numOfLikes;
    private String type;
    private Course courseID;
    private Chapter chapterID;

    public LessonVideo() {
    }

    public LessonVideo(String id, String name, String content, String link, Time duration, String retry, String date, String numOfLikes, String type, Course courseID, Chapter chapterID) {
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

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
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

    public Course getCourseID() {
        return courseID;
    }

    public void setCourseID(Course courseID) {
        this.courseID = courseID;
    }

    public Chapter getChapterID() {
        return chapterID;
    }

    public void setChapterID(Chapter chapterID) {
        this.chapterID = chapterID;
    }
    
}
