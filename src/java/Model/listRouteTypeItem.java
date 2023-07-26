/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TIEN DAT
 */
public class listRouteTypeItem {
    String id;
    String nameRouteType;
    String nameRoutyTypeItem;
    String description;
    String title;

    public listRouteTypeItem() {
    }

    public listRouteTypeItem(String id, String nameRouteType, String nameRoutyTypeItem, String description, String title) {
        this.id = id;
        this.nameRouteType = nameRouteType;
        this.nameRoutyTypeItem = nameRoutyTypeItem;
        this.description = description;
        this.title = title;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameRouteType() {
        return nameRouteType;
    }

    public void setNameRouteType(String nameRouteType) {
        this.nameRouteType = nameRouteType;
    }

    public String getNameRoutyTypeItem() {
        return nameRoutyTypeItem;
    }

    public void setNameRoutyTypeItem(String nameRoutyTypeItem) {
        this.nameRoutyTypeItem = nameRoutyTypeItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "listRouteTypeItem{" + "id=" + id + ", nameRouteType=" + nameRouteType + ", nameRoutyTypeItem=" + nameRoutyTypeItem + ", description=" + description + ", title=" + title + '}';
    }
    
}
