package com.medicaldb.model;

public class Drug {
    private int drugid;
    private String drugname;
    private String sideeffects;
    private String benefits;

    // Default constructor
    public Drug() {}

    // Parameterized constructor
    public Drug(int drugid, String drugname, String sideeffects, String benefits) {
        this.drugid = drugid;
        this.drugname = drugname;
        this.sideeffects = sideeffects;
        this.benefits = benefits;
    }

    // Getters and Setters
    public int getDrugId() {
        return drugid;
    }

    public void setDrugId(int drugid) {
        this.drugid = drugid;
    }

    public String getDrugName() {
        return drugname;
    }

    public void setDrugName(String drugname) {
        this.drugname = drugname;
    }

    public String getSideEffects() {
        return sideeffects;
    }

    public void setSideEffects(String sideeffects) {
        this.sideeffects = sideeffects;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    @Override
    public String toString() {
        return "Drug [ID=" + drugid + ", Name=" + drugname + "]";
    }
}