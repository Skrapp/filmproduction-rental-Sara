package com.nilsson.rental.entity;

import com.nilsson.rental.pricepolicy.PricePolicy;

public class Member implements Comparable<Member>{
    /*• Member (id, namn, status/level, historik)*/
    private static long idCounter = 0;
    private final String id;
    private String name;
    private PricePolicy status;

    public Member(String name, PricePolicy status) {
        this();
        this.name = name;
        this.status = status;
    }

    public Member() {
        id = setID();
    }

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

    //Sorterar enligt namn
    @Override
    public int compareTo(Member otherMember) {
        return this.name.compareTo(otherMember.getName());
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
