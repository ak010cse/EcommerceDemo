package com.example.ecommerce.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.activity.ProductDetailsActivity;
import com.example.ecommerce.model.DealsOfTheModel;

import java.util.ArrayList;

public class DealsOfTheDayAdapter extends RecyclerView.Adapter<DealsOfTheDayAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<DealsOfTheModel> dealsOfTheModels;

    public DealsOfTheDayAdapter(Activity activity, ArrayList<DealsOfTheModel> dealsOfTheModels) {
        this.activity = activity;
        this.dealsOfTheModels = dealsOfTheModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_deals_of_the_day,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.deal_price.setText(dealsOfTheModels.get(position).getPrice());
        holder.deal_type.setText(dealsOfTheModels.get(position).getType());
        holder.deal_name.setText(dealsOfTheModels.get(position).getName());

        Glide.with(activity).load(dealsOfTheModels.get(position).getImage()).into(holder.deal_image);

        holder.product_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, ProductDetailsActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dealsOfTheModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView deal_image;
        TextView deal_name,deal_type,deal_price;
        LinearLayout product_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deal_image=(ImageView)itemView.findViewById(R.id.deals_image);
            deal_name=(TextView)itemView.findViewById(R.id.deal_name);
            deal_type=(TextView)itemView.findViewById(R.id.deal_type);
            deal_price=(TextView)itemView.findViewById(R.id.deal_price);
            product_layout=(LinearLayout) itemView.findViewById(R.id.product_layout);
        }
    }
}
