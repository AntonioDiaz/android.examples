package com.adiaz.testingsqllite.retrofit.callbacks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.adiaz.testingsqllite.db.DbContract;
import com.adiaz.testingsqllite.db.DbHelper;
import com.adiaz.testingsqllite.retrofit.entities.sports.SportsDeref;
import com.adiaz.testingsqllite.retrofit.entities.sports.Town;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adiaz on 2/1/18.
 */

public class SportsCallback implements Callback<List<Town>> {

    private static final String LEGANES = "Leganés";
    private static final String TAG = SportsCallback.class.getSimpleName();

    private Context context;
    private FinishLoad finishLoad;


    public SportsCallback(Context context, FinishLoad finishLoad) {
        this.context = context;
        this.finishLoad = finishLoad;
    }

    @Override
    public void onResponse(Call<List<Town>> call, Response<List<Town>> response) {
        List<Town> towns = response.body();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DbContract.SportsEntry.TABLE_NAME, null, null);
        for (Town town : towns) {
            if (town.getName().equals(LEGANES)) {
                for (SportsDeref sport : town.getSportsDeref()) {
                    ContentValues values = new ContentValues();
                    values.put(DbContract.SportsEntry.COLUMN_ID, sport.getId());
                    values.put(DbContract.SportsEntry.COLUMN_NAME, sport.getName());
                    values.put(DbContract.SportsEntry.COLUMN_TAG, sport.getTag());
                    values.put(DbContract.SportsEntry.COLUMN_IMAGE, sport.getImage());
                    long insert = db.insert(DbContract.SportsEntry.TABLE_NAME, null, values);
                }
            }
        }
        finishLoad.onFinishLoadSports();
    }

    @Override
    public void onFailure(Call<List<Town>> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.getMessage());
    }

    public interface FinishLoad {
        public void onFinishLoadSports();
    }
}
