package com.medicaldb.model;

public class InsuredPatient extends Patient {
    private String company;
    private String insurancetype;
    private int durationofinsurance;

    // Default constructor
    public InsuredPatient() {}

    // Parameterized constructor
    public InsuredPatient(int patientId, String firstName, String surname, String postcode, String address, String phone, String email, String company, String insuranceType, int durationOfInsurance) {
        super(patientId, firstName, surname, postcode, address, phone, email);
        this.company = company;
        this.insurancetype = insuranceType;
        this.durationofinsurance = durationOfInsurance;
    }

    // Getters and Setters
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInsuranceType() {
        return insurancetype;
    }

    public void setInsuranceType(String insuranceType) {
        this.insurancetype = insuranceType;
    }

    public int getDurationOfInsurance() {
        return durationofinsurance;
    }

    public void setDurationOfInsurance(int durationOfInsurance) {
        this.durationofinsurance = durationOfInsurance;
    }

    @Override
    public String toString() {
        return "InsuredPatient [ID=" + getPatientId() + ", Name=" + getFirstName() + " " + getSurname() + ", Company=" + company + ", InsuranceType=" + insurancetype + ", DurationOfInsurance=" + durationofinsurance + "]";
    }
}