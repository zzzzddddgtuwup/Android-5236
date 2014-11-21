package com.android.mobileapp;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.Map;


public class MapActivity extends ActionBarActivity {
    protected GoogleMap mMap;
    protected LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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

    //public static

    @Override
    protected  void onStart(){
        super.onStart();
        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(mLocationManager.GPS_PROVIDER, 1000L, 500.0f, mLocationListener);

        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
/*        Location currLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        double currLat = currLocation.getLatitude();
        double currLng = currLocation.getLongitude();*/
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(39.998890, -83.015000), 15.0f) );


        /*
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(40, -83.02), 15.0f) );
        Marker mForum = mMap.addMarker(new MarkerOptions().position(new LatLng(40, -83.02))
                .title("Dreese Lab").draggable(true));
        */
        PolygonOptions ohioUnion = new PolygonOptions().add(
                new LatLng(39.998717, -83.009481),
                new LatLng(39.998717, -83.008108),
                new LatLng(39.996835, -83.008108),
                new LatLng(39.996835, -83.009481)
        );
        Polygon ohioUnionPolygon = mMap.addPolygon(ohioUnion);
        Marker mOhioUnion = mMap.addMarker(new MarkerOptions().position(new LatLng(39.998717, -83.009481))
                .title("Ohio Union").draggable(false));



        PolygonOptions officeOfInternational = new PolygonOptions().add(
                new LatLng(39.996703, -83.014681),
                new LatLng(39.996703, -83.012191),
                new LatLng(39.995553, -83.012191),
                new LatLng(39.995553, -83.014681)
        );
        Polygon oiaPolygon = mMap.addPolygon(officeOfInternational);
        Marker mOIA = mMap.addMarker(new MarkerOptions().position(new LatLng(39.996703, -83.014681))
                .title("Office of International Affair").draggable(false));

        PolygonOptions thompsonLibrary = new PolygonOptions().add(
                new LatLng(39.999728, -83.015389),
                new LatLng(39.999728, -83.014123),
                new LatLng(39.998890, -83.014123),
                new LatLng(39.998890, -83.015389)
        );
        Polygon thompsonLibPolygon = mMap.addPolygon(thompsonLibrary);
        Marker mThompsonLib = mMap.addMarker(new MarkerOptions().position(new LatLng(39.999728, -83.015389))
                .title("Thompson Library").draggable(false));

        PolygonOptions wilceCenter = new PolygonOptions().add(
                new LatLng(39.999934, -83.016912),
                new LatLng(39.999934, -83.016006),
                new LatLng(39.999034, -83.016006),
                new LatLng(39.999034, -83.016912)
        );
        Polygon wilceCenterPolygon = mMap.addPolygon(wilceCenter);
        Marker mWilceCenter = mMap.addMarker(new MarkerOptions().position(new LatLng(39.999934, -83.016912))
                .title("Wilce Health Center").draggable(false));

        PolygonOptions rpec = new PolygonOptions().add(
                new LatLng(40.000110, -83.019313),
                new LatLng(40.000110, -83.017610),
                new LatLng(39.999001, -83.017610),
                new LatLng(39.999001, -83.019313)
        );
        Polygon rpecPolygon = mMap.addPolygon(rpec);
        Marker mRpec = mMap.addMarker(new MarkerOptions().position(new LatLng(40.000110, -83.019313))
                .title("RPEC").draggable(false));

        PolygonOptions library18 = new PolygonOptions().add(
                new LatLng(40.001918, -83.013723),
                new LatLng(40.001918, -83.012940),
                new LatLng(40.001368, -83.012940),
                new LatLng(40.001368, -83.013723)
        );
        Polygon lib18Polygon = mMap.addPolygon(library18);
        Marker mLibrary18 = mMap.addMarker(new MarkerOptions().position(new LatLng(40.001918, -83.013723))
                .title("18th Library").draggable(false));

        PolygonOptions dreeseLab = new PolygonOptions().add(
                new LatLng(40.002058, -83.016459),
                new LatLng(40.002058, -83.015483),
                new LatLng(40.001203, -83.015483),
                new LatLng(40.001203, -83.016459)
        );
        Polygon dreeseLabPolygon = mMap.addPolygon(dreeseLab);
        Marker mDreeseLab = mMap.addMarker(new MarkerOptions().position(new LatLng(40.002058, -83.016459))
                .title("Dreese Laboratory").draggable(false));

        PolygonOptions hitchcockAndBolz = new PolygonOptions().add(
                new LatLng(40.003883, -83.015700),
                new LatLng(40.003883, -83.014563),
                new LatLng(40.002814, -83.014563),
                new LatLng(40.002814, -83.015700)
        );
        Polygon hitchcockAndBolzPolygon = mMap.addPolygon(hitchcockAndBolz);
        Marker mHitchcockBolz = mMap.addMarker(new MarkerOptions().position(new LatLng(40.003883, -83.015700))
                .title("Hitchcock & Bolz Hall").draggable(false));

        PolygonOptions ohioStadium = new PolygonOptions().add(
                new LatLng(40.003381, -83.021311),
                new LatLng(40.003381, -83.018071),
                new LatLng(40.000340, -83.018071),
                new LatLng(40.000340, -83.021311)
        );
        Polygon ohioStadiumPolygon = mMap.addPolygon(ohioStadium);
        Marker mOhioStadium = mMap.addMarker(new MarkerOptions().position(new LatLng(40.003381, -83.021311))
                .title("Ohio Stadium").draggable(false));

        PolygonOptions caldwellLab = new PolygonOptions().add(
                new LatLng(40.002780, -83.015441),
                new LatLng(40.002780, -83.014540),
                new LatLng(40.001692, -83.014540),
                new LatLng(40.001692, -83.015441)
        );
        Polygon caldwellPolygon = mMap.addPolygon(caldwellLab);
        Marker mCaldwell = mMap.addMarker(new MarkerOptions().position(new LatLng(40.002780, -83.015441))
                .title("Caldwell Laboratory").draggable(false));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                int forumNumber = getForumNumber(latLng);
                if(forumNumber > 0) {
                    Log.d("Map", "Map clicked on " + forumNumber);
                    //jump to forum
                    Intent forumIntent = new Intent(MapActivity.this, ForumActivity.class);
                    mLocationManager.removeUpdates(mLocationListener);
//                    mLocationManager = null;
                    forumIntent.putExtra(getString(R.string.map_to_forum_intent_extra), forumNumber);
                    startActivity(forumIntent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
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

        if (isInArea(lat, lng, 40.001692, 40.002780, -83.015441, -83.001692)) {
            return 10;
        }//Caldwell Laboratory

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
            View rootView = inflater.inflate(R.layout.fragment_map, container, false);
            return rootView;
        }
    }

    public void searchLocation(View view) {
        EditText text = (EditText)findViewById(R.id.map_search);
        String searchString = text.getText().toString();

        Intent searchResultIntent = new Intent(MapActivity.this,ForumSearchActivity.class);
        searchResultIntent.putExtra(getString(R.string.forum_search_content),searchString);
        startActivity(searchResultIntent);
    }

    @Override

    public void onDestroy() {
        super.onDestroy();
        mLocationManager.removeUpdates(mLocationListener);
        mLocationManager = null;
    }
}
