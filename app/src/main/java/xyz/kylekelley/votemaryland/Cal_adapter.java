package xyz.kylekelley.votemaryland;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TJ on 4/19/2016.
 */
public class Cal_adapter extends ArrayAdapter<String>{
    ArrayList<String> data =null;
    int layoutID;
    Context con;

    public Cal_adapter(Context con, int layoutResourceID, ArrayList<String> data){
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

        //This is where you are going to put items in each view, based on position.
        //Example :
        String title = getItem(pos);

        //Then you will place the different attributes/variables from the object
        //into the view that you have (item).
        //Example :
       // TextView textTitle = (TextView) item.findViewById(R.id.eventTitle);
       // if(title != null) textTitle.setText(title);

        // The above two lines give you access to the view, in this case the title for this
        // row, and then if that works correctly, then you will set the Text of the view.
        // You will need to write the getTitle() method that will return a string of the title.

        return item;
    }

}
