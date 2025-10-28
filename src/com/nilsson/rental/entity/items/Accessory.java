package com.nilsson.rental.entity.items;

public class Accessory extends Item{
    private Item accessoryFor;

    public Accessory(boolean inStock, double dailyRate, String name, String brand, String description, Item accessoryFor) {
        super(inStock, dailyRate, name, brand, description);
        this.accessoryFor = accessoryFor;
    }

    public Accessory() {
    }
}
