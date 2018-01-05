package com.adiaz.testingsqllite.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.adiaz.testingsqllite.R;
import com.adiaz.testingsqllite.adapters.CompetitionsAdapter;
import com.adiaz.testingsqllite.db.DbHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.adiaz.testingsqllite.db.DbContract.CompetitionsEntry;
import com.adiaz.testingsqllite.db.entities.Competition;
import com.adiaz.testingsqllite.db.entities.Sport;
import com.adiaz.testingsqllite.utils.Constants;

/**
 * Created by adiaz on 2/1/18.
 */

public class CompetitionsActivity extends AppCompatActivity implements CompetitionsAdapter.ListItemClickListener {

    @BindView(R.id.pb_competitions)
    ProgressBar progressBar;

    @BindView(R.id.rv_competitions)
    RecyclerView rvCompetitions;
    private Cursor mCursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competitions);
        ButterKnife.bind(this);


        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundleExtra = (Bundle) getIntent().getExtras();
        Sport sport = (Sport) bundleExtra.getParcelable(Constants.EXTRA_SPORT);

        getSupportActionBar().setTitle("Sport: " + sport.name());
        progressBar.setVisibility(View.INVISIBLE);
        rvCompetitions.setVisibility(View.VISIBLE);
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = "id_sport=?";
        String[] selectionArgs = new String[]{sport.id().toString()};
        mCursor = db.query(CompetitionsEntry.TABLE_NAME, CompetitionsEntry.PROJECTION, selection, selectionArgs, null, null, null);
        CompetitionsAdapter competitionsAdapter = new CompetitionsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvCompetitions.setLayoutManager(linearLayoutManager);
        rvCompetitions.setAdapter(competitionsAdapter);
        competitionsAdapter.swapCursor(mCursor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        mCursor.moveToPosition(clickedItemIndex);
        Competition competition = CompetitionsEntry.initEntity(mCursor);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Constants.EXTRA_COMPETITION, competition);
        startActivity(intent);
    }
}
