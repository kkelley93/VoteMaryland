package xyz.kylekelley.votemaryland;

/**
 * Created by KyleKelley on 4/17/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    ArrayList<cal_obj> eventObjectsPlaceholder = new ArrayList<cal_obj>();


    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<String> getNames() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT First, Last FROM CandidateList", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0) + " " + cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<String> getCalName(String date ) {
        ArrayList<String> name = new ArrayList<>();
        Cursor cursor = null;
        String Query = "SELECT Name, Type FROM Events WHERE DATE ='"+date+"'" ;
        cursor = database.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                name.add(cursor.getString(0).trim());
                name.add(cursor.getString(1).trim());
            } while (cursor.moveToNext());
            cursor.close();
        }
        return name;
    }
    public ArrayList<String> getCalEvent(String name ) {
        ArrayList<String> name_date_address_start_end = new ArrayList<>();
        Cursor cursor = null;
        String Query = "SELECT Name,DATE,Address,Start_Time,End_Time FROM Events WHERE Name = '"+name+"'" ;
        cursor = database.rawQuery(Query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                name_date_address_start_end.add(cursor.getString(0).trim());
                name_date_address_start_end.add(cursor.getString(1).trim());
                name_date_address_start_end.add(cursor.getString(2).trim());
                name_date_address_start_end.add(cursor.getString(3).trim());
                name_date_address_start_end.add(cursor.getString(4).trim());
            } while (cursor.moveToNext());
            cursor.close();
        }
        return name_date_address_start_end;
    }
}
