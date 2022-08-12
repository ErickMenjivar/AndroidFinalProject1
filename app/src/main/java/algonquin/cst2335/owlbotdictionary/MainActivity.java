package algonquin.cst2335.owlbotdictionary;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    /**
     * This is the onCreate function of the application, on creation all variables will be initialized
     * as well as all of the onClick listeners of the entire application will be initialized
     * @param savedInstanceState the default bundle of the onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button help_button = findViewById(R.id.button);
        Button activity_button1 = findViewById(R.id.button2);
        Button activity_button2 = findViewById(R.id.button3);
        //Button activity_button3 = findViewById(R.id.button4);
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
                Intent goToActivity1 = new Intent(MainActivity.this, MainSearch.class);
                startActivity(goToActivity1);
            }
        });
        /**
         * This is an onClick Listener for to set intent and change activity
         */
        activity_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity2 = new Intent(MainActivity.this, Favorites.class);
                startActivity(goToActivity2);
            }
        });
        /**
         * This is an onClick Listener for to set intent and change activity
         */
//        activity_button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent goToActivity3 = new Intent(MainActivity.this, Home.class);
//                startActivity(goToActivity3);
//            }
//        });
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
                AlertDialog.Builder aboutalert = new AlertDialog.Builder(MainActivity.this);
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
                Toast.makeText(MainActivity.this, getResources().getString(R.string.Name_Help), Toast.LENGTH_LONG).show();
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




//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
////
////        Toast.makeText(getApplicationContext(), "welcome to the OWLBOT dictionary",
////                Toast.LENGTH_SHORT).show();
//        // Toast toast=Toast. makeText(getApplicationContext(),"welcome to the dictionary",Toast. LENGTH_LONG);
////        Snackbar snackbar = Snackbar
////                .make(, "www.journaldev.com", Snackbar.LENGTH_LONG);
////        snackbar.show();
//
//
//        ed = findViewById(R.id.search_text);
//        search = findViewById(R.id.search_button);
//        view = findViewById(R.id.recycle);
//        editSearch = ed.getText().toString();
//
//
//        search.setOnClickListener((click) -> {
////            runForcast(editSearch);
//
////            Executor newThread = Executors.newSingleThreadExecutor();
////            newThread.execute(() -> {
////                try {
////
////
//                    stringURL = "https://owlbot.info/?q="
//                            + URLEncoder.encode(editSearch, "UTF-8")
//                            + "&appid=16ee0955a4a3550d113eb768cbcb1eaba7934384";
////
////
////                    URL url = new URL(stringURL);
////                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
////                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
////
////                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
////                    factory.setNamespaceAware(false);
////                    XmlPullParser xpp = factory.newPullParser();
////                    xpp.setInput(in, "UTF-8");
//
//
////                    while (xpp.next() != XmlPullParser.END_DOCUMENT) {
////
////                        switch (xpp.getEventType()) {
////                            case XmlPullParser.START_TAG:
////                                if (xpp.getName().equals("temperature")) {
////                                    current = xpp.getAttributeValue(null, "value");  //this gets the current temperature
////
////                                    min = xpp.getAttributeValue(null, "min"); //this gets the min temperature
////
////                                    max = xpp.getAttributeValue(null, "max"); //this gets the max temperature
////                                } else if (xpp.getName().equals("weather")) {
////                                    description = xpp.getAttributeValue(null, "value");
////
////                                    iconName = xpp.getAttributeValue(null, "icon");
////                                } else if (xpp.getName().equals("humidity")) {
////                                    humidity = xpp.getAttributeValue(null, "value");
////                                }
////                                break;
////                            case XmlPullParser.END_TAG:
////                                break;
////                            case XmlPullParser.TEXT:
////                                break;
////                        }
////
////                    }
//            ///////
//
//
////        } catch (IOException | XmlPullParserException ioe) {
////                    Log.e("Connection Error", ioe.getMessage());
////                }
////
////    }}}
//
//
//            Executor newThread = Executors.newSingleThreadExecutor();
//            newThread.execute(() -> {
//                try {
//                    stringURL = "https://owlbot.info/?q="
//                            + URLEncoder.encode(editSearch, "UTF-8")
//                            + "&appid=16ee0955a4a3550d113eb768cbcb1eaba7934384";
//
//
//                    URL url = new URL(stringURL);
//                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//
//                    String text = (new BufferedReader(
//                            new InputStreamReader(in, StandardCharsets.UTF_8)))
//                            .lines()
//                            .collect(Collectors.joining("\n"));
//
//
//                    JSONObject theDocument = new JSONObject(text); //this converts the String to JSON Object.
//                    //JSONArray theArray = new JSONArray( text );
//
//
//////
//                    JSONObject pobject = theDocument.getJSONObject("pronunciation");
//                    String pronunciation = pobject.getString("pronunciation");
//                    JSONArray definitions = theDocument.getJSONArray("definitions");
//                    JSONObject position0 = definitions.getJSONObject(0);
//                    String definition = position0.getString("definition");
//
//                    runOnUiThread(() -> {
//
//                        TextView pronun = findViewById(R.id.rec);
//                        pronun.setText("The pronunciation is " + pronunciation);
//
//                        TextView temp = findViewById(R.id.textView4);
//                        temp.setText("The pronunciation is " + definition);
//                    });
//
////                JSONObject mainObject = theDocument.getJSONObject("main");
////                double current = mainObject.getDouble("temp");
////                double min = mainObject.getDouble("temp_min");
////                double max = mainObject.getDouble("temp_max");
////                int humidity = mainObject.getInt("humidity");
//
//
//                } catch (IOException | JSONException ioe) {
//                    Log.e("Connection Error", ioe.getMessage());
//                }
//            });
//        });
//    }


