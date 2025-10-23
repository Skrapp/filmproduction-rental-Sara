package com.nilsson.rental.entity;

import com.nilsson.rental.pricepolicy.PricePolicy;

public class Member {
    /*• Member (id, namn, status/level, historik)*/
    private static long idCounter = 0;
    private String id;
    private String name;
    private PricePolicy status;
    private boolean admin;

    public Member(String name, PricePolicy status, boolean isAdmin) {
        this();
        this.name = name;
        this.status = status;
        this.admin = isAdmin;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
