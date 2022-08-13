package algonquin.cst2335.owlbotdictionary;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class for the favourite words the user will save.
 */
public class Favorites extends AppCompatActivity implements SelectListener {

    RecyclerView recViewDb;
    List<WordModelClass> wordListDb;
    List<WordModelDbClass> wordListDbWord;
    List<WordModelDbClass> wordListDbPro;
    List<WordModelDbClass> wordListDbDef;
    DataBase db;

    /**
     * The onCreate function.
     * @param savedInstanceState The bundled saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db =new DataBase(Favorites.this);
        db.fetch();
        setContentView(R.layout.activity_favorites);
        recViewDb = findViewById(R.id.rv_word_fav);
        wordListDb = db.fetch()[0];
        wordListDbWord = db.fetch()[0];
        wordListDbPro = db.fetch()[2];
        wordListDbDef = db.fetch()[1];

        List<WordModelDbClass> wordListDbWord = new ArrayList<WordModelDbClass>();

        putDataIntoRecyclerView(wordListDb);
    }

//    private void putDataIntoRecyclerView(List<WordModelDbClass> wordListDbWord, List<WordModelDbClass> wordListDbPro,List<WordModelDbClass> wordListDbDef){
//        DbAdapterClass adapter = new DbAdapterClass(this, wordListDbWord,wordListDbPro,wordListDbDef, this);
//        recViewDb.setLayoutManager(new LinearLayoutManager(this));
//        recViewDb.setAdapter(adapter);
//    }

    /**
     * A method to put data into a recycler view.
     * @param wordList The word list data to put into a recycler view.
     */
    private void putDataIntoRecyclerView(List<WordModelClass> wordList){
        AdapterClass adapter = new AdapterClass(this, wordList, this);
        recViewDb.setLayoutManager(new LinearLayoutManager(this));
        recViewDb.setAdapter(adapter);
    }


    /**
     * A method to call when an item is clicked.
     * @param position The position.
     */
    @Override
    public void onItemClicked(int position) {

    }

    /**
     * A method to save the variable values.
     * @param val1 The first value.
     * @param val2 The second value.
     * @param val3 The third value.
     */
    @Override
    public void saveVarValues(String val1, String val2, String val3) {

    }
}