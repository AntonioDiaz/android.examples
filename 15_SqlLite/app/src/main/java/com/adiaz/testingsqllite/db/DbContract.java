package com.adiaz.testingsqllite.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.adiaz.testingsqllite.db.entities.Competition;
import com.adiaz.testingsqllite.db.entities.Match;
import com.adiaz.testingsqllite.db.entities.Sport;

/**
 * Created by adiaz on 1/1/18.
 */

public class DbContract {

    public static final String AUTHORITY = "com.adiaz.testingsqllite";
    public static final Uri BASE_CONTENT = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_SPORTS = "sports";

    public static final class SportsEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT.buildUpon().appendPath(PATH_SPORTS).build();
        public static final String TABLE_NAME = "SPORTS";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TAG = "tag";
        public static final String COLUMN_IMAGE = "image";

        public static final String[] PROJECTION = {COLUMN_ID, COLUMN_NAME, COLUMN_TAG, COLUMN_IMAGE};
        public static final int INDEX_ID = 0;
        public static final int INDEX_NAME = 1;
        public static final int INDEX_TAG = 2;
        public static final int INDEX_IMAGE = 3;

        public static Sport initEntity(Cursor cursor) {
            Sport sport = Sport.builder()
                    .id(cursor.getLong(INDEX_ID))
                    .name(cursor.getString(INDEX_NAME))
                    .tag(cursor.getString(INDEX_TAG))
                    .image(cursor.getString(INDEX_IMAGE)).build();
            return sport;
        }
    }

    public static final class CompetitionsEntry implements BaseColumns {

        public static final String TABLE_NAME = "COMPETITIONS";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_ID_SPORT = "id_sport";

        public static final String[] PROJECTION = {COLUMN_ID, COLUMN_NAME, COLUMN_CATEGORY, COLUMN_ID_SPORT};
        public static final int INDEX_ID = 0;
        public static final int INDEX_NAME = 1;
        public static final int INDEX_CATEGORY = 2;
        public static final int INDEX_ID_SPORT = 3;

        public static Competition initEntity(Cursor cursor) {
            Competition competition = Competition.builder()
                    .id(cursor.getLong(INDEX_ID))
                    .name(cursor.getString(INDEX_NAME))
                    .category(cursor.getString(INDEX_CATEGORY))
                    .idSport(cursor.getLong(INDEX_ID_SPORT))
                    .build();
            return competition;
        }
    }

    public static final class MatchesEntry implements BaseColumns {

        public static final String TABLE_NAME = "MATCHES";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TEAM_LOCAL = "team_local";
        public static final String COLUMN_TEAM_VISITOR = "team_visitor";
        public static final String COLUMN_COMPETITION_ID = "id_competition";

        public static final String[] PROJECTION = {COLUMN_ID, COLUMN_TEAM_LOCAL, COLUMN_TEAM_VISITOR};
        public static final int INDEX_ID = 0;
        public static final int INDEX_TEAM_LOCAL = 1;
        public static final int INDEX_TEAM_VISITOR = 2;

        public static Match initEntity(Cursor cursor) {
            Match match = Match.builder()
                    .id(cursor.getLong(INDEX_ID))
                    .teamLocal(cursor.getString(INDEX_TEAM_LOCAL))
                    .teamVisitor(cursor.getString(INDEX_TEAM_VISITOR))
                    .build();
            return match;
        }
    }

}
