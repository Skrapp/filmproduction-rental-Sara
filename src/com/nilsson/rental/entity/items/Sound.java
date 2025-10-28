package com.nilsson.rental.entity.items;

public class Sound extends Item{
    protected String soundOutput;

    public Sound(boolean inStock, double dailyRate, String name, String brand, String description) {
        super(inStock, dailyRate, name, brand, description);
    }

    public Sound(boolean inStock, double dailyRate, String name, String brand, String description, String soundOutput) {
        super(inStock, dailyRate, name, brand, description);
        this.soundOutput = soundOutput;
    }

    public Sound() {
    }


}
