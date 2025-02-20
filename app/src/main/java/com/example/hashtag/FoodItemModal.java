package com.example.hashtag;

public class FoodItemModal {

    int img;

    String name;
    String rate;
    String quan;

    String category;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public FoodItemModal(){

    }

    public FoodItemModal(int img, String name, String rate, String quan,String category){
        this.img=img;
        this.name=name;
        this.rate=rate;
        this.quan=quan;
        this.category=category;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }
}
