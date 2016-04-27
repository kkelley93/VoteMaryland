package xyz.kylekelley.votemaryland;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class CandidateDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_detail);
        /*
        * Sets up a Collapsing Toolbar that inserts an image in the flexible space
        * of the expanded toolbar.
        */
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Test");
        collapsingToolbar.setCollapsedTitleTextColor(0xFFFFFFFF);
        collapsingToolbar.setExpandedTitleColor(0xFFFFFFFF);

        loadBackdrop();
    }
    /*
    * Uses the Glide library to load and cache images with a focus on smooth scrolling
    */
    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(R.drawable.berniesanders).centerCrop().into(imageView);
    }
}
