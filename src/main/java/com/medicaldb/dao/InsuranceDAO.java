package com.medicaldb.dao;

import com.medicaldb.model.Insurance;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The InsuranceDAO class provides methods to interact with the Insurance table in the database.
 * It includes methods to add, retrieve, update, and delete insurance information.
 */
public class InsuranceDAO {

    /**
     * Inserts a new insurance record into the database.
     *
     * @param insurance the insurance object to be added to the database
     * @throws SQLException if a database access error occurs
     */
    public void addInsurance(Insurance insurance) throws SQLException {
        String sql = "INSERT INTO Insurance (insuranceID, company, address, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insurance.getInsuranceId());
            stmt.setString(2, insurance.getCompany());
            stmt.setString(3, insurance.getAddress());
            stmt.setString(4, insurance.getPhone());
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves all insurance records from the database.
     *
     * @return a list containing all insurance providers in the database
     * @throws SQLException if a database access error occurs
     */
    public List<Insurance> getAllInsurances() throws SQLException {
        List<Insurance> insurances = new ArrayList<>();
        String sql = "SELECT * FROM Insurance";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getString("insuranceID"),
                        rs.getString("company"),
                        rs.getString("address"),
                        rs.getString("phone"));
                insurances.add(insurance);
            }
        }
        return insurances;
    }

    /**
     * Retrieves an insurance record from the database by its ID.
     *
     * @param insuranceId the unique identifier of the insurance provider to retrieve
     * @return the insurance object if found, null otherwise
     * @throws SQLException if a database access error occurs
     */
    public Insurance getInsuranceById(String insuranceId) throws SQLException {
        String sql = "SELECT * FROM Insurance WHERE insuranceID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insuranceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Insurance(
                            rs.getString("insuranceID"),
                            rs.getString("company"),
                            rs.getString("address"),
                            rs.getString("phone"));
                }
            }
        }
        return null;
    }

    /**
     * Updates an existing insurance record in the database.
     *
     * @param insurance the insurance object with updated information
     * @throws SQLException if a database access error occurs
     */
    public void updateInsurance(Insurance insurance) throws SQLException {
        String sql = "UPDATE Insurance SET company = ?, address = ?, phone = ? WHERE insuranceID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insurance.getCompany());
            stmt.setString(2, insurance.getAddress());
            stmt.setString(3, insurance.getPhone());
            stmt.setString(4, insurance.getInsuranceId());
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes an insurance record from the database based on its ID.
     *
     * @param insuranceId the unique identifier of the insurance provider to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteInsurance(String insuranceId) throws SQLException {
        String sql = "DELETE FROM Insurance WHERE insuranceID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insuranceId);
            stmt.executeUpdate();
        }
    }
}