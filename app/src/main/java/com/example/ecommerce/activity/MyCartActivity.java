package com.example.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecommerce.R;
import com.example.ecommerce.adapter.MyCartAdapter;
import com.example.ecommerce.helper.CustomNavigationMethod;
import com.example.ecommerce.model.MyCartModel;

import java.util.ArrayList;

public class MyCartActivity extends AppCompatActivity {
    RecyclerView myCart_recyclerView;
    ArrayList<MyCartModel> modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        CustomNavigationMethod.toolbar_back(MyCartActivity.this,"MyCart List");

        myCart_recyclerView=(RecyclerView)findViewById(R.id.myCart_recyclerView);
        setWishListData();

    }
    public void setWishListData() {
        modelArrayList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
           /* MyCartModel myWishListModel = new MyCartModel();

            myWishListModel.setImage("https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg");
            myWishListModel.setName("GooGle Pixel XL2 (Mirror Black, 128 GB)");
            myWishListModel.setPrice("45,999/-");
            myWishListModel.setCutPrice("49,999/-");

            modelArrayList.add(myWishListModel);*/
        }
        MyCartAdapter adapter = new MyCartAdapter(modelArrayList, MyCartActivity.this);
        myCart_recyclerView.setAdapter(adapter);

    }
}
