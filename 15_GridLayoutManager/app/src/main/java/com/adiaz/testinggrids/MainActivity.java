package com.adiaz.testinggrids;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adiaz.testinggrids.retrofit.LocalSportsRestApi;
import com.adiaz.testinggrids.retrofit.entities.Town;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<Town>>, TownsAdapter.ListItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://localsports-web.appspot.com";

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<Town>mTowns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LocalSportsRestApi localSportsRestApi = retrofit.create(LocalSportsRestApi.class);
        Call<List<Town>> repositoriesCall = localSportsRestApi.queryTowns ();
        repositoriesCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Town>> call, Response<List<Town>> response) {
        Log.d(TAG, "onResponse: townsList -->" + response.body().size());
        progressBar.setVisibility(View.INVISIBLE);
        mTowns = response.body();
        recyclerView.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        TownsAdapter townsAdapter = new TownsAdapter(mTowns, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(townsAdapter);
    }

    @Override
    public void onFailure(Call<List<Town>> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.getMessage());
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        String toastStr = "Item #" + clickedItemIndex + " clicked.";
        Log.d(TAG, "Toast: " + toastStr);
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.town_select), mTowns.get(clickedItemIndex).getName());
        editor.commit();


        Intent intent = new Intent(this, SportsActivity.class);
        startActivity(intent);

    }
}
