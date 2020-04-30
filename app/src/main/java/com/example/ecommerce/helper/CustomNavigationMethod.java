package com.example.ecommerce.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ecommerce.R;

public class CustomNavigationMethod {

    public static void blue_toolbar_back(final Activity activity, final String title) {
        LinearLayout toolbar_linearLayout = (LinearLayout) activity.findViewById(R.id.toolbar_linearLayout);
        TextView toolbar_text = (TextView) activity.findViewById(R.id.activityName);
        ImageView backImage = (ImageView) activity.findViewById(R.id.back_image);
        toolbar_linearLayout.setBackground(activity.getResources().getDrawable(R.color.blue_toolbar)/*.color.blue_toolbar*/);
        toolbar_text.setText(title);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });
    }

    public static void toolbar_back(final Activity activity, final String title) {
        LinearLayout toolbar_linearLayout = (LinearLayout) activity.findViewById(R.id.toolbar_linearLayout);
        TextView toolbar_text = (TextView) activity.findViewById(R.id.activityName);
        ImageView backImage = (ImageView) activity.findViewById(R.id.back_image);
//        toolbar_linearLayout.setBackground(activity.getResources().getDrawable(R.color.colorRed));
        toolbar_text.setText(title);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });
    }

    public static void serachView(final Activity activity){
        LinearLayout search_view_layout = (LinearLayout)activity.findViewById(R.id.search_view_layout);
    }
}
