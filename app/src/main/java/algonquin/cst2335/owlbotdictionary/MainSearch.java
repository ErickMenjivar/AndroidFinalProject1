package algonquin.cst2335.owlbotdictionary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainSearch extends AppCompatActivity implements SelectListener {
    private static final String TAG = "MAIN_ACTIVITY";
    public static final String WORD = "word";
    public static final String DEFINE = "define";
    public static final String PRONUNCIATION = "pronunciation";

    EditText edit;
    Button search;
    Button favs;
    private String stringURL;
    RecyclerView recView;
    String editSearch;
    TextView showWord;
    TextView def;
    TextView pro;
    List<WordModelClass> wordList;
    DataBase dataBase;
    String wordTopass;
    String pronunciationToPass;
    String checkVal1 = null;
    String checkVal2 = null;
    String checkVal3 = null;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        Log.d(TAG, " onCreate");
        search = findViewById(R.id.search_button);
        recView = findViewById(R.id.rv_word);
        dataBase =new DataBase(MainSearch.this);





        //add the <>

        edit = findViewById(R.id.search_text);
        edit = findViewById(R.id.search_text);
        SharedPreferences pref = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        String hi = pref.getString("type", "no");
        edit.setText(hi);

//        editor.putString("key_name",get); // Storing string
//        editor.apply();
//        String key = pref.getString("key_name", null); // getting String


        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);




        search.setOnClickListener(click -> {
            editSearch = edit.getText().toString();
            myToolbar.getMenu().add(0, 1, 0, editSearch)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
            runSearch(editSearch);
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.clear:
                pro.setVisibility(View.INVISIBLE);
                def.setVisibility(View.INVISIBLE);
                showWord.setVisibility(View.INVISIBLE);
                edit.setText("");
                break;
            case R.id.help:
                AlertDialog dialog = new AlertDialog.Builder(MainSearch.this)
                        .setTitle("Help")
                        .setMessage("To search for definition, type your word in the search box " +
                                "below and press the search button. Click the save button if you want to " +
                                "save the definition to the word " + editSearch)
                        .show();

        }
        return super.onOptionsItemSelected(item);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void runSearch(String search) {
        wordList = new ArrayList<>();
        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Processing...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();//displays the progress bar




        Executor newThread = Executors.newSingleThreadExecutor();
        newThread.execute(() -> {
            try {
                stringURL = "https://owlbot.info/api/v4/dictionary/"
                        + URLEncoder.encode(editSearch, "UTF-8");
                URL url = new URL(stringURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Authorization", "Token 83f1e73ee0b5daed582b133867bb10f0b32db6a4");
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String text = (new BufferedReader(
                        new InputStreamReader(in, StandardCharsets.UTF_8)))
                        .lines()
                        .collect(Collectors.joining("\n"));
                Log.d(" connected", text);
                JSONObject theDocument = new JSONObject(text);
                String word = theDocument.getString("word");
                String pronunciation = theDocument.getString("pronunciation");
                //have some problems here
//                    JSONArray pobject = theDocument.getJSONArray("pronunciation");
//                    String pronunciation = pobject.getString("pronunciation");
                JSONArray definitions = theDocument.getJSONArray("definitions");


                for (int i =0; i< definitions.length(); i++){
                    JSONObject jsonObject1 = definitions.getJSONObject(i);
                    WordModelClass model =  new WordModelClass();
                    String definition = jsonObject1.getString("definition");
                    model.setDefinition(definition);
                    wordList.add(model);
                }


                JSONObject position0 = definitions.getJSONObject(0);
                String define = position0.getString("definition");


//                    JSONObject mainObject = theDocument.getJSONObject("pronunciation");
//                    String value = mainObject.getString("pronunciation");
                runOnUiThread(() -> {

                    putDataIntoRecyclerView(wordList);

                    pro = findViewById(R.id.pronunciation);
                    pro.setText("Pronunciation: " + pronunciation);
                    pro.setVisibility(View.VISIBLE);

                    //def = findViewById(R.id.definition);
                    //def.setText("Definition: " + define);
                    //def.setVisibility(View.VISIBLE);
                    wordTopass = word;
                    pronunciationToPass = pronunciation;
                    showWord = findViewById(R.id.word);
                    showWord.setText("Word: " + word);
                    showWord.setVisibility(View.VISIBLE);
                    progressBar.hide();
//                        dialog.hide();

                    //boolean checkInsert = dataBase.insert(word, define, pronunciation);
//                    if(checkInsert == true){
//                        Toast.makeText(MainActivity.this, "Definition inserted into database", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(MainActivity.this, "Definition not inserted into database", Toast.LENGTH_SHORT).show();
//                    }

                    Bundle dataToPass = new Bundle();
                    dataToPass.putString(WORD, word);
                    dataToPass.putString(DEFINE, define);
                    dataToPass.putString(PRONUNCIATION, pronunciation);



                    //Clicking on the word will take you to a fragment containing the definition.
                    showWord.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FragmentManager fm = getSupportFragmentManager();

                            //fm.beginTransaction().replace(R.id.frame, DetailFragment.class, null).commit();
                        }
                    });


                });
            } catch (IOException | JSONException ioe) {
                Log.e("Connection Error", ioe.getMessage());
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, " onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, " onPause");
        String get = edit.getText().toString();

        //Save the shared preference while the activity is paused
        //Search term is saved and will be called back in the onCreate method.
        SharedPreferences myPref = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("type", get);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, " onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, " onStop");

        String get = edit.getText().toString();

        //Save the shared preference while the activity is paused
        //Search term is saved and will be called back in the onCreate method.
        SharedPreferences myPref = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("type", get);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " onDestroy");
    }

    private void putDataIntoRecyclerView(List<WordModelClass> wordList){
        AdapterClass adapter = new AdapterClass(this, wordList, this);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {
        replaceFragment(new FragmentDefinition(), position, wordList, wordTopass);
//        wordList.get(position);
//        Intent i2  = new Intent(MainActivity.this, BlanckActivity.class);
//        startActivity(i2);
    }

    @Override
    public void saveVarValues(String val1, String val2, String val3) {
        String checkVal1 = val1;
        String checkVal2 = val2;
        String checkVal3 = val3;
        if (checkVal1!=null){
            dataBase.insert(checkVal1, checkVal2, checkVal3);
        }

    }

    @Override
    public void onBackPressed() {
        closeFragment(new FragmentDefinition());
    }

    private void closeFragment(FragmentDefinition fragmentDefinition) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentDefinition fragment = (FragmentDefinition) fragmentManager.findFragmentById(R.id.fragment_frame);
        if (fragment != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }else{
            super.onBackPressed();
        }

    }

    private void replaceFragment(FragmentDefinition fragmentDefinition, int position, List<WordModelClass> wordList, String word) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle data = new Bundle();
        data.putString("Definition", (wordList.get(position)).getDefinition());
        data.putString("Word", word);
        data.putString("Pronunciation", pronunciationToPass);
        fragmentDefinition.setArguments(data);
        fragmentTransaction.replace(R.id.fragment_frame, fragmentDefinition);
        fragmentTransaction.commit();
    }
}