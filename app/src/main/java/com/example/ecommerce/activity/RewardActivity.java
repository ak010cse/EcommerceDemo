package com.example.ecommerce.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;
import com.example.ecommerce.adapter.MyRewardListAdapter;
import com.example.ecommerce.helper.CustomNavigationMethod;
import com.example.ecommerce.model.MyRewardModel;

import java.util.ArrayList;

public class RewardActivity extends AppCompatActivity {
    private RecyclerView myReward_recyclerView;
    private ArrayList<MyRewardModel> modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        CustomNavigationMethod.blue_toolbar_back(RewardActivity.this,"My Rewards");

        myReward_recyclerView = (RecyclerView) findViewById(R.id.myReward_recyclerView);
        setOrderListData();
    }

    public void setOrderListData() {
        modelArrayList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            MyRewardModel rewardModel = new MyRewardModel();

            rewardModel.setDate(" till 3rd, August 2018");
            rewardModel.setReward("GET 20% OFF on any product above Rs.500/- and below Rs.2500/-");

            modelArrayList.add(rewardModel);
        }
        MyRewardListAdapter adapter = new MyRewardListAdapter(modelArrayList, RewardActivity.this);
        myReward_recyclerView.setAdapter(adapter);

    }
}
