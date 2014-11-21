package com.android.mobileapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


public class SettingActivity extends ActionBarActivity {
    //private final String TAG = ((Object)this).getClass().getSimpleName();
    private LocationManager mLocationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "+++ In onCreate() +++");
        setContentView(R.layout.activity_setting);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_login) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
            return rootView;
        }
    }

    public void selectNotiFreq(View view) {
        Intent intent = new Intent(this, NotiFreqActivity.class);
        startActivity(intent);
    }

    public void selectLocation(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    public void useCurrentLocation(View view) {
        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(mLocationManager.GPS_PROVIDER, 1000L, 500.0f, mLocationListener);
        //Location currLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location currLocation = getCurrLocation();
        double currLat = currLocation.getLatitude();
        double currLng = currLocation.getLongitude();
        int forumNumber = getForumNumber(new LatLng(currLat, currLng));
        //int forumNumber = 1;
        if(forumNumber > 0) {
            //jump to forum
            Intent forumIntent = new Intent(SettingActivity.this, ForumActivity.class);
            forumIntent.putExtra(getString(R.string.map_to_forum_intent_extra), forumNumber);
            startActivity(forumIntent);
        }
        else {
            Toast.makeText(this, "No forum available here. Please select your location.", Toast.LENGTH_SHORT).show();
        }

    }

    public static int getForumNumber(LatLng point) {
        double lat = point.latitude;
        double lng = point.longitude;

        if (isInArea(lat, lng, 39.996835, 39.998717, -83.009481, -83.008108)) {
            return 1;
        }//ohio union

        if (isInArea(lat, lng, 39.995553, 39.996703, -83.014681, -83.012191)) {
            return 2;
        }//oia

        if (isInArea(lat, lng, 39.998890, 39.999728, -83.015389, -83.014123)) {
            return 3;
        }//Thompson lib

        if (isInArea(lat, lng, 39.999034, 39.999934, -83.016912, -83.016006)) {
            return 4;
        }//wilce center

        if (isInArea(lat, lng, 39.999001, 40.000110, -83.019313, -83.017610)) {
            return 5;
        }//rpec

        if (isInArea(lat, lng, 40.001368, 40.001918, -83.013723, -83.012940)) {
            return 6;
        }//18th lib

        if (isInArea(lat, lng, 40.001203, 40.002058, -83.016459, -83.015483)) {
            return 7;
        }//Dreese lab

        if (isInArea(lat, lng, 40.002814, 40.003883, -83.015700, -83.014563)) {
            return 8;
        }//Hitchcock & Bolz

        if (isInArea(lat, lng, 40.000340, 40.003381, -83.021311, -83.018071)) {
            return 9;
        }//Ohio stadium

        return 0;
    }

    public static boolean isInArea(double lat, double lng, double latMin, double latMax, double lngMin, double lngMax) {
        if (lat < latMax && lat > latMin) {
            if (lng < lngMax && lng > lngMin) {
                return true;
            }
        }
        return false;
    }

    private Location getCurrLocation() {
        mLocationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        mLocationManager.removeUpdates(mLocationListener);
        mLocationManager = null;
        return bestLocation;
    }
}
