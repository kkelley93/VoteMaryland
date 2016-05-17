package xyz.kylekelley.votemaryland;

/**
 * Created by KyleKelley on 5/17/2016.
 */
public class Event {
    public String name;
    public String locationTitle;
    public String street;
    public String cityState;
    public String zip;
    //public String party;
    public String type;
    public String startTime;
    public String endTime;
    public String date;
    public String description;
    public int favorited;
    public int id;

    public Event(String name, String locationTitle, String street, String cityState, String zip, String type, String startTime, String endTime, String date, String description, int favorited, int id){
        this.name = name;
        this.locationTitle = locationTitle;
        this.street = street;
        this.cityState = cityState;
        this.zip = zip;
        this.type = type;
        //this.party = party;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.description = description;
        this.favorited = favorited;
        this.id = id;
    }

    public Event(String name, String type, int id){
        this.name = name;
        this.type = type;
        this.id = id;
    }

    public Event(String name, int id){
        this.name = name;
        this.id = id;
    }

}
