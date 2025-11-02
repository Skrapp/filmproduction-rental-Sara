package com.nilsson.rental.service;

import com.nilsson.rental.dao.Inventory;
import com.nilsson.rental.dao.Rental;
import com.nilsson.rental.entity.Member;
import com.nilsson.rental.entity.items.Item;

import java.util.List;

public class RentalService {
    /*• RentalService och MembershipService ska innehålla affärslogiken*/
    private Inventory inventory;
    private double income;

    public RentalService(Inventory inventory, double income) {
        this.inventory = inventory;
        this.income = income;
    }

    public RentalService() {
        inventory = new Inventory();
        income = 0;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void addItem(Item item){
        inventory.addItem(item);
    }

    public void printEntireInventory(){
        inventory.printItems(inventory.getEntireInventory());
    }

    public void printCategory(Class<? extends Item> category){
        inventory.printItems(inventory.getCategory(category));
    }

    public void addRentalToMember(Member member, Rental rental){
        member.addRental(rental);
        income += member.getLevel().applyDiscount(rental.getTotalCost());
    }

    public Item getSingleItemByName(String name){
        return inventory.getItemByName(name).getFirst();
    }

    public List<Item> getAllItemsByName(String name){
        return inventory.getItemByName(name);
    }

}
