package com.example.weather;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {

    public static final String CITY = "city";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String city = extras.getString(CITY);
            SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.resultFragment);
            assert secondFragment != null;
            secondFragment.setCity(city);
        }
    }
}
