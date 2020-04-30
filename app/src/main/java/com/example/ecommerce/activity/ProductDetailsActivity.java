package com.example.ecommerce.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ecommerce.R;
import com.example.ecommerce.adapter.ProductDetailsAdapter;
import com.example.ecommerce.adapter.ProductImageAdapter;
import com.example.ecommerce.helper.CustomNavigationMethod;
import com.example.ecommerce.model.SliderModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

    private FloatingActionButton floating_action_button;
    private SliderView imageSlider;
    ArrayList<SliderModel> sliderModelList;

    private static boolean already_addedToWishList = false;
    private TabLayout productDetailsTabLayout;
    private ViewPager productDetailsViewPager;

    private LinearLayout rateNowContainer;
    private LinearLayout add_to_cart_button;
    private Button buy_now_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        CustomNavigationMethod.toolbar_back(ProductDetailsActivity.this, "");
        floating_action_button = (FloatingActionButton) findViewById(R.id.floating_action_button);
        imageSlider = (SliderView) findViewById(R.id.imageSlider);

        productDetailsTabLayout = (TabLayout) findViewById(R.id.product_details_tabLayout);
        productDetailsViewPager = (ViewPager) findViewById(R.id.product_details_viewpager);


        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (already_addedToWishList) {
                    already_addedToWishList = false;
                    floating_action_button.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                } else {
                    already_addedToWishList = true;
                    floating_action_button.setSupportImageTintList(getResources().getColorStateList(R.color.colorRed));

                }

            }
        });
        setImageSliderData();


        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount()));
        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //rating now//

        rateNowContainer = (LinearLayout) findViewById(R.id.rate_now_container);

        for (int i = 0; i < rateNowContainer.getChildCount(); i++) {
            final int starPosition=i;
            rateNowContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }
        //rating now //
    }
private void setRating(int starPosition){
        for (int i=0;i<rateNowContainer.getChildCount();i++){
            ImageView starBtn=(ImageView)rateNowContainer.getChildAt(i);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));

            if (i<=starPosition){
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
}

    public void setImageSliderData() {
        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel("https://i.pcmag.com/imagery/reviews/0021S2Fa8eGumFir6rueMCH-22..v_1574731296.jpg"));
        sliderModelList.add(new SliderModel("https://www.anysoftwaretools.com/wp-content/uploads/iphone-powerbank-1.png"));
        sliderModelList.add(new SliderModel("https://i.ytimg.com/vi/xFe_ZYtfsZg/maxresdefault.jpg"));
        sliderModelList.add(new SliderModel("https://images-na.ssl-images-amazon.com/images/I/61oVUtl66XL._AC_SY445_.jpg"));
        sliderModelList.add(new SliderModel("https://www.anysoftwaretools.com/wp-content/uploads/iphone-powerbank-1.png"));

//        sliderModelList.add(new SliderModel("https://images-na.ssl-images-amazon.com/images/I/61oVUtl66XL._AC_SY445_.jpg"));
//        sliderModelList.add(new SliderModel("https://i.ytimg.com/vi/xFe_ZYtfsZg/maxresdefault.jpg"));
//        sliderModelList.add(new SliderModel("https://i.pcmag.com/imagery/reviews/0021S2Fa8eGumFir6rueMCH-22..v_1574731296.jpg"));
//        sliderModelList.add(new SliderModel("https://www.anysoftwaretools.com/wp-content/uploads/iphone-powerbank-1.png"));

        ProductImageAdapter sliderAdapter = new ProductImageAdapter(sliderModelList, ProductDetailsActivity.this);
        imageSlider.setSliderAdapter(sliderAdapter);
        imageSlider.setClipToPadding(false);

    }
}
