package com.example.ecommerce.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;
import com.example.ecommerce.adapter.ProductSpecificationAdapter;
import com.example.ecommerce.model.ProductSpecificationModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductSpecificationFragment extends Fragment {
    private RecyclerView product_specification_recyclerView;
    private ArrayList<ProductSpecificationModel> modelArrayList;


    public ProductSpecificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_specification, container, false);

        product_specification_recyclerView = (RecyclerView) view.findViewById(R.id.product_specification_recyclerView);
        setSpecificationData();
        return view;
    }

    public void setSpecificationData() {
        modelArrayList = new ArrayList<>();

//        for (int i = 0; i < 5; i++) {
        modelArrayList.add(new ProductSpecificationModel(0,"General"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));

        modelArrayList.add(new ProductSpecificationModel(0,"Display"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));

        modelArrayList.add(new ProductSpecificationModel(0,"General"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));

        modelArrayList.add(new ProductSpecificationModel(0,"Display"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        modelArrayList.add(new ProductSpecificationModel(1,"RAM","4GB"));

//        }
        ProductSpecificationAdapter specificationAdapter=new ProductSpecificationAdapter(modelArrayList,getActivity());
        product_specification_recyclerView.setAdapter(specificationAdapter);
        specificationAdapter.notifyDataSetChanged();
    }

}
