package com.example.ecommerce.model;

public class DealsOfTheModel {
    private String name, type, price;
    private int image;

    public DealsOfTheModel(String name, String type, String price, int image) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.image = image;
    }

    public DealsOfTheModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
