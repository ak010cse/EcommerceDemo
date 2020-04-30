package com.example.ecommerce.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.model.MyCartModel;

import java.util.ArrayList;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    ArrayList<MyCartModel> modelArrayList;
    Activity activity;

    public MyCartAdapter(ArrayList<MyCartModel> modelArrayList, Activity activity) {
        this.modelArrayList = modelArrayList;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case MyCartModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_my_cart, parent, false);
                return new ViewHolder(cartItemView);
            case MyCartModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout, parent, false);
                return new ViewHolder(cartTotalView);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (modelArrayList.get(position).getType()){
            case MyCartModel.CART_ITEM:
                holder.price.setText(modelArrayList.get(position).getPrice());
                holder.cutPrice.setText(modelArrayList.get(position).getCutPrice());
                holder.name.setText(modelArrayList.get(position).getName());

                Glide.with(activity).load(modelArrayList.get(position).getImage()).into(holder.imageView);
                holder.delete_items.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, "deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            case MyCartModel.TOTAL_AMOUNT:
                default:return;
        }


    }

    @Override
    public int getItemViewType(int position) {

        switch (modelArrayList.get(position).getType()) {
            case 0:
                return MyCartModel.CART_ITEM;
            case 1:
                return MyCartModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return modelArrayList == null ? 0 : modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, free_coupens_icon;
        TextView name, price, cutPrice, free_coupens, coupens_applied, offer_applied, no_of_quantity;
        LinearLayout delete_items;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            free_coupens_icon = (ImageView) itemView.findViewById(R.id.free_coupens_icon);
            name = (TextView) itemView.findViewById(R.id.name);
            cutPrice = (TextView) itemView.findViewById(R.id.cut_price);
            price = (TextView) itemView.findViewById(R.id.price);
            free_coupens = (TextView) itemView.findViewById(R.id.free_coupens);
            offer_applied = (TextView) itemView.findViewById(R.id.offer_applied);
            coupens_applied = (TextView) itemView.findViewById(R.id.coupens_applied);
            no_of_quantity = (TextView) itemView.findViewById(R.id.no_of_quantity);
            delete_items = (LinearLayout) itemView.findViewById(R.id.delete_items);
        }

        public void setItemDetails(String img, String title, int freeCoupens, String productPrice, String cutedProductPrice, int offerAppliedNo, int coupansApplied) {

            name.setText(title);
            if (freeCoupens > 0) {
                free_coupens.setVisibility(View.VISIBLE);
                free_coupens_icon.setVisibility(View.VISIBLE);
                if (freeCoupens == 1) {
                    free_coupens.setText("free " + freeCoupens + " Coupen");

                } else {
                    free_coupens.setText("free " + freeCoupens + " Coupens");

                }
            } else {
                free_coupens.setVisibility(View.INVISIBLE);
                free_coupens_icon.setVisibility(View.INVISIBLE);
            }

            price.setText(productPrice);
            cutPrice.setText(cutedProductPrice);

            if (offerAppliedNo > 0) {

                offer_applied.setVisibility(View.VISIBLE);
                offer_applied.setText(offerAppliedNo + "offers applied");
            } else {
                offer_applied.setVisibility(View.INVISIBLE);

            }

            if (coupansApplied > 0) {

                coupens_applied.setVisibility(View.VISIBLE);
                coupens_applied.setText(coupansApplied + "coupens applied");
            } else {
                coupens_applied.setVisibility(View.INVISIBLE);

            }
        }
    }

     class CartTotalAmountViewHolder extends RecyclerView.ViewHolder {
        TextView totalItems, totalItemsPrice, deliveryPrice,totalAmount,savedAmount;

        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);

            totalItems = (TextView) itemView.findViewById(R.id.total_items);
            totalItemsPrice = (TextView) itemView.findViewById(R.id.totalItemsPrice);
            deliveryPrice = (TextView) itemView.findViewById(R.id.delivery_price);
            totalAmount = (TextView) itemView.findViewById(R.id.total_price);
            savedAmount = (TextView) itemView.findViewById(R.id.saved_price);
        }
    }
}
