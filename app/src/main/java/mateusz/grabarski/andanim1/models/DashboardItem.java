package mateusz.grabarski.andanim1.models;

import java.io.Serializable;

/**
 * Created by MGrabarski on 06.12.2017.
 */

public class DashboardItem implements Serializable {

    private String title;
    private String description;
    private int picture;

    public DashboardItem(String title, String description, int picture) {
        this.title = title;
        this.description = description;
        this.picture = picture;
    }

    public String getTitle() { 
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "DashboardItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picture=" + picture +
                '}';
    }
}
