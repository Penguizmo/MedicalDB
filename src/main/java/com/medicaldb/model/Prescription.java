package com.medicaldb.model;

import java.util.Date;

// The Prescription class represents a medical prescription with its details.
public class Prescription {
    // Private fields to store prescription information
    private int prescriptionid;
    private Date dateprescribed;
    private String dosage;
    private int duration;
    private String comment;

    // Default constructor
    public Prescription() {}

    // Parameterized constructor to initialize a prescription with specific details
    public Prescription(int prescriptionId, Date datePrescribed, String dosage, int duration, String comment) {
        this.prescriptionid = prescriptionId;
        this.dateprescribed = datePrescribed;
        this.dosage = dosage;
        this.duration = duration;
        this.comment = comment;
    }

    // Getter for prescriptionid
    public int getPrescriptionId() {
        return prescriptionid;
    }

    // Setter for prescriptionid
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionid = prescriptionId;
    }

    // Getter for dateprescribed
    public Date getDatePrescribed() {
        return dateprescribed;
    }

    // Setter for dateprescribed
    public void setDatePrescribed(Date datePrescribed) {
        this.dateprescribed = datePrescribed;
    }

    // Getter for dosage
    public String getDosage() {
        return dosage;
    }

    // Setter for dosage
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    // Getter for duration
    public int getDuration() {
        return duration;
    }

    // Setter for duration
    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Getter for comment
    public String getComment() {
        return comment;
    }

    // Setter for comment
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Override the toString method to provide a string representation of the prescription
    @Override
    public String toString() {
        return "Prescription [ID=" + prescriptionid + ", Dosage=" + dosage + "]";
    }
}