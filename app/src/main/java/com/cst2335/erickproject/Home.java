package com.cst2335.erickproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button help_button = findViewById(R.id.button);
        Button activity_button1 = findViewById(R.id.button2);
        Button activity_button2 = findViewById(R.id.button3);
        Button activity_button3 = findViewById(R.id.button4);
        ImageButton about_me = findViewById(R.id.imageButton);
        ImageButton about_name = findViewById(R.id.imageButton2);

        EditText name_field =  findViewById(R.id.editTextTextPersonName);
        SharedPreferences nameremain = getSharedPreferences("name", android.content.Context.MODE_PRIVATE);
        String name = nameremain.getString("nameapp","");
        name_field.setText(name);

        activity_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity1 = new Intent(Home.this, Home.class);
                startActivity(goToActivity1);
                    }
                });
        activity_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity2 = new Intent(Home.this, Home.class);
                startActivity(goToActivity2);
            }
        });
        activity_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity3 = new Intent(Home.this, Home.class);
                startActivity(goToActivity3);
            }
        });
        help_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(help_button, getResources().getString(R.string.Enter_Menu2), Snackbar.LENGTH_LONG).show();

            }
        });
        about_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder aboutalert = new AlertDialog.Builder(Home.this);
                aboutalert.setTitle(getResources().getString(R.string.About_Title))
                        .setMessage(getResources().getString(R.string.About_Me)).create().show();
            }


        });
        about_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, getResources().getString(R.string.Name_Help), Toast.LENGTH_LONG).show();
            }
        });
             }
    @Override
    protected void onPause(){
        super.onPause();
        EditText name_field =  findViewById(R.id.editTextTextPersonName);
        SharedPreferences nameremain = getSharedPreferences("name", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = nameremain.edit();
        edit.putString("nameapp", name_field.getText().toString());
        edit.commit();

    }
        }