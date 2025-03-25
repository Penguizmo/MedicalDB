package com.medicaldb.model;

import java.util.Date;

public class Prescription {
    private int prescriptionid;
    private Date dateprescribed;
    private String dosage;
    private int duration;
    private String comment;

    // Default constructor
    public Prescription() {}

    // Parameterized constructor
    public Prescription(int prescriptionId, Date datePrescribed, String dosage, int duration, String comment) {
        this.prescriptionid = prescriptionId;
        this.dateprescribed = datePrescribed;
        this.dosage = dosage;
        this.duration = duration;
        this.comment = comment;
    }

    // Getters and Setters
    public int getPrescriptionId() {
        return prescriptionid;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionid = prescriptionId;
    }

    public Date getDatePrescribed() {
        return dateprescribed;
    }

    public void setDatePrescribed(Date datePrescribed) {
        this.dateprescribed = datePrescribed;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Prescription [ID=" + prescriptionid + ", Dosage=" + dosage + "]";
    }
}