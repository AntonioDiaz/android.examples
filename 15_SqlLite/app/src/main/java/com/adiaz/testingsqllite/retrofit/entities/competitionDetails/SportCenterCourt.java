
package com.adiaz.testingsqllite.retrofit.entities.competitionDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SportCenterCourt {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nameWithCenter")
    @Expose
    private String nameWithCenter;
    @SerializedName("sportCenter")
    @Expose
    private SportCenter sportCenter;

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

    public String getNameWithCenter() {
        return nameWithCenter;
    }

    public void setNameWithCenter(String nameWithCenter) {
        this.nameWithCenter = nameWithCenter;
    }

    public SportCenter getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenter sportCenter) {
        this.sportCenter = sportCenter;
    }

}
