package xyz.kylekelley.votemaryland;

/**
 * Created by KyleKelley on 4/17/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    ArrayList<CalObj> eventObjectsPlaceholder = new ArrayList<CalObj>();


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
    public ArrayList<Event> getCalName(String date ) {
        ArrayList<Event> events = new ArrayList<>();
        Cursor cursor = null;
        String Query = "SELECT Name, Type, _id FROM Events WHERE DATE ='"+date+"'" ;
        cursor = database.rawQuery(Query, null);
        Event myEvent;
        String name, type;
        int id;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                name = cursor.getString(0).trim();
                type = cursor.getString(1).trim();
                id = cursor.getInt(2);
                myEvent = new Event(name, type, id);
                events.add(myEvent);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return events;
    }
    public ArrayList<Event> getCalEvent(int id) {
        ArrayList<Event> events = new ArrayList<Event>();
        Event myEvent;
        Cursor cursor = null;
        String Query = "SELECT Name, LocationTitle, Street, CityState, Zip, Type, Start_Time, End_Time, DATE, Description, _id FROM Events WHERE _id = "+Integer.toString(id);
        cursor = database.rawQuery(Query, null);
        String eventName, locationTitle, street, cityState, zip, type, startTime, endTime, date, description;
        int favorited = 0;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                eventName = cursor.getString(0).trim();
                locationTitle = cursor.getString(1).trim();
                street = cursor.getString(2).trim();
                cityState = cursor.getString(3).trim();
                zip = cursor.getString(4).trim();
                type = cursor.getString(5).trim();
                startTime = cursor.getString(6).trim();
                endTime = cursor.getString(7).trim();
                date = cursor.getString(8).trim();
                description = cursor.getString(9).trim();
                id = cursor.getInt(10);
                myEvent = new Event(eventName, locationTitle, street, cityState, zip, type, startTime, endTime, date, description, favorited, id);
                events.add(myEvent);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return events;
    }

    public void updateState(){
        database.execSQL("UPDATE State SET saved = 1");
    }

    public int checkState(){
        Cursor cursor = null;
        String Query = "SELECT saved FROM State";
        cursor = database.rawQuery(Query, null);
        int visited = 0;
        if(cursor != null && cursor.moveToFirst()){
            visited = cursor.getInt(0);
        }

        return visited;
    }

    public void addFavorite(int id){
        database.execSQL("UPDATE Events SET Favorited = 1 WHERE _id = "+Integer.toString(id));
    }

    public ArrayList<Event> getFavorites(){
        ArrayList<Event> favorites = new ArrayList<Event>();
        Cursor cursor = null;
        String Query = "SELECT _id, name FROM Events WHERE favorited = 1";
        cursor = database.rawQuery(Query, null);
        Event myEvent;
        String name;
        int id;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                id = cursor.getInt(0);
                name = cursor.getString(1);
                myEvent = new Event(name, id);
                favorites.add(myEvent);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return favorites;
    }

    public void removeFavorite(int id){
        database.execSQL("UPDATE Events SET Favorited = 0 WHERE _id = " + Integer.toString(id));
    }
}
