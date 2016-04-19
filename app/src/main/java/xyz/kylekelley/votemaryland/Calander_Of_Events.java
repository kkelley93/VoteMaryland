package xyz.kylekelley.votemaryland;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    //ArrayList<EventObjects> eo; Placeholder below
    ArrayList<String> eventObjectsPlaceholder;
    Cal_adapter cAdapter;
    ListView listView;



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


        eventObjectsPlaceholder = new ArrayList<String>();
        cAdapter = new Cal_adapter(this, R.layout.cal_list_row, eventObjectsPlaceholder);
        listView = (ListView) findViewById(R.id.eventList);

        cAdapter.add("Meet at Sardis");

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(getApplicationContext(), dateFormat.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " + dateFormat.format(date),
                        Toast.LENGTH_SHORT).show();
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
}
