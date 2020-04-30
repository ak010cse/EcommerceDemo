package com.example.ecommerce.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.ecommerce.R;
import com.example.ecommerce.adapter.CategoryAdapter;
import com.example.ecommerce.adapter.DealsOfTheDayAdapter;
import com.example.ecommerce.adapter.SliderAdapter;
import com.example.ecommerce.adapter.TrendingProductAdapter;
import com.example.ecommerce.model.CategoryModel;
import com.example.ecommerce.model.DealsOfTheModel;
import com.example.ecommerce.model.SliderModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView category_recyclerView;
    private CategoryAdapter categoryAdapter;

    ///*****Banner slider******///
    private ViewPager viewPager;
    ArrayList<SliderModel> sliderModelList;
    ///*****Banner slider******///
    TextView deals_text, trending_text;
    Button viewAll_deals_button, viewAll_button;
    RecyclerView dealsOfTheDay_recyclerView, trending_recyclerView;
    private ArrayList<DealsOfTheModel> dealsOfTheModelArrayList;
    SliderView imageSlider;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        category_recyclerView = (RecyclerView) view.findViewById(R.id.category_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        category_recyclerView.setLayoutManager(linearLayoutManager);

        List<CategoryModel> modelList = new ArrayList<CategoryModel>();
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-vector/home-icon-260nw-153874403.jpg", "Home"));
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-vector/circuit-tech-element-electronics-icon-600w-622200185.jpg", "Electronics"));
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-vector/vector-illustration-book-icon-isolated-600w-252563749.jpg", "Books"));
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-vector/single-sneaker-sport-shoe-icon-260nw-686003236.jpg", "Shoes"));
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-illustration/blank-white-clean-tshirt-mockup-600w-1488735947.jpg", "T-shirt"));
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-vector/mobile-phone-vector-icon-isolated-600w-1156131226.jpg", "Mobile"));
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-vector/charging-phone-icon-flat-style-600w-1521755333.jpg", "Charger"));
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-vector/power-bank-icon-simple-design-600w-1105162760.jpg", "Power bank"));
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-vector/black-headphone-vector-icon-illustration-600w-1578059326.jpg", "Headphone"));
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-vector/computer-mouse-icon-vector-600w-195740618.jpg", "Mouse"));
        modelList.add(new CategoryModel("https://image.shutterstock.com/image-vector/soccer-ball-symbol-style-grunge-600w-293090687.jpg", "Sports"));
        categoryAdapter = new CategoryAdapter(getActivity(), modelList);
        category_recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        ///*****Banner slider******///
        viewPager = (ViewPager) view.findViewById(R.id.banner_slide_view_pager);
        imageSlider = (SliderView) view.findViewById(R.id.imageSlider);

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


        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList, getActivity());
        imageSlider.setSliderAdapter(sliderAdapter);
        imageSlider.setClipToPadding(false);


        deals_text = (TextView) view.findViewById(R.id.deals_text);
        viewAll_deals_button = (Button) view.findViewById(R.id.viewAll_deals_button);
        dealsOfTheDay_recyclerView = (RecyclerView) view.findViewById(R.id.dealsOfTheDay_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        dealsOfTheDay_recyclerView.setLayoutManager(layoutManager);
        viewAll_deals_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "View All", Toast.LENGTH_SHORT).show();
            }
        });

        //**** trending product ****//
        trending_text = (TextView) view.findViewById(R.id.trending_text);
        viewAll_button = (Button) view.findViewById(R.id.viewAll_button);
        trending_recyclerView = (RecyclerView) view.findViewById(R.id.trending_recyclerView);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);

        trending_recyclerView.setLayoutManager(manager);
        viewAll_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "View All", Toast.LENGTH_SHORT).show();
            }
        });
        setDataInDealsOfDay();
        setTrendingData();
        //**** trending product ****//


        return view;
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


        dealsOfTheDay_recyclerView.setAdapter(new DealsOfTheDayAdapter(getActivity(), dealsOfTheModelArrayList));

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

        trending_recyclerView.setAdapter(new TrendingProductAdapter(getActivity(), dealsOfTheModelArrayList));

    }



}
