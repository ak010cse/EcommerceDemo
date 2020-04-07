package com.example.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecommerce.R;
import com.example.ecommerce.fragment.HomeFragment;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.ecommerce.fragment.MyCartFragment;
import com.example.ecommerce.fragment.OrderFragment;
import com.example.ecommerce.fragment.RewardFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private FrameLayout main_frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        firebaseAuth = FirebaseAuth.getInstance();
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        main_frameLayout=(FrameLayout)findViewById(R.id.main_frameLayout);
        setFragment(new HomeFragment());


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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(this, "you clicked on search bar", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_cart) {
            Toast.makeText(this, "you clicked on Add to cart", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_notification) {
            Toast.makeText(this, "you clicked on notification", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_my_home) {
            Toast.makeText(this, "you clicked on Home", Toast.LENGTH_SHORT).show();
            setFragment(new HomeFragment());
        } else if (id == R.id.nav_my_order) {
            Toast.makeText(this, "you clicked on My Orders", Toast.LENGTH_SHORT).show();
            // Handle the camera action
            setFragment(new OrderFragment());

        } else if (id == R.id.nav_my_rewards) {
            Toast.makeText(this, "you clicked on My Rewards", Toast.LENGTH_SHORT).show();
            setFragment(new RewardFragment());
        } else if (id == R.id.nav_my_cart) {

            setFragment(new MyCartFragment());

            Toast.makeText(this, "you clicked on My Cart", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_my_wishlist) {
            Toast.makeText(this, "you clicked on My Wishlist", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_my_account) {
            Toast.makeText(this, "you clicked on My Account", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_signOut) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Fragment fragment){

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frameLayout.getId(),fragment);
        transaction.commit();

    }


}
