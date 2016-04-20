package xyz.kylekelley.votemaryland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



}
