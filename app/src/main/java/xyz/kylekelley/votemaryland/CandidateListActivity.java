package xyz.kylekelley.votemaryland;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xyz.kylekelley.votemaryland.adapter.CandidatesAdapter;
import xyz.kylekelley.votemaryland.models.Candidate;
import xyz.kylekelley.votemaryland.models.Contest;
import xyz.kylekelley.votemaryland.models.VoterInfo;
import xyz.kylekelley.votemaryland.models.singletons.UserPreferences;

public class CandidateListActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Candidate> listEntries;
    private CandidatesAdapter candidatesAdapter;
    public static Candidate cand;
    
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        result = new DrawerBuilder(this)
                //this layout have to contain child layouts
                .withRootView(R.id.drawer_container)
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
                .withSelectedItem(2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            Intent intent = null;
                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    intent = new Intent(CandidateListActivity.this, EventCalendarActivity.class);
                                    break;
                                case 2:
                                    intent = new Intent(CandidateListActivity.this, CandidateListActivity.class);
                                    break;
                                case 3:
                                    intent = new Intent(CandidateListActivity.this, PollingPlaceActivity.class);
                                    break;
                                case 4:
                                    intent = new Intent(CandidateListActivity.this, FavoritesActivity.class);
                                    break;
                                case 5:
                                    intent = new Intent(CandidateListActivity.this, FaqActivity.class);
                                    break;
                                case 6:
                                    intent = new Intent(CandidateListActivity.this, RegistrationWebViewActivity.class);
                                    break;
                            }

                            if (intent != null) {
                                CandidateListActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
//                .withSavedInstance(savedInstanceState)
                .build();
        
        
        //Opens connection to MD_Candidates.db, runs getNames() function in DatabaseAccess class
        //displays names of candidates in a generic ListView.
        this.listView = (ListView) findViewById(R.id.listView);
//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
//        databaseAccess.open();
//        List<String> names = databaseAccess.getNames();
//        databaseAccess.close();


        listEntries = new ArrayList<Candidate>();

        ArrayList<Contest> contests = new ArrayList<>();
        VoterInfo voterInfo = UserPreferences.getVoterInfo();
        candidatesAdapter = new CandidatesAdapter(this, R.layout.candidate_list_item, listEntries);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(candidatesAdapter);

        contests.addAll(voterInfo.getFilteredContestsForParty(UserPreferences.getSelectedParty()));

//        ArrayList<String> candidateStrings = new ArrayList<>();
        Iterator<Contest> contestIterator = voterInfo.contests.iterator();

        while (contestIterator.hasNext()) {

            Contest contest = contestIterator.next();
            List<Candidate> candidates = contest.candidates;
            if (candidates != null) {

                Iterator<Candidate> candidateIterator = candidates.iterator();
                while (candidateIterator.hasNext()) {
                    Candidate candidate = candidateIterator.next();
                    candidatesAdapter.add(candidate);
//                    candidateStrings.add(candidate.name);
                }
            }

        }

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, candidateStrings);
//        CandidatesAdapter adapter1 = new CandidatesAdapter(this, contests.get(0).candidates);
//        this.listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Candidate candidate = candidatesAdapter.getItem(position);
//                    ItemClicked current = parent.getItemAtPosition(position);
//                    a = current.get_id();
                    cand = candidate;
                    Intent myIntent = new Intent(CandidateListActivity.this, CandidateDetailActivity.class);
                    CandidateListActivity.this.startActivity(myIntent);
            }
        });

//        this.listView.setAdapter(adapter1);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }


}
