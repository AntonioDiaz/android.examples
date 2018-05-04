package com.adiaz.searchwidget.retrofit;

import com.adiaz.searchwidget.retrofit.entities.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TeamsRestApi {

    @GET("/server/findTeam")
    Call<List<Team>> queryTeams(@Query("team_name") String teamName);

}
