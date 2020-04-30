package com.example.ecommerce.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.model.MyOrderListModel;
import com.example.ecommerce.model.MyRewardModel;

import java.util.ArrayList;

public class MyRewardListAdapter extends RecyclerView.Adapter<MyRewardListAdapter.ViewHolder> {

    ArrayList<MyRewardModel> listModels;
    Activity activity;

    public MyRewardListAdapter(ArrayList<MyRewardModel> listModels, Activity activity) {
        this.listModels = listModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_my_rewardlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tillDate.setText(listModels.get(position).getDate());
        holder.reward.setText(listModels.get(position).getReward());

    }

    @Override
    public int getItemCount() {
        return listModels == null ? 0 : listModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tillDate, reward;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tillDate = (TextView) itemView.findViewById(R.id.tillDate);
            reward = (TextView) itemView.findViewById(R.id.reward);


        }
    }
}
