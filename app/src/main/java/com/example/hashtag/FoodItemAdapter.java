package com.example.hashtag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodItemAdapter extends ArrayAdapter<FoodItemModal> {

    private final DatabaseReference dbRef;

    public FoodItemAdapter(@NonNull Context context, ArrayList<FoodItemModal> resource) {
        super(context, 0, resource);
        dbRef = FirebaseDatabase.getInstance().getReference("cart");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listitemView = convertView;
        if (listitemView == null)
        {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.itemlayout, parent, false);
        }

        FoodItemModal item = getItem(position);

        ImageView itemImg = listitemView.findViewById(R.id.itemimg);
        TextView itemName = listitemView.findViewById(R.id.itemname);
        TextView itemRate = listitemView.findViewById(R.id.rate);
        TextView itemQuan = listitemView.findViewById(R.id.quan);
        Button addToCartBtn = listitemView.findViewById(R.id.addToCartBtn);

        itemImg.setImageResource(item.getImg());
        itemName.setText(item.getName());
        itemRate.setText(item.getRate());
        itemQuan.setText(item.getQuan());

        addToCartBtn.setOnClickListener(v -> {
            String id = dbRef.push().getKey();

            HashMap<String, Object> cartItem = new HashMap<>();
            cartItem.put("id", id);
            cartItem.put("img", item.getImg());
            cartItem.put("name", item.getName());
            cartItem.put("rate", item.getRate());
            cartItem.put("quan", item.getQuan());
            cartItem.put("category", item.getCategory());

            if (id != null) {
                dbRef.child(id).setValue(cartItem)
                        .addOnSuccessListener(aVoid ->
                                Toast.makeText(getContext(), "Added to cart", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e ->
                                Toast.makeText(getContext(), "Failed to add to cart", Toast.LENGTH_SHORT).show());
            }
        });

        return listitemView;
    }
}
