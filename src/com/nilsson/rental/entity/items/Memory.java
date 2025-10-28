package com.nilsson.rental.entity.items;

public class Memory extends Item {
    private int memorySize;

    public Memory(boolean inStock, double dailyRate, String name, String brand, String description, int memorySize) {
        super(inStock, dailyRate, name, brand, description);
        this.memorySize = memorySize;
    }

    public Memory() {
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }
}
