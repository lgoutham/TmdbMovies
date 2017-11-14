package com.example.greddy.movies.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.greddy.movies.database.TablesData.CREATE_TABLE_MOVIE;
import static com.example.greddy.movies.database.TablesData.MOVIE_TABLE;

/**
 * Created by gautam on 1/11/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MoviesManager";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating required tables
        db.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + MOVIE_TABLE);

        //create new tables
        onCreate(db);
    }

    public SQLiteDatabase getDbWithReadAccess(){
        return this.getReadableDatabase();
    }

    public SQLiteDatabase getDbWithWriteAccess(){
        return this.getWritableDatabase();
    }
}
