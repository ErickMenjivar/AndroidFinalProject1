package com.cst2335.erickproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class NormalRV extends AppCompatActivity {

    RecyclerView  normal_rv;
    private ArrayList<Food> foodArrayList;
    private FoodAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_rv);
        normal_rv = findViewById(R.id.normalrv);
        normal_rv.setLayoutManager(new LinearLayoutManager(this));
        foodArrayList = new ArrayList<>();
        adapter = new FoodAdapter(this, foodArrayList);
        normal_rv.setAdapter(adapter);
        normal_rv.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        
        createListData();

    }

    private void createListData() {
        Food food = new Food("apple", 1);
        foodArrayList.add(food);

        food = new Food("pineapple", 2);
        foodArrayList.add(food);
        food = new Food("potato", 3);
        foodArrayList.add(food);
        food = new Food("pineapple", 2);
        foodArrayList.add(food);
        food = new Food("potato", 3);
        foodArrayList.add(food);
        food = new Food("pineapple", 2);
        foodArrayList.add(food);
        food = new Food("potato", 3);
        foodArrayList.add(food);
        food = new Food("pineapple", 2);
        foodArrayList.add(food);
        food = new Food("potato", 3);
        foodArrayList.add(food);
        food = new Food("pineapple", 2);
        foodArrayList.add(food);
        food = new Food("potato", 3);
        foodArrayList.add(food);
        food = new Food("pineapple", 2);
        foodArrayList.add(food);
        food = new Food("potato", 3);
        foodArrayList.add(food);

        adapter.notifyDataSetChanged();
    }
}