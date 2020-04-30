package com.example.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecommerce.R;
import com.example.ecommerce.adapter.MyWishListAdapter;
import com.example.ecommerce.helper.CustomNavigationMethod;
import com.example.ecommerce.model.MyWishListModel;

import java.util.ArrayList;

public class MyWishListActivity extends AppCompatActivity {
    private RecyclerView myWishList_recyclerView;
    private ArrayList<MyWishListModel> modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list);
        myWishList_recyclerView = (RecyclerView)findViewById(R.id.myWishList_recyclerView);
        setWishListData();
        CustomNavigationMethod.toolbar_back(MyWishListActivity.this,"My WishList");
    }
    public void setWishListData() {
        modelArrayList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            MyWishListModel myWishListModel = new MyWishListModel();

            myWishListModel.setImage("https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg");
            myWishListModel.setName("GooGle Pixel XL2 (Mirror Black, 128 GB)");
            myWishListModel.setPrice("45,999/-");
            myWishListModel.setCutPrice("49,999/-");
            myWishListModel.setDeliveryMode("Cash On Delivery Available");
            myWishListModel.setAllRatings("(8,540)");
            myWishListModel.setRating("4.5 *");
            modelArrayList.add(myWishListModel);
        }
        MyWishListAdapter adapter = new MyWishListAdapter(modelArrayList, MyWishListActivity.this);
        myWishList_recyclerView.setAdapter(adapter);

    }
}
