package com.nilsson.rental.entity.items;

public class Memory extends Item {
    private int memorySize;

    public Memory(boolean inStock, double dailyRate, String name, String brand, String description) {
        super(inStock, dailyRate, name, brand, description);
    }

    public Memory() {
    }
}
