package com.cst2335.erickproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button loginbtn;
    EditText username;
    EditText password;
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor1;
    static String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginbtn = findViewById(R.id.loginbtn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password1);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor1 = mPrefs.edit();
        checkSharePreferences();


        loginbtn.setOnClickListener(view -> {
            String name = username.getText().toString();
            editor1.putString(getString(R.string.name), name);
            editor1.commit();
            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
            Intent i3 = new Intent(Login.this, MainMenu.class);
            startActivity(i3);
        });
    }
    private void checkSharePreferences(){
        String name =  mPrefs.getString(getString(R.string.name), "");
        username.setText(name);
    }
}