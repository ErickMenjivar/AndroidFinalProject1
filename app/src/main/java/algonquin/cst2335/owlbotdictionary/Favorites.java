package algonquin.cst2335.owlbotdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Favorites extends AppCompatActivity implements SelectListener {

    RecyclerView recViewDb;
    List<WordModelClass> wordListDb;
    List<WordModelDbClass> wordListDbWord;
    List<WordModelDbClass> wordListDbPro;
    List<WordModelDbClass> wordListDbDef;
    DataBase db;

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

    private void putDataIntoRecyclerView(List<WordModelClass> wordList){
        AdapterClass adapter = new AdapterClass(this, wordList, this);
        recViewDb.setLayoutManager(new LinearLayoutManager(this));
        recViewDb.setAdapter(adapter);
    }


    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void saveVarValues(String val1, String val2, String val3) {

    }
}