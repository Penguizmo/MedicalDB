package com.medicaldb.model;

// The Doctor class represents a medical doctor with basic personal information.
public class Doctor {
    // Private fields to store doctor's information
    private int doctorid;
    private String firstname;
    private String surname;
    private String address;
    private String email;

    // Default constructor
    public Doctor() {}

    // Parameterized constructor to initialize a doctor with specific details
    public Doctor(int doctorid, String firstname, String surname, String address, String email) {
        this.doctorid = doctorid;
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
        this.email = email;
    }

    // Getter for doctorid
    public int getDoctorId() {
        return doctorid;
    }

    // Setter for doctorid
    public void setDoctorId(int doctorid) {
        this.doctorid = doctorid;
    }

    // Getter for firstname
    public String getFirstName() {
        return firstname;
    }

    // Setter for firstname
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    // Getter for surname
    public String getSurname() {
        return surname;
    }

    // Setter for surname
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Override the toString method to provide a string representation of the doctor
    @Override
    public String toString() {
        return "Doctor [ID=" + doctorid + ", Name=" + firstname + " " + surname + "]";
    }
}