package com.medicaldb.dao;

import com.medicaldb.model.Drug;
import com.medicaldb.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugDAO {
    public void create(Drug drug) {
        String sql = "INSERT INTO drug (drugId, drugName, sideEffects, benefits) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, drug.getDrugId());
            pstmt.setString(2, drug.getDrugName());
            pstmt.setString(3, drug.getSideEffects());
            pstmt.setString(4, drug.getBenefits());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating drug: " + e.getMessage());
        }
    }

    public Drug read(int drugId) {
        String sql = "SELECT * FROM drug WHERE drugId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, drugId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Drug(
                    rs.getInt("drugId"),
                    rs.getString("drugName"),
                    rs.getString("sideEffects"),
                    rs.getString("benefits")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error reading drug: " + e.getMessage());
        }
        return null;
    }

    public List<Drug> readAll() {
        List<Drug> drugs = new ArrayList<>();
        String sql = "SELECT * FROM drug";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                drugs.add(new Drug(
                    rs.getInt("drugId"),
                    rs.getString("drugName"),
                    rs.getString("sideEffects"),
                    rs.getString("benefits")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error reading all drugs: " + e.getMessage());
        }
        return drugs;
    }

    public void update(Drug drug) {
        String sql = "UPDATE drug SET drugName = ?, sideEffects = ?, benefits = ? WHERE drugId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, drug.getDrugName());
            pstmt.setString(2, drug.getSideEffects());
            pstmt.setString(3, drug.getBenefits());
            pstmt.setInt(4, drug.getDrugId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating drug: " + e.getMessage());
        }
    }

    public void delete(int drugId) {
        String sql = "DELETE FROM drug WHERE drugId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, drugId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting drug: " + e.getMessage());
        }
    }
}