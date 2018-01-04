package com.adiaz.testingsqllite.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.adiaz.testingsqllite.db.entities.Competition;
import com.adiaz.testingsqllite.db.entities.Sport;
import com.adiaz.testingsqllite.utils.Constants;

/**
 * Created by adiaz on 2/1/18.
 */

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        Competition competition = (Competition) extras.getParcelable(Constants.EXTRA_COMPETITION);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("ohhh yeah" + competition.name());
        }
    }
}
