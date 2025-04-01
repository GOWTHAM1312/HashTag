package com.example.hashtag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {
    private final Context context;
    private final ArrayList<SliderModel> sliderModalArrayList;

    public SliderAdapter(Context context, ArrayList<SliderModel> sliderModalArrayList) {
        this.context = context;
        this.sliderModalArrayList = sliderModalArrayList;
    }


    @Override
    public int getCount() {
        return sliderModalArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object; //  Fixed casting issue
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView imageView = view.findViewById(R.id.flashcard);
        TextView titleTV = view.findViewById(R.id.flashhead);
        TextView headingTV = view.findViewById(R.id.flashpar);

        SliderModel modal = sliderModalArrayList.get(position);
        titleTV.setText(modal.getHead());
        headingTV.setText(modal.getPra());

        //  Fix: Correctly setting image resource
        imageView.setImageResource(modal.getImg());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object); //  Fixed casting issue
    }
}
