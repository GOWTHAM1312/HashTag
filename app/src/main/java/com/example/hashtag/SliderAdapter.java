package com.example.hashtag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<SliderModel> sliderModalArrayList;


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
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView imageView = view.findViewById(R.id.flashcard);
        TextView titleTV = view.findViewById(R.id.flashhead);
        TextView headingTV = view.findViewById(R.id.flashpar);
        LinearLayout sliderRL = view.findViewById(R.id.main);

        SliderModel modal = sliderModalArrayList.get(position);
        titleTV.setText(modal.getHead());
        headingTV.setText(modal.getPra());
        Picasso.get().load(modal.getImg()).into(imageView);

        sliderRL.setBackground(context.getResources().getDrawable(modal.getImg()));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}