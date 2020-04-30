package com.example.ecommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;
import com.example.ecommerce.model.ProductSpecificationModel;

import java.util.ArrayList;


public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder> {
    private ArrayList<ProductSpecificationModel> modelArrayList;
    private Activity activity;

    public ProductSpecificationAdapter(ArrayList<ProductSpecificationModel> modelArrayList, Activity activity) {
        this.modelArrayList = modelArrayList;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        switch (modelArrayList.get(position).getType()) {
            case 0:
                return ProductSpecificationModel.SPECIFICATION_TITLE;
            case 1:
                return ProductSpecificationModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ProductSpecificationModel.SPECIFICATION_TITLE:
                TextView title = new TextView(parent.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setDp(16, parent.getContext()),
                        setDp(16, parent.getContext()),
                        setDp(16, parent.getContext()),
                        setDp(8, parent.getContext()));

                title.setLayoutParams(layoutParams);
                return new ViewHolder(title);

            case ProductSpecificationModel.SPECIFICATION_BODY:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_specification_items_layout, parent, false);
                return new ViewHolder(view);
            default:
                return null;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.feature_name.setText(modelArrayList.get(position).getFeatureName());
//        holder.feature_value.setText(modelArrayList.get(position).getFeatureValue());

        switch (modelArrayList.get(position).getType()){
            case ProductSpecificationModel.SPECIFICATION_TITLE:
                holder.setTitle(modelArrayList.get(position).getTitle());

            case ProductSpecificationModel.SPECIFICATION_BODY:
                String featureTitle=modelArrayList.get(position).getFeatureName();
                String featureDetails=modelArrayList.get(position).getFeatureValue();
                holder.setFeaturedDetails(featureTitle,featureDetails);
                break;

                default: return;
        }

    }

    @Override
    public int getItemCount() {
        return modelArrayList == null ? 0 : modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView feature_name, feature_value;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }

        private void setTitle(String titleText){
            title= (TextView) itemView;
            title.setText(titleText);
        }
        private void setFeaturedDetails(String featureTitle , String featureDetails){
            feature_name = (TextView) itemView.findViewById(R.id.feature_name);
            feature_value = (TextView) itemView.findViewById(R.id.feature_value);
            if (featureTitle!=null && featureDetails!=null){
                feature_name.setText(featureTitle);
                feature_value.setText(featureDetails);
            }

        }
    }

    private int setDp(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
