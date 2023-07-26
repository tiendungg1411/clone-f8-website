/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Blog {
    private String id;
    private String title;
    private String topic;
    private String stateId;
    private String content;
    private int numOfLikes;
    private String date;
    private String userID;
    private String userAvatar;
    private String userName;
    private String time;
    private String img;
    private String readMinute;
    private String fullName;
    public Blog() {
    }

    public Blog(String id, String title, String topic, String content, int numOfLikes, String date, String userID, String userAvatar, String userName, String time, String img, String readMinute) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.content = content;
        this.numOfLikes = numOfLikes;
        this.date = date;
        this.userID = userID;
        this.userAvatar = userAvatar;
        this.userName = userName;
        this.time = time;
        this.img = img;
        this.readMinute = readMinute;
    }
    
    public Blog(String id, String title, String topic, String content, int numOfLikes, String date, String userID, String userAvatar, String userName, String time, String img) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.content = content;
        this.numOfLikes = numOfLikes;
        this.date = date;
        this.userID = userID;
        this.userAvatar = userAvatar;
        this.userName = userName;
        this.time = time;
        this.img = img;
    }

    
    public Blog(String id, String title, String topic, String content, int numOfLikes, String date, String userID, String userAvatar, String userName) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.content = content;
        this.numOfLikes = numOfLikes;
        this.date = date;
        this.userID = userID;
        this.userAvatar = userAvatar;
        this.userName = userName;
    }

    public Blog(String id, String title, String stateId, String content, String date, String userID, String userName, String fullName) {
        this.id = id;
        this.title = title;
        this.stateId = stateId;
        this.content = content;
        this.date = date;
        this.userID = userID;
        this.userName = userName;
        this.fullName = fullName;
    }

    

    
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }
    

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(int numOfLikes) {
        this.numOfLikes = numOfLikes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getReadMinute() {
        return readMinute;
    }

    public void setReadMinute(String readMinute) {
        this.readMinute = readMinute;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", topic=" + topic + ", stateId=" + stateId + ", content=" + content + ", numOfLikes=" + numOfLikes + ", date=" + date + ", userID=" + userID + ", userAvatar=" + userAvatar + ", userName=" + userName + ", time=" + time + ", img=" + img + ", readMinute=" + readMinute + ", fullName=" + fullName + '}';
    }
    

    

    
}
