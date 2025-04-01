package com.example.hashtag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class FoodCategoryAdapter extends ArrayAdapter<FoodCategory> {
    public FoodCategoryAdapter(@NonNull Context context, ArrayList<FoodCategory> resource) {
        super(context, 0,resource);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listitemView = convertView;
        if (listitemView == null)
        {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.categorylayout, parent, false);
        }

        FoodCategory itemModel = getItem(position);
        CardView itmCV=listitemView.findViewById(R.id.card);
        TextView itmTV = listitemView.findViewById(R.id.cardtext);
        ImageView itmIV = listitemView.findViewById(R.id.cardimg);

        itmTV.setText(itemModel.getData());
        itmIV.setImageResource(itemModel.getImg());
        itmCV.setCardBackgroundColor(itemModel.getColour());
        return listitemView;
    }
}
