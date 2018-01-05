
package com.adiaz.testingsqllite.retrofit.entities.competitionDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompetitionDetails {

    @SerializedName("lastPublished")
    @Expose
    private Long lastPublished;
    @SerializedName("matches")
    @Expose
    private List<Match> matches = null;
    @SerializedName("classification")
    @Expose
    private List<Classification> classification = null;

    public Long getLastPublished() {
        return lastPublished;
    }

    public void setLastPublished(Long lastPublished) {
        this.lastPublished = lastPublished;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<Classification> getClassification() {
        return classification;
    }

    public void setClassification(List<Classification> classification) {
        this.classification = classification;
    }

}
