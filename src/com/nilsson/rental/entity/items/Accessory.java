package com.nilsson.rental.entity.items;

public class Accessory extends Item{
    private Class<? extends Item> accessoryFor;

    public Accessory(boolean inStock, double dailyRate, String name, String brand, String description,
                     Class<? extends Item> accessoryForType) {
        super(inStock, dailyRate, name, brand, description);
        this.accessoryFor = accessoryFor;
    }

    public Accessory() {
    }

    public Class getAccessoryFor() {
        return accessoryFor;
    }

    public void setAccessoryFor(Class<? extends Item> accessoryFor) {
        this.accessoryFor = accessoryFor;
    }
}
