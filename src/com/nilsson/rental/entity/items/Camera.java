package com.nilsson.rental.entity.items;

public class Camera extends Item{
    private boolean autoFocus;
    private String microphoneInput;

    public Camera(boolean inStock, double dailyRate, String name, String brand, String description,
                  boolean autoFocus, String microphoneInput) {
        super(inStock, dailyRate, name, brand, description);
        this.autoFocus = autoFocus;
        this.microphoneInput = microphoneInput;
    }

    public Camera() {
    }

    public boolean isAutoFocus() {
        return autoFocus;
    }

    public void setAutoFocus(boolean autoFocus) {
        this.autoFocus = autoFocus;
    }

    public String getMicrophoneInput() {
        return microphoneInput;
    }

    public void setMicrophoneInput(String microphoneInput) {
        this.microphoneInput = microphoneInput;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Autofocus: " + ((autoFocus) ? "Ja" : "Nej") + "\n" +
                "Mikrofoninput: " + microphoneInput;
    }

    public String getCategoryName(){
        return "Kamera";
    }
}
