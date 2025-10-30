package com.nilsson.rental.dao;

import com.nilsson.rental.entity.items.*;

import java.util.*;

public class Inventory {
    /* Inventory och MemberRegistry (hanteras i minnet via List, Map eller Set)*/

    /*private List<Accessory> accessories;
    private List<Camera> cameras;
    private List<Headphone> headphones;
    private List<Light> lights;
    private List<Memory> memories;
    private List<Microphone> microphones;
    private List<Sound> sounds;

    public Inventory(){
        accessories = new ArrayList<>();
        cameras = new ArrayList<>();
        headphones = new ArrayList<>();
        lights = new ArrayList<>();
        memories = new ArrayList<>();
        microphones = new ArrayList<>();
        sounds = new ArrayList<>();
    }

    public Inventory(ArrayList<Accessory> accessories, ArrayList<Camera> cameras, ArrayList<Headphone> headphones,
                     ArrayList<Light> lights, ArrayList<Memory> memories, ArrayList<Microphone> microphones,
                     ArrayList<Sound> sounds) {
        this.accessories = accessories;
        this.cameras = cameras;
        this.headphones = headphones;
        this.lights = lights;
        this.memories = memories;
        this.microphones = microphones;
        this.sounds = sounds;
    }*/

    /**
     * Lägger till ett nytt objekt i inventory. Använder type pattern matching
     * @param item Objekt som ska läggas till i inventory
     */
    /*public void addItem(Item item) {
        switch (item){
            case Accessory accessory -> accessories.add(accessory);
            case Camera camera -> cameras.add(camera);
            case Headphone headphone -> headphones.add(headphone);
            case Light light -> lights.add(light);
            case Memory memory -> memories.add(memory);
            case Microphone microphone -> microphones.add(microphone);
            case Sound sound -> sounds.add(sound);

            default -> throw new IllegalStateException("Tillhör en ogiltig klass: " + item);
        }
    }

    public List<Item> getEntireInventory(){
        List<Item> entireInventory = new ArrayList<>();
        entireInventory.addAll(accessories);
        entireInventory.addAll(cameras);
        entireInventory.addAll(headphones);
        entireInventory.addAll(lights);
        entireInventory.addAll(memories);
        entireInventory.addAll(microphones);
        entireInventory.addAll(sounds);
        return entireInventory;
    }

    public List<? extends Item> getCategory(Class<? extends Item> category){
        String className = category.getSimpleName();
        switch (className){
            case "Accessory" -> {
                return accessories;
            }
            case "Camera" -> {
                return cameras;
            }
            case "Headphone"-> {
                return headphones;
            }
            case "Light"-> {
                return lights;
            }
            case "Memory"-> {
                return memories;
            }
            case "Microphone"-> {
                return microphones;
            }
            case "Sound"-> {
                return sounds;
            }

            default -> throw new IllegalStateException("Tillhör en ogiltig klass: " + category.getSimpleName());
        }
    }*/

    private List<Item> items;

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
    }

    /**
     * Skriver ut de item som finns i listan som skickas in och hur många som är tillgängliga av dem
     * TODO lägg till hur många som finns totalt (t.ex. 3/5 tillgängliga)
     */
    public void printSortedItems(List<? extends Item> items){
        Map<String, Integer> inventoryWithStock = new TreeMap<>();
        for(Item item : items) {
            if(item.isInStock()) {
                inventoryWithStock.put(item.toString(), inventoryWithStock.getOrDefault(item.toString(), 0) + 1);
            }else {
                inventoryWithStock.put(item.toString(), inventoryWithStock.getOrDefault(item.toString(), 0));
            }
        }
        for(Map.Entry<String, Integer> entry : inventoryWithStock.entrySet()){
            System.out.println(entry.getKey() + "\tTillgängliga: " + entry.getValue());
            System.out.println();
        }
    }

    private int getNumberInStock(Item lookForItem, List<Item> items){
        int numberInStock = 0;
        for(Item item : items){
            if(item.getName().equals(lookForItem.getName()) && item.getBrand().equals(lookForItem.getBrand())
                    && item.isInStock()){
                numberInStock++;
            }
        }
        return numberInStock;
    }

    private int getNumberNotInStock(Item lookForItem, List<Item> items){
        int numberNotInStock = 0;
        for(Item item : items){
            if(item.getName().equals(lookForItem.getName()) && item.getBrand().equals(lookForItem.getBrand())
                    && !item.isInStock()){
                numberNotInStock++;
            }
        }
        return numberNotInStock;
    }

}
