package com.example.hashtag;

public class FoodCartModal {
    private String name;
    private int price;
    private int numitems;
    private int imageResId;

    public FoodCartModal(String name, int price, int numitems, int imageResId) {
        this.name = name;
        this.price = price;
        this.numitems = numitems;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getNumitems() { return numitems; }
    public int getImageResId() { return imageResId; }

    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setNumitems(int numitems) { this.numitems = numitems; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }
}
