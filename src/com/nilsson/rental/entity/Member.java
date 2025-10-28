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
    private PricePolicy level;
    private TreeSet<Rental> rentalHistory;

    public Member(String name, PricePolicy level) {
        this();
        this.name = name;
        this.level = level;
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

    public PricePolicy getLevel() {
        return level;
    }

    public void setLevel(PricePolicy level) {
        this.level = level;
    }
    
    public void setStatus(String status){
        switch (status.toLowerCase().trim()){
            case "standard" -> this.level = new Standard();
            case "premium" -> this.level = new Premium();
            case "student" -> this.level = new Student();
            default -> throw new IllegalStateException("Unexpected value: " + status);
        }
    }

    @Override
    public String toString() {
        String statusInfo="";
        if(level.getClass() == Student.class){
            statusInfo = "Student";
        } else if (level.getClass() == Standard.class){
            statusInfo = "Standard";
        } else if(level.getClass() == Premium.class){
            statusInfo = "Premium";
        }
        return "id: " + id + "\t| Namn: " + name + "\t| Level: " + statusInfo;
    }

    @Override
    public int compareTo(Member otherMember) {
        return Integer.parseInt(this.id) - Integer.parseInt(otherMember.id);
    }
}
