package com.cst2335.erickproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FoodAdapter2 extends RecyclerView.Adapter<FoodAdapter2.MyViewHolder>{

    private Context mContext;
    private List<FoodModelClass> mData;

    public FoodAdapter2(Context mContext, List<FoodModelClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v =inflater.inflate(R.layout.food_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(mData.get(position).getName());
        holder.category.setText(mData.get(position).getCategory());

        Glide.with(mContext)
                .load(mData.get(position).getImage())
                .into(holder.image);




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView category;
        ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_txt);
            category = itemView.findViewById(R.id.category_txt);
            image =  itemView.findViewById(R.id.image_view1);
        }
    }
}
