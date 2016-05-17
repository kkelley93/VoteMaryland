package xyz.kylekelley.votemaryland;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import xyz.kylekelley.votemaryland.adapter.CandidatesAdapter;
import xyz.kylekelley.votemaryland.models.Candidate;


public class CandidateDetailActivity extends AppCompatActivity {
    public String eventName = "";
    public String eventAddress = "";
    public String eventStartTime = "";
    public String eventEndTime = "";
    public String eventDate = "";
    public String eventDescription = "";
    DateFormat dateFormat ;
    Date temp = null ;
    ArrayList<Candidate> eventObjectsPlaceholder;
    CandidatesAdapter cAdapter;
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



        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
//        int QNAME = EventCalendarActivity.a;
        eventObjectsPlaceholder = new ArrayList<Candidate>();
        cAdapter = new CandidatesAdapter(this, R.layout.content_candidate_detail_list_row, eventObjectsPlaceholder);
        listView = (ListView) findViewById(R.id.map_event);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        listView.setAdapter(cAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Candidate current = cAdapter.getItem(position);
//                if(current.get_image() == "car".trim()) {
//                    Intent myIntent = new Intent(CandidateDetailActivity.this, MainActivity.class);
//                    CandidateDetailActivity.this.startActivity(myIntent);
//                }
            }
        });
        ArrayList<Event> events = null;

//        events = databaseAccess.getCalEvent((QNAME));
//        Event myEvent = events.get(0);
//        if (myEvent != null) {
//            //name_date_address_start_end
//
//            eventName = myEvent.name;
//            eventDate = myEvent.date;
//            eventAddress = myEvent.street + "\n" + myEvent.cityState + " " + myEvent.zip;
//            eventStartTime = myEvent.startTime;
//            eventEndTime = myEvent.endTime;
//            eventDescription = myEvent.description;
//        }

        cAdapter.add(CandidateListActivity.cand);

//        cAdapter.add(new Candidate("dir",eventAddress,myEvent.id));
//
//        cAdapter.add(new Candidate("time",eventDate + "\n" + eventStartTime + "-" +
//                eventEndTime, myEvent.id));
//        cAdapter.add(new Candidate("description", eventDescription, myEvent.id));

//        cAdapter.add(new Candidate());

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
        Glide.with(this).load(R.drawable.candidate).centerCrop().into(imageView);
    }
//    public void onClickToFav(View v){
//
//            Intent myIntent = new Intent(CandidateDetailActivity.this, FavoritesActivity.class);
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




}



//package xyz.kylekelley.votemaryland;
//
//import android.support.design.widget.CollapsingToolbarLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.ImageView;
//import com.bumptech.glide.Glide;
//
//public class CandidateDetailActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_candidate_detail);
//        /*
//        * Sets up a Collapsing Toolbar that inserts an image in the flexible space
//        * of the expanded toolbar.
//        */
//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("Test");
//        collapsingToolbar.setCollapsedTitleTextColor(0xFFFFFFFF);
//        collapsingToolbar.setExpandedTitleColor(0xFFFFFFFF);
//
//        loadBackdrop();
//    }
//    /*
//    * Uses the Glide library to load and cache images with a focus on smooth scrolling
//    */
//    private void loadBackdrop() {
//        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
//        Glide.with(this).load(R.mipmap.berniesanders).centerCrop().into(imageView);
//    }
//}
