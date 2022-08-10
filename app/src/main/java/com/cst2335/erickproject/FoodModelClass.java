package com.cst2335.erickproject;

public class FoodModelClass {

    String name;
    String category;
    String image;

    public FoodModelClass(String name, String category, String image){
        this.name= name;
        this.category = category;
        this.image = image;
    }

    public FoodModelClass(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
