package xyz.kylekelley.votemaryland.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import xyz.kylekelley.votemaryland.R;
import xyz.kylekelley.votemaryland.asynctasks.FetchImageQuery;
import xyz.kylekelley.votemaryland.models.Candidate;

/**
 * Created by kathrynkillebrew on 8/4/14.
 */
public class CandidatesAdapter extends ArrayAdapter<Candidate> {

//    VIPTabBarActivity myActivity;
//    Comparator<Candidate> candidateComparator;

    ArrayList<Candidate> data = null;
    int layoutID;
    Context con;

    public CandidatesAdapter(Context con, int layoutResourceID, ArrayList<Candidate> data) {
        super(con, layoutResourceID, data);
        this.con = con;
        this.layoutID = layoutResourceID;
        this.data = data;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View item = convertView;

        if(item == null){
            LayoutInflater inflater = LayoutInflater.from(con);
            item = inflater.inflate(layoutID, parent, false);
        }
//        String name = getItem(pos).name;
//        String party = getItem(pos).party;
//        String url = getItem(pos).candidateUrl;
//        String phone = getItem(pos).phone;
//        String photoURL = getItem(pos).photoUrl;
//        String email = getItem(pos).email;
//
//        TextView textName = (TextView) item.findViewById(R.id.candidate_name);
//        TextView textParty = (TextView) item.findViewById(R.id.candidate_party);
//        TextView textUrl = (TextView) item.findViewById(R.id.candidate_url);
//        TextView textPhone = (TextView) item.findViewById(R.id.candidate_phone);
//        TextView textPhotoURL = (TextView) item.findViewById(R.id.candidate_photo_url);
//        TextView textEmail = (TextView) item.findViewById(R.id.candidate_email);
//
//        if (textName != null && name != null) {
//            textName.setText(name);
//        }
//
//        if (textParty != null && party != null) {
//            textParty.setText(party);
//        }
//
//        if (textUrl != null && url != null) {
//            textParty.setText(url);
//        }
//
//
//        if (textPhone != null && phone != null) {
//            textParty.setText(phone);
//        }
//
//
//        if (textPhotoURL != null && photoURL != null) {
//            textParty.setText(photoURL);
//        }
//
//
//        if (textEmail != null && email != null) {
//            textParty.setText(email);
//        }

        String title = getItem(pos).name;
        String party = getItem(pos).party;

        TextView listItem = (TextView) item.findViewById(R.id.candidate_name);
        TextView textTitle = (TextView) item.findViewById(R.id.candidate_party);

        listItem.setText(title);
        textTitle.setText(party);

//
//        textUrl.setText(url);
//        textPhone.setText(phone);
//        textPhotoURL.setText(photoURL);
//        textEmail.setText(email);

        return item;
    }


    // View lookup cache.  Pattern from here:
    // https://github.com/thecodepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
//    private static class ViewHolder {
//        TextView name;
//        TextView party;
//        ImageView photo;
//        boolean alreadyQueryingForPhoto;
//    }
//
//    public void sortList() {
//        sort(candidateComparator);
//    }

    /**
     *
     * @param context VIPTabBarActivity that owns the ballot view
     * @param candidates list of contests to display
     */
//    public CandidatesAdapter(Activity context, List<Candidate> candidates) {
//        super(context, R.layout.candidate_list_item, candidates);
////        this.myActivity = context;
//
//        // sort contests by ballot placement
//        candidateComparator = new Comparator<Candidate>() {
//            @Override
//            public int compare(Candidate candidate1, Candidate candidate2) {
//                return Double.compare(candidate1.orderOnBallot, candidate2.orderOnBallot);
//            }
//        };
//    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Candidate candidate = getItem(position);
//        // Check if an existing view is being reused, otherwise inflate the view
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.candidate_list_item, parent, false);
//            viewHolder.name = (TextView) convertView.findViewById(R.id.candidate_list_item_name);
//            viewHolder.party = (TextView) convertView.findViewById(R.id.candidate_list_item_party);
//            viewHolder.photo = (ImageView) convertView.findViewById(R.id.candidate_list_item_picture);
//            viewHolder.alreadyQueryingForPhoto = false;
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        setTextView(viewHolder.name, candidate.name);
//        setTextView(viewHolder.party, candidate.party);
//
//        Bitmap havePhoto = candidate.getCandidatePhoto();
//        if (havePhoto != null) {
//            viewHolder.photo.setImageBitmap(havePhoto);
//            viewHolder.photo.setVisibility(View.VISIBLE);
//        } else if (candidate.photoUrl != null && !candidate.photoUrl.isEmpty() && !viewHolder.alreadyQueryingForPhoto) {
//            viewHolder.alreadyQueryingForPhoto = true;
//            new FetchImageQuery(candidate, viewHolder.photo).execute(candidate.photoUrl);
//        }
//
//        // Return the completed view to render on screen
//        return convertView;
//    }

    /**
     * Helper function to set text in view, or hide view if text empty or missing
     * @param view TextView to put text into
     * @param text String to put in the TextView
     */
//    private void setTextView(TextView view, String text) {
//        if (text != null && !text.isEmpty()) {
//            view.setText(text);
//        } else {
//            view.setVisibility(View.GONE);
//        }
//    }
}
