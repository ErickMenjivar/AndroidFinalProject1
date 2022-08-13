package algonquin.cst2335.owlbotdictionary;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for the data base.
 */
public class DataBase extends SQLiteOpenHelper {

    public static final String OWLBOT_TABLE = "OWLBOT_TABLE";
    public static final String OWLBOT_WORD = "OWLBOT_WORD";
    public static final String OWLBOT_DEFINITION = "OWLBOT_DEFINITION";
    public static final String OWLBOT_PRONUNCIATION = "OWLBOT_PRONUNCIATION";
    public static final String OWLBOT_ID = "_id";
    private static final String TAG = "MAIN_ACTIVITY";

    /**
     * The overloaded constructor for the DataBase class.
     * @param context
     */
    public DataBase(Context context) {
        super(context, "OwlBotDictionary.db", null, 3);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    /**
     * Method is called to create a database if it doesn't exist.
     * @param db The database.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + OWLBOT_TABLE + " ( " + OWLBOT_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + OWLBOT_WORD + " TEXT, "
                + OWLBOT_DEFINITION + " TEXT, "
                + OWLBOT_PRONUNCIATION + " TEXT); ";
        db.execSQL(CREATE_TABLE);

    }

    /**
     * Method is called if newer database is constructed.
     * @param db The database.
     * @param oldVersion The old version.
     * @param newVersion The new version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + OWLBOT_TABLE);

    }

    /**
     * A method to insert a word and its details into the database.
     * @param word The word.
     * @param def The definition.
     * @param pro The pronunciation.
     * @return A boolean where true is a successful insertion.
     */
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

    /**
     * A method to fetch the list of data from the database.
     * @return The list array from the database.
     */
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

    /**
     * A method to delete a word and its details from the database.
     * @param word The word.
     * @param def The definition.
     * @param pro The pronunciation.
     * @return A boolean where true is a successful deletion.
     */
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
