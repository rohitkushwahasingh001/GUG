package com.example.gug;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public Toolbar toolbar;
    private FragmentManager fragmentManager;private BottomNavigationView bottomNavigationView;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize FragmentManager
        fragmentManager = getSupportFragmentManager();
        //initialize bottom nav bar
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set up the Drawer Layout and Toolbar
        drawerLayout = findViewById(R.id.main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize ActionBarDrawerToggle before calling super.onCreate()
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replaceFragment(new Fragment_Home());
                return true;
            } else if (itemId == R.id.chats) {replaceFragment(new chat());
                return true;
            } else if (itemId == R.id.Settings) {
                replaceFragment(new Settings());
                return true;
            } else {
                return false;
            }
        });

        // Load the HomeFragment as the default fragment when the app starts
        if (savedInstanceState == null) {
            replaceFragment(new Fragment_Home());
            currentFragment = new Fragment_Home();// Load your HomeFragment
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
    }

    // Method to replace the current fragment with the selected one
    private void replaceFragment(Fragment fragment) {
        if (fragment != currentFragment) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            currentFragment = fragment;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            // Update currentFragment after popping back stack
            if (fragmentManager.getBackStackEntryCount() == 0) {
                // If back stack is empty, set currentFragment to HomeFragment
                currentFragment = new Fragment_Home();
                bottomNavigationView.setSelectedItemId(R.id.home); // Set the home item as selected
            } else {
                currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Inflate your menu XML

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle searchquery submission
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle search query text change
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}