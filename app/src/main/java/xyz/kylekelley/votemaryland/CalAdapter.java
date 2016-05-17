package xyz.kylekelley.votemaryland;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import java.util.ArrayList;

/**
 * Created by TJ on 4/19/2016.
 */
public class CalAdapter extends ArrayAdapter<CalObj>{
    ArrayList<CalObj> data =null;
    int layoutID;
    Context con;




    public CalAdapter(Context con, int layoutResourceID, ArrayList<CalObj> data){
        super(con, layoutResourceID, data);
        this.con = con;
        this.layoutID = layoutResourceID;
        this.data = data;
    }
    private Drawer result = null;

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        View item = convertView;

        if(item == null){
            LayoutInflater inflater = LayoutInflater.from(con);
            item = inflater.inflate(layoutID, parent, false);
        }
        String title = getItem(pos).get_title();
        String image = getItem(pos).get_image();

        ImageView IV = (ImageView) item.findViewById(R.id.eventImage);
        TextView textTitle = (TextView) item.findViewById(R.id.eventTitle);
        if(title != null) {
            textTitle.setText(title);

            if (image.equals("pod".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_settings_voice)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            } else if(image.equals("vote".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_check_box)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }else if(image.equals("dir".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_place)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }else if(image.equals("fav".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_favorite)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }else if(image.equals("time".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_query_builder)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }else if(image.equals("NONE".trim())) {
                Drawable d = null;
                IV.setImageDrawable(d);
            }else if(image.equals("rally".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_volume_up)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }else if(image.equals("phone".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_phone)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }else if(image.equals("march".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_directions_run)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }else if(image.equals("register".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_assignment)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }else if(image.equals("meetup".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_people)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }else if(image.equals("description".trim())) {
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_description)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }
            else{
                Drawable d = new IconicsDrawable(getContext())
                        .icon(GoogleMaterial.Icon.gmd_settings_voice)
                        .color(Color.RED)
                        .sizeDp(10);
                IV.setImageDrawable(d);
            }

        }
        return item;
    }

}
