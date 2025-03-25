package com.medicaldb.model;

import java.util.Date;

public class Visit {
    private Date dateofvisit;
    private String symptoms;
    private int diagnosisid;

    // Default constructor
    public Visit() {}

    // Parameterized constructor
    public Visit(Date dateOfVisit, String symptoms, int diagnosisId) {
        this.dateofvisit = dateOfVisit;
        this.symptoms = symptoms;
        this.diagnosisid = diagnosisId;
    }

    // Getters
    public Date getDateOfVisit() {
        return dateofvisit;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public int getDiagnosisId() {
        return diagnosisid;
    }

    // Setters
    public void setDateOfVisit(Date dateOfVisit) {
        this.dateofvisit = dateOfVisit;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisid = diagnosisId;
    }

    @Override
    public String toString() {
        return "Visit [DateOfVisit=" + dateofvisit + ", Symptoms=" + symptoms + ", DiagnosisId=" + diagnosisid + "]";
    }
}