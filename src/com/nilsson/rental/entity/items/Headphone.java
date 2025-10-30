package com.nilsson.rental.entity.items;

public class Headphone extends Item {
    private String soundInput;

    public Headphone(boolean inStock, double dailyRate, String name, String brand, String description, String soundInput) {
        super(inStock, dailyRate, name, brand, description);
        this.soundInput = soundInput;
    }

    public Headphone() {

    }

    @Override
    public String getCategoryName() {
        return "Hörlurar";
    }

    public String getSoundInput() {
        return soundInput;
    }

    public void setSoundInput(String soundInput) {
        this.soundInput = soundInput;
    }
}
