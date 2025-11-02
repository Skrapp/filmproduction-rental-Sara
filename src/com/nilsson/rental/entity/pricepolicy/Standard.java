package com.nilsson.rental.entity.pricepolicy;

public class Standard implements PricePolicy{
    private double priceReduction;

    public Standard() {
        priceReduction = 1;
    }

    public double getPriceReduction() {
        return priceReduction;
    }

    public void setPriceReduction(double priceReduction) {
        this.priceReduction = priceReduction;
    }

    @Override
    public double applyDiscount(double amount) {
        return priceReduction*amount;
    }

    @Override
    public double getFee() {
        return 0;
    }

    @Override
    public String toString() {
        return "Standard";
    }
}
