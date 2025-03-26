package com.medicaldb.model;

import java.util.Date;

// The Visit class represents a medical visit with its details.
public class Visit {
    // Private fields to store visit information
    private Date dateofvisit;
    private String symptoms;
    private int diagnosisid;

    // Default constructor
    public Visit() {}

    // Parameterized constructor to initialize a visit with specific details
    public Visit(Date dateOfVisit, String symptoms, int diagnosisId) {
        this.dateofvisit = dateOfVisit;
        this.symptoms = symptoms;
        this.diagnosisid = diagnosisId;
    }

    // Getter for dateofvisit
    public Date getDateOfVisit() {
        return dateofvisit;
    }

    // Setter for dateofvisit
    public void setDateOfVisit(Date dateOfVisit) {
        this.dateofvisit = dateOfVisit;
    }

    // Getter for symptoms
    public String getSymptoms() {
        return symptoms;
    }

    // Setter for symptoms
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    // Getter for diagnosisid
    public int getDiagnosisId() {
        return diagnosisid;
    }

    // Setter for diagnosisid
    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisid = diagnosisId;
    }

    // Override the toString method to provide a string representation of the visit
    @Override
    public String toString() {
        return "Visit [DateOfVisit=" + dateofvisit + ", Symptoms=" + symptoms + ", DiagnosisId=" + diagnosisid + "]";
    }
}