package com.example.hashtag;

public class FoodCategory {
    int img;

    String data;
    int colour;

    public FoodCategory(){

    }

    public FoodCategory(int img,String data,int colour){
        this.img=img;
        this.data=data;
        this.colour=colour;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public int getColour() {
        return colour;
    }
}
