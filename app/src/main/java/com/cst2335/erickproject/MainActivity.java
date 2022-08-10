package com.cst2335.erickproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    //https://api.edamam.com/api/food-database/parser?app_id=05584855&app_key=3482f48b78ba3c7ca6f09daeb1d14c81&ingr=apple
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button2);
        btn1.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, Login.class);
            startActivity(i);
        });


    }


}