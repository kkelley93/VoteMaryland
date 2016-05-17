package xyz.kylekelley.votemaryland;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EventDetailActivity extends AppCompatActivity {
   public String eventName = "";
    public String eventAddress = "";
    public String eventStartTime = "";
    public String eventEndTime = "";
    public String eventDate = "";
    public String eventDescription = "";
    public int id;
    DateFormat dateFormat ;
    Date temp = null ;
    ArrayList<CalObj> eventObjectsPlaceholder;
    CalAdapter cAdapter;
    ArrayList<String> one = null;
    ListView listView;
    Date current_date  = new Date();
    DatabaseAccess databaseAccess = null;
    public FloatingActionButton fab;
    private Drawer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        Intent receiveIntent = getIntent();
//        int QNAME = receiveIntent.getIntExtra("id", 0);
        int QNAME = EventCalendarActivity.a;
        eventObjectsPlaceholder = new ArrayList<CalObj>();
        cAdapter = new CalAdapter(this, R.layout.cal_detail_list_row, eventObjectsPlaceholder);
        listView = (ListView) findViewById(R.id.map_event);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        listView.setAdapter(cAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CalObj current = cAdapter.getItem(position);
                    if(current.get_image() == "car".trim()) {
                        Intent myIntent = new Intent(EventDetailActivity.this, MainActivity.class);
                        EventDetailActivity.this.startActivity(myIntent);
                    }
            }
        });
        ArrayList<Event> events = null;

        events = databaseAccess.getCalEvent((QNAME));
        Event myEvent = events.get(0);
            if (myEvent != null) {
                //name_date_address_start_end

                eventName = myEvent.name;
                eventDate = myEvent.date;
                eventAddress = myEvent.street + "\n" + myEvent.cityState + " " + myEvent.zip;
                eventStartTime = myEvent.startTime;
                eventEndTime = myEvent.endTime;
                eventDescription = myEvent.description;
                id = myEvent.id;
            }
            cAdapter.add(new CalObj("dir",eventAddress,myEvent.id));

        cAdapter.add(new CalObj("time",eventDate + "\n" + eventStartTime + "-" +
                eventEndTime, myEvent.id));
        cAdapter.add(new CalObj("description", eventDescription, myEvent.id));
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(eventName);
        fillFab();
        loadBackdrop();
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(eventName);
//        loadBackdrop();

    }

   private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(R.drawable.event).centerCrop().into(imageView);
    }
//    public void onClickToFav(View v){
//
//            Intent myIntent = new Intent(EventDetailActivity.this, FavoritesActivity.class);
//            startActivity(myIntent);
//        setContentView(R.layout.activity_favorites);
//
//    }

    private void fillFab() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        fab.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_favorite).actionBar().color(Color.WHITE));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
//        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public void addToFavorites(View v){

        int favId = id;
        DatabaseAccess databaseAccess;
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        databaseAccess.addFavorite(id);
        databaseAccess.close();

        Snackbar.make(v, eventName + " has been added to your favorites", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        /*create notifications*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_event_available_black_24dp);
        builder.setContentTitle("Favorite event coming up!");
        builder.setContentText("Reminder: "+eventName + " is today!");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1111, builder.build());
    }




}

