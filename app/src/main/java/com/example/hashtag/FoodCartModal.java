package com.example.hashtag;

public class FoodCartModal {

    int img;
    String name;
    String price;
    String quan;
    int numitems;

    public FoodCartModal(){

    }

    public FoodCartModal(int img, String name, String price, String quan, int numitems){
        this.img = img;
        this.name = name;
        this.price = price;
        this.quan = quan;
        this.numitems = numitems;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public int getNumitems() {
        return numitems;
    }

    public void setNumitems(int numitems) {
        this.numitems = numitems;
    }
}
