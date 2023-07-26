/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class RouteTypeItemAndCourse {
    private String id;
    private String routeTypeItemID;
    private String courseID;

    public RouteTypeItemAndCourse() {
    }

    public RouteTypeItemAndCourse(String id, String routeTypeItemID, String courseID) {
        this.id = id;
        this.routeTypeItemID = routeTypeItemID;
        this.courseID = courseID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRouteTypeItemID() {
        return routeTypeItemID;
    }

    public void setRouteTypeItemID(String routeTypeItemID) {
        this.routeTypeItemID = routeTypeItemID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "RouteTypeItemAndCourse{" + "id=" + id + ", routeTypeItemID=" + routeTypeItemID + ", courseID=" + courseID + '}';
    }
    
}
