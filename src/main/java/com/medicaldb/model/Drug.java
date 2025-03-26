package com.medicaldb.model;

// The Drug class represents a drug with its details.
public class Drug {
    // Private fields to store drug's information
    private int drugid;
    private String drugname;
    private String sideeffects;
    private String benefits;

    // Default constructor
    public Drug() {}

    // Parameterized constructor to initialize a drug with specific details
    public Drug(int drugid, String drugname, String sideeffects, String benefits) {
        this.drugid = drugid;
        this.drugname = drugname;
        this.sideeffects = sideeffects;
        this.benefits = benefits;
    }

    // Getter for drugid
    public int getDrugId() {
        return drugid;
    }

    // Setter for drugid
    public void setDrugId(int drugid) {
        this.drugid = drugid;
    }

    // Getter for drugname
    public String getDrugName() {
        return drugname;
    }

    // Setter for drugname
    public void setDrugName(String drugname) {
        this.drugname = drugname;
    }

    // Getter for sideeffects
    public String getSideEffects() {
        return sideeffects;
    }

    // Setter for sideeffects
    public void setSideEffects(String sideeffects) {
        this.sideeffects = sideeffects;
    }

    // Getter for benefits
    public String getBenefits() {
        return benefits;
    }

    // Setter for benefits
    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    // Override the toString method to provide a string representation of the drug
    @Override
    public String toString() {
        return "Drug [ID=" + drugid + ", Name=" + drugname + "]";
    }
}