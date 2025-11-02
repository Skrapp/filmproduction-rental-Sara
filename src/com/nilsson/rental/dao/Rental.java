package com.nilsson.rental.dao;

import com.nilsson.rental.entity.items.Item;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Rental implements Comparable<Rental>{
    /*• Rental (kopplar member och item för en viss tidsperiod)*/
    private static long idCounter = 0;
    private String id;
    private LocalDateTime startDate;
    private LocalDateTime rentUntilDate;
    private boolean returned;
    private Item item;

    public Rental() {
        id = setID();
    }

    public Rental(LocalDateTime startDate, LocalDateTime rentUntilDate, Item item) {
        this();
        this.startDate = startDate;
        this.rentUntilDate = rentUntilDate;
        this.item = item;
        returned = false;
    }

    //Skapar nytt ID för varje skapat objekt
    private static synchronized String setID(){
        return String.valueOf(idCounter++);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getRentUntilDate() {
        return rentUntilDate;
    }

    public void setRentUntilDate(LocalDateTime rentUntilDate) {
        this.rentUntilDate = rentUntilDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public double getTotalCost(){
        return item.getDailyRate() * startDate.until(rentUntilDate, ChronoUnit.DAYS);
    }

    @Override
    public int compareTo(Rental otherRental) {
        return this.startDate.compareTo(otherRental.startDate);
    }

    @Override
    public String toString() {
        return "Uthyrning| id: " + id + '\n' +
                "\tprodukt: " + item.getName() + " - " + item.getBrand() + '\n' +
                "\tUtlåningsdatum: " + startDate.format(DateTimeFormatter.ISO_DATE_TIME) + '\n' +
                "\tLämna tillbaka senast: " + rentUntilDate.format(DateTimeFormatter.ISO_DATE_TIME) + '\n' +
                "\tTotal kostnad: " + getTotalCost() + '\n' +
                "\treturnerad: " + ((returned) ? "Ja" : "Nej");
    }
}
