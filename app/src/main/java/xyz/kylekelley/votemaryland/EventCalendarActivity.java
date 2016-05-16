package xyz.kylekelley.votemaryland;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class EventCalendarActivity extends AppCompatActivity {
    DateFormat dateFormat ;
    Date temp = null ;
    ArrayList<CalObj> eventObjectsPlaceholder;
    CalAdapter cAdapter;
    ArrayList<String> one = null;
    ListView listView;
    Date current_date  = new Date();
    DatabaseAccess databaseAccess = null;
    static String a = "";
    public void call( Date date){
        if(one != null) {
            one.clear();
        }
        cAdapter.clear();
        temp = date;
        String a = "";
        String b = "";
        one = databaseAccess.getCalName(dateFormat.format(date));
        //two = databaseAccess.getCalImage(dateFormat.format(date));
        if (one != null) {
            for(int i = 0; i < one.size(); i+=2) {
                b = one.get(i);
                a = one.get(i + 1);
                cAdapter.add(new CalObj(a, b));
            }
        }
        if(cAdapter.isEmpty()){
            one = null;
            cAdapter.add(new CalObj("NONE","Sorry No Events For This Day"));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_calendar);
        final CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        final Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        eventObjectsPlaceholder = new ArrayList<CalObj>();
        cAdapter = new CalAdapter(this, R.layout.cal_list_row, eventObjectsPlaceholder);
        listView = (ListView) findViewById(R.id.eventList);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        listView.setAdapter(cAdapter);
        this.call(current_date);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (one != null) {
                    CalObj current = cAdapter.getItem(position);
                    a = current.get_title();
                    Intent myIntent = new Intent(EventCalendarActivity.this, EventDetailActivity.class);
                    EventCalendarActivity.this.startActivity(myIntent);
                }
            }
        });

         CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                if(temp != null) {
                    caldroidFragment.clearTextColorForDate(temp);
                }
                caldroidFragment.setTextColorForDate(R.color.RED, date);
                caldroidFragment.refreshView();
                if(one != null) {
                    one.clear();
                }
                cAdapter.clear();
                temp = date;
                String a = "";
                String b = "";
                one = databaseAccess.getCalName(dateFormat.format(date));
                //two = databaseAccess.getCalImage(dateFormat.format(date));
                if (one != null) {
                for(int i = 0; i < one.size(); i+=2) {
                        b = one.get(i);
                        a = one.get(i + 1);
                        cAdapter.add(new CalObj(a, b));
                    }
                }
                if(cAdapter.isEmpty()){
                    one = null;
                    cAdapter.add(new CalObj("NONE","Sorry No Events For This Day"));
                }
            }
            public void setBackgroundDrawableForDate(Drawable drawable, Date date){

            }
            @Override
            public void onChangeMonth(int month, int year) {
            }
            @Override
            public void onLongClickDate(Date date, View view) {
            }
            @Override
            public void onCaldroidViewCreated() {
            }
        };
        caldroidFragment.setCaldroidListener(listener);
        android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendarView, caldroidFragment);
        t.commit();
    }
    @Override
    public void onPause(){
        super.onPause();
        databaseAccess.close();

    }
}
