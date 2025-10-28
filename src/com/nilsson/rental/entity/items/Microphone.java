package com.nilsson.rental.entity.items;

public class Microphone extends Sound {
    private boolean wireless;
    private String soundOutput;

    public Microphone(boolean inStock, double dailyRate, String name, String brand, String description,
                      String soundOutput, boolean wireless) {
        super(inStock, dailyRate, name, brand, description);
        this.wireless = wireless;
        this.soundOutput = soundOutput;
    }

    public Microphone() {

    }

    public boolean isWireless() {
        return wireless;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }
}
