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
public class LearningPathWrap {
    private RouteTypeItem routeTypeItem;
    private ArrayList<CourseDetail> courses;

    public LearningPathWrap() {
    }

    public LearningPathWrap(RouteTypeItem routeTypeItem, ArrayList<CourseDetail> courses) {
        this.routeTypeItem = routeTypeItem;
        this.courses = courses;
    }

    

    public RouteTypeItem getRouteTypeItem() {
        return routeTypeItem;
    }

    public void setRouteTypeItem(RouteTypeItem routeTypeItem) {
        this.routeTypeItem = routeTypeItem;
    }

    public ArrayList<CourseDetail> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CourseDetail> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "LearningPathWrap{" + "routeTypeItem=" + routeTypeItem + ", courses=" + courses + '}';
    }

    
}
