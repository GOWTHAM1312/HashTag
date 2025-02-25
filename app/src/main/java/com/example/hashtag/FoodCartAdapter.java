package com.example.hashtag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodCartAdapter extends ArrayAdapter<FoodCartModal> {
    int x=1;

    public FoodCartAdapter(@androidx.annotation.NonNull Context context, ArrayList<FoodCartModal> resource) {
        super(context, 0,resource);
    }

    @androidx.annotation.NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {
        View listitemView = convertView;
        //ViewHolder holder;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.cartlayout, parent, false);
        }

        FoodCartModal itemModel = getItem(position);
        TextView itmTV = listitemView.findViewById(R.id.cartnameTv);
        TextView itmTV1 = listitemView.findViewById(R.id.cartquanTv);
        TextView itmTV2= listitemView.findViewById(R.id.cartrateTv);
        TextView itmTV3= listitemView.findViewById(R.id.cartnumofitemTv);
        ImageView itmIV = listitemView.findViewById(R.id.cartImg);
        ImageView minusIV = listitemView.findViewById(R.id.minus);
        ImageView plusIV = listitemView.findViewById(R.id.plus);

        itmTV.setText(itemModel.getName());
        itmTV2.setText(itemModel.getPrice());
        itmTV1.setText(itemModel.getQuan());
        itmTV3.setText(String.valueOf(itemModel.getNumitems()));
        itmIV.setImageResource(itemModel.getImg());

        minusIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), itmTV.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        plusIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), itmTV.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return listitemView;

    }
}
