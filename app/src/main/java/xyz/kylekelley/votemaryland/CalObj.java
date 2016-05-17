package xyz.kylekelley.votemaryland;

/**
 * Created by TJ on 4/21/2016.
 */
public class CalObj {

    public String image;
    public String title;

    public CalObj(String i, String s){
        image = i;
        title = s;

    }
    public String get_title(){
        return title;
    }
    public String get_image(){
        return image;
    }

}
