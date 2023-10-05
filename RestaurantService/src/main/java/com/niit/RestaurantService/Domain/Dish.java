package com.niit.RestaurantService.Domain;

public class Dish {

    private int dishId;
    private String dishName;
    private int dishCost;
    private String dishType;
    private String dishImage;

    public Dish(){

    }

    public Dish(int dishId, String dishName, int dishCost, String dishType, String dishImage) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishCost = dishCost;
        this.dishType = dishType;
        this.dishImage = dishImage;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishCost() {
        return dishCost;
    }

    public void setDishCost(int dishCost) {
        this.dishCost = dishCost;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public String getDishImage() {
        return dishImage;
    }

    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", dishCost=" + dishCost +
                ", dishType='" + dishType + '\'' +
                ", dishImage='" + dishImage + '\'' +
                '}';
    }
}
