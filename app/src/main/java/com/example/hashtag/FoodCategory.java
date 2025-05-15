package com.example.hashtag;

public class FoodCategory {
    private int img;
    private String data;
    private int colour;

    public FoodCategory() {
        // Required empty constructor (especially for Firebase)
    }

    public FoodCategory(int img, String data, int colour) {
        this.img = img;
        this.data = data;
        this.colour = colour;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }
}
