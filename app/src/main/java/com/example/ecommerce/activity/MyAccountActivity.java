package com.example.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ecommerce.R;
import com.example.ecommerce.helper.CustomNavigationMethod;

public class MyAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        CustomNavigationMethod.toolbar_back(MyAccountActivity.this,"My Account");
    }
}
