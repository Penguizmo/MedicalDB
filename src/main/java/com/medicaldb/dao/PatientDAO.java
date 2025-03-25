package com.medicaldb.dao;

import com.medicaldb.model.Patient;
import com.medicaldb.model.InsuredPatient;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void addPatient(Patient patient) {
        String sql = "INSERT INTO patient (patientid, firstname, surname, postcode, address, phone, email";
        if (patient instanceof InsuredPatient) {
            sql += ", company, insurancetype, durationofinsurance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            sql += ") VALUES (?, ?, ?, ?, ?, ?, ?)";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Patient getPatient(int patientId) {
        String sql = "SELECT * FROM patient WHERE patientID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("company") != null) {
                    return new InsuredPatient(
                            rs.getInt("patientID"),
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
                            rs.getInt("patientID"),
                            rs.getString("firstName"),
                            rs.getString("surname"),
                            rs.getString("postcode"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (rs.getString("company") != null) {
                    patients.add(new InsuredPatient(
                            rs.getInt("patientId"),
                            rs.getString("firstName"),
                            rs.getString("surname"),
                            rs.getString("postcode"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("company"),
                            rs.getString("insuranceType"),
                            rs.getInt("durationOfInsurance")
                    ));
                } else {
                    patients.add(new Patient(
                            rs.getInt("patientId"),
                            rs.getString("firstName"),
                            rs.getString("surname"),
                            rs.getString("postcode"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE patient SET firstName = ?, surname = ?, postcode = ?, address = ?, phone = ?, email = ?";
        if (patient instanceof InsuredPatient) {
            sql += ", company = ?, insurancetype = ?, durationofinsurance = ? WHERE patientId = ?";
        } else {
            sql += " WHERE patientId = ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
                stmt.setInt(7, patient.getPatientId());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(int patientId) {
        String sql = "DELETE FROM patient WHERE patientId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}