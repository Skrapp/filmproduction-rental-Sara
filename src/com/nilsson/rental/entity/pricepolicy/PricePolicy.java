package com.nilsson.rental.entity.pricepolicy;

public interface PricePolicy {
    /*• PricePolicy (interface) + konkreta strategier, ex. standard, student, premium.*/
    double applyDiscount(double amount);
    double getFee();
    String toString();
}
