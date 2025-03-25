package com.medicaldb.dao;

import com.medicaldb.model.Prescription;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    public void addPrescription(Prescription prescription) {
        String sql = "INSERT INTO prescription (prescriptionid, dateprescribed, dosage, duration, comment) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prescription.getPrescriptionId());
            stmt.setDate(2, new java.sql.Date(prescription.getDatePrescribed().getTime()));
            stmt.setString(3, prescription.getDosage());
            stmt.setInt(4, prescription.getDuration());
            stmt.setString(5, prescription.getComment());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Prescription getPrescription(int prescriptionId) {
        String sql = "SELECT * FROM prescription WHERE prescriptionid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prescriptionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Prescription(
                        rs.getInt("prescriptionid"),
                        rs.getDate("dateprescribed"),
                        rs.getString("dosage"),
                        rs.getInt("duration"),
                        rs.getString("comment")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Prescription> getAllPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM prescription";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                prescriptions.add(new Prescription(
                        rs.getInt("prescriptionid"),
                        rs.getDate("dateprescribed"),
                        rs.getString("dosage"),
                        rs.getInt("duration"),
                        rs.getString("comment")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescriptions;
    }

    public void updatePrescription(Prescription prescription) {
        String sql = "UPDATE prescription SET dateprescribed = ?, dosage = ?, duration = ?, comment = ? WHERE prescriptionid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(prescription.getDatePrescribed().getTime()));
            stmt.setString(2, prescription.getDosage());
            stmt.setInt(3, prescription.getDuration());
            stmt.setString(4, prescription.getComment());
            stmt.setInt(5, prescription.getPrescriptionId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePrescription(int prescriptionId) {
        String sql = "DELETE FROM prescription WHERE prescriptionid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prescriptionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}