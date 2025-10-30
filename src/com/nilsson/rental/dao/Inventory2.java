package com.nilsson.rental.dao;

import com.nilsson.rental.entity.items.*;

import java.util.ArrayList;
import java.util.List;

public class Inventory2 {
    private List<Item> items;

    public Inventory2(List<Item> items) {
        this.items = items;
    }

    public Inventory2() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        items.add(item);
    }

    //TODO sortera enligt brand
}
