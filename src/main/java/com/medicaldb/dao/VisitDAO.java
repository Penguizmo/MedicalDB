package com.medicaldb.dao;

import com.medicaldb.model.Visit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisitDAO {
    private static final Logger logger = Logger.getLogger(VisitDAO.class.getName());
    private Connection connection;

    public VisitDAO(Connection connection) {
        this.connection = connection;
    }

    public void createVisit(Visit visit) throws SQLException {
        String sql = "INSERT INTO visit (dateofvisit, symptoms, diagnosisid) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(visit.getDateOfVisit().getTime()));
            stmt.setString(2, visit.getSymptoms());
            stmt.setInt(3, visit.getDiagnosisId());
            stmt.executeUpdate();
            logger.log(Level.INFO, "Visit created successfully: {0}", visit);
        }
    }

    public Visit getVisit(Date dateOfVisit) throws SQLException {
        String sql = "SELECT * FROM visit WHERE dateofvisit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(dateOfVisit.getTime()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Visit(
                        rs.getDate("dateofvisit"),
                        rs.getString("symptoms"),
                        rs.getInt("diagnosisid")
                );
            }
        }
        return null;
    }

    public List<Visit> getAllVisits() throws SQLException {
        List<Visit> visits = new ArrayList<>();
        String sql = "SELECT * FROM visit";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                visits.add(new Visit(
                        rs.getDate("dateofvisit"),
                        rs.getString("symptoms"),
                        rs.getInt("diagnosisid")
                ));
            }
        }
        return visits;
    }

    public void updateVisit(Visit visit, Date dateOfVisit) throws SQLException {
        String sql = "UPDATE visit SET symptoms = ?, diagnosisid = ? WHERE dateofvisit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, visit.getSymptoms());
            stmt.setInt(2, visit.getDiagnosisId());
            stmt.setDate(3, new java.sql.Date(dateOfVisit.getTime()));
            stmt.executeUpdate();
            logger.log(Level.INFO, "Visit updated successfully: {0}", visit);
        }
    }

    public void deleteVisit(Date dateOfVisit) throws SQLException {
        String sql = "DELETE FROM visit WHERE dateofvisit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(dateOfVisit.getTime()));
            stmt.executeUpdate();
            logger.log(Level.INFO, "Visit deleted successfully: {0}", dateOfVisit);
        }
    }
}