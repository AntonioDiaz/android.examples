
package com.adiaz.testingsqllite.retrofit.entities.competitionDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("date")
    @Expose
    private Long date;
    @SerializedName("scoreLocal")
    @Expose
    private Long scoreLocal;
    @SerializedName("scoreVisitor")
    @Expose
    private Long scoreVisitor;
    @SerializedName("week")
    @Expose
    private Long week;
    @SerializedName("workingCopy")
    @Expose
    private Boolean workingCopy;
    @SerializedName("teamLocalEntity")
    @Expose
    private TeamLocalEntity teamLocalEntity;
    @SerializedName("teamVisitorEntity")
    @Expose
    private TeamVisitorEntity teamVisitorEntity;
    @SerializedName("state")
    @Expose
    private Long state;
    @SerializedName("sportCenterCourt")
    @Expose
    private SportCenterCourt sportCenterCourt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getScoreLocal() {
        return scoreLocal;
    }

    public void setScoreLocal(Long scoreLocal) {
        this.scoreLocal = scoreLocal;
    }

    public Long getScoreVisitor() {
        return scoreVisitor;
    }

    public void setScoreVisitor(Long scoreVisitor) {
        this.scoreVisitor = scoreVisitor;
    }

    public Long getWeek() {
        return week;
    }

    public void setWeek(Long week) {
        this.week = week;
    }

    public Boolean getWorkingCopy() {
        return workingCopy;
    }

    public void setWorkingCopy(Boolean workingCopy) {
        this.workingCopy = workingCopy;
    }

    public TeamLocalEntity getTeamLocalEntity() {
        return teamLocalEntity;
    }

    public void setTeamLocalEntity(TeamLocalEntity teamLocalEntity) {
        this.teamLocalEntity = teamLocalEntity;
    }

    public TeamVisitorEntity getTeamVisitorEntity() {
        return teamVisitorEntity;
    }

    public void setTeamVisitorEntity(TeamVisitorEntity teamVisitorEntity) {
        this.teamVisitorEntity = teamVisitorEntity;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public SportCenterCourt getSportCenterCourt() {
        return sportCenterCourt;
    }

    public void setSportCenterCourt(SportCenterCourt sportCenterCourt) {
        this.sportCenterCourt = sportCenterCourt;
    }

}
