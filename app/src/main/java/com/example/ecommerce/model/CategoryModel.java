package com.example.ecommerce.model;

public class CategoryModel {
    private String categoryIconUrl;
    private String categoryName;

    public CategoryModel(String categoryIconUrl, String categoryName) {
        this.categoryIconUrl = categoryIconUrl;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIconUrl() {
        return categoryIconUrl;
    }

    public void setCategoryIconUrl(String categoryIconUrl) {
        this.categoryIconUrl = categoryIconUrl;
    }
}
