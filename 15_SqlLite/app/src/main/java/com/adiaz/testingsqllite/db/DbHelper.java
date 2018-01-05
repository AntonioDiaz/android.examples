package com.adiaz.testingsqllite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.adiaz.testingsqllite.db.DbContract.SportsEntry;
import static com.adiaz.testingsqllite.db.DbContract.CompetitionsEntry;
import static com.adiaz.testingsqllite.db.DbContract.MatchesEntry;

/**
 * Created by adiaz on 31/12/17.
 */

public class DbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "database.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_SPORTS_TABLE =
                "CREATE TABLE " + SportsEntry.TABLE_NAME +
                        "(" +
                        SportsEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        SportsEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                        SportsEntry.COLUMN_TAG + " TEXT NOT NULL, " +
                        SportsEntry.COLUMN_IMAGE + " TEXT NOT NULL " +
                        ")";
        sqLiteDatabase.execSQL(SQL_CREATE_SPORTS_TABLE);

        final String SQL_CREATE_COMPETITIONS_TABLE =
                "CREATE TABLE " + CompetitionsEntry.TABLE_NAME +
                        "(" +
                        CompetitionsEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        CompetitionsEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                        CompetitionsEntry.COLUMN_CATEGORY + " TEXT NOT NULL, " +
                        CompetitionsEntry.COLUMN_ID_SPORT + " INTEGER NOT NULL, " +
                        "FOREIGN KEY(" + CompetitionsEntry.COLUMN_ID_SPORT + ") REFERENCES " + SportsEntry.TABLE_NAME + "(" + SportsEntry.COLUMN_ID + ")" +
                        ")";
        sqLiteDatabase.execSQL(SQL_CREATE_COMPETITIONS_TABLE);

        final String SQL_CREATE_MATCHES_TABLE =
                "CREATE TABLE " + MatchesEntry.TABLE_NAME +
                        "(" +
                        MatchesEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        MatchesEntry.COLUMN_TEAM_LOCAL + " TEXT NOT NULL, " +
                        MatchesEntry.COLUMN_TEAM_VISITOR + " TEXT NOT NULL, " +
                        MatchesEntry.COLUMN_COMPETITION_ID + " INTEGER NOT NULL, " +
                        "FOREIGN KEY(" + MatchesEntry.COLUMN_COMPETITION_ID + ") REFERENCES " + CompetitionsEntry.TABLE_NAME + "(" + CompetitionsEntry.COLUMN_ID + ")" +
                        ")";
        sqLiteDatabase.execSQL(SQL_CREATE_MATCHES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SportsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CompetitionsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MatchesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
