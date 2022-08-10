package com.cst2335.erickproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {

    private Context context;
    private ArrayList<Food> foods;

    public FoodAdapter(Context context, ArrayList<Food> foods){
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.planet_layout_item,parent,false);
        return new FoodHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodHolder holder, int position) {

        Food food = foods.get(position);
        holder.SetDetails(food);

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    class FoodHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtCategory;

        public FoodHolder(@NonNull View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtCategory = itemView.findViewById(R.id.txtCategory);

        }

        void SetDetails(Food food){
            txtName.setText(food.getFoodName());
            txtCategory.setText(String.format(Locale.CANADA,
                    "food category is: %d ", food.getCategory()));

        }
    }
}
