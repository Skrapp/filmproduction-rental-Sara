package com.nilsson.rental.entity.items;

public class Camera extends Item{
    private boolean autoFocus;
    private boolean stabilization;
    private int maxResolution;
    private String microphoneInput;

    public Camera(boolean inStock, double dailyRate, String name, String brand, String description,
                  boolean autoFocus, boolean stabilization, int maxResolution, String microphoneInput) {
        super(inStock, dailyRate, name, brand, description);
        this.autoFocus = autoFocus;
        this.stabilization = stabilization;
        this.maxResolution = maxResolution;
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

    public boolean isStabilization() {
        return stabilization;
    }

    public void setStabilization(boolean stabilization) {
        this.stabilization = stabilization;
    }

    public int getMaxResolution() {
        return maxResolution;
    }

    public void setMaxResolution(int maxResolution) {
        this.maxResolution = maxResolution;
    }

    public String getMicrophoneInput() {
        return microphoneInput;
    }

    public void setMicrophoneInput(String microphoneInput) {
        this.microphoneInput = microphoneInput;
    }
}
