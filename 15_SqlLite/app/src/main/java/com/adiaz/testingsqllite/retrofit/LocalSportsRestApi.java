package com.adiaz.testingsqllite.retrofit;

import com.adiaz.testingsqllite.retrofit.entities.competitionDetails.CompetitionDetails;
import com.adiaz.testingsqllite.retrofit.entities.competitions.CompetitionRest;
import com.adiaz.testingsqllite.retrofit.entities.sports.Town;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by adiaz on 30/12/17.
 */

public interface LocalSportsRestApi {
    @GET("/server/towns")
    Call<List<Town>> queryTowns();

    @GET("/server/search_competitions/")
    Call<List<CompetitionRest>> queryCompetitions(@Query("idTown")Long idTown);

    @GET("/server/competitiondetails/{idCompetition}")
    Call<CompetitionDetails> queryCompetitionDetails(@Path("idCompetition")Long idCompetition);
}
