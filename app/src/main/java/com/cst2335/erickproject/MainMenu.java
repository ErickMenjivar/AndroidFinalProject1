package com.cst2335.erickproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    Button btnSearch;
    Button btnFavorites;
    Button btnAbout;
    TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnSearch = findViewById(R.id.button3);
        btnFavorites = findViewById(R.id.button4);
        btnAbout =  findViewById(R.id.button5);
        nameText = findViewById(R.id.nametv);

        SharedPreferences mypref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mypref.edit();

        String name = mypref.getString(getString(R.string.name), "");
        nameText.setText(name);

        btnSearch.setOnClickListener(view ->{
            Intent i1 = new Intent(MainMenu.this, RvJson.class);
            startActivity(i1);
        });
    }
}