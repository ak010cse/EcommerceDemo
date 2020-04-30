package com.example.ecommerce.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ecommerce.fragment.ProductDescriptionFragment;
import com.example.ecommerce.fragment.ProductSpecificationFragment;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int totalsTabs;

    public ProductDetailsAdapter(@NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalsTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                ProductDescriptionFragment descriptionFragment1 =new ProductDescriptionFragment();
                return descriptionFragment1;
            case 1:
                ProductSpecificationFragment specificationFragment =new ProductSpecificationFragment();
                return specificationFragment;
            case 2:
                ProductDescriptionFragment descriptionFragment2 =new ProductDescriptionFragment();
                return descriptionFragment2;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return totalsTabs;
    }
}
