package com.nilsson.rental.entity.items;

public class Microphone extends Item {
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

    @Override
    public String getCategoryName() {
        return "Mikrofon";
    }

    public boolean isWireless() {
        return wireless;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }

    public String getSoundOutput() {
        return soundOutput;
    }

    public void setSoundOutput(String soundOutput) {
        this.soundOutput = soundOutput;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Trådlös: " + ((wireless) ? "Ja":"Nej") + "\n" +
                "Ljudutgång: " + soundOutput;
    }
}
