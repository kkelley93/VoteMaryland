package xyz.kylekelley.votemaryland;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import xyz.kylekelley.votemaryland.fragments.CivicFragment;
import xyz.kylekelley.votemaryland.models.VoterInfo;
import xyz.kylekelley.votemaryland.models.singletons.UserPreferences;


public class MainActivity extends FragmentActivity implements CivicFragment.OnInteractionListener, LoaderManager.LoaderCallbacks<Cursor> {

    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(R.string.app_name);
//        getSupportActionBar().getThemedContext();

//        AccountHeader headerResult = new AccountHeaderBuilder()
//                .withActivity(this)
//                .withHeaderBackground(R.drawable.header)
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
//                .build();

        result = new DrawerBuilder(this)
                //this layout have to contain child layouts
                .withRootView(R.id.drawer_container)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
//                .withAccountHeader(headerResult)
//                .withHeader(R.layout.nav_drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_calendar).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_candidates).withIcon(FontAwesome.Icon.faw_gamepad).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_polling_places).withIcon(FontAwesome.Icon.faw_eye).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_favorites).withIcon(FontAwesome.Icon.faw_eye).withIdentifier(4),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_faq).withIcon(FontAwesome.Icon.faw_eye).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Registration").withIcon(FontAwesome.Icon.faw_folder).withIdentifier(6)
                )
                .withSelectedItem(6)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            Intent intent = null;
                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    intent = new Intent(MainActivity.this, EventCalendarActivity.class);
                                    break;
                                case 2:
                                    intent = new Intent(MainActivity.this, CandidateListActivity.class);
                                    break;
                                case 3:
                                    intent = new Intent(MainActivity.this, PollingPlaceActivity.class);
                                    break;
                                case 4:
                                    intent = new Intent(MainActivity.this, FavoritesActivity.class);
                                    break;
                                case 5:
                                    intent = new Intent(MainActivity.this, FaqActivity.class);
                                    break;
                                case 6:
                                    intent = new Intent(MainActivity.this, RegistrationWebViewActivity.class);
                                    break;
                            }

                            if (intent != null) {
                                MainActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        //result.setSelection(1);
        int state;
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        state = databaseAccess.checkState();
        databaseAccess.close();

        if(state == 0){
            Intent myIntent = new Intent(MainActivity.this, RegistrationWebViewActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
        else{
            Intent myIntent = new Intent(MainActivity.this, EventCalendarActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
//        result.setSelection(1);
    }

    @Override
    public void onStart() {
        super.onStart();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CivicFragment fragment = new CivicFragment();
        fragmentTransaction.add(fragment, "polling_fragment");
        fragmentTransaction.commit();
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

    public void launchCalendar(View v){
        Intent myIntent = new Intent(MainActivity.this, EventCalendarActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchEventDetail(View v){
        Intent myIntent = new Intent(MainActivity.this, EventDetailActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchCandidateList(View v){
        Intent myIntent = new Intent(MainActivity.this, CandidateListActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchCandidateDetail(View v){
        Intent myIntent = new Intent(MainActivity.this, CandidateDetailActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchFAQ(View v){
        Intent myIntent = new Intent(MainActivity.this, FaqActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchFindPlace(View v){
        Intent myIntent = new Intent(MainActivity.this, PollingPlaceActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchRegistration(View v){
        Intent myIntent = new Intent(MainActivity.this, RegistrationWebViewActivity.class);
        MainActivity.this.startActivity(myIntent);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onGoButtonPressed(View view) {

    }

    @Override
    public void onAboutUsButtonPressed() {

    }

    @Override
    public void onSelectContactButtonPressed(View view) {

    }

    @Override
    public void searchedAddress(VoterInfo voterInfo) {
        // set VoterInfo object on app singleton
        if (voterInfo == null) {
            UserPreferences.setSelectedParty("");
            UserPreferences.setVoterInfo(null);
        } else {
//            UserPreferences.setSelectedParty(selectedParty);
            UserPreferences.setVoterInfo(voterInfo);
        }
    }
}
