package com.example.ecommerce.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerce.R;
import com.example.ecommerce.activity.MyCartActivity;
import com.example.ecommerce.adapter.MyCartAdapter;
import com.example.ecommerce.model.MyCartModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {
    RecyclerView myCart_recyclerView;
    ArrayList<MyCartModel> modelArrayList;


    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_cart, container, false);
        myCart_recyclerView=(RecyclerView)view.findViewById(R.id.myCart_recyclerView);
        setWishListData();
        return view;
    }

    public void setWishListData() {
        modelArrayList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            MyCartModel myWishListModel = new MyCartModel(2,"200","2000","You saved 4000 only");

            myWishListModel.setImage("https://static.toiimg.com/thumb/msid-63393835,width-220,resizemode-4,imgv-0/Vivo-Apex.jpg");
            myWishListModel.setName("GooGle Pixel XL2 (Mirror Black, 128 GB)");
            myWishListModel.setPrice("45,999/-");
            myWishListModel.setCutPrice("49,999/-");

            modelArrayList.add(myWishListModel);
        }
        MyCartAdapter adapter = new MyCartAdapter(modelArrayList, getActivity());
        myCart_recyclerView.setAdapter(adapter);

    }

}
