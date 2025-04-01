package com.medicaldb.model;

import java.util.Date;

/**
 * The Visit class represents a medical visit with its details.
 * This class provides getter and setter methods for each field, and a toString method
 * for a string representation of the visit.
 */
public class Visit {
    private String patientID;
    private int doctorid;
    private Date dateofvisit;
    private String symptoms;
    private String diagnosisid;

    /**
     * Default constructor. This method is called when a new Visit object is created without any details.
     */
    public Visit() {}

    /**
     * Constructor. This method is called when a new Visit object is created with specific details.
     * It takes parameters to set the fields of the class.
     *
     * @param dateofvisit The date of the visit
     * @param symptoms The symptoms reported by the patient
     * @param diagnosisid The diagnosis provided by the doctor
     * @param doctorid The ID of the doctor who conducted the visit
     * @param patientID The ID of the patient who was examined
     */
    public Visit(String patientID, int doctorid, Date dateofvisit, String symptoms, String diagnosisid) {
        this.patientID = patientID;
        this.doctorid = doctorid;
        this.dateofvisit = dateofvisit;
        this.symptoms = symptoms;
        this.diagnosisid = diagnosisid;
    }

    /**
     * Getter method for dateofvisit. This method returns the value of the dateofvisit field.
     *
     * @return The date of the visit
     */
    public Date getDateofvisit() {
        return dateofvisit;
    }

    /**
     * Setter method for dateofvisit. This method sets the value of the dateofvisit field.
     *
     * @param dateofvisit The date of the visit
     */
    public void setDateofvisit(Date dateofvisit) {
        this.dateofvisit = dateofvisit;
    }

    /**
     * Getter method for symptoms. This method returns the value of the symptoms field.
     *
     * @return The symptoms reported by the patient
     */
    public String getSymptoms() {
        return symptoms;
    }

    /**
     * Setter method for symptoms. This method sets the value of the symptoms field.
     *
     * @param symptoms The symptoms reported by the patient
     */
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    /**
     * Getter method for diagnosisid. This method returns the value of the diagnosisid field.
     *
     * @return The diagnosis provided by the doctor
     */
    public String getDiagnosisid() {
        return diagnosisid;
    }

    /**
     * Setter method for diagnosisid. This method sets the value of the diagnosisid field.
     *
     * @param diagnosisid The diagnosis provided by the doctor
     */
    public void setDiagnosisid(String diagnosisid) {
        this.diagnosisid = diagnosisid;
    }

    /**
     * Getter method for doctorid. This method returns the value of the doctorid field.
     *
     * @return The ID of the doctor who conducted the visit
     */
    public int getDoctorid() {
        return doctorid;
    }

    /**
     * Setter method for doctorid. This method sets the value of the doctorid field.
     *
     * @param doctorid The ID of the doctor who conducted the visit
     */
    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    /**
     * Getter method for patientID. This method returns the value of the patientID field.
     *
     * @return The ID of the patient who was examined
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Setter method for patientID. This method sets the value of the patientID field.
     *
     * @param patientID The ID of the patient who was examined
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * Override the toString method to provide a string representation of the visit.
     * This method returns a formatted string with visit details.
     *
     * @return A string representation of the visit
     */
    @Override
    public String toString() {
        return "Visit [PatientID=" + patientID + ", Doctorid=" + doctorid + " Dateofvisit=" + dateofvisit + ", Symptoms=" + symptoms + ", Diagnosisid=" + diagnosisid + "]";
    }
}