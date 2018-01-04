package com.adiaz.testingsqllite.retrofit.callbacks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.adiaz.testingsqllite.db.DbHelper;
import com.adiaz.testingsqllite.db.entities.Competition;
import com.adiaz.testingsqllite.retrofit.LocalSportsRestApi;
import com.adiaz.testingsqllite.retrofit.entities.competitionDetails.CompetitionDetails;
import com.adiaz.testingsqllite.retrofit.entities.competitions.CompetitionRest;
import com.adiaz.testingsqllite.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.adiaz.testingsqllite.db.DbContract.CompetitionsEntry;

/**
 * Created by adiaz on 2/1/18.
 */

public class CompetitionsCallback implements Callback<List<CompetitionRest>>, CompetitionDetailsCallback.FinishLoad {

    public static final String TAG = CompetitionsCallback.class.getSimpleName();
    private Context mContext;
    private FinishLoad mFinishLoad;
    private int competitionsToLoad = 0;

    public CompetitionsCallback(Context mContext, FinishLoad mFinishLoad) {
        this.mContext = mContext;
        this.mFinishLoad = mFinishLoad;
    }

    @Override
    public void onResponse(Call<List<CompetitionRest>> call, Response<List<CompetitionRest>> response) {
        List<CompetitionRest> competitionRests = response.body();
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(CompetitionsEntry.TABLE_NAME, null, null);
        competitionsToLoad = competitionRests.size();
        for (CompetitionRest competitionRest : competitionRests) {
            ContentValues values = new ContentValues();
            values.put(CompetitionsEntry.COLUMN_ID, competitionRest.getId());
            values.put(CompetitionsEntry.COLUMN_NAME, competitionRest.getName());
            values.put(CompetitionsEntry.COLUMN_CATEGORY, competitionRest.getCategoryEntity().getName());
            values.put(CompetitionsEntry.COLUMN_ID_SPORT, competitionRest.getSportEntity().getId());
            long insert = db.insert(CompetitionsEntry.TABLE_NAME, null, values);
            String select = "id=?";
            String []selectArgs = {Long.toString(insert)};
            Cursor cursor = db.query(CompetitionsEntry.TABLE_NAME, CompetitionsEntry.PROJECTION, select, selectArgs,
                    null, null, null, null);
            cursor.moveToFirst();
            Competition competition = CompetitionsEntry.initEntity(cursor);
            Log.d(TAG, "onResponse: inserted " + insert);
            //query competition details
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LocalSportsRestApi restApi = retrofit.create(LocalSportsRestApi.class);
            Call<CompetitionDetails> competitionDetailsCall = restApi.queryCompetitionDetails(competitionRest.getId());
            CompetitionDetailsCallback competitionDetailsCallback = new CompetitionDetailsCallback(mContext, this, competition);
            competitionDetailsCall.enqueue(competitionDetailsCallback);
        }
        if(competitionsToLoad==0) {
            mFinishLoad.onFinishLoadCompetitions();
        }
    }

    @Override
    public void onFailure(Call<List<CompetitionRest>> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.getMessage());
    }

    @Override
    public void onFinishLoadCompetitionDetails() {
        competitionsToLoad--;
        if(competitionsToLoad==0) {
            mFinishLoad.onFinishLoadCompetitions();
        }
    }

    public interface FinishLoad {
        public void onFinishLoadCompetitions();
    }

}
