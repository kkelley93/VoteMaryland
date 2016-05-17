package xyz.kylekelley.votemaryland.models;

import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * Polling location or early voting site
 * https://developers.google.com/civic-information/docs/v1/voterinfo
 */
public class PollingLocation {
    public CivicApiAddress address;
    public String id;
    public String name;
    public String startDate;
    public String endDate;
    public String notes;
    public String pollingHours;
    public List<Source> sources;
    public String voterServices; // This field is not populated for polling locations.
}
