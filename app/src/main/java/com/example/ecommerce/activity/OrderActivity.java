package com.example.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecommerce.R;
import com.example.ecommerce.adapter.MyOrderListAdapter;
import com.example.ecommerce.helper.CustomNavigationMethod;
import com.example.ecommerce.model.MyOrderListModel;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    RecyclerView orderRecyclerView;
    ArrayList<MyOrderListModel> modelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        CustomNavigationMethod.toolbar_back(OrderActivity.this,"Order List");
        orderRecyclerView=(RecyclerView)findViewById(R.id.orderRecyclerView);
        setOrderListData();
    }
    public void setOrderListData() {
        modelArrayList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            MyOrderListModel orderListModel = new MyOrderListModel();

            orderListModel.setImage("https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg");
            orderListModel.setName("GooGle Pixel XL2 (Mirror Black, 128 GB)");
            orderListModel.setDescription("GooGle Pixel XL2 (Mirror Black, 128 GB)");

            modelArrayList.add(orderListModel);
        }
        MyOrderListAdapter adapter = new MyOrderListAdapter(modelArrayList, OrderActivity.this);
        orderRecyclerView.setAdapter(adapter);

    }
}
