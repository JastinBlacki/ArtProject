package com.example.artproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Artwork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle argument;

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_artwork);

        argument = getIntent().getExtras();


        int id = Integer.parseInt(argument.get("id").toString());
        String typeArt = argument.get("typeArt").toString();


        Intent intent;
        switch (typeArt) {
            case "Art":
                intent = new Intent(Artwork.this, Art_view.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case "Poem":
                intent = new Intent(Artwork.this, Poem_view.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case "Story":
                intent = new Intent(Artwork.this, Story_activity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            default:
                intent = new Intent(Artwork.this, Music_view.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
        }
    }
}