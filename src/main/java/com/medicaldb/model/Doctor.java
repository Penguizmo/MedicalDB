package com.medicaldb.model;

public class Doctor {
    private int doctorid;
    private String firstname;
    private String surname;
    private String address;
    private String email;

    // Default constructor
    public Doctor() {}

    // Parameterized constructor
    public Doctor(int doctorid, String firstname, String surname, String address, String email) {
        this.doctorid = doctorid;
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
        this.email = email;
    }

    // Getters
    public int getDoctorId() {
        return doctorid;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setDoctorId(int doctorid) {
        this.doctorid = doctorid;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Doctor [ID=" + doctorid + ", Name=" + firstname + " " + surname + "]";
    }
}