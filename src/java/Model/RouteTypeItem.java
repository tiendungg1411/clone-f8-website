/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class RouteTypeItem {
    private String id;
    private String name;
    private String description;
    private String routeTypeID;

    public RouteTypeItem() {
    }

    public RouteTypeItem(String id, String name, String description, String routeTypeID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.routeTypeID = routeTypeID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRouteTypeID() {
        return routeTypeID;
    }

    public void setRouteTypeID(String routeTypeID) {
        this.routeTypeID = routeTypeID;
    }

    @Override
    public String toString() {
        return "RouteTypeItem{" + "id=" + id + ", name=" + name + ", description=" + description + ", routeTypeID=" + routeTypeID + '}';
    }
    
}
