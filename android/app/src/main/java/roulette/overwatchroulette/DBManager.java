package roulette.overwatchroulette;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import roulette.overwatchroulette.maps.MapInformation;

/**
 * Created by Harjit on 5/16/2016.
 */
public class DBManager {
    private static final String TAG = "DBAdapterStratRoulette";

    public static final String KEY_ROWID = "id";
    public static final int COL_ROWID = 0;

    public static final String KEY_MAP = "map_name";//can be a game mode or map name
    public static final String KEY_TEAM = "team_name";
    public static final String KEY_DESCRIPTION = "strat_description";
    public static final String KEY_TITLE = "strat_title";

    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_MAP, KEY_TEAM, KEY_TITLE, KEY_DESCRIPTION};

    // DB info: it's name, and the table we are using (just one).
    public static final String DATABASE_NAME = "OverWatch";
    public static final String DATABASE_FAVORITES_TABLE = "Favorites";
    public static final String DATABASE_STRATS_TABLE = "Strats";
    // Track DB version if a new version of your app changes the format.
    public static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE_FAVORITES_SQL =
            "create table " + DATABASE_FAVORITES_TABLE
                    + " (" + KEY_ROWID + " integer primary key, "
                    + KEY_MAP + " text, "
                    + KEY_TEAM + " text, "
                    + KEY_TITLE + " text,"
                    + KEY_DESCRIPTION + " text"
                    + ");";

    private static final String DATABASE_CREATE_STRATS_SQL =
            "create table " + DATABASE_STRATS_TABLE
                    + " (" + KEY_ROWID + " integer primary key, "
                    + KEY_MAP + " text, "
                    + KEY_TEAM + " text, "
                    + KEY_TITLE + " text,"
                    + KEY_DESCRIPTION + " text"
                    + ");";

    // Context of application who uses us.

    private static DatabaseHelper myDBHelper;
    private static SQLiteDatabase db;

    public static void init(Context context){
        myDBHelper = new DatabaseHelper(context);
        open();
    }

    public static void open(){
        db = myDBHelper.getWritableDatabase();

        Cursor c = getAllFavoritesRows();
        if(c.getCount() == 0){
            //fillFavoritesWithDummys();
            fillStratWithDummys();
        }
    }

    public static Cursor getFavoriteStrats(String map, String team){
        String type = MapInformation.gameModes.get(map);
        String where ="("+KEY_MAP +"=? OR "+KEY_MAP+ "=? OR " + KEY_MAP + "=?) AND ("+ KEY_TEAM +"=? OR " + KEY_TEAM+"=?);";
        //Cursor c = db.rawQuery("SELECT * from "+DATABASE_TABLE +" WHERE " + KEY_MAP +" = '"+map+"' AND (" + KEY_TEAM +" = '"+team+"' OR " + KEY_TEAM+" = 'Both')",null);
        Cursor c = db.query(DATABASE_FAVORITES_TABLE, ALL_KEYS, where, new String[]{map, type, "All", team, "Both"}, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public static long removeFromFavorites(String title){
        return db.delete(DATABASE_FAVORITES_TABLE, KEY_TITLE +"=?", new String[]{title});
    }

    public static long insertStratRow(int id, String map, String team, String title, String description){
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_ROWID, id);
        initialValues.put(KEY_MAP, map);
        initialValues.put(KEY_TEAM, team);
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_DESCRIPTION, description);
        // Insert it into the database.
        return db.insert(DATABASE_STRATS_TABLE, null, initialValues);
    }

    public static String getFavoriteStratDescription(String title){
        String where = KEY_TITLE +"=?";

        Cursor c = db.query(DATABASE_FAVORITES_TABLE, ALL_KEYS, where, new String[]{title},null, null, null,null);
        c.moveToFirst();
        return c.getString(c.getColumnIndex(KEY_DESCRIPTION));
    }

    public static Cursor getAllFavoritesRows() {
        String where = null;
        Cursor c = 	db.query(true, DATABASE_FAVORITES_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public static Cursor getAllStratsRows() {
        String where = null;
        Cursor c = 	db.query(true, DATABASE_STRATS_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public static Cursor getRandomStrat(String map, String team){
        String type = MapInformation.gameModes.get(map);
        String where ="("+KEY_MAP +"=? OR "+KEY_MAP+ "=? OR " + KEY_MAP + "=?) AND ("+ KEY_TEAM +"=? OR " + KEY_TEAM+"=?) ORDER BY RANDOM() LIMIT 1;";
        Cursor c = db.query(DATABASE_STRATS_TABLE, ALL_KEYS, where, new String[]{map, type, "All", team, "Both"},null, null, null,null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public static long insertFavoriteRow(int id, String map, String team, String title, String description){
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_ROWID, id);
        initialValues.put(KEY_MAP, map);
        initialValues.put(KEY_TEAM, team);
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_DESCRIPTION, description);
        // Insert it into the database.
        return db.insert(DATABASE_FAVORITES_TABLE, null, initialValues);
    }



    private static void fillStratWithDummys(){
        insertStratRow(0, "All", "Both", "FLASH EVERYTHING", "6 Mcrees " +
                "\n Throw Flashes around all corners." +
                "\n Stagger the throws");
        insertStratRow(1, "All", "Both", "The Flying Snake", "1 Pharah, 5 Mercy's" +
                "\n Start Flying");
        insertStratRow(2, "All", "Defending", "FORM THE TURTLE", "6 Reinhardts" +
                "\n Stack Shields into a shell");
        insertStratRow(3, "All", "Both", "RobinHood", "6 Hanzos" +
                "\n Embrace your Inner Archer" +
                "\n Release the Dragons at the same time");
        insertStratRow(4, "All", "Defending", "Great Wall of Mei", "6 Mei's" +
                "\n Attempt to prevent any attacker from leaving their base" +
                "\n 2 people per exit to their base, stagger ice walls to block them in");
        insertStratRow(5, "All", "Both", "SURPESSING FIREEEE", "3 Bastions, 3 Torbjorns" +
                "\n More bullets, MORE BULLETS");
        insertStratRow(6, "All", "Both", "CMIYC", "6 Tracers" +
                "\n Catch Me If You Can!");
        insertStratRow(7, "All", "Both", "Where was that??", "Wear your headset backwards");
        insertStratRow(8, "All", "Both", "MLG 420% Skillshots Only!", "Jumpshots ONLY!" +
                "\n Best with 6 Mcree's");

    }
    private static void fillFavoritesWithDummys(){
        insertFavoriteRow(0, "All", "Both", "FLASH EVERYTHING", "6 Mcrees " +
                "\n Throw Flashes around all corners." +
                "\n Stagger the throws");
        insertFavoriteRow(1, "All", "Both", "The Flying Snake", "1 Pharah, 5 Mercy's" +
                "\n Start Flying");
        insertFavoriteRow(2, "All", "Defending", "FORM THE TURTLE", "6 Reinhardts" +
                "\n Stack Shields into a shell");
        insertFavoriteRow(3, "All", "Both", "RobinHood", "6 Hanzos" +
                "\n Embrace your Inner Archer" +
                "\n Release the Dragons at the same time");
        insertFavoriteRow(4, "All", "Defending", "Great Wall of Mei", "6 Mei's" +
                "\n Attempt to prevent any attacker from leaving their base" +
                "\n 2 people per exit to their base, stagger ice walls to block them in");
        insertFavoriteRow(5, "All", "Both", "SURPESSING FIREEEE", "3 Bastions, 3 Torbjorns" +
                "\n More bullets, MORE BULLETS");
        insertFavoriteRow(6, "All", "Both", "CMIYC", "6 Tracers" +
                "\n Catch Me If You Can!");
        insertFavoriteRow(7, "All", "Both", "Where was that??", "Wear your headset backwards");
        insertFavoriteRow(8, "All", "Both", "MLG 420% Skillshots Only!", "Jumpshots ONLY!" +
                "\n Best with 6 Mcree's");

    }

    // Private class from Marc
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_FAVORITES_SQL);
            _db.execSQL(DATABASE_CREATE_STRATS_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_FAVORITES_TABLE);
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_STRATS_TABLE);

            // Recreate new database:
            onCreate(_db);
        }
    }
}
