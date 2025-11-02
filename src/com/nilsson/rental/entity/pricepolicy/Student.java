package com.nilsson.rental.entity.pricepolicy;

public class Student implements PricePolicy{
    private double priceReduction;

    public Student() {
        priceReduction = 0.6;
    }

    @Override
    public double applyDiscount(double amount) {
        return priceReduction * amount;
    }

    @Override
    public double getFee() {
        return 0;
    }

    @Override
    public String toString() {
        return "Student";
    }
}
