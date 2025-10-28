package com.nilsson.rental.entity.items;

public abstract class Item {
    /*• Item (abstract)  Subklasser som ex. Vehicle, Tool, SportGear eller liknande
beroende på ditt val av applikation. Var och en av klasserna ska innehålla sina
unika attribut och metoder enligt det vi pratat om.*/
    protected static long idCounter = 0;
    protected String id;
    protected boolean inStock;
    protected double dailyRate;
    protected String name;
    protected String brand;
    protected String description;

    public Item(boolean inStock, double dailyRate, String name, String brand, String description) {
        id = setID();
        this.inStock = inStock;
        this.dailyRate = dailyRate;
        this.name = name;
        this.brand = brand;
        this.description = description;
    }

    public Item() {
    }

    //Skapar nytt ID för varje skapat objekt
    private static synchronized String setID(){
        return String.valueOf(idCounter++);
    }

    public String getId() {
        return id;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
