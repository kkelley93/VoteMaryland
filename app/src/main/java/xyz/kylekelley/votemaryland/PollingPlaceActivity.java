package xyz.kylekelley.votemaryland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class PollingPlaceActivity extends AppCompatActivity {


    private Drawer result = null;

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
                        new PrimaryDrawerItem().withName(R.string.drawer_item_calendar).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_candidates).withIcon(FontAwesome.Icon.faw_gamepad).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_polling_places).withIcon(FontAwesome.Icon.faw_eye).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_favorites).withIcon(FontAwesome.Icon.faw_eye).withIdentifier(4),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_faq).withIcon(FontAwesome.Icon.faw_eye).withIdentifier(5)
                )
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
                                    intent = new Intent(PollingPlaceActivity.this, EventCalendarActivity.class);
                                    break;
                                case 5:
                                    intent = new Intent(PollingPlaceActivity.this, FaqActivity.class);
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
