package com.nilsson.rental.entity;

import com.nilsson.rental.dao.Rental;
import com.nilsson.rental.pricepolicy.Premium;
import com.nilsson.rental.pricepolicy.PricePolicy;
import com.nilsson.rental.pricepolicy.Standard;
import com.nilsson.rental.pricepolicy.Student;

import java.util.TreeSet;

public class Member implements Comparable<Member>{
    /*• Member (id, namn, status/level, historik)*/
    private static long idCounter = 0;
    private final String id;
    private String name;
    private PricePolicy status;
    private TreeSet<Rental> rentalHistory;

    public Member(String name, PricePolicy status) {
        this();
        this.name = name;
        this.status = status;
    }

    public Member() {
        id = setID();
    }

    //Skapar nytt ID för varje skapat objekt
    private static synchronized String setID(){
        return String.valueOf(idCounter++);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PricePolicy getStatus() {
        return status;
    }

    public void setStatus(PricePolicy status) {
        this.status = status;
    }
    
    public void setStatus(String status){
        switch (status.toLowerCase().trim()){
            case "standard" -> this.status = new Standard();
            case "premium" -> this.status = new Premium();
            case "student" -> this.status = new Student();
            default -> throw new IllegalStateException("Unexpected value: " + status);
        }
    }

    //Sorterar enligt namn
    @Override
    public int compareTo(Member otherMember) {
        return Integer.parseInt(this.id) - Integer.parseInt(otherMember.id);
    }

    @Override
    public String toString() {
        String statusInfo="";
        if(status.getClass() == Student.class){
            statusInfo = "Student";
        } else if (status.getClass() == Standard.class){
            statusInfo = "Standard";
        } else if(status.getClass() == Premium.class){
            statusInfo = "Premium";
        }
        return "id: " + id + "\t| Namn: " + name + "\t| Level: " + statusInfo;
    }
}
