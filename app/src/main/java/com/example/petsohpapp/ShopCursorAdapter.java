package com.example.petsohpapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

import com.example.petsohpapp.data.ShopContract;

/**
 * Created by Innovation Lab on 6/12/2024.
 */

public class ShopCursorAdapter extends CursorAdapter {

    private String dogName, dogWeight;
    private TextView tvDogName, tvDogWeight;

    public ShopCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

//    public ShopCursorAdapter(Context context, Cursor cursor, int flags) {
//        super(context, cursor, flags);
//    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        // Setup layout
        return LayoutInflater.from(context).inflate(R.layout.dog_list_item, viewGroup, false);
    }

    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Get Cursor Result
        dogName = cursor.getString(cursor.getColumnIndex(ShopContract.ShopEntry.COLUMN_DOG_NAME));
        dogWeight = cursor.getString(cursor.getColumnIndex(ShopContract.ShopEntry.COLUMN_DOG_WEIGHT));

        // Find views
        tvDogName = view.findViewById(R.id.tvDogName);
        tvDogWeight = view.findViewById(R.id.tvDogWeight);

        // Set text in views
        tvDogName.setText(dogName);
        tvDogWeight.setText(dogWeight);

    }
}
