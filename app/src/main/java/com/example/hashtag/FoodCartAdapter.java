package com.example.hashtag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FoodCartAdapter extends ArrayAdapter<FoodCartModal> {

    private Context context;
    private List<FoodCartModal> itemList;
    private DatabaseReference dbRef;

    public FoodCartAdapter(Context context, List<FoodCartModal> itemList) {
        super(context, 0, itemList);
        this.context = context;
        this.itemList = itemList;
        dbRef = FirebaseDatabase.getInstance().getReference("cart");
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.cartlayout, parent, false);
            holder = new ViewHolder();
            holder.imgView = view.findViewById(R.id.cartImg);
            holder.nameTV = view.findViewById(R.id.cartnameTv);
            holder.priceTV = view.findViewById(R.id.cartpriceTv);
            holder.numItemsTV = view.findViewById(R.id.cartnumofitemTv);
            holder.plus = view.findViewById(R.id.plus);
            holder.minus = view.findViewById(R.id.minus);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        FoodCartModal item = getItem(position);
        if (item == null) return view;

        holder.imgView.setImageResource(item.getImageResId());
        holder.nameTV.setText(item.getName());
        holder.priceTV.setText("₹" + item.getPrice()); // display item price
        holder.numItemsTV.setText(String.valueOf(item.getNumitems()));

        holder.plus.setOnClickListener(v -> {
            item.setNumitems(item.getNumitems() + 1);
            holder.numItemsTV.setText(String.valueOf(item.getNumitems()));
            ((AddtocartActivity) context).updateTotalViews();
        });

        holder.minus.setOnClickListener(v -> {
            int count = item.getNumitems() - 1;
            if (count > 0) {
                item.setNumitems(count);
                holder.numItemsTV.setText(String.valueOf(count));
            } else {
                // Remove from list and Firebase
                itemList.remove(position);
                notifyDataSetChanged();

                dbRef.orderByChild("name").equalTo(item.getName())
                        .addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                                for (com.google.firebase.database.DataSnapshot snap : snapshot.getChildren()) {
                                    snap.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull com.google.firebase.database.DatabaseError error) {
                                Toast.makeText(context, "Failed to remove item", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            ((AddtocartActivity) context).updateTotalViews();
        });

        return view;
    }

    static class ViewHolder {
        ImageView imgView, plus, minus;
        TextView nameTV, priceTV, numItemsTV;
    }
}
