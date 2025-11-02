package com.nilsson.rental.entity.items;

import java.util.Objects;

public class Light extends Item{
    private boolean wireless;
    private boolean cameraMountable;


    public Light(boolean inStock, double dailyRate, String name, String brand, String description,
                 boolean wireless, boolean cameraMountable) {
        super(inStock, dailyRate, name, brand, description);
        this.wireless = wireless;
        this.cameraMountable = cameraMountable;
    }

    public Light() {
    }

    @Override
    public String getCategoryName() {
        return "Ljus";
    }

    public boolean isWireless() {
        return wireless;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }

    public boolean isCameraMountable() {
        return cameraMountable;
    }

    public void setCameraMountable(boolean cameraMountable) {
        this.cameraMountable = cameraMountable;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Trådlös: " + ((wireless) ? "Ja":"Nej") +
                ((cameraMountable) ? "\nGår att fästa på kamera" : "");
    }
}
