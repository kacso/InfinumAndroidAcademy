package co.infinum.academy.danijel_sokac.boatit.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Danijel on 20.7.2015..
 */
public class BoatitDatabaseHelper extends SQLiteOpenHelper {
    public BoatitDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, BoatitDatabase.NAME, null, BoatitDatabase.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Boatit (token TEXT PRIMARY KEY, boats TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
