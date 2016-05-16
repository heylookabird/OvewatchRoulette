package roulette.overwatchroulette.favorites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Harjit on 5/15/2016.
 */
public class FavoritesDB {

    private static final String TAG = "DBAdapterIngredients";

    public static final String KEY_ROWID = "id";
    public static final int COL_ROWID = 0;

    public static final String KEY_MAP = "map_name";//can be a game mode or map name
    public static final String KEY_TEAM = "team_name";
    public static final String KEY_DESCRIPTION = "strat_description";
    public static final String KEY_TITLE = "strat_title";

    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_MAP, KEY_TEAM, KEY_TITLE, KEY_DESCRIPTION};

    // DB info: it's name, and the table we are using (just one).
    public static final String DATABASE_NAME = "OverWatch";
    public static final String DATABASE_TABLE = "Favorites";
    // Track DB version if a new version of your app changes the format.
    public static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE_SQL =
            "create table " + DATABASE_TABLE
                    + " (" + KEY_ROWID + " integer primary key, "
                    + KEY_MAP + " text, "
                    + KEY_TEAM + " text, "
                    + KEY_TITLE + " text,"
                    + KEY_DESCRIPTION + " text"
                    + ");";

    // Context of application who uses us.
    private final Context context;

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    public FavoritesDB(Context ctx) {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }
    /**
     *
     * @return FavoritesDB opened for writing
     */
    public FavoritesDB open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    /**
     * Close current open database
     */
    public void close() {
        myDBHelper.close();
    }


    public long insertRow(int id, String map, String team, String title, String description) {
        ContentValues initialValues = new ContentValues();
        //map = "'"+map+"'";
        //team = "'"+team+"'";
        //description="'"+description+"'";
        initialValues.put(KEY_ROWID, id);
        initialValues.put(KEY_MAP, map);
        initialValues.put(KEY_TEAM, team);
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_DESCRIPTION, description);
        // Insert it into the database.
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     *
     * @param rowId RowID of the Ingredient to delete
     * @return True if the deletion was successful
     */
    public boolean deleteRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        return db.delete(DATABASE_TABLE, where, null) != 0;
    }

    /**
     *
     * @return Cursor object placed at the first row in database
     */
    public Cursor getAllRows() {
        String where = null;
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getStrats(String map, String team){
        //String where = KEY_MAP+"="+map+" AND "+ KEY_TEAM +"="+team;
        String where =KEY_MAP +"=? AND ("+ KEY_TEAM +"=? OR " + KEY_TEAM+"=?);";
        //Cursor c = db.rawQuery("SELECT * from "+DATABASE_TABLE +" WHERE " + KEY_MAP +" = '"+map+"' AND (" + KEY_TEAM +" = '"+team+"' OR " + KEY_TEAM+" = 'Both')",null);
        Cursor c = db.query(DATABASE_TABLE, ALL_KEYS, where, new String[]{map, team, "Both"},null, null, null,null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     *
     * @param rowId RowID of the desired location in database
     * @return Cursor positioned at the location of desired Ingredient
     */
    public Cursor getRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Private class from Marc
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

            // Recreate new database:
            onCreate(_db);
        }
    }
}
