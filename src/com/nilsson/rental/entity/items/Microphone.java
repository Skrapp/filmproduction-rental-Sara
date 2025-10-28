package com.nilsson.rental.entity.items;

public class Microphone extends Sound {
    private boolean wireless;

    public Microphone(boolean inStock, double dailyRate, String name, String brand, String description,
                      String soundOutput, boolean wireless) {
        super(inStock, dailyRate, name, brand, description, soundOutput);
        this.wireless = wireless;
    }

    public Microphone() {

    }
}
