package com.nilsson.rental.entity.items;

public class Accessory extends Item{
    private Class<? extends Item> accessoryForType;

    public Accessory(boolean inStock, double dailyRate, String name, String brand, String description,
                     Class<? extends Item> accessoryForType) {
        super(inStock, dailyRate, name, brand, description);
        this.accessoryForType = accessoryForType;
    }

    public Accessory() {
    }

    public Class getAccessoryForType() {
        return accessoryForType;
    }

    public void setAccessoryForType(Class<? extends Item> accessoryForType) {
        this.accessoryForType = accessoryForType;
    }

    @Override
    public String toString() {
        return "Accessoar för: " + accessoryForType.getSimpleName() + " " + super.toString();
    }


    public String getCategoryName(){
        return "Accessoar";
    }
}
