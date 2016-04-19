package xyz.kylekelley.votemaryland;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void launchCalendar(View v){
        Intent myIntent = new Intent(MainActivity.this, Calendar_Of_Events.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchEventDetail(View v){
        Intent myIntent = new Intent(MainActivity.this, EventDetail.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchCandidateList(View v){
        Intent myIntent = new Intent(MainActivity.this, candidate_list.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchCandidateDetail(View v){
        Intent myIntent = new Intent(MainActivity.this, CandidateDetail.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchFAQ(View v){
        Intent myIntent = new Intent(MainActivity.this, FAQ.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchFindPlace(View v){
        Intent myIntent = new Intent(MainActivity.this, FindMyPollingPlace.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void launchRegistration(View v){
        Intent myIntent = new Intent(MainActivity.this, registration_webview.class);
        MainActivity.this.startActivity(myIntent);
    }

    private void addDrawerItems() {
        String[] osArray = {"Candidate List", "Calendar of Events", "FAQ", "FindMyPollingPlace", "Registration"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
