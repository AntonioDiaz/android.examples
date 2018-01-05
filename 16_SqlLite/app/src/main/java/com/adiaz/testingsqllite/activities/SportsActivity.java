package com.adiaz.testingsqllite.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.adiaz.testingsqllite.R;
import com.adiaz.testingsqllite.adapters.SportsAdapter;
import com.adiaz.testingsqllite.db.DbContract;
import com.adiaz.testingsqllite.db.DbHelper;
import com.adiaz.testingsqllite.db.entities.Sport;
import com.adiaz.testingsqllite.retrofit.callbacks.CompetitionsCallback;
import com.adiaz.testingsqllite.retrofit.LocalSportsRestApi;
import com.adiaz.testingsqllite.retrofit.callbacks.SportsCallback;
import com.adiaz.testingsqllite.retrofit.entities.competitions.CompetitionRest;
import com.adiaz.testingsqllite.retrofit.entities.sports.Town;
import com.adiaz.testingsqllite.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SportsActivity extends AppCompatActivity implements 
        SportsCallback.FinishLoad,
        CompetitionsCallback.FinishLoad,
        SportsAdapter.ListItemClickListener {

    private static final String TAG = SportsActivity.class.getSimpleName();
    private static final Long ID_TOWN = 4886402546270208L;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.rv_sports)
    RecyclerView rvSports;

    DbHelper dbHelper;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DbHelper(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_getafe);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(" " + getString(R.string.app_title));
        getSupportActionBar().setSubtitle(" " + getString(R.string.app_subtitle));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LocalSportsRestApi restApi = retrofit.create(LocalSportsRestApi.class);
        Call<List<Town>> repositoriesCall = restApi.queryTowns();
        SportsCallback sportsCallback = new SportsCallback(this, this);
        repositoriesCall.enqueue(sportsCallback);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, CompetitionsActivity.class);
        mCursor.moveToPosition(clickedItemIndex);
        Sport sport = DbContract.SportsEntry.initEntity(mCursor);
        intent.putExtra(Constants.EXTRA_SPORT, sport);
        startActivity(intent);
    }

    @Override
    public void onFinishLoadSports() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LocalSportsRestApi restApi = retrofit.create(LocalSportsRestApi.class);
        Call<List<CompetitionRest>> call = restApi.queryCompetitions(ID_TOWN);
        CompetitionsCallback competitionsCallback = new CompetitionsCallback(this, this);
        call.enqueue(competitionsCallback);
    }

    @Override
    public void onFinishLoadCompetitions() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        mCursor = db.query(
                DbContract.SportsEntry.TABLE_NAME, DbContract.SportsEntry.PROJECTION, null, null, null, null, null);
        Log.d(TAG, "onResponse: " + mCursor.getCount());
        progressBar.setVisibility(View.INVISIBLE);
        rvSports.setVisibility(View.VISIBLE);
        SportsAdapter adapter = new SportsAdapter(this);
        rvSports.setLayoutManager(new LinearLayoutManager(this));
        rvSports.setAdapter(adapter);
        adapter.swapCursor(mCursor);
    }
}
