package xyz.kylekelley.votemaryland.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import xyz.kylekelley.votemaryland.models.CivicApiError;
import xyz.kylekelley.votemaryland.models.Election;
import xyz.kylekelley.votemaryland.models.VoterInfo;
import xyz.kylekelley.votemaryland.models.api.interactors.CivicInfoInteractor;
import xyz.kylekelley.votemaryland.models.api.requests.CivicInfoRequest;

/**
 * Created by kaminskyd on 5/17/16.
 */
public class CivicFragment extends Fragment implements CivicInfoInteractor.CivicInfoCallback {

    private final String TAG = CivicFragment.class.getSimpleName();
    private String electionID = "2000";
//    private String electionID = "4204"; // Idaho
//    private String electionID = "4209"; // California

    private Activity activity;
    private Context mContext;
    private String address;
    private OnInteractionListener mListener;
    private Button goButton;

    public CivicFragment() {

    }

    public static CivicFragment newInstance() { return new CivicFragment(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity =  getActivity();
        mContext = activity.getApplicationContext();

        setRetainInstance(true);

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_polling, container, false);
//
//        goButton = (Button) rootView.findViewById(R.id.go_button);
//
//        goButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                constructQuery();
//            }
//        });
//
//        return rootView;
//    }

    public void checkInternetConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

//        if (netInfo == null || !netInfo.isConnectedOrConnecting()) {
//            homeGoButton.setVisibility(View.INVISIBLE);
//            homeTextViewStatus.setText(mContext.getResources().getText(R.string.fragment_home_error_no_internet));
//        }
    }

    public void constructQuery() {
        checkInternetConnectivity();

        CivicInfoRequest request = new CivicInfoRequest(getActivity(), electionID, this.address);

        CivicInfoInteractor civicInfoInteractor = new CivicInfoInteractor();
        civicInfoInteractor.enqueueRequest(request, this);
    }

    private void presentVoterInfoResult(VoterInfo voterInfo) {
        Log.d(TAG, "Presenting...");
        System.out.println("Presenting...");
        Log.d(TAG, "Election: " + voterInfo.election);
        ArrayList<Election> elections = new ArrayList<>(voterInfo.otherElections);
        for (Election e: elections) {
            Log.d(TAG, "Election: " + e.toString());
        }
//        Log.d(TAG, voterInfo.contests.toString());
        mListener.searchedAddress(voterInfo);
    }

    @Override
    public void civicInfoResponse(VoterInfo response) {
        if (response != null) {
            if (response.isSuccessful()) {
                presentVoterInfoResult(response);
            } else {
                CivicApiError error = response.getError();

                CivicApiError.Error error1 = error.errors.get(0);

                Log.d(TAG, "Civic API returned error: " + error.code + ": " +
                        error.message + " " + error1.domain + " " + error1.reason + " " +
                        error1.message);
            }
        } else {
            Log.d(TAG, "API returned null response");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

//        this.setAddress("5016 Lackawanna St");
//        this.setAddress("615 8th Ave E, Gooding");
        this.setAddress("10454 Scenic Ct, Cupertino");
        constructQuery();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mContext = null;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public interface OnInteractionListener {
        void onGoButtonPressed(View view);

        void onAboutUsButtonPressed();

        void onSelectContactButtonPressed(View view);

        void searchedAddress(VoterInfo voterInfo);
    }
}
