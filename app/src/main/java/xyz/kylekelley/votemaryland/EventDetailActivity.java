package xyz.kylekelley.votemaryland;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EventDetailActivity extends AppCompatActivity {

   public String eventName = "";
    public String eventAddress = "";
    public String eventStartTime = "";
    public String eventEndTime = "";
    public String eventDate = "";
    DateFormat dateFormat ;
    Date temp = null ;
    ArrayList<CalObj> eventObjectsPlaceholder;
    CalAdapter cAdapter;
    ArrayList<String> one = null;
    ListView listView;
    Date current_date  = new Date();
    DatabaseAccess databaseAccess = null;
    public FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        String QNAME = EventCalendarActivity.a;
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

        one = databaseAccess.getCalEvent((QNAME));
        Toast.makeText(getApplicationContext(), Integer.toString(one.size()), Toast.LENGTH_SHORT).show();

            if (one != null) {
                //name_date_address_start_end

                eventName = one.get(0);
                eventDate = one.get(1);
                eventAddress = one.get(2);
                eventStartTime = one.get(3);
                eventEndTime = one.get(4);
            }
            cAdapter.add(new CalObj("dir",eventAddress));

        cAdapter.add(new CalObj("time",eventDate + "\n" + eventStartTime + "-" +
                eventEndTime));
        Toast.makeText(getApplicationContext(), Integer.toString(cAdapter.getCount()), Toast.LENGTH_SHORT).show();
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(eventName);
        loadBackdrop();

    }

   private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(R.mipmap.pod).centerCrop().into(imageView);
    }
    public void onClickToFav(View v){

            Intent myIntent = new Intent(EventDetailActivity.this, FavoritesActivity.class);
            startActivity(myIntent);
        setContentView(R.layout.activity_favorites);

    }




}

