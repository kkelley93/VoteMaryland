package xyz.kylekelley.votemaryland;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class RegistrationWebViewActivity extends AppCompatActivity {
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_web_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
                                    intent = new Intent(RegistrationWebViewActivity.this, EventCalendarActivity.class);
                                    break;
                                case 2:
                                    intent = new Intent(RegistrationWebViewActivity.this, CandidateListActivity.class);
                                    break;
                                case 3:
                                    intent = new Intent(RegistrationWebViewActivity.this, PollingPlaceActivity.class);
                                    break;
                                case 4:
                                    intent = new Intent(RegistrationWebViewActivity.this, FavoritesActivity.class);
                                    break;
                                case 5:
                                    intent = new Intent(RegistrationWebViewActivity.this, FaqActivity.class);
                                    break;
                                case 6:
                                    intent = new Intent(RegistrationWebViewActivity.this, RegistrationWebViewActivity.class);
                                    break;

                            }

                            if (intent != null) {
                                RegistrationWebViewActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
//                .withSavedInstance(savedInstanceState)
                .build();

    }
    public void launchExternalWebview(View v){
        String url = "https://voterservices.elections.maryland.gov/OnlineVoterRegistration/InstructionsStep1";
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(myIntent);

    }
    public void alreadyRegistered(View v){
        Intent myIntent = new Intent(RegistrationWebViewActivity.this, EventCalendarActivity.class);
        startActivity(myIntent);
    }

    public void launchNotification(View v){

        /*create notifications*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //builder.setSmallIcon(R.drawable.ic_notifications_active);
        builder.setContentTitle("My Notification");
        builder.setContentText("Reminder: Maryland Votes Today!");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1111, builder.build());

    }

}
