package com.medicaldb.model;

import java.util.Date;

/**
 * The Prescription class represents a medical prescription with its details.
 * This class provides getter and setter methods for each field, and a toString method
 * for a string representation of the prescription.
 */
public class Prescription {
    // This field stores the unique identifier for the prescription.
    private int prescriptionid;
    private Date dateprescribed;
    private String dosage;
    private String duration;
    private String comment;
    private int drugid;
    private int doctorid;
    private String patientID;

    /**
     * Default constructor. This method is called when a new Prescription object is created without any details.
     */
    public Prescription() {}

    /**
     * Constructor. This method is called when a new Prescription object is created with specific details.
     * It takes parameters to set the fields of the class.
     *
     * @param prescriptionId The unique identifier for the prescription
     * @param datePrescribed The date the prescription was prescribed
     * @param dosage The dosage of the prescription
     * @param duration The duration of the prescription
     * @param comment Any additional comments about the prescription
     * @param drugid The drug ID associated with the prescription
     * @param doctorid The doctor ID who prescribed the medication
     * @param patientID The patient ID for whom the prescription is made
     */
    public Prescription(int prescriptionId, Date datePrescribed, String dosage, String duration, String comment, int drugid, int doctorid, String patientID) {
        this.prescriptionid = prescriptionId;
        this.dateprescribed = datePrescribed;
        this.dosage = dosage;
        this.duration = duration;
        this.comment = comment;
        this.drugid = drugid;
        this.doctorid = doctorid;
        this.patientID = patientID;
    }

    /**
     * Getter method for prescriptionid. This method returns the value of the prescriptionid field.
     *
     * @return The unique identifier for the prescription
     */
    public int getPrescriptionId() {
        return prescriptionid;
    }

    /**
     * Setter method for prescriptionid. This method sets the value of the prescriptionid field.
     *
     * @param prescriptionId The unique identifier for the prescription
     */
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionid = prescriptionId;
    }

    /**
     * Getter method for dateprescribed. This method returns the value of the dateprescribed field.
     *
     * @return The date the prescription was prescribed
     */
    public Date getDatePrescribed() {
        return dateprescribed;
    }

    /**
     * Setter method for dateprescribed. This method sets the value of the dateprescribed field.
     *
     * @param datePrescribed The date the prescription was prescribed
     */
    public void setDatePrescribed(Date datePrescribed) {
        this.dateprescribed = datePrescribed;
    }

    /**
     * Getter method for dosage. This method returns the value of the dosage field.
     *
     * @return The dosage of the prescription
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Setter method for dosage. This method sets the value of the dosage field.
     *
     * @param dosage The dosage of the prescription
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * Getter method for duration. This method returns the value of the duration field.
     *
     * @return The duration of the prescription
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Setter method for duration. This method sets the value of the duration field.
     *
     * @param duration The duration of the prescription
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Getter method for comment. This method returns the value of the comment field.
     *
     * @return Any additional comments about the prescription
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter method for comment. This method sets the value of the comment field.
     *
     * @param comment Any additional comments about the prescription
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter method for drugid. This method returns the value of the drugid field.
     *
     * @return The drug ID associated with the prescription
     */
    public int getDrugId() {
        return drugid;
    }

    /**
     * Setter method for drugid. This method sets the value of the drugid field.
     *
     * @param drugid The drug ID associated with the prescription
     */
    public void setDrugId(int drugid) {
        this.drugid = drugid;
    }

    /**
     * Getter method for doctorid. This method returns the value of the doctorid field.
     *
     * @return The doctor ID who prescribed the medication
     */
    public int getDoctorId() {
        return doctorid;
    }

    /**
     * Setter method for doctorid. This method sets the value of the doctorid field.
     *
     * @param doctorid The doctor ID who prescribed the medication
     */
    public void setDoctorId(int doctorid) {
        this.doctorid = doctorid;
    }

    /**
     * Getter method for patientID. This method returns the value of the patientID field.
     *
     * @return The patient ID for whom the prescription is made
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Setter method for patientID. This method sets the value of the patientID field.
     *
     * @param patientID The patient ID for whom the prescription is made
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * Override the toString method to provide a string representation of the prescription.
     * This method returns a formatted string with prescription details.
     *
     * @return A string representation of the prescription
     */
    @Override
    public String toString() {
        return "Prescription [ID=" + prescriptionid +
                ", Date Prescribed=" + dateprescribed +
                ", Dosage=" + dosage +
                ", Duration=" + duration +
                ", Comment=" + comment +
                ", Drug ID=" + drugid +
                ", Doctor ID=" + doctorid +
                ", Patient ID=" + patientID + "]";
    }
}