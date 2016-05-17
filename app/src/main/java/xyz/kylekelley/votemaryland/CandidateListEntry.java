package xyz.kylekelley.votemaryland;

import xyz.kylekelley.votemaryland.models.Candidate;

/**
 * Created by kaminskyd on 5/17/16.
 */
public class CandidateListEntry extends Candidate {

    public String image;
    public String title;
    public String description;
    public int id;

    public CandidateListEntry(String i, String s, int id){
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
