package com.softwares.swamisamarth.contentproviderandobserver.models;

/**
 * Created by Narendra on 8/27/2016.
 */
public class Student {
    private int id;
    private String name;
    private boolean isMale;
    private String dob;
    private int std;

    private String division;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getStd() {
        return std;
    }

    public void setStd(int std) {
        this.std = std;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}
