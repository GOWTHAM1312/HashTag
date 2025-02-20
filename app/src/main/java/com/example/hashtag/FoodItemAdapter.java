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

public class FoodItemAdapter extends ArrayAdapter<FoodItemModal> {

    public FoodItemAdapter(@NonNull Context context, ArrayList<FoodItemModal> resource) {
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
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.itemlayout, parent, false);
        }

        FoodItemModal itemModel1 = getItem(position);

        ImageView itmIV1= listitemView.findViewById(R.id.itemimg);
        TextView itmTV1 = listitemView.findViewById(R.id.itemname);
        TextView itmTV2= listitemView.findViewById(R.id.rate);
        TextView itmTV3= listitemView.findViewById(R.id.quan);



        itmIV1.setImageResource(itemModel1.getImg());
        itmTV1.setText(itemModel1.getName());
        itmTV2.setText(itemModel1.getRate());
        itmTV3.setText(itemModel1.getQuan());


        return listitemView;
    }
}
