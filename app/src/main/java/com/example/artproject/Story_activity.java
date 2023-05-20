package com.example.artproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class Story_activity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor query;
    Cursor query2;
    String name = "";
    String text_path = "";
    String author_text = "";
    int author_id = 0;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        getSupportActionBar().hide();

        Bundle argument;

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();

        argument = getIntent().getExtras();

        id = Integer.parseInt(argument.get("id").toString());

        getData();

        TextView name_ = (TextView) findViewById(R.id.name_);
        TextView author = (TextView) findViewById(R.id.author);
        TextView about_pict = (findViewById(R.id.text_about_pict));

        name_.setText(name);
        author.setText(author_text);
        String str_ = "";

        try{
            InputStream inputStream = getAssets().open(text_path);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);

            str_ = new String(buffer);
        } catch (IOException e){
            e.printStackTrace();
        }

        about_pict.setText(str_);
    }

    private void getData(){
        db = databaseHelper.open();
        query = db.rawQuery("SELECT * FROM Story;", null);
        query2 = db.rawQuery("SELECT * FROM Country;", null);

        while (query.moveToNext()) {
            if((Integer.parseInt(query.getString(0)) - 1) == id){
                name =  query.getString(1);
                text_path = query.getString(3);
                author_id = Integer.parseInt(query.getString(2));
            }

        }

        while (query2.moveToNext()) {
            if((Integer.parseInt(query2.getString(0))) == author_id) author_text = query2.getString(1);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        query.close();
        query2.close();
    }
}