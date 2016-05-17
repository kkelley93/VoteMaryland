package xyz.kylekelley.votemaryland;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xyz.kylekelley.votemaryland.models.Contest;
import xyz.kylekelley.votemaryland.models.Election;
import xyz.kylekelley.votemaryland.models.PollingLocation;
import xyz.kylekelley.votemaryland.models.VoterInfo;
import xyz.kylekelley.votemaryland.models.singletons.UserPreferences;

public class PollingPlaceActivity extends AppCompatActivity {

    private final String TAG = PollingPlaceActivity.class.getSimpleName();
    private Drawer result = null;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polling_place);

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
                .withSelectedItem(3)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            Intent intent = null;
                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    intent = new Intent(PollingPlaceActivity.this, EventCalendarActivity.class);
                                    break;
                                case 2:
                                    intent = new Intent(PollingPlaceActivity.this, CandidateListActivity.class);
                                    break;
                                case 3:
                                    intent = new Intent(PollingPlaceActivity.this, PollingPlaceActivity.class);
                                    break;
                                case 4:
                                    intent = new Intent(PollingPlaceActivity.this, FavoritesActivity.class);
                                    break;
                                case 5:
                                    intent = new Intent(PollingPlaceActivity.this, FaqActivity.class);
                                    break;
                                case 6:
                                    intent = new Intent(PollingPlaceActivity.this, RegistrationWebViewActivity.class);
                                    break;
                            }

                            if (intent != null) {
                                PollingPlaceActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

//        CivicInfoRequest request = new CivicInfoRequest(this, "2000", "5016 Lackawanna St");
//
//        CivicInfoInteractor civicInfoInteractor = new CivicInfoInteractor();
//        civicInfoInteractor.enqueueRequest(request, this);
        Log.d(TAG, "Running fragment");
//        CivicFragment myFragment = (CivicFragment) getSupportFragmentManager().findFragmentById(R.id.polling_fragment);
//        myFragment.constructQuery();
//        getFragmentManager().beginTransaction().add(R.id.polling_fragment, new CivicFragment()).commit();
//        FragmentManager fragmentManager = getFragmentManager()
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        CivicFragment fragment = new CivicFragment();
//        fragmentTransaction.add(fragment, "polling_fragment");
//        fragmentTransaction.commit();

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        CivicInfoRequest request = new CivicInfoRequest(this, "2000", "5010 Lackawanna St.");
//
//        CivicInfoInteractor civicInfoInteractor = new CivicInfoInteractor();
//        CivicInfoInteractor.CivicInfoCallback callback = new CivicInfoInteractor.CivicInfoCallback() {
//            @Override
//            public void civicInfoResponse(VoterInfo response) {
//                if (response != null) {
//                    if (response.isSuccessful()) {
//                        Log.d(TAG, "Response successful");
//                        PollingPlaceActivity.this.presentVoterInfoResult(response);
//                    } else {
//                        CivicApiError error = response.getError();
//
//                        CivicApiError.Error error1 = error.errors.get(0);
//
//                        Log.d(TAG, "Civic API returned error: " + error.code + ": " +
//                                error.message + " " + error1.domain + " " + error1.reason + " " +
//                                error1.message);
//                    }
//                } else {
//                    Log.d(TAG, "API returned null response");
//                }
//            }
//        };
//        civicInfoInteractor.enqueueRequest(request, callback);
//    }


    @Override
    protected void onStart() {
        super.onStart();

        VoterInfo voterInfo = UserPreferences.getVoterInfo();

        Log.d(TAG, "OH YEAH WE GOT IT!");
        Log.d(TAG, voterInfo.election.getName());

        this.listView = (ListView) findViewById(R.id.listView);
        List<String> pollingLocations = new ArrayList<>();
//        VoterInfo voterInfo = UserPreferences.getVoterInfo();

//        contests.addAll(voterInfo.getFilteredContestsForParty(UserPreferences.getSelectedParty()));
//        pollingLocations.addAll(voterInfo.getAllLocations());
        List<PollingLocation> polls = voterInfo.pollingLocations;
        if (polls != null) {
            Iterator<PollingLocation> pollingLocationIterator = voterInfo.pollingLocations.iterator();
            while (pollingLocationIterator.hasNext()) {
                PollingLocation p = pollingLocationIterator.next();
                pollingLocations.add(p.address.toString());
            }
        } else {
            Log.d(TAG, "Polling locations are empty!");
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pollingLocations);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>()

//        CandidatesAdapter adapter1 = new CandidatesAdapter(this, contests.get(0).candidates);
        this.listView.setAdapter(adapter);
//        this.listView.setAdapter(adapter1);
    }

    private void presentVoterInfoResult(VoterInfo voterInfo) {
        Log.d(TAG, "Election: " + voterInfo.election);
//        ArrayList<Election> elections = new ArrayList<>(voterInfo.otherElections);
        for (Election e: voterInfo.otherElections) {
            Log.d(TAG, "Election: " + e.toString());
        }
        for (PollingLocation p: voterInfo.getAllLocations()) {
            Log.d(TAG, "\tPolling: " + p.address);
        }
        Log.d(TAG, voterInfo.contests.toString());
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
