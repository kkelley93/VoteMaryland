package xyz.kylekelley.votemaryland;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        Intent myIntent = new Intent(RegistrationWebViewActivity.this, ExternalWebViewActivity.class);
        RegistrationWebViewActivity.this.startActivity(myIntent);
    }

}
