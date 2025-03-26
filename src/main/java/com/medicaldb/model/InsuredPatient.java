package com.medicaldb.model;

// The InsuredPatient class extends the Patient class and adds insurance-related fields.
public class InsuredPatient extends Patient {
    // Private fields to store insurance information
    private String company;
    private String insurancetype;
    private int durationofinsurance;

    // Default constructor
    public InsuredPatient() {}

    // Parameterized constructor to initialize an insured patient with specific details
    public InsuredPatient(int patientId, String firstName, String surname, String postcode, String address, String phone, String email, String company, String insuranceType, int durationOfInsurance) {
        // Call the constructor of the superclass (Patient)
        super(patientId, firstName, surname, postcode, address, phone, email);
        this.company = company;
        this.insurancetype = insuranceType;
        this.durationofinsurance = durationOfInsurance;
    }

    // Getter for company
    public String getCompany() {
        return company;
    }

    // Setter for company
    public void setCompany(String company) {
        this.company = company;
    }

    // Getter for insurancetype
    public String getInsuranceType() {
        return insurancetype;
    }

    // Setter for insurancetype
    public void setInsuranceType(String insuranceType) {
        this.insurancetype = insuranceType;
    }

    // Getter for durationofinsurance
    public int getDurationOfInsurance() {
        return durationofinsurance;
    }

    // Setter for durationofinsurance
    public void setDurationOfInsurance(int durationOfInsurance) {
        this.durationofinsurance = durationOfInsurance;
    }

    // Override the toString method to provide a string representation of the insured patient
    @Override
    public String toString() {
        return "InsuredPatient [ID=" + getPatientId() + ", Name=" + getFirstName() + " " + getSurname() + ", Company=" + company + ", InsuranceType=" + insurancetype + ", DurationOfInsurance=" + durationofinsurance + "]";
    }
}