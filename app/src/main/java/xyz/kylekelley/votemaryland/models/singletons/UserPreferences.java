package xyz.kylekelley.votemaryland.models.singletons;

import android.location.Location;

import xyz.kylekelley.votemaryland.models.VoterInfo;

import java.util.Locale;

/**
 * Created by marcvandehey on 3/2/16.
 */
public class UserPreferences {
    private static final String TAG = UserPreferences.class.getSimpleName();

    private static UserPreferences ourInstance = new UserPreferences();

    private String selectedParty ;

    private boolean useMetric;
    private Location homeLocation;
    private VoterInfo voterInfo;

    private static UserPreferences getInstance() {
        return ourInstance;
    }

    private UserPreferences() {
        this.useMetric = !Locale.getDefault().getISO3Country().equalsIgnoreCase(Locale.US.getISO3Country());
        this.homeLocation = null;
        this.voterInfo = null;
        this.selectedParty = "Democratic";
    }

    public static Location getHomeLocation() {
        return getInstance().homeLocation;
    }

    public static void setHomeLocation(Location homeLocation) {
        getInstance().homeLocation = homeLocation;
    }

    public static boolean useMetric() {
        return getInstance().useMetric;
    }

    public static boolean setUseMetric(boolean useMetric) {
        return getInstance().useMetric = useMetric;
    }

    public static void setVoterInfo(VoterInfo voterInfo) {
        getInstance().voterInfo = voterInfo;
    }

    public static VoterInfo getVoterInfo() {
        return getInstance().voterInfo;
    }

    public static void setSelectedParty(String selectedParty) {
        ourInstance.selectedParty = selectedParty;
    }

    public static String getSelectedParty() {
        return ourInstance.selectedParty;
    }
}
