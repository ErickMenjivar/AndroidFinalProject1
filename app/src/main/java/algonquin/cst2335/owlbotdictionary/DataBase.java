package algonquin.cst2335.owlbotdictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    public static final String OWLBOT_TABLE = "OWLBOT_TABLE";
    public static final String OWLBOT_WORD = "OWLBOT_WORD";
    public static final String OWLBOT_DEFINITION = "OWLBOT_DEFINITION";
    public static final String OWLBOT_PRONUNCIATION = "OWLBOT_PRONUNCIATION";
    public static final String OWLBOT_ID = "_id";
    private static final String TAG = "MAIN_ACTIVITY";

    public DataBase(Context context) {
        super(context, "OwlBotDictionary.db", null, 3);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //Method is called to create a database if it doesn't exist.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + OWLBOT_TABLE + " ( " + OWLBOT_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + OWLBOT_WORD + " TEXT, "
                + OWLBOT_DEFINITION + " TEXT, "
                + OWLBOT_PRONUNCIATION + " TEXT); ";
        db.execSQL(CREATE_TABLE);

    }

    //Method is called if newer database is constructed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + OWLBOT_TABLE);

    }

    public boolean insert(String word, String def, String pro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBase.OWLBOT_WORD, word);
        cv.put(DataBase.OWLBOT_DEFINITION, def);
        cv.put(DataBase.OWLBOT_PRONUNCIATION, pro);
        long result = db.insert(OWLBOT_TABLE, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List[] fetch() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from OWLBOT_TABLE", null, null);
        List[] ans = new List[3];
        List<String> mWords = new ArrayList<>();
        List<String> mPro = new ArrayList<>();
        List<String> mDef = new ArrayList<>();
        while (cursor.moveToNext()) {
            mWords.add(cursor.getString(1));
            mPro.add(cursor.getString(2));
            mDef.add(cursor.getString(3));
            Log.d(TAG, " Fecth all the data: " + cursor.getString(1) + " and message is: " + cursor.getString(1));
        }
        cursor.close();
        ans[0]=mWords;
        ans[1]=mPro;
        ans[2]=mDef;
        return ans;
    }

    public boolean delete(String word, String def, String pro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBase.OWLBOT_WORD, word);
        cv.put(DataBase.OWLBOT_DEFINITION, def);
        cv.put(DataBase.OWLBOT_PRONUNCIATION, pro);
        long result = db.delete(OWLBOT_TABLE, DataBase.OWLBOT_ID + "= ?", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

}
