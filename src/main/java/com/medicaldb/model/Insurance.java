package com.medicaldb.model;

// The Insurance class represents an insurance company with its details.
public class Insurance {
    // Private fields to store insurance information
    private String insuranceID;
    private String company;
    private String address;
    private String phone;

    // Default constructor
    public Insurance() {}

    // Parameterized constructor to initialize an insurance with specific details
    public Insurance(String insuranceID, String company, String address, String phone) {
        this.insuranceID = insuranceID;
        this.company = company;
        this.address = address;
        this.phone = phone;
    }

    // Getter for insuranceID
    public String getInsuranceId() {
        return insuranceID;
    }

    // Setter for insuranceID
    public void setInsuranceId(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    // Getter for company
    public String getCompany() {
        return company;
    }

    // Setter for company
    public void setCompany(String company) {
        this.company = company;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter for phone
    public String getPhone() {
        return phone;
    }

    // Setter for phone
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Override the toString method to provide a string representation of the insurance
    @Override
    public String toString() {
        return "Insurance [ID=" + insuranceID + ", Company=" + company + ", Address=" + address + ", Phone=" + phone + "]";
    }
}