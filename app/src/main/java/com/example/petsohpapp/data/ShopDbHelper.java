package com.example.petsohpapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Created by Sheeraz on 6/12/2024.
 */

public class ShopDbHelper extends SQLiteOpenHelper {

    // Name of the Database
    private static final String DATABASE_NAME = "petshop.db";

    // Version of the Database
    private static final int DATABASE_VERSION = 1;

    // Create Database
    private static final String CREATE_TABLE =
            "CREATE TABLE " + ShopContract.ShopEntry.TABLE_NAME + " (" +
                    ShopContract.ShopEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ShopContract.ShopEntry.COLUMN_DOG_NAME + " TEXT NOT NULL, " +
                    ShopContract.ShopEntry.COLUMN_DOG_WEIGHT + " INTEGER NOT NULL DEFAULT 0)";


    // Constructor
    public ShopDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ShopContract.ShopEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
