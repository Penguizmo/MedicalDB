package com.medicaldb.dao;

import com.medicaldb.model.Patient;
import com.medicaldb.model.InsuredPatient;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientDAO {
    private static final Logger logger = Logger.getLogger(PatientDAO.class.getName());
    private Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    public void addPatient(Patient patient) {
        String sql = "INSERT INTO patient (patientid, firstname, surname, postcode, address, phone, email, company, insurancetype, durationofinsurance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getPatientId());
            stmt.setString(2, patient.getFirstName());
            stmt.setString(3, patient.getSurname());
            stmt.setString(4, patient.getPostcode());
            stmt.setString(5, patient.getAddress());
            stmt.setString(6, patient.getPhone());
            stmt.setString(7, patient.getEmail());
            if (patient instanceof InsuredPatient) {
                InsuredPatient insuredPatient = (InsuredPatient) patient;
                stmt.setString(8, insuredPatient.getCompany());
                stmt.setString(9, insuredPatient.getInsuranceType());
                stmt.setInt(10, insuredPatient.getDurationOfInsurance());
            } else {
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.VARCHAR);
                stmt.setNull(10, Types.INTEGER);
            }
            stmt.executeUpdate();
            logger.log(Level.INFO, "Patient added successfully: {0}", patient);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding patient", e);
        }
    }

    public Patient getPatient(int patientId) {
        String sql = "SELECT * FROM patient WHERE patientid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("company") != null) {
                    return new InsuredPatient(
                            rs.getInt("patientid"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("postcode"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("company"),
                            rs.getString("insurancetype"),
                            rs.getInt("durationofinsurance")
                    );
                } else {
                    return new Patient(
                            rs.getInt("patientid"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("postcode"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving patient", e);
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (rs.getString("company") != null) {
                    patients.add(new InsuredPatient(
                            rs.getInt("patientid"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("postcode"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("company"),
                            rs.getString("insurancetype"),
                            rs.getInt("durationofinsurance")
                    ));
                } else {
                    patients.add(new Patient(
                            rs.getInt("patientid"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("postcode"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving all patients", e);
        }
        return patients;
    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE patient SET firstname = ?, surname = ?, postcode = ?, address = ?, phone = ?, email = ?, company = ?, insurancetype = ?, durationofinsurance = ? WHERE patientid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getSurname());
            stmt.setString(3, patient.getPostcode());
            stmt.setString(4, patient.getAddress());
            stmt.setString(5, patient.getPhone());
            stmt.setString(6, patient.getEmail());
            if (patient instanceof InsuredPatient) {
                InsuredPatient insuredPatient = (InsuredPatient) patient;
                stmt.setString(7, insuredPatient.getCompany());
                stmt.setString(8, insuredPatient.getInsuranceType());
                stmt.setInt(9, insuredPatient.getDurationOfInsurance());
                stmt.setInt(10, patient.getPatientId());
            } else {
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.INTEGER);
                stmt.setInt(10, patient.getPatientId());
            }
            stmt.executeUpdate();
            logger.log(Level.INFO, "Patient updated successfully: {0}", patient);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating patient", e);
        }
    }

    public void deletePatient(int patientId) {
        String sql = "DELETE FROM patient WHERE patientid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
            logger.log(Level.INFO, "Patient deleted successfully: {0}", patientId);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting patient", e);
        }
    }
}