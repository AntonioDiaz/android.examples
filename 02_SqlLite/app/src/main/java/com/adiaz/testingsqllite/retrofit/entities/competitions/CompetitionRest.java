
package com.adiaz.testingsqllite.retrofit.entities.competitions;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompetitionRest {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sportEntity")
    @Expose
    private SportEntity sportEntity;
    @SerializedName("categoryEntity")
    @Expose
    private CategoryEntity categoryEntity;
    @SerializedName("lastPublished")
    @Expose
    private Long lastPublished;
    @SerializedName("teamsDeref")
    @Expose
    private List<TeamsDeref> teamsDeref = null;
    @SerializedName("teamsAffectedByLastUpdateDeref")
    @Expose
    private List<TeamsAffectedByLastUpdateDeref> teamsAffectedByLastUpdateDeref = null;
    @SerializedName("fullName")
    @Expose
    private String fullName;

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

    public SportEntity getSportEntity() {
        return sportEntity;
    }

    public void setSportEntity(SportEntity sportEntity) {
        this.sportEntity = sportEntity;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public Long getLastPublished() {
        return lastPublished;
    }

    public void setLastPublished(Long lastPublished) {
        this.lastPublished = lastPublished;
    }

    public List<TeamsDeref> getTeamsDeref() {
        return teamsDeref;
    }

    public void setTeamsDeref(List<TeamsDeref> teamsDeref) {
        this.teamsDeref = teamsDeref;
    }

    public List<TeamsAffectedByLastUpdateDeref> getTeamsAffectedByLastUpdateDeref() {
        return teamsAffectedByLastUpdateDeref;
    }

    public void setTeamsAffectedByLastUpdateDeref(List<TeamsAffectedByLastUpdateDeref> teamsAffectedByLastUpdateDeref) {
        this.teamsAffectedByLastUpdateDeref = teamsAffectedByLastUpdateDeref;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
