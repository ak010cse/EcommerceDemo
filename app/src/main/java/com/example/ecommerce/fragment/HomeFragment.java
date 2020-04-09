package com.example.ecommerce.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.adapter.DealsOfTheDayAdapter;
import com.example.ecommerce.adapter.SliderAdapter;
import com.example.ecommerce.model.CategoryModel;
import com.example.ecommerce.R;
import com.example.ecommerce.adapter.CategoryAdapter;
import com.example.ecommerce.model.DealsOfTheModel;
import com.example.ecommerce.model.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView category_recyclerView;
    private CategoryAdapter categoryAdapter;

    ///*****Banner slider******///
    private ViewPager viewPager;
    List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;

    ///*****Banner slider******///
    TextView deals_text;
    Button viewAll_deals_button;
    RecyclerView dealsOfTheDay_recyclerView;
    private ArrayList<DealsOfTheModel> dealsOfTheModelArrayList;

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

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.ic_shopping_cart_black_241dp));
        sliderModelList.add(new SliderModel(R.drawable.ic_wishlist));

        sliderModelList.add(new SliderModel(R.drawable.ic_mailsent));
        sliderModelList.add(new SliderModel(R.drawable.ic_mail));
        sliderModelList.add(new SliderModel(R.drawable.forget_img));
        sliderModelList.add(new SliderModel(R.drawable.ic_menu_gallery));
        sliderModelList.add(new SliderModel(R.drawable.ic_menu_slideshow));
        sliderModelList.add(new SliderModel(R.drawable.ic_menu_manage));

        sliderModelList.add(new SliderModel(R.drawable.ic_person_outline_black_24dp));
        sliderModelList.add(new SliderModel(R.drawable.ic_menu_camera));
        sliderModelList.add(new SliderModel(R.drawable.ic_shopping_cart_black_241dp));
        sliderModelList.add(new SliderModel(R.drawable.ic_gift));

        sliderModelList.add(new SliderModel(R.drawable.ic_mailsent));
        sliderModelList.add(new SliderModel(R.drawable.ic_mail));

        viewPager = (ViewPager) view.findViewById(R.id.banner_slide_view_pager);
        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(20);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    pageLooper();

                }
            }
        };

        viewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startBannerSlideShow();
                }
                return false;
            }
        });
        ///*****Banner slider******///

        deals_text = (TextView) view.findViewById(R.id.deals_text);
        viewAll_deals_button = (Button) view.findViewById(R.id.viewAll_deals_button);
        dealsOfTheDay_recyclerView = (RecyclerView) view.findViewById(R.id.dealsOfTheDay_recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        dealsOfTheDay_recyclerView.setLayoutManager(layoutManager);
        viewAll_deals_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "View All", Toast.LENGTH_SHORT).show();
            }
        });
        setDataInDealsOfDay();

        return view;
    }

    public void setDataInDealsOfDay() {
        dealsOfTheModelArrayList = new ArrayList<>();

            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_mail));
            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_person_outline_black_24dp));
            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_close_black_24dp));
            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_gift));
            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_menu_camera));
            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_shopping_cart_black_241dp));
            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_menu_gallery));
            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_menu_send));
            dealsOfTheModelArrayList.add(new DealsOfTheModel("SamSung", "J2 PRO", "6000",R.drawable.ic_order));


        dealsOfTheDay_recyclerView.setAdapter(new DealsOfTheDayAdapter(getActivity(), dealsOfTheModelArrayList));

    }

    ///*****Banner slider******///
    private void pageLooper() {
        if (currentPage == sliderModelList.size() - 2) {
            currentPage = 2;
            viewPager.setCurrentItem(currentPage, false);

        }
        if (currentPage == 1) {
            currentPage = sliderModelList.size() - 3;
            viewPager.setCurrentItem(currentPage, false);

        }
    }

    private void startBannerSlideShow() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {

                if (currentPage >= sliderModelList.size()) {
                    currentPage = 1;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSlideShow() {
        timer.cancel();
    }

    ///*****Banner slider******///

}
