package com.example.ecommerce.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.model.CategoryModel;
import com.example.ecommerce.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Activity activity;
    private List<CategoryModel> categoryArrayList;
    private boolean isClicked = false;


    public CategoryAdapter(Activity activity, List<CategoryModel> categoryArrayList) {
        this.activity = activity;
        this.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.categoryName.setText(categoryArrayList.get(position).getCategoryName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clicked = categoryArrayList.get(position).getCategoryName();

                Toast.makeText(activity, clicked, Toast.LENGTH_SHORT).show();
            }
        });
//        Glide.with(activity).load(categoryArrayList.get(position).getCategoryIconUrl()).into(holder.categoryIcon);
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryName;
        private ImageView categoryIcon;
        LinearLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = (TextView) itemView.findViewById(R.id.category_name);
            categoryIcon = (ImageView) itemView.findViewById(R.id.category_icon);
            constraintLayout = (LinearLayout) itemView.findViewById(R.id.constraintsLayout);
        }
    }
}
