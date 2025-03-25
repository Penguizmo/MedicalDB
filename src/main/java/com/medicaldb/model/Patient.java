package com.medicaldb.model;

public class Patient {
    private int patientID;
    private String firstname;
    private String surname;
    private String postcode;
    private String address;
    private String phone;
    private String email;

    // Constructors
    public Patient() {}

    public Patient(int patientId, String firstName, String surname, String postcode, String address, String phone, String email) {
        this.patientID = patientId;
        this.firstname = firstName;
        this.surname = surname;
        this.postcode = postcode;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public int getPatientId() {
        return patientID;
    }

    public void setPatientId(int patientId) {
        this.patientID = patientId;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient [ID=" + patientID + ", Name=" + firstname + " " + surname + "]";
    }
}