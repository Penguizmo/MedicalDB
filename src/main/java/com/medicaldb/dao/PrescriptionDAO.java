package com.medicaldb.dao;

import com.medicaldb.model.Prescription;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The PrescriptionDAO class provides methods to interact with the Prescription table in the database.
 * It includes methods to add, retrieve, update, and delete prescription information.
 */
public class PrescriptionDAO {
    private static final Logger logger = Logger.getLogger(PrescriptionDAO.class.getName());
    private Connection connection;

    /**
     * Constructor with connection parameter. This is used when a specific connection is provided.
     *
     * @param connection the database connection to be used
     */
    public PrescriptionDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Default constructor. This is used when no specific connection is provided.
     */
    public PrescriptionDAO() {
    }

    /**
     * Adds a new prescription to the database.
     *
     * @param prescription the prescription to be added
     * @throws SQLException if a database access error occurs
     */
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
            logger.log(Level.INFO, "Prescription added successfully: {0}", prescription);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding prescription", e);
        }
    }

    /**
     * Retrieves a prescription by its ID.
     *
     * @param prescriptionId the ID of the prescription to retrieve
     * @return the prescription with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
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
            logger.log(Level.SEVERE, "Error retrieving prescription", e);
        }
        return null;
    }

    /**
     * Retrieves all prescriptions from the database.
     *
     * @return a list of all prescriptions
     * @throws SQLException if a database access error occurs
     */
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
            logger.log(Level.SEVERE, "Error retrieving all prescriptions", e);
        }
        return prescriptions;
    }

    /**
     * Updates the details of an existing prescription in the database.
     *
     * @param prescription the prescription with updated details
     * @throws SQLException if a database access error occurs
     */
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
            logger.log(Level.INFO, "Prescription updated successfully: {0}", prescription);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating prescription", e);
        }
    }

    /**
     * Deletes a prescription from the database by its ID.
     *
     * @param prescriptionId the ID of the prescription to delete
     * @throws SQLException if a database access error occurs
     */
    public void deletePrescription(int prescriptionId) {
        String sql = "DELETE FROM prescription WHERE prescriptionid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prescriptionId);
            stmt.executeUpdate();
            logger.log(Level.INFO, "Prescription deleted successfully: {0}", prescriptionId);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting prescription", e);
        }
    }
}