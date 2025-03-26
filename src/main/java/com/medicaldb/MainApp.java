package com.medicaldb;

import com.medicaldb.dao.*;
import com.medicaldb.model.*;
import com.medicaldb.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public MainApp() {
    }

    public static void main(String[] args) {
        // DoctorDAO tests
        DoctorDAO doctorDAO = new DoctorDAO();
        Doctor doctor = new Doctor(1, "John", "Doe", "123 Main St", "john.doe@example.com");
        doctorDAO.addDoctor(doctor);
        Specialist specialist = new Specialist(2, "Jane", "Smith", "456 Elm St", "jane.smith@example.com", "Cardiology", 10);
        doctorDAO.addDoctor(specialist);
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        for (Doctor doc : doctors) {
            System.out.println(doc);
        }
        System.out.println("Retrieved Doctor: " + doctorDAO.getDoctor(1));
        System.out.println("Retrieved Specialist: " + doctorDAO.getDoctor(2));

        // DrugDAO tests
        DrugDAO drugDAO = new DrugDAO();
        Drug drug = new Drug(1, "Aspirin", "Nausea", "Pain relief");
        drugDAO.addDrug(drug);
        List<Drug> drugs = drugDAO.getAllDrugs();
        for (Drug drg : drugs) {
            System.out.println(drg);
        }
        System.out.println("Retrieved Drug: " + drugDAO.getDrug(1));

        // InsuranceDAO tests
        InsuranceDAO insuranceDAO = new InsuranceDAO();
        Insurance insurance = new Insurance("1", "HealthCo", "789 Pine St", "555-1234");
        insuranceDAO.addInsurance(insurance);
        List<Insurance> insurances = insuranceDAO.getAllInsurances();
        for (Insurance ins : insurances) {
            System.out.println(ins);
        }
        System.out.println("Retrieved Insurance: " + insuranceDAO.getInsurance("1"));

        // PatientDAO tests
        PatientDAO patientDAO = new PatientDAO();
        Patient patient = new Patient(1, "Alice", "Brown", "98765", "321 Oak St", "555-5678", "alice.brown@example.com");
        patientDAO.addPatient(patient);
        InsuredPatient insuredPatient = new InsuredPatient(2, "Bob", "White", "54321", "654 Maple St", "555-8765", "bob.white@example.com", "HealthCo", "Full", 12);
        patientDAO.addPatient(insuredPatient);
        List<Patient> patients = patientDAO.getAllPatients();
        for (Patient pat : patients) {
            System.out.println(pat);
        }
        System.out.println("Retrieved Patient: " + patientDAO.getPatient(1));
        System.out.println("Retrieved Insured Patient: " + patientDAO.getPatient(2));

        // PrescriptionDAO tests
        PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
        Prescription prescription = new Prescription(1, new Date(System.currentTimeMillis()), "2 pills", 7, "Take with food");
        prescriptionDAO.addPrescription(prescription);
        List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
        for (Prescription pres : prescriptions) {
            System.out.println(pres);
        }
        System.out.println("Retrieved Prescription: " + prescriptionDAO.getPrescription(1));

        // VisitDAO tests
        try (Connection connection = DatabaseConnection.getConnection()) {
            VisitDAO visitDAO = new VisitDAO(connection);
            Visit visit = new Visit(new Date(System.currentTimeMillis()), "Cough", 1);
            visitDAO.createVisit(visit);
            List<Visit> visits = visitDAO.getAllVisits();
            for (Visit vis : visits) {
                System.out.println(vis);
            }
            System.out.println("Retrieved Visit: " + visitDAO.getVisit(new Date(System.currentTimeMillis())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}