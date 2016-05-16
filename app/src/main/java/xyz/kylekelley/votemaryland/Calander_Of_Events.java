package xyz.kylekelley.votemaryland;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
    Date temp = null ;
    //ArrayList<EventObjects> eo; Placeholder below
    ArrayList<cal_obj> eventObjectsPlaceholder;
    Cal_adapter cAdapter;
    ArrayList<String> one = null;
    ListView listView;
    DatabaseAccess databaseAccess = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander__of__events);
        final CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        final Calendar cal = Calendar.getInstance();
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
                if (one != null) {
                    cal_obj current = cAdapter.getItem(position);
                    Intent myIntent = new Intent(Calander_Of_Events.this, EventDetail.class);
                    Calander_Of_Events.this.startActivity(myIntent);
                }
            }
        });

        final CaldroidListener listener = new CaldroidListener() {
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
               // Toast.makeText(getApplicationContext(),  dateFormat.format(date),
                 //       Toast.LENGTH_SHORT).show();
                temp = date;

                //ArrayList<String> two = null ;
                String a = "";
                String b = "";
                one = databaseAccess.getCalName(dateFormat.format(date));
                //two = databaseAccess.getCalImage(dateFormat.format(date));
                Toast.makeText(getApplicationContext(), Integer.toString(one.size()), Toast.LENGTH_SHORT).show();
                if (one != null) {
                for(int i = 0; i < one.size(); i+=2) {
                        b = one.get(i);
                        a = one.get(i + 1);
                        cAdapter.add(new cal_obj(a, b));
                    }
                }
                else{
                    cAdapter.add(new cal_obj("NONE","Sorry No Events For This Day"));
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
