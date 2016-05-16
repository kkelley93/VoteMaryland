package xyz.kylekelley.votemaryland;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EventDetail extends AppCompatActivity {

   public String eventName = "EVENT NAME";
    public String eventAddress = "127 EVENT asdjakdha ";
    public String eventStartTime = "";
    public String eventEndTime = "";
    public String eventDate = "";
    DateFormat dateFormat ;
    Date temp = null ;
    //ArrayList<EventObjects> eo; Placeholder below
    ArrayList<cal_obj> eventObjectsPlaceholder;
    Cal_adapter cAdapter;
    ListView listView;
    DatabaseAccess databaseAccess = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        eventObjectsPlaceholder = new ArrayList<cal_obj>();
        cAdapter = new Cal_adapter(this, R.layout.cal_list_row, eventObjectsPlaceholder);
        listView = (ListView) findViewById(R.id.eventList);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        listView.setAdapter(cAdapter);

        ArrayList<String> one = null;
        one = databaseAccess.getCalEvent((eventName));
        Toast.makeText(getApplicationContext(), Integer.toString(one.size()), Toast.LENGTH_SHORT).show();

            if (one != null) {
                //name_date_address_start_end
                eventName = one.get(0);
                eventDate = one.get(1);
                eventAddress = one.get(2);
                eventStartTime = one.get(3);
                eventEndTime = one.get(4);
            }
            cAdapter.add(new cal_obj("car",eventAddress));
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




}

