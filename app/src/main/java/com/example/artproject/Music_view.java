package com.example.artproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Music_view extends AppCompatActivity {


    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    Cursor userCursor2;
    String name = "";
    String music_path;
    String text_path = "";
    String author_text = "";
    String mus_text_path = "";
    int author_id = 0;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle argument;

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_music_view);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();

        argument = getIntent().getExtras();

        id = Integer.parseInt(argument.get("id").toString());

        TextView name_ = (TextView) findViewById(R.id.name_);
        TextView author = (TextView) findViewById(R.id.author);
        TextView about_pict = (findViewById(R.id.text_about_pict));

        getData();

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
        userCursor = db.rawQuery("select * from Music;", null);
        userCursor2 = db.rawQuery("select * from Creator;", null);


        while (userCursor.moveToNext()) {
            if((Integer.parseInt(userCursor.getString(0)) - 1) == id){
                name =  userCursor.getString(1);
                text_path = userCursor.getString(3);
                author_id = Integer.parseInt(userCursor.getString(4));
            }

        }

        while (userCursor2.moveToNext()) {
            if((Integer.parseInt(userCursor2.getString(0))) == author_id) author_text = userCursor2.getString(1);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        userCursor.close();
        userCursor2.close();
    }
}