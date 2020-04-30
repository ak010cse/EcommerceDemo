package com.example.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.R;
import com.example.ecommerce.adapter.DealsOfTheDayAdapter;
import com.example.ecommerce.adapter.SliderAdapter;
import com.example.ecommerce.adapter.TrendingProductAdapter;
import com.example.ecommerce.helper.CustomNavigationMethod;
import com.example.ecommerce.model.DealsOfTheModel;
import com.example.ecommerce.model.SliderModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView category_recyclerView;
    private ViewPager viewPager;
    ArrayList<SliderModel> sliderModelList;
    ///*****Banner slider******///
    TextView deals_text, trending_text;
    Button viewAll_deals_button, viewAll_button;
    RecyclerView dealsOfTheDay_recyclerView, trending_recyclerView;
    private ArrayList<DealsOfTheModel> dealsOfTheModelArrayList;
    SliderView imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        category_recyclerView=(RecyclerView)findViewById(R.id.category_recyclerView);

        String item=getIntent().getExtras().getString("productName");
        CustomNavigationMethod.toolbar_back(CategoryActivity.this,item);
        ///*****Banner slider******///
        viewPager = (ViewPager) findViewById(R.id.banner_slide_view_pager);
        imageSlider = (SliderView) findViewById(R.id.imageSlider);

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel("https://i.pcmag.com/imagery/reviews/0021S2Fa8eGumFir6rueMCH-22..v_1574731296.jpg"));
        sliderModelList.add(new SliderModel("https://www.anysoftwaretools.com/wp-content/uploads/iphone-powerbank-1.png"));
        sliderModelList.add(new SliderModel("https://i.ytimg.com/vi/xFe_ZYtfsZg/maxresdefault.jpg"));
        sliderModelList.add(new SliderModel("https://images-na.ssl-images-amazon.com/images/I/61oVUtl66XL._AC_SY445_.jpg"));
        sliderModelList.add(new SliderModel("https://www.anysoftwaretools.com/wp-content/uploads/iphone-powerbank-1.png"));

        sliderModelList.add(new SliderModel("https://images-na.ssl-images-amazon.com/images/I/61oVUtl66XL._AC_SY445_.jpg"));
        sliderModelList.add(new SliderModel("https://i.ytimg.com/vi/xFe_ZYtfsZg/maxresdefault.jpg"));
        sliderModelList.add(new SliderModel("https://i.pcmag.com/imagery/reviews/0021S2Fa8eGumFir6rueMCH-22..v_1574731296.jpg"));
        sliderModelList.add(new SliderModel("https://www.anysoftwaretools.com/wp-content/uploads/iphone-powerbank-1.png"));


        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList, this);
        imageSlider.setSliderAdapter(sliderAdapter);
        imageSlider.setClipToPadding(false);


        deals_text = (TextView)findViewById(R.id.deals_text);
        viewAll_deals_button = (Button) findViewById(R.id.viewAll_deals_button);
        dealsOfTheDay_recyclerView = (RecyclerView) findViewById(R.id.dealsOfTheDay_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        dealsOfTheDay_recyclerView.setLayoutManager(layoutManager);
        viewAll_deals_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this, "View All", Toast.LENGTH_SHORT).show();
            }
        });

        //**** trending product ****//
        trending_text = (TextView)findViewById(R.id.trending_text);
        viewAll_button = (Button) findViewById(R.id.viewAll_button);
        trending_recyclerView = (RecyclerView) findViewById(R.id.trending_recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this, 2);

        trending_recyclerView.setLayoutManager(manager);
        viewAll_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this, "View All", Toast.LENGTH_SHORT).show();
            }
        });
        setDataInDealsOfDay();
        setTrendingData();
        //**** trending product ****//

    }

    public void setDataInDealsOfDay() {
        dealsOfTheModelArrayList = new ArrayList<>();

        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
//        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000","https://i.ytimg.com/vi/xFe_ZYtfsZg/maxresdefault.jpg"));
//        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000","https://i.pcmag.com/imagery/reviews/0021S2Fa8eGumFir6rueMCH-22..v_1574731296.jpg"));
//            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_menu_camera));
//            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_shopping_cart_black_241dp));
//            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_menu_gallery));
//            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_menu_send));
//            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_order));


        dealsOfTheDay_recyclerView.setAdapter(new DealsOfTheDayAdapter(this, dealsOfTheModelArrayList));


    }
    public void setTrendingData() {
        dealsOfTheModelArrayList = new ArrayList<>();

        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
//        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
//        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
//        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
//        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000", "https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg"));
//        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000","https://i.ytimg.com/vi/xFe_ZYtfsZg/maxresdefault.jpg"));
//        dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000","https://i.pcmag.com/imagery/reviews/0021S2Fa8eGumFir6rueMCH-22..v_1574731296.jpg"));
//            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_menu_camera));
//            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_shopping_cart_black_241dp));
//            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_menu_gallery));
//            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_menu_send));
//            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_order));

        trending_recyclerView.setAdapter(new TrendingProductAdapter(this, dealsOfTheModelArrayList));

    }

}
