package com.medicaldb.model;

// Import the Doctor class
import com.medicaldb.model.Doctor;

public class Specialist extends Doctor {
    private String specialization;
    private int experience;

    // Constructor
    public Specialist(int doctorId, String firstName, String surname, String address, String email,
                      String specialization, int experience) {
        super(doctorId, firstName, surname, address, email);
        this.specialization = specialization;
        this.experience = experience;
    }

    // Getters
    public String getSpecialization() {
        return specialization;
    }

    public int getExperience() {
        return experience;
    }

    // Setters
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialization=" + specialization + ", Experience=" + experience;
    }
}
