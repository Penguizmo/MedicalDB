package com.medicaldb.dao;

import com.medicaldb.model.Drug;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DrugDAO {
    private static final Logger logger = Logger.getLogger(DrugDAO.class.getName());
    private Connection connection;

    public DrugDAO(Connection connection) {
        this.connection = connection;
    }

    public void addDrug(Drug drug) {
        String sql = "INSERT INTO drug (drugid, drugname, sideeffects, benefits) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, drug.getDrugId());
            stmt.setString(2, drug.getDrugName());
            stmt.setString(3, drug.getSideEffects());
            stmt.setString(4, drug.getBenefits());
            stmt.executeUpdate();
            logger.log(Level.INFO, "Drug added successfully: {0}", drug);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding drug", e);
        }
    }

    public Drug getDrug(int drugId) {
        String sql = "SELECT * FROM drug WHERE drugid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, drugId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Drug(
                        rs.getInt("drugid"),
                        rs.getString("drugname"),
                        rs.getString("sideeffects"),
                        rs.getString("benefits")
                );
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving drug", e);
        }
        return null;
    }

    public List<Drug> getAllDrugs() {
        List<Drug> drugs = new ArrayList<>();
        String sql = "SELECT * FROM drug";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                drugs.add(new Drug(
                        rs.getInt("drugid"),
                        rs.getString("drugname"),
                        rs.getString("sideeffects"),
                        rs.getString("benefits")
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving all drugs", e);
        }
        return drugs;
    }

    public void updateDrug(Drug drug) {
        String sql = "UPDATE drug SET drugname = ?, sideeffects = ?, benefits = ? WHERE drugid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, drug.getDrugName());
            stmt.setString(2, drug.getSideEffects());
            stmt.setString(3, drug.getBenefits());
            stmt.setInt(4, drug.getDrugId());
            stmt.executeUpdate();
            logger.log(Level.INFO, "Drug updated successfully: {0}", drug);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating drug", e);
        }
    }

    public void deleteDrug(int drugId) {
        String sql = "DELETE FROM drug WHERE drugid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, drugId);
            stmt.executeUpdate();
            logger.log(Level.INFO, "Drug deleted successfully: {0}", drugId);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting drug", e);
        }
    }
}