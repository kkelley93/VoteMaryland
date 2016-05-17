package xyz.kylekelley.votemaryland;

/**
 * Created by TJ on 4/21/2016.
 */
public class CalObj {

    public String image;
    public String title;
    public String description;
    public int id;

    public CalObj(String i, String s, int id){
        image = i;
        title = s;
        this.id = id;
    }
    public String get_title(){
        return title;
    }
    public String get_image(){
        return image;
    }
    public int get_id(){ return id; }

}
