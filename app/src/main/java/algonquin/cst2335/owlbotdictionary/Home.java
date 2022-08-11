package algonquin.cst2335.owlbotdictionary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
    /**
     * This is the onCreate function of the application, on creation all variables will be initialized
     * as well as all of the onClick listeners of the entire application will be initialized
     * @param savedInstanceState the default bundle of the onCreate
     */
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

        /**
         * This is an onClick Listener for to set intent and change activity
         */
        activity_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity1 = new Intent(Home.this, Home.class);
                startActivity(goToActivity1);
            }
        });
        /**
         * This is an onClick Listener for to set intent and change activity
         */
        activity_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity2 = new Intent(Home.this, Home.class);
                startActivity(goToActivity2);
            }
        });
        /**
         * This is an onClick Listener for to set intent and change activity
         */
        activity_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity3 = new Intent(Home.this, Home.class);
                startActivity(goToActivity3);
            }
        });
        /**
         * This oncllicklistener is for the help button, it displays a snackbar at the bottom of the screen
         * to provide assistance on how to use this page of the project
         */
        help_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(help_button, getResources().getString(R.string.Enter_Menu2), Snackbar.LENGTH_LONG).show();

            }
        });
        /**
         * This onclicklistener is for the about me button, or "Information Button" this calls upon a
         * alertdialogue to display the information about the application and who created it
         */
        about_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder aboutalert = new AlertDialog.Builder(Home.this);
                aboutalert.setTitle(getResources().getString(R.string.About_Title))
                        .setMessage(getResources().getString(R.string.About_Me)).create().show();
            }


        });
        /**
         * This oncllicklistener is for the help button, it displays a toast at the bottom of the screen
         * to provide assistance on how the shared preference welcome screen works
         */
        about_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, getResources().getString(R.string.Name_Help), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * This is the onpause of the application, it stores the Shared Preference so that when the app is not in use
     * the name entered by the application will save and be displayed on resuming the application
     */
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
