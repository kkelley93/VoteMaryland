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

public class RegistrationWebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void launchExternalWebview(View v){
        String url = "https://voterservices.elections.maryland.gov/OnlineVoterRegistration/InstructionsStep1";
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
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
