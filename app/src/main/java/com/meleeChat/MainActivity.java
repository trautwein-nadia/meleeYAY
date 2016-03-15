package com.meleeChat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.meleeChat.info.SecureRandomString;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MAIN_ACTIVITY";
    private double prevAcc = (double) 1e10;
    private double lat;
    private double lon;
    private long prevAccTime = 0;
    Location prevLoc;


    private boolean getLocationPermission(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        return settings.getBoolean("location_allowed", false);
    }

    private void setLocationPermission(boolean b) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("location_allowed", b);
        editor.commit();
    }

    public void TOLogin(View v) {
        if (getLocationPermission()) {
            //if location accuracy is good?//
            Intent intent = new Intent(this, TOLogin.class);

            Bundle b = new Bundle();
            b.putFloat("LAT", (float) lat);
            b.putFloat("LON", (float) lon);
            intent.putExtras(b);

            startActivity(intent);
        }
        else {
            //make a toast saying wait for location
        }
    }

    public void playerLogin(View v) {
        //check for stuff later
        Intent intent = new Intent(this, PlayerLogin.class);

        Bundle b = new Bundle();
        b.putFloat("LAT", (float) lat);
        b.putFloat("LON", (float) lon);
        intent.putExtras(b);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String user_id;
        // Gets the settings, and creates a random user id if missing.
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        user_id = settings.getString("user_id", null);
        if (user_id == null) {
            // Creates a random one, and sets it.
            SecureRandomString srs = new SecureRandomString();
            user_id = srs.nextString();
            SharedPreferences.Editor e = settings.edit();
            e.putString("user_id", user_id);
            e.commit();
        }
    }

    @Override
    protected  void onResume() {
        setLocationPermission(false);
        try {
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        catch (SecurityException e) {

        }
        Button t = (Button) findViewById(R.id.toLogin);
        Button p = (Button) findViewById(R.id.playerLogin);
        t.setEnabled(false);
        p.setEnabled(false);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stops the location updates.
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.removeUpdates(locationListener);
        }
        catch (SecurityException e) {

        }

    }


    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location loc) {
            double newAcc = loc.getAccuracy();
            long newTime = loc.getTime();
            lat = loc.getLatitude();
            lon = loc.getLongitude();
            Log.i(LOG_TAG, "prevLoc: " + prevLoc + "\tprevAcc: " + prevAcc + "\tprevAccTime: " + prevAccTime);
            //Log.i(LOG_TAG, "Accuracy is " + newAcc);

            boolean isBetter = ((prevLoc == null) ||
                    newAcc < prevAcc + (newTime - prevAccTime));

            if (isBetter) {
                prevLoc = loc;
                prevAcc = loc.getAccuracy();
                prevAccTime = loc.getTime();
            }

            if (newAcc <= 50) {
                setLocationPermission(true);
            } else {
                setLocationPermission(false);
            }
            Button t = (Button) findViewById(R.id.toLogin);
            Button p = (Button) findViewById(R.id.playerLogin);
            if (getLocationPermission()) {
                t.setEnabled(true);
                p.setEnabled(true);
            } else {
                t.setEnabled(false);
                p.setEnabled(false);
            }

        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    };

}