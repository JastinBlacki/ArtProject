package com.example.artproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;;

import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button bt_art;
        Button bt_mus;
        Button bt_poem;
        Button bt_story;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        bt_art = findViewById(R.id.button_art);
        bt_mus = findViewById(R.id.button_music);
        bt_poem = findViewById(R.id.button_poem);
        bt_story = findViewById(R.id.button_story);


        bt_art.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, List_of_art.class);
                        intent.putExtra("typeArt", "Art");
                        startActivity(intent);
                    }
                }
        );


        bt_mus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, List_of_art.class);
                        intent.putExtra("typeArt", "Music");
                        startActivity(intent);
                    }
                }
        );

        bt_poem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, List_of_art.class);
                        intent.putExtra("typeArt", "Poem");
                        startActivity(intent);
                    }
                }
        );

        bt_story.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, List_of_art.class);
                        intent.putExtra("typeArt", "Story");
                        startActivity(intent);
                    }
                }
        );
    }


    // TODO: сделать шапку сайта
    // TODO: Сдеелать кнопочку назад
}