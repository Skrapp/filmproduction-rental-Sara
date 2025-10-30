package com.nilsson.rental.entity.items;

public class Sound extends Item{


    public Sound(boolean inStock, double dailyRate, String name, String brand, String description) {
        super(inStock, dailyRate, name, brand, description);
    }

    public Sound() {
    }

    @Override
    public String getCategoryName() {
        return "Ljud";
    }


}
