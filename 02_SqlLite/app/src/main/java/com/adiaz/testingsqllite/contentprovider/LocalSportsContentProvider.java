package com.adiaz.testingsqllite.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.adiaz.testingsqllite.db.DbContract;
import com.adiaz.testingsqllite.db.DbHelper;

import static com.adiaz.testingsqllite.db.DbContract.SportsEntry;

/**
 * Created by adiaz on 1/1/18.
 */

public class LocalSportsContentProvider extends ContentProvider {

    public static final int SPORTS = 100;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private DbHelper mDbHelper;

    public static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DbContract.AUTHORITY, DbContract.PATH_SPORTS, SPORTS);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri returnedUri = null;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match) {
            case SPORTS:
                long idNew = db.insert(SportsEntry.TABLE_NAME, null, contentValues);
                if (idNew>0) {
                    Uri.Builder builder = SportsEntry.CONTENT_URI.buildUpon();
                    returnedUri = ContentUris.appendId(builder, idNew).build();
                } else {
                    throw new SQLException("error on insert" + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("ERROR " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnedUri;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int newRecords = 0;
        switch (match) {
            case SPORTS:
                int rowsInserted = 0;
                try {
                    db.beginTransaction();
                    for (ContentValues value: values) {
                        final long idInserted = db.insert(SportsEntry.TABLE_NAME, null, value);
                        if (idInserted != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                if (rowsInserted>0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsInserted;
            default:
                super.bulkInsert(uri, values);
        }
        return newRecords;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
