
package com.adiaz.testingsqllite.retrofit.entities.competitionDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamLocalEntity {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;

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

}
