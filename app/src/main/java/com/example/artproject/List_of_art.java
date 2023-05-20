package com.example.artproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class List_of_art extends AppCompatActivity {


    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    ArrayList<String> art_name = new ArrayList<String>();
    Cursor userCursor;
    Bundle argument;
    String typeArt;
    TextView head;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_art);
        getSupportActionBar().hide();


        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();

        super.onResume();

        argument = getIntent().getExtras();
        
        typeArt = argument.get("typeArt").toString();

        head = (TextView) findViewById(R.id.head);

        switch (typeArt) {
            case "Art":
                art_name = getArtData();
                head.setText("Живопись");
                break;
            case "Poem":
                art_name = getPoemData();
                head.setText("Поэзия");
                break;
            case "Story":
                art_name = getStoryData();
                head.setText("Легенды");
                break;
            default:
                art_name = getMusicData();
                head.setText("Музыка");
                break;
        }

        ListView list_art = findViewById(R.id.just_list);

        TextView txv = (TextView) findViewById(R.id.head);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, art_name);

        list_art.setAdapter(adapter);

        list_art.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {

                Intent intent = new Intent(List_of_art.this, Artwork.class);
                intent.putExtra("id", id);
                intent.putExtra("typeArt", typeArt);
                startActivity(intent);
            }
        });

    }

    private ArrayList<String> getMusicData(){
        ArrayList<String> name = new ArrayList<String>();

        db = databaseHelper.open();
        userCursor = db.rawQuery("select * from Music", null);
        while (userCursor.moveToNext()) {
            name.add(userCursor.getString(1));
        }

        return name;
    }

    private ArrayList<String> getArtData(){
        ArrayList<String> name = new ArrayList<String>();

        db = databaseHelper.open();
        userCursor = db.rawQuery("select * from Art", null);
        while (userCursor.moveToNext()) {
            name.add(userCursor.getString(1));
        }

        return name;
    }

    private ArrayList<String> getPoemData(){
        ArrayList<String> name = new ArrayList<String>();

        db = databaseHelper.open();
        userCursor = db.rawQuery("select * from Poem", null);
        while (userCursor.moveToNext()) {
            name.add(userCursor.getString(2));
        }

        return name;
    }

    private ArrayList<String> getStoryData(){
        ArrayList<String> name = new ArrayList<String>();

        db = databaseHelper.open();
        userCursor = db.rawQuery("select * from Story", null);
        while (userCursor.moveToNext()) {
            name.add(userCursor.getString(1));
        }

        return name;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        userCursor.close();
    }
}