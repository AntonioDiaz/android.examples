package com.adiaz.testinggrids.retrofit.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Town {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("iconName")
    @Expose
    private String iconName;
    @SerializedName("colorPrimary")
    @Expose
    private Object colorPrimary;
    @SerializedName("colorAccent")
    @Expose
    private Object colorAccent;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("sportsDeref")
    @Expose
    private List<SportsDeref> sportsDeref = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public Object getColorPrimary() {
        return colorPrimary;
    }

    public void setColorPrimary(Object colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    public Object getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(Object colorAccent) {
        this.colorAccent = colorAccent;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<SportsDeref> getSportsDeref() {
        return sportsDeref;
    }

    public void setSportsDeref(List<SportsDeref> sportsDeref) {
        this.sportsDeref = sportsDeref;
    }

}
