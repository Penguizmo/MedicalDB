package com.medicaldb.model;

// The Specialist class extends the Doctor class and adds specialization and experience fields.
public class Specialist extends Doctor {
    // Private fields to store specialist's additional information
    private String specialization;
    private int experience;

    // Constructor to initialize a specialist with specific details
    public Specialist(int doctorId, String firstName, String surname, String address, String email,
                      String specialization, int experience) {
        // Call the constructor of the superclass (Doctor)
        super(doctorId, firstName, surname, address, email);
        this.specialization = specialization;
        this.experience = experience;
    }

    // Getter for specialization
    public String getSpecialization() {
        return specialization;
    }

    // Setter for specialization
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Getter for experience
    public int getExperience() {
        return experience;
    }

    // Setter for experience
    public void setExperience(int experience) {
        this.experience = experience;
    }

    // Override the toString method to provide a string representation of the specialist
    @Override
    public String toString() {
        return super.toString() + ", Specialization=" + specialization + ", Experience=" + experience;
    }
}