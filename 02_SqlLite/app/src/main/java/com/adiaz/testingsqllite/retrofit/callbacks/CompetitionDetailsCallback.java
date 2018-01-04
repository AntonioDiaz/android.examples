package com.adiaz.testingsqllite.retrofit.callbacks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.adiaz.testingsqllite.db.DbHelper;
import com.adiaz.testingsqllite.db.entities.Competition;
import com.adiaz.testingsqllite.retrofit.entities.competitionDetails.Classification;
import com.adiaz.testingsqllite.retrofit.entities.competitionDetails.CompetitionDetails;
import com.adiaz.testingsqllite.retrofit.entities.competitionDetails.Match;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.adiaz.testingsqllite.db.DbContract.MatchesEntry;

/**
 * Created by adiaz on 2/1/18.
 */

public class CompetitionDetailsCallback implements Callback<CompetitionDetails> {

    private static final String TAG = CompetitionDetailsCallback.class.getSimpleName();
    Competition mCompetition;
    Context mContext;
    FinishLoad finishLoad;

    public CompetitionDetailsCallback(Context context, FinishLoad finishLoad, Competition competition) {
        this.mContext = context;
        this.finishLoad = finishLoad;
        this.mCompetition = competition;
    }

    @Override
    public void onResponse(Call<CompetitionDetails> call, Response<CompetitionDetails> response) {
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = "id=?";
        String[] selectionArgs = new String[]{mCompetition.id().toString()};
        db.delete(MatchesEntry.TABLE_NAME, selection, selectionArgs);
        for (Match match : response.body().getMatches()) {
            //Log.d(TAG, "onResponse: match" + match);
            ContentValues cv = new ContentValues();
            cv.put(MatchesEntry.COLUMN_ID, match.getId());
            cv.put(MatchesEntry.COLUMN_TEAM_LOCAL, match.getTeamLocalEntity().getName());
            cv.put(MatchesEntry.COLUMN_TEAM_VISITOR, match.getTeamVisitorEntity().getName());
        }
        // TODO: 2/1/18 load classification.

        finishLoad.onFinishLoadCompetitionDetails();
    }

    @Override
    public void onFailure(Call<CompetitionDetails> call, Throwable t) {

    }

    public interface FinishLoad {
        public void onFinishLoadCompetitionDetails();
    }
}
