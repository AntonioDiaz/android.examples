
package com.adiaz.testingsqllite.retrofit.entities.competitions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamsDeref {

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
