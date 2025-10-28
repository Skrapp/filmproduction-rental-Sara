package com.nilsson.rental.entity.items;

public class Light extends Item{
    private boolean builtInBattery;
    private boolean cameraMountable;
    private int maxLux;
    private double height;
    private double width;
    private double depth;




    public Light(boolean inStock, double dailyRate, String name, String brand, String description,
                 boolean builtInBattery, boolean cameraMountable, int maxLux,
                 double height, double width, double depth) {
        super(inStock, dailyRate, name, brand, description);
        this.builtInBattery = builtInBattery;
        this.cameraMountable = cameraMountable;
        this.maxLux = maxLux;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public Light() {
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

    public int getMaxLux() {
        return maxLux;
    }

    public void setMaxLux(int maxLux) {
        this.maxLux = maxLux;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}
