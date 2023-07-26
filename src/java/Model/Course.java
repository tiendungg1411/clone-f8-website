/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */
public class Course {

    String id;
    String title;
    String numOfPeopleJoin;
    String price;
    String routeID;

    public Course() {
    }

    public Course(String id, String title, String numOfPeopleJoin, String price, String routeID) {
        this.id = id;
        this.title = title;
        this.numOfPeopleJoin = numOfPeopleJoin;
        this.price = price;
        this.routeID = routeID;
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

    public String getNumOfPeopleJoin() {
        return numOfPeopleJoin;
    }

    public void setNumOfPeopleJoin(String numOfPeopleJoin) {
        this.numOfPeopleJoin = numOfPeopleJoin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", title=" + title + ", numOfPeopleJoin=" + numOfPeopleJoin + ", price=" + price + ", routeID=" + routeID + '}';
    }
    
}
