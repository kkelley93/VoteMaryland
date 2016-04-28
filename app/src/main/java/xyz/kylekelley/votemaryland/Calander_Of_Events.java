package xyz.kylekelley.votemaryland;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class Calander_Of_Events extends AppCompatActivity {
    DateFormat dateFormat ;
    Date temp ;
    //ArrayList<EventObjects> eo; Placeholder below
    ArrayList<cal_obj> eventObjectsPlaceholder;
    Cal_adapter cAdapter;
    ListView listView;
    DatabaseAccess databaseAccess = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander__of__events);
        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        eventObjectsPlaceholder = new ArrayList<cal_obj>();
        cAdapter = new Cal_adapter(this, R.layout.cal_list_row, eventObjectsPlaceholder);
        listView = (ListView) findViewById(R.id.eventList);
         databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        listView.setAdapter(cAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cal_obj current = cAdapter.getItem(position);
                Intent myIntent = new Intent(Calander_Of_Events.this, EventDetail.class);
                Calander_Of_Events.this.startActivity(myIntent);
            }
        });
        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {

                cAdapter.clear();
               // Toast.makeText(getApplicationContext(),  dateFormat.format(date),
                 //       Toast.LENGTH_SHORT).show();
                ArrayList<String> one = null;
                //ArrayList<String> two = null ;
                String a = "";
                String b = "";
                one = databaseAccess.getCalName(dateFormat.format(date));
                //two = databaseAccess.getCalImage(dateFormat.format(date));
                if(one != null) {

                     a = one.get(1);
                     b = one.get(0);
                }
                if(a != "" && b !="") {
                    cAdapter.add(new cal_obj(a, b));
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
