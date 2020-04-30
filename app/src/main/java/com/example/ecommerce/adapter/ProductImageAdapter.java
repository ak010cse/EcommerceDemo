package com.example.ecommerce.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.model.SliderModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class ProductImageAdapter extends SliderViewAdapter<ProductImageAdapter.ViewHolder>{
    ArrayList<SliderModel> sliderModelList;
    Activity activity;

    public ProductImageAdapter(ArrayList<SliderModel> sliderModelList, Activity activity) {
        this.sliderModelList = sliderModelList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.product_images_slider_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Glide.with(activity).load(sliderModelList.get(position).getBanner()).into(viewHolder.slider_banner);

    }

    @Override
    public int getCount() {
        return sliderModelList == null ? 0 : sliderModelList.size();
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder{
        ImageView slider_banner;
        public ViewHolder(View itemView) {
            super(itemView);
            slider_banner = (ImageView) itemView.findViewById(R.id.slider_banner);
        }
    }
}
