package xyz.kylekelley.votemaryland;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TJ on 4/19/2016.
 */
public class Cal_adapter extends ArrayAdapter<cal_obj>{
    ArrayList<cal_obj> data =null;
    int layoutID;
    Context con;




    public Cal_adapter(Context con, int layoutResourceID, ArrayList<cal_obj> data){
        super(con, layoutResourceID, data);
        this.con = con;
        this.layoutID = layoutResourceID;
        this.data = data;
    }

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
                Drawable d = getContext().getResources().getDrawable(R.mipmap.pod);

                IV.setImageDrawable(d);
            } else if(image.equals("vote".trim())) {
                Drawable d = getContext().getResources().getDrawable(R.mipmap.vote);
                IV.setImageDrawable(d);
            }else if(image.equals("car".trim())) {
                Drawable d = getContext().getResources().getDrawable(R.mipmap.car);
                IV.setImageDrawable(d);
            }else if(image.equals("dir".trim())) {
                Drawable d = getContext().getResources().getDrawable(R.mipmap.dir);
                IV.setImageDrawable(d);
            }else if(image.equals("fav".trim())) {
                Drawable d = getContext().getResources().getDrawable(R.mipmap.fav);
                IV.setImageDrawable(d);
            }else if(image.equals("time".trim())) {
                Drawable d = getContext().getResources().getDrawable(R.mipmap.time);
                IV.setImageDrawable(d);
            }else{
                Drawable d = getContext().getResources().getDrawable(R.mipmap.vote);
                IV.setImageDrawable(d);

            }

        }
        return item;
    }

}
