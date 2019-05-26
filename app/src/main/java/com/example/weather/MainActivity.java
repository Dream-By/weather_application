package com.example.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OnFragmentInteractionListener {

    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initSideMenu(toolbar);
        initFragment();
    }

    private void initFragment() {
        mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.cityFragment, mainFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(String city) {
        SecondFragment fragment = new SecondFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(mainFragment);
        fragmentTransaction.add(R.id.resultFragment, fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();

        if (fragment.isInLayout()) {
            fragment.setCity(city);
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    SecondActivity.class);
            intent.putExtra(SecondActivity.CITY, city);
            startActivity(intent);
        }
    }

    private void initSideMenu(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_developer) {
            DeveloperFragment developerFragment = new DeveloperFragment();
            fragmentTransaction.remove(mainFragment);
            fragmentTransaction.add(R.id.cityFragment, developerFragment);
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_feedback) {
            FeedbackFragment feedbackFragment = new FeedbackFragment();
            fragmentTransaction.remove(mainFragment);
            fragmentTransaction.add(R.id.cityFragment, feedbackFragment);
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
