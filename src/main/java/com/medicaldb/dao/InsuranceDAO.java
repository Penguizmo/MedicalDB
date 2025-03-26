package com.medicaldb.dao;

import com.medicaldb.model.Insurance;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsuranceDAO {
    private static final Logger logger = Logger.getLogger(InsuranceDAO.class.getName());
    private Connection connection;

    public InsuranceDAO(Connection connection) {
        this.connection = connection;
    }

    public void addInsurance(Insurance insurance) {
        String sql = "INSERT INTO insurance (insuranceid, company, address, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insurance.getInsuranceId());
            stmt.setString(2, insurance.getCompany());
            stmt.setString(3, insurance.getAddress());
            stmt.setString(4, insurance.getPhone());
            stmt.executeUpdate();
            logger.log(Level.INFO, "Insurance added successfully: {0}", insurance);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding insurance", e);
        }
    }

    public Insurance getInsurance(String insuranceId) {
        String sql = "SELECT * FROM insurance WHERE insuranceid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insuranceId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Insurance(
                        rs.getString("insuranceid"),
                        rs.getString("company"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving insurance", e);
        }
        return null;
    }

    public List<Insurance> getAllInsurances() {
        List<Insurance> insurances = new ArrayList<>();
        String sql = "SELECT * FROM insurance";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                insurances.add(new Insurance(
                        rs.getString("insuranceid"),
                        rs.getString("company"),
                        rs.getString("address"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving all insurances", e);
        }
        return insurances;
    }

    public void updateInsurance(Insurance insurance) {
        String sql = "UPDATE insurance SET company = ?, address = ?, phone = ? WHERE insuranceid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insurance.getCompany());
            stmt.setString(2, insurance.getAddress());
            stmt.setString(3, insurance.getPhone());
            stmt.setString(4, insurance.getInsuranceId());
            stmt.executeUpdate();
            logger.log(Level.INFO, "Insurance updated successfully: {0}", insurance);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating insurance", e);
        }
    }

    public void deleteInsurance(String insuranceId) {
        String sql = "DELETE FROM insurance WHERE insuranceid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insuranceId);
            stmt.executeUpdate();
            logger.log(Level.INFO, "Insurance deleted successfully: {0}", insuranceId);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting insurance", e);
        }
    }
}