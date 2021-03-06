package xyz.kylekelley.votemaryland;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
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
    ArrayList<Event> one = null;
    ListView listView;
    Date current_date  = new Date();
    DatabaseAccess databaseAccess = null;
    static int a;
    private Drawer result = null;

    public void call( Date date){
        if(one != null) {
            one.clear();
        }
        cAdapter.clear();
        temp = date;
        String type, name;
        int id;
        one = databaseAccess.getCalName(dateFormat.format(date));
        //two = databaseAccess.getCalImage(dateFormat.format(date));
        if (one != null) {
            for(int i = 0; i < one.size(); i+=1) {
                Event currentEvent = one.get(i);
                name = currentEvent.name;
                type = currentEvent.type;
                id = currentEvent.id;
                cAdapter.add(new CalObj(type, name, id));
            }
        }
        if(cAdapter.isEmpty()){
            one = null;
            cAdapter.add(new CalObj("NONE","Sorry No Events For This Day",0));
        }
        if(cAdapter.isEmpty()){
            one = null;
            cAdapter.add(new CalObj("NONE","Sorry No Events For This Day",0));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        AccountHeader headerResult = new AccountHeaderBuilder()
//                .withActivity(this)
//                .withHeaderBackground(R.drawable.header)
//                .withCompactStyle(true)
//                .withTranslucentStatusBar(false)
//                .addProfiles(
//                        new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile))
//                )
//                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
//                    @Override
//                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
//                        return false;
//                    }
//                })
//                .withSelectionListEnabledForSingleProfile(false)
//                .withOnAccountHeaderSelectionViewClickListener(new AccountHeader.OnAccountHeaderSelectionViewClickListener() {
//                    @Override
//                    public boolean onClick(View view, IProfile profile) {
//                        Toast.makeText(view.getContext(), "Header click", Toast.LENGTH_SHORT).show();
//                        return false;
//                    }
//                })
//                .build();


        result = new DrawerBuilder(this)
                //this layout have to contain child layouts
                .withRootView(R.id.drawer_container)
//                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_calendar).withIcon(GoogleMaterial.Icon.gmd_perm_contact_calendar).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_candidates).withIcon(GoogleMaterial.Icon.gmd_account_circle).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_polling_places).withIcon(GoogleMaterial.Icon.gmd_place).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_favorites).withIcon(GoogleMaterial.Icon.gmd_favorite).withIdentifier(4),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_faq).withIcon(GoogleMaterial.Icon.gmd_burst_mode).withIdentifier(5),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_registration).withIcon(GoogleMaterial.Icon.gmd_description).withIdentifier(6)

                )
                .withSelectedItem(1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            Intent intent = null;
                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    intent = new Intent(EventCalendarActivity.this, EventCalendarActivity.class);
                                    break;
                                case 2:
                                    intent = new Intent(EventCalendarActivity.this, CandidateListActivity.class);
                                    break;
                                case 3:
                                    intent = new Intent(EventCalendarActivity.this, PollingPlaceActivity.class);
                                    break;
                                case 4:
                                    intent = new Intent(EventCalendarActivity.this, FavoritesActivity.class);
                                    break;
                                case 5:
                                    intent = new Intent(EventCalendarActivity.this, FaqActivity.class);
                                    break;
                                case 6:
                                    intent = new Intent(EventCalendarActivity.this, RegistrationWebViewActivity.class);
                                    break;

                            }

                            if (intent != null) {
                                EventCalendarActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

//        result.setSelection(1);

        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
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
                    a = current.get_id();

                    Intent myIntent = new Intent(EventCalendarActivity.this, EventDetailActivity.class);
                    myIntent.putExtra("id", current.get_id());
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
                String type, name;
                int id;
                one = databaseAccess.getCalName(dateFormat.format(date));
                //two = databaseAccess.getCalImage(dateFormat.format(date));
                if (one != null) {
                    for(int i = 0; i < one.size(); i+=1) {
                        Event currentEvent = one.get(i);
                        name = currentEvent.name;
                        type = currentEvent.type;
                        id = currentEvent.id;
                        cAdapter.add(new CalObj(type, name, id));
                    }
                }
                if(cAdapter.isEmpty()){
                    one = null;
                    cAdapter.add(new CalObj("NONE","Sorry No Events For This Day",0));
                }
                if(cAdapter.isEmpty()){
                    one = null;
                    cAdapter.add(new CalObj("NONE","Sorry No Events For This Day",0));
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
