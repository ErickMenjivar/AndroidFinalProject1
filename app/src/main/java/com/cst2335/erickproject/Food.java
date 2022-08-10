package com.cst2335.erickproject;

public class Food {

    private String foodName;
    private int foodCategory;


    //constructor
    public Food(String foodName, int foodCategory){
        this.foodName = foodName;
        this.foodCategory = foodCategory;
    }

    public String getFoodName(){
        return foodName;
    }

    public int getCategory(){
        return foodCategory;
    }
}
