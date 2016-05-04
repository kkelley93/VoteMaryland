package xyz.kylekelley.votemaryland;

import android.app.NotificationManager;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_webview);
    }

    public void launchCalendar(View v){
        Intent myIntent = new Intent(MainActivity.this, Calander_Of_Events.class);
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

    public void launchExternalWebview(View v){
        String url = "https://voterservices.elections.maryland.gov/VoterSearch";
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(myIntent);

    }
    public void launchNotification(View v){

        /*create notifications*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_notifications_active);
        builder.setContentTitle("My Notification");
        builder.setContentText("Reminder: Maryland Votes Today!");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1111, builder.build());



    }



}
