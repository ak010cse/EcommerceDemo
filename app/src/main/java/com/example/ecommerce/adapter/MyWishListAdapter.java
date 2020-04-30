package com.example.ecommerce.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.model.MyWishListModel;

import java.util.ArrayList;

public class MyWishListAdapter extends RecyclerView.Adapter<MyWishListAdapter.ViewHolder> {

    ArrayList<MyWishListModel> listModels;
    Activity activity;

    public MyWishListAdapter(ArrayList<MyWishListModel> listModels, Activity activity) {
        this.listModels = listModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_my_wishlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(listModels.get(position).getName());
        holder.rating.setText(listModels.get(position).getRating());
        holder.allRatings.setText(listModels.get(position).getAllRatings());
        holder.price.setText(listModels.get(position).getPrice());
        holder.cutPrice.setText(listModels.get(position).getCutPrice());
        holder.deliveryMode.setText(listModels.get(position).getDeliveryMode());

        Glide.with(activity).load(listModels.get(position).getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listModels == null ? 0 : listModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView, delete_items;
        TextView name, rating, allRatings, price, cutPrice, deliveryMode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            rating = (TextView) itemView.findViewById(R.id.rating);
            allRatings = (TextView) itemView.findViewById(R.id.allRating);
            price = (TextView) itemView.findViewById(R.id.price);
            cutPrice = (TextView) itemView.findViewById(R.id.cutPrice);
            deliveryMode = (TextView) itemView.findViewById(R.id.deliveryMode);

            imageView = (ImageView) itemView.findViewById(R.id.pd_image);
            delete_items = (ImageView) itemView.findViewById(R.id.delete_items);
        }
    }
}
