package com.nilsson.rental.entity.items;

public class Speaker extends Sound {
    private boolean bluetooth;
    private double height;
    private double width;
    private double depth;

    public Speaker(boolean inStock, double dailyRate, String name, String brand, String description, String soundOutput,
                   boolean bluetooth, double height, double width, double depth) {
        super(inStock, dailyRate, name, brand, description, soundOutput);
        this.bluetooth = bluetooth;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public Speaker() {

    }
}
