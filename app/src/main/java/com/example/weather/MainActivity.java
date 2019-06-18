package com.example.weather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements MainNavigator {

    private static final int PERMISSION_REQUEST_CODE = 10;
    private CityPreference cityPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityPreference = new CityPreference(this);
        if (savedInstanceState == null)
            initFragment();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocation();
        } else {
            requestLocationPermissions();
        }
    }

    private void initFragment() {
        SearchFragment searchFragment = new SearchFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main, searchFragment);
        fragmentTransaction.commit();
    }

    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        String provider = locationManager.getBestProvider(criteria, true);
        if (provider != null) {
            locationManager.requestLocationUpdates(provider, 10000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    String latitude = Double.toString(location.getLatitude());
                    String longitude = Double.toString(location.getLongitude());
                    startResultFragmentFromCoordinates(latitude, longitude);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                }
            });
        }
    }

    private void requestLocationPermissions() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == 2 &&
                    (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                requestLocation();
            }
        }
    }

    @Override
    public void startResultFragment(String city) {
        ResultFragment resultFragment = new ResultFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main, resultFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        resultFragment.requestRetrofit(city);
        cityPreference.setCity(city);

    }

    @Override
    public void startResultFragmentFromList(String city, String lat, String lon) {
        ResultFragment resultFragment = new ResultFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main, resultFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        resultFragment.requestRetrofitCoordinates(lat, lon);
        cityPreference.setCity(city);
    }

    public void startResultFragmentFromCoordinates(String lat, String lon) {
        ResultFragment resultFragment = new ResultFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main, resultFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        resultFragment.requestRetrofitCoordinates(lat, lon);
    }
}

