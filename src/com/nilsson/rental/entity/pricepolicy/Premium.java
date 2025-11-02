package com.nilsson.rental.entity.pricepolicy;

public class Premium implements PricePolicy{
    private double priceReduction;
    private double fee;

    public Premium() {
        priceReduction = 0.75;
        fee = 100;
    }

    public double getPriceReduction() {
        return priceReduction;
    }

    public void setPriceReduction(double priceReduction) {
        this.priceReduction = priceReduction;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
    @Override
    public double applyDiscount(double amount) {
        return priceReduction*amount;
    }

    @Override
    public String toString() {
        return "Premium";
    }
}
