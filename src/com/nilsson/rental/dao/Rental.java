package com.nilsson.rental.dao;

import com.nilsson.rental.entity.items.Item;

import java.time.LocalDateTime;

public class Rental implements Comparable<Rental>{
    /*• Rental (kopplar member och item för en viss tidsperiod)*/
    private LocalDateTime startDate;
    private LocalDateTime rentUntilDate;
    private boolean returned;
    private Item item;

    @Override
    public int compareTo(Rental otherRental) {
        return 0;
    }
}
