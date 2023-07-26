/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class RouteType {
    private String id;
    private String name;
    private String image;
    private String description1;
    private String description2;
    private String status;

    public RouteType() {
    }

    public RouteType(String id, String name, String image, String description1, String description2, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description1 = description1;
        this.description2 = description2;
        this.status = status;
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

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RouteType{" + "id=" + id + ", name=" + name + ", image=" + image + ", description1=" + description1 + ", description2=" + description2 + ", status=" + status + '}';
    }

    

}
