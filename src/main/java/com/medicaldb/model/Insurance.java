package com.medicaldb.model;

public class Insurance {
    private String insuranceID;
    private String company;
    private String address;
    private String phone;

    // Default constructor
    public Insurance() {}

    // Parameterized constructor
    public Insurance(String insuranceID, String company, String address, String phone) {
        this.insuranceID = insuranceID;
        this.company = company;
        this.address = address;
        this.phone = phone;
    }

    // Getters
    public String getInsuranceId() {
        return insuranceID;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    // Setters
    public void setInsuranceId(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Insurance [ID=" + insuranceID + ", Company=" + company + ", Address=" + address + ", Phone=" + phone + "]";
    }
}