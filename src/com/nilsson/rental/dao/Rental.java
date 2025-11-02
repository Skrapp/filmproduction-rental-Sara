package com.nilsson.rental.dao;

import com.nilsson.rental.entity.items.Item;

import java.time.LocalDateTime;

public class Rental implements Comparable<Rental>{
    /*• Rental (kopplar member och item för en viss tidsperiod)*/
    private LocalDateTime startDate;
    private LocalDateTime rentUntilDate;
    private boolean returned;
    private Item item;

    public Rental() {
    }

    public Rental(LocalDateTime startDate, LocalDateTime rentUntilDate, Item item) {
        this.startDate = startDate;
        this.rentUntilDate = rentUntilDate;
        this.item = item;
        returned = false;
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

    @Override
    public int compareTo(Rental otherRental) {
        return this.startDate.compareTo(otherRental.startDate);
    }
}
