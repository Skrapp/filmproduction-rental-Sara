package com.nilsson.rental.service;

import com.nilsson.rental.dao.Inventory;
import com.nilsson.rental.entity.items.Item;

public class RentalService {
    /*• RentalService och MembershipService ska innehålla affärslogiken*/
    private Inventory inventory;

    public RentalService(Inventory inventory) {
        this.inventory = inventory;
    }

    public RentalService() {
        inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item){
        inventory.addItem(item);
    }

    public void printEntireInventory(){
        inventory.printItems(inventory.getEntireInventory());
    }

    public void printCategory(Class<? extends Item> category){
        inventory.printItems(inventory.getFilteredListGeneral(inventory.getCategory(category), "Canon"));
    }

    /*public void removeItem(Item item){
        inventory.removeItem(item);
    }*/


}
