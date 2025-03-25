package com.medicaldb.dao;

import com.medicaldb.model.Insurance;
import com.medicaldb.model.InsuredPatient;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsuranceDAO {

    // Create
    public void addInsurance(Insurance insurance) {
        String sql = "INSERT INTO insurance (insuranceId, company, address, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insurance.getInsuranceId());
            stmt.setString(2, insurance.getCompany());
            stmt.setString(3, insurance.getAddress());
            stmt.setString(4, insurance.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public Insurance getInsurance(String insuranceId) {
        String sql = "SELECT * FROM insurance WHERE insuranceId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insuranceId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Insurance(
                        rs.getString("insuranceId"),
                        rs.getString("company"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read All
    public List<Insurance> getAllInsurances() {
        List<Insurance> insurances = new ArrayList<>();
        String sql = "SELECT * FROM insurance";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                insurances.add(new Insurance(
                        rs.getString("insuranceId"),
                        rs.getString("company"),
                        rs.getString("address"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insurances;
    }

    // Update
    public void updateInsurance(Insurance insurance) {
        String sql = "UPDATE insurance SET company = ?, address = ?, phone = ? WHERE insuranceId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insurance.getCompany());
            stmt.setString(2, insurance.getAddress());
            stmt.setString(3, insurance.getPhone());
            stmt.setString(4, insurance.getInsuranceId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteInsurance(String insuranceId) {
        String sql = "DELETE FROM insurance WHERE insuranceId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, insuranceId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check if data is from InsuredPatient or Insurance
    public void checkData(Object obj) {
        if (obj instanceof InsuredPatient) {
            InsuredPatient insuredPatient = (InsuredPatient) obj;
            System.out.println("Data is from InsuredPatient: " + insuredPatient.toString());
        } else if (obj instanceof Insurance) {
            Insurance insurance = (Insurance) obj;
            System.out.println("Data is from Insurance: " + insurance.toString());
        } else {
            System.out.println("Unknown data type");
        }
    }
}