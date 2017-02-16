package com.example.jiaw2.mysecondapplication.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.base.BaseActivity;
import com.example.jiaw2.mysecondapplication.util.UIHelper;

import java.util.Iterator;

/**
 * Created by jiaw2 on 2017/1/10.
 */
public class AndroidLocationActivity extends BaseActivity {

    private LocationManager locationManager;
    private GpsStatus gpsStatus;
    /* GPS Constant Permission */
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;

    /* Position */
    private static final int MINIMUM_TIME = 5000;  // 5s
    private static final int MINIMUM_DISTANCE = 50; // 50m

    @Override
    public void initValue(Bundle bundle) {

    }

    @Override
    public void initView(View view) {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);

        // API 23: we have to check if ACCESS_FINE_LOCATION and/or ACCESS_COARSE_LOCATION permission are granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // No one provider activated: prompt GPS
            if (provider == null || provider.equals("")) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }

            // At least one provider activated. Get the coordinates
            switch (provider) {
                case "passive":
                    locationManager.requestLocationUpdates(provider, MINIMUM_TIME, MINIMUM_DISTANCE, locationListener);
                    Location location = locationManager.getLastKnownLocation(provider);
                    updateWithNewLocation("passive", location);
                    break;
                case "network":
                    locationManager.requestLocationUpdates(provider, MINIMUM_TIME, MINIMUM_DISTANCE, locationListener);
                    location = locationManager.getLastKnownLocation(provider);
                    updateWithNewLocation("network", location);
                    break;
                case "gps":
                    locationManager.requestLocationUpdates(provider, MINIMUM_TIME, MINIMUM_DISTANCE, locationListener);
                    location = locationManager.getLastKnownLocation(provider);
                    updateWithNewLocation("gps", location);
                    break;
            }

            // One or both permissions are denied.
        } else {

            // The ACCESS_COARSE_LOCATION is denied, then I request it and manage the result in
            // onRequestPermissionsResult() using the constant MY_PERMISSION_ACCESS_FINE_LOCATION
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSION_ACCESS_COARSE_LOCATION);
            }
            // The ACCESS_FINE_LOCATION is denied, then I request it and manage the result in
            // onRequestPermissionsResult() using the constant MY_PERMISSION_ACCESS_FINE_LOCATION
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_ACCESS_FINE_LOCATION);
            }
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public int bindLayout() {
        return R.layout.android_location_layout;
    }

    @Override
    public void onClick(View v) {

    }

    private void updateWithNewLocation(String s, Location location) {
        if (location != null) {
            float speed = location.getSpeed();
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            UIHelper.ToastMessage(getActivity(), "provide==" + s + ",speed=" + speed + ",lat=" + lat + ",lng=" + lng);
        } else {
            UIHelper.ToastMessage(getActivity(), "location is null");
        }
    }


    private GpsStatus.Listener gpsListener = new GpsStatus.Listener() {
        @Override
        public void onGpsStatusChanged(int event) {
            gpsStatus = locationManager.getGpsStatus(null);
            switch (event) {
                //第一次定位时的事件
                case GpsStatus.GPS_EVENT_FIRST_FIX:
                    break;
                //开始定位的事件
                case GpsStatus.GPS_EVENT_STARTED:
                    break;
                //发送GPS卫星状态事件
                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                    Toast.makeText(getActivity(), "GPS_EVENT_SATELLITE_STATUS", Toast.LENGTH_SHORT).show();
                    Iterable<GpsSatellite> allSatellites = gpsStatus.getSatellites();
                    Iterator<GpsSatellite> it = allSatellites.iterator();
                    int count = 0;
                    while (it.hasNext()) {
                        count++;
                    }
                    Toast.makeText(getActivity(), "Satellite Count:" + count, Toast.LENGTH_SHORT).show();
                    break;
                //停止定位事件
                case GpsStatus.GPS_EVENT_STOPPED:
                    Log.d("Location", "GPS_EVENT_STOPPED");
                    break;
            }
        }
    };

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.e("Location", "onLocationChanged");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e("Location", "onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e("Location", "onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e("Location", "onProviderDisabled");
        }
    };

    // 計算兩點距離
    private final double EARTH_RADIUS = 6378137.0;
    private double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
        double radLat1 = (lat_a * Math.PI / 180.0);
        double radLat2 = (lat_b * Math.PI / 180.0);
        double a = radLat1 - radLat2;
        double b = (lng_a - lng_b) * Math.PI / 180.0;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
}
