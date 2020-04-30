package com.example.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecommerce.R;
import com.example.ecommerce.fragment.HomeFragment;
import com.example.ecommerce.fragment.MyCartFragment;
import com.example.ecommerce.helper.CustomNavigationMethod;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private FrameLayout main_frameLayout;

    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;

    private static int currentFragment;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        firebaseAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        main_frameLayout = (FrameLayout) findViewById(R.id.main_frameLayout);
        setFragment(new HomeFragment(), HOME_FRAGMENT);


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
        if (currentFragment == HOME_FRAGMENT) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(this, "You clicked on search bar", Toast.LENGTH_SHORT).show();
            CustomNavigationMethod.serachView(MainActivity.this);
            return true;
        } else if (id == R.id.action_cart) {
//            Toast.makeText(this, "You clicked on Add to cart", Toast.LENGTH_SHORT).show();

            myCart();
            return true;
        } else if (id == R.id.action_notification) {
            Toast.makeText(this, "You clicked on notification", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void myCart() {
        invalidateOptionsMenu();
        setFragment(new MyCartFragment(), CART_FRAGMENT);
        navigationView.getMenu().getItem(3).setChecked(true);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_my_home) {
//            Toast.makeText(this, "You clicked on Home", Toast.LENGTH_SHORT).show();
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        } else if (id == R.id.nav_my_order) {
//            Toast.makeText(this, "You clicked on My Orders", Toast.LENGTH_SHORT).show();
//            setFragment(new OrderFragment());
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_my_rewards) {
//            Toast.makeText(this, "You clicked on My Rewards", Toast.LENGTH_SHORT).show();
//            setFragment(new RewardFragment());

            Intent intent = new Intent(MainActivity.this, RewardActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_my_cart) {
            /*Intent intent = new Intent(MainActivity.this, MyCartActivity.class);
            startActivity(intent);*/

            myCart();
        } else if (id == R.id.nav_my_wishlist) {
            Intent intent = new Intent(MainActivity.this, MyWishListActivity.class);
            startActivity(intent);
//            setFragment(new MyWishListFragment());
//            Toast.makeText(this, "You clicked on My Wishlist", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_my_account) {
//            setFragment(new MyAccountFragment());
            Intent intent = new Intent(MainActivity.this, MyAccountActivity.class);
            startActivity(intent);
//            Toast.makeText(this, "You clicked on My Account", Toast.LENGTH_SHORT).show();
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

    public void setFragment(Fragment fragment, int fragmentNo) {
        currentFragment = fragmentNo;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frameLayout.getId(), fragment);
        transaction.commit();

    }


}
