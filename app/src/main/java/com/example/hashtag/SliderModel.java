package com.example.hashtag;

import android.widget.ImageView;
import android.widget.TextView;

public class SliderModel {

    int img;
    String head;
    String pra;

    public SliderModel(){
    }

    public SliderModel(int img,String head,String pra){
        this.img=img;
        this.head=head;
        this.pra=pra;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPra() {
        return pra;
    }

    public void setPra(String pra) {
        this.pra = pra;
    }
}
