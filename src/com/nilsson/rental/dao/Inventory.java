package com.nilsson.rental.dao;

import com.nilsson.rental.entity.items.*;

import java.util.*;

public class Inventory {
    /* Inventory och MemberRegistry (hanteras i minnet via List, Map eller Set)*/

    private Map<Class<? extends Item>, List<Item>> itemsInCategories;

    public Inventory(){
        itemsInCategories = new HashMap<>();
    }

    public Inventory(ArrayList<Item> accessories, ArrayList<Item> cameras,
                     ArrayList<Item> lights, ArrayList<Item> microphones) {
        this();
        itemsInCategories.put(Accessory.class, accessories);
        itemsInCategories.put(Camera.class, cameras);
        itemsInCategories.put(Light.class, lights);
        itemsInCategories.put(Microphone.class, microphones);
    }

    /**
     * Lägger till ett nytt objekt i inventory. Använder type pattern matching
     * todo Felhantering, om klassen är fel, im item är null,
     * @param item Objekt som ska läggas till i inventory
     */
    public void addItem(Item item) {
        Class<? extends Item> itemClass = item.getClass();
        itemsInCategories.putIfAbsent(itemClass, new ArrayList<>());
        itemsInCategories.get(itemClass).add(item);
    }

    public List<Item> getEntireInventory(){
        List<Item> entireInventory = new ArrayList<>();
        for(Map.Entry<Class<? extends Item>, List<Item>> entry : itemsInCategories.entrySet()){
            entireInventory.addAll(entry.getValue());
        }

        return entireInventory;
    }

    public List<Item> getCategory(Class<? extends Item> category){
        return itemsInCategories.get(category);
    }

    public List<Item> getFilteredListGeneral(List<Item> items, boolean inStock, String searchWord){
        List<Item> filteredList = new ArrayList<>();
        for(Item item : items){
            if(item.isInStock() == inStock &&
                    (item.getName().contains(searchWord)
                    || item.getBrand().contains(searchWord)
                    || item.getDescription().contains(searchWord))){
                filteredList.add(item);
            }
        }
        return new ArrayList<>();
    }

    public List<Item> getFilteredListGeneral(List<Item> items, String searchWord){
        List <Item> filteredList = new ArrayList<>();
        for(Item item : items){
            if(item.getName().contains(searchWord)
                    || item.getBrand().contains(searchWord)
                    || item.getDescription().contains(searchWord)){
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public boolean isNameInInventory(String name){
        for(Map.Entry<Class<? extends Item>, List<Item>> entry : itemsInCategories.entrySet()){
            for(Item item : entry.getValue()){
                if(item.getName().equalsIgnoreCase(name)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Skriver ut de item som finns i listan som skickas in och hur många som är tillgängliga av dem
     * TODO lägg till hur många som finns totalt (t.ex. 3/5 tillgängliga)
     */
    public void printItems(List<Item> items){
        Map<String, Integer> inventoryWithStock = new TreeMap<>();
        for(Item item : items) {
            if(item.isInStock()) {
                inventoryWithStock.put(item.toString(), inventoryWithStock.getOrDefault(item.toString(), 0) + 1);
            }else {
                inventoryWithStock.put(item.toString(), inventoryWithStock.getOrDefault(item.toString(), 0));
            }
        }
        for(Map.Entry<String, Integer> entry : inventoryWithStock.entrySet()){
            System.out.println(entry.getKey());
            System.out.println("Tillgängliga: " + entry.getValue());
            System.out.println();
        }
    }

    public List<Item> getItemByName(String name){
        List<Item> itemsWithName = new ArrayList<>();
        for(Map.Entry<Class<? extends Item>, List<Item>> entry : itemsInCategories.entrySet()){
            for(Item item : entry.getValue()){
                if(item.getName().equalsIgnoreCase(name)){
                    itemsWithName.add(item);
                }
            }
        }
        return itemsWithName;
    }

    /*private List<Item> items;

    public Inventory(List<Item> items) {
        this.items = items;
    }

    public Inventory() {
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

    public List<? extends Item> getCategoryItems(Class<?extends Item> categoryClass){
        List<Item> result = new ArrayList<>();
        for(Item item : items){
            if(categoryClass.isInstance(item)){
                result.add(item);
            }
        }
        return result;
    }*/
}
