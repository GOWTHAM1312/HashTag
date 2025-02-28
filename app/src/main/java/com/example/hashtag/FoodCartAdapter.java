package com.example.hashtag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodCartAdapter extends ArrayAdapter<FoodCartModal> {
    int x=1;

    private Context context;
    private List<FoodCartModal> itemList;

    public FoodCartAdapter(Context context, List<FoodCartModal> itemList) {
        super(context, 0, itemList);
        this.context = context;
        this.itemList = itemList;
    }


    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        FoodCartAdapter.ViewHolder holder;

        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.cartlayout, parent, false);
            holder = new ViewHolder();
            holder.imgview = view.findViewById(R.id.cartImg);
            holder.textView = view.findViewById(R.id.cartnameTv);
            holder.textView1 = view.findViewById(R.id.cartquanTv);
            holder.valueTextView = view.findViewById(R.id.cartnumofitemTv);
            holder.incrementButton = view.findViewById(R.id.plus);
            holder.decrementButton = view.findViewById(R.id.minus);
        }
        else
        {
            holder = (FoodCartAdapter.ViewHolder) view.getTag();
        }

        final FoodCartModal item = getItem(position);

        holder.textView.setText("Item " + item.getName());
        holder.valueTextView.setText(String.valueOf(item.getNumitems()));
        holder.textView1.setText("Rs. "+item.getPrice());

        holder.incrementButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                item.setNumitems(item.getNumitems() + 1);
                holder.valueTextView.setText(String.valueOf(item.getNumitems()));
            }
        });

        holder.decrementButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(item.getNumitems()>=1)
                {
                    item.setNumitems(item.getNumitems() - 1);
                    holder.valueTextView.setText(String.valueOf(item.getNumitems()));
                }
            }
        });

        return view;
    }

    private static class ViewHolder
    {
        ImageView imgview;
        TextView textView;
        TextView textView1;
        TextView valueTextView;
        ImageView incrementButton;
        ImageView decrementButton;
    }


}
