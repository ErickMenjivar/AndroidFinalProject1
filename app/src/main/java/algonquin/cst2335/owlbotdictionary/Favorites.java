package algonquin.cst2335.owlbotdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Favorites extends AppCompatActivity implements SelectListener {

    RecyclerView recViewDb;

    ArrayList<String> wordListDbWord;
    ArrayList<String> wordListDbPro;
    ArrayList<String> wordListDbDef;
    ArrayList<String> wordListDbWordTest;
    ArrayList<String> wordListDbWordTest2;
    ArrayList<String> wordListDbWordTest3;
    DataBase db;
    DbAdapterClass dbAdapterClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        db =new DataBase(Favorites.this);
        wordListDbWord = new ArrayList<>();
        wordListDbPro = new ArrayList<>();
        wordListDbDef = new ArrayList<>();
        wordListDbWordTest = new ArrayList<>();
        storeDataArrays();
        wordListDbWordTest = wordListDbWord;
        wordListDbWordTest2 = wordListDbPro;
        wordListDbWordTest3= wordListDbDef;

        recViewDb = findViewById(R.id.rv_word_fav);
        dbAdapterClass = new DbAdapterClass(Favorites.this, wordListDbWordTest,wordListDbDef,wordListDbPro);
        recViewDb.setAdapter(dbAdapterClass);
        recViewDb.setLayoutManager(new LinearLayoutManager(this));




//        db.fetch();
//        setContentView(R.layout.activity_favorites);
//        recViewDb = findViewById(R.id.rv_word_fav);
//        wordListDbWord = db.fetch()[0];
//        wordListDbPro = db.fetch()[2];
//        wordListDbDef = db.fetch()[1];
//
//        List<WordModelDbClass> wordListDbWord = new ArrayList<WordModelDbClass>();
//
//        putDataIntoRecyclerView(wordListDb);
    }

//    private void putDataIntoRecyclerView(List<WordModelDbClass> wordListDbWord, List<WordModelDbClass> wordListDbPro,List<WordModelDbClass> wordListDbDef){
//        DbAdapterClass adapter = new DbAdapterClass(this, wordListDbWord,wordListDbPro,wordListDbDef, this);
//        recViewDb.setLayoutManager(new LinearLayoutManager(this));
//        recViewDb.setAdapter(adapter);
//    }

    void storeDataArrays(){
        Cursor cursor = db.readAll();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                wordListDbWord.add(cursor.getString(1));
                wordListDbDef.add(cursor.getString(2));
                wordListDbPro.add(cursor.getString(3));
            }
        }

    }


    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void saveVarValues(String val1, String val2, String val3) {

    }
}