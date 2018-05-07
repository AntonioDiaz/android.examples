package com.adiaz.searchwidget.activities;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.adiaz.searchwidget.R;
import com.adiaz.searchwidget.adapter.SearchTeamAdapter;
import com.adiaz.searchwidget.retrofit.TeamsRestApi;
import com.adiaz.searchwidget.retrofit.entities.Team;

import java.util.List;
import java.util.concurrent.TimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<Team>> {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://munisports-web.appspot.com";
    public static final Integer TOTAL_RETRIES = 3;
    private int retryCount = 0;
    ProgressDialog progressDialog;

    @BindView(R.id.view_sports)
    View viewSports;

    @BindView(R.id.view_search_results)
    View viewSearchResults;

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @BindView(R.id.rv_teams)
    RecyclerView rvTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Its loading");
        progressDialog.setTitle("ProgressDialog bar example");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        handleIntent(getIntent());
        viewSports.setVisibility(View.VISIBLE);
        viewSearchResults.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.d(TAG, "handleIntent: " + intent.getAction());
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String teamName = intent.getStringExtra(SearchManager.QUERY);
            progressDialog.show();
            retryCount = 0;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            TeamsRestApi teamsRestApi = retrofit.create(TeamsRestApi.class);
            Call<List<Team>> repositoriesCall = teamsRestApi.queryTeams(teamName);
            repositoriesCall.enqueue(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItemSearch = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItemSearch.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        menuItemSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                viewSports.setVisibility(View.VISIBLE);
                viewSearchResults.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
        progressDialog.dismiss();
        viewSports.setVisibility(View.INVISIBLE);
        viewSearchResults.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTeams.setLayoutManager(layoutManager);
        rvTeams.setHasFixedSize(true);
        SearchTeamAdapter adapter = new SearchTeamAdapter(response.body(), this);
        rvTeams.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<Team>> call, Throwable t) {
        Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
        if (t instanceof TimeoutException && retryCount++<TOTAL_RETRIES) {
            Log.d(TAG, "retrying retryCount: " + retryCount + " out of " + TOTAL_RETRIES);
            call.clone().enqueue(this);
        } else {
            progressDialog.dismiss();
            viewSports.setVisibility(View.INVISIBLE);
            viewSearchResults.setVisibility(View.VISIBLE);
        }
    }
}
