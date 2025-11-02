package com.nilsson.rental.entity.items;

public class Camera extends Item{
    private boolean autoFocus;
    private String soundInput;

    public Camera(boolean inStock, double dailyRate, String name, String brand, String description,
                  boolean autoFocus, String soundInput) {
        super(inStock, dailyRate, name, brand, description);
        this.autoFocus = autoFocus;
        this.soundInput = soundInput;
    }

    public Camera() {
    }

    public boolean isAutoFocus() {
        return autoFocus;
    }

    public void setAutoFocus(boolean autoFocus) {
        this.autoFocus = autoFocus;
    }

    public String getSoundInput() {
        return soundInput;
    }

    public void setSoundInput(String soundInput) {
        this.soundInput = soundInput;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Autofocus: " + ((autoFocus) ? "Ja" : "Nej") + "\n" +
                "Mikrofoninput: " + soundInput;
    }

    public String getCategoryName(){
        return "Kamera";
    }
}
