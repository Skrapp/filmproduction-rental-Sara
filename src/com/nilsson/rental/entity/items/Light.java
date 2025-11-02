package com.nilsson.rental.entity.items;

public class Light extends Item{
    private boolean builtInBattery;
    private boolean cameraMountable;


    public Light(boolean inStock, double dailyRate, String name, String brand, String description,
                 boolean builtInBattery, boolean cameraMountable) {
        super(inStock, dailyRate, name, brand, description);
        this.builtInBattery = builtInBattery;
        this.cameraMountable = cameraMountable;
    }

    public Light() {
    }

    @Override
    public String getCategoryName() {
        return "Ljus";
    }

    public boolean isBuiltInBattery() {
        return builtInBattery;
    }

    public void setBuiltInBattery(boolean builtInBattery) {
        this.builtInBattery = builtInBattery;
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
                "Inbyggda batterier: " + ((builtInBattery) ? "Ja":"Nej") +
                ((cameraMountable) ? "\nGår att fästa på kamera" : "");
    }
}
