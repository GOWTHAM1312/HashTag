package com.example.hashtag;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FoodCartModal implements Parcelable{
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

    protected FoodCartModal(Parcel in) {
        name = in.readString();
        price = in.readInt();
        numitems = in.readInt();
        imageResId = in.readInt();
    }

    public static final Creator<FoodCartModal> CREATOR = new Creator<FoodCartModal>() {
        @Override
        public FoodCartModal createFromParcel(Parcel in) {
            return new FoodCartModal(in);
        }

        @Override
        public FoodCartModal[] newArray(int size) {
            return new FoodCartModal[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNumitems() {
        return numitems;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setNumitems(int numitems) {
        this.numitems = numitems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeInt(numitems);
        dest.writeInt(imageResId);
    }

}