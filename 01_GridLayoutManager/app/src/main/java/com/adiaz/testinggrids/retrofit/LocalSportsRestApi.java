package com.adiaz.testinggrids.retrofit;

import com.adiaz.testinggrids.retrofit.entities.Town;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adiaz on 30/12/17.
 */

public interface LocalSportsRestApi {
    @GET("/server/towns")
    Call<List<Town>> queryTowns();
}
