package com.adiaz.testinggrids;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.adiaz.testinggrids.retrofit.LocalSportsRestApi;
import com.adiaz.testinggrids.retrofit.entities.SportsDeref;
import com.adiaz.testinggrids.retrofit.entities.Town;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adiaz on 30/12/17.
 */

public class SportsActivity extends AppCompatActivity implements Callback<List<Town>>  {
    private static final String TAG = SportsActivity.class.getSimpleName();

    @BindView(R.id.pb_sports)
    ProgressBar progressBar;

    @BindView(R.id.rv_sports)
    RecyclerView recyclerView;

    List<SportsDeref> sportsList;
    private String mTownSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        ButterKnife.bind(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        mTownSelected  = sharedPref.getString(getString(R.string.town_select), null);
        setTitle(mTownSelected);
        getSupportActionBar().setTitle(getString(R.string.app_name) + " - " + mTownSelected);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LocalSportsRestApi localSportsRestApi = retrofit.create(LocalSportsRestApi.class);
        Call<List<Town>> repositoriesCall = localSportsRestApi.queryTowns();
        repositoriesCall.enqueue(this);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResponse(Call<List<Town>> call, Response<List<Town>> response) {
        progressBar.setVisibility(View.INVISIBLE);
        List<Town> towns = response.body();
        for (Town town : towns) {
            if (town.getName().equals(mTownSelected)) {
                sportsList = town.getSportsDeref();
            }
        }
        recyclerView.setVisibility(View.VISIBLE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        SportsAdapter sportsAdapter = new SportsAdapter(sportsList);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(sportsAdapter);
        sportsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<Town>> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.getMessage());
        progressBar.setVisibility(View.INVISIBLE);
    }
}
