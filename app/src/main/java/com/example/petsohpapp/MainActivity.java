package com.example.petsohpapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.petsohpapp.data.ShopContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    // Cursor Adapter for the data
    private CursorAdapter cursorAdapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating cursor Adapter
        cursorAdapter = new ShopCursorAdapter(this, null, 0);

        // Getting the list layout and applying the cursor Adapter
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(cursorAdapter);

        // Toolbar
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Init loader
        getSupportLoaderManager().initLoader(0, null, this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creating a dialog to enter Dog information
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View dialogView = layoutInflater.inflate(R.layout.dialog_get_dog_info, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView);

                // Get dialog views objects
                final EditText etDogName = dialogView.findViewById(R.id.etDogName);
                final EditText etDogWeight = dialogView.findViewById(R.id.etDogWeight);

                // Add functionality
                builder.setCancelable(false)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                // Get user input and set it to result
                                insertDog(etDogName.getText().toString(), etDogWeight.getText().toString());
                                restartLoader();


                            }
                        }).create()
                        .show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void insertDog(String dogName, String dogWeight) {

        // Place values
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShopContract.ShopEntry.COLUMN_DOG_NAME, dogName);
        contentValues.put(ShopContract.ShopEntry.COLUMN_DOG_WEIGHT, dogWeight);
        // // Insert a new row for Dog input into the provider using the ContentResolver.
        getContentResolver().insert(ShopContract.ShopEntry.CONTENT_URI, contentValues);


    }

    private void restartLoader() {

        getSupportLoaderManager().restartLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        // connection to contactProvider
        return new CursorLoader(this, ShopContract.ShopEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

        // results from the query  of the contact provider
        // set results into adapter
        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

        cursorAdapter.swapCursor(null);
    }
}