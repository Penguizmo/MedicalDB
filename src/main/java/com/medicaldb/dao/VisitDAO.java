package com.medicaldb.dao;

import com.medicaldb.model.Visit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitDAO {
    private Connection connection;

    public VisitDAO(Connection connection) {
        this.connection = connection;
    }

    public void createVisit(Visit visit) throws SQLException {
        String sql = "INSERT INTO visit (dateofvisit, symptoms, diagnosisid) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(visit.getDateOfVisit().getTime()));
            statement.setString(2, visit.getSymptoms());
            statement.setInt(3, visit.getDiagnosisId());
            statement.executeUpdate();
        }
    }

    public Visit getVisit(Date dateOfVisit) throws SQLException {
        String sql = "SELECT * FROM visit WHERE dateofvisit = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(dateOfVisit.getTime()));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Visit(
                        resultSet.getDate("dateofvisit"),
                        resultSet.getString("symptoms"),
                        resultSet.getInt("diagnosisid")
                    );
                }
            }
        }
        return null;
    }

    public List<Visit> getAllVisits() throws SQLException {
        List<Visit> visits = new ArrayList<>();
        String sql = "SELECT * FROM visit";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                visits.add(new Visit(
                    resultSet.getDate("dateofvisit"),
                    resultSet.getString("symptoms"),
                    resultSet.getInt("diagnosisid")
                ));
            }
        }
        return visits;
    }

    public void updateVisit(Visit visit, Date dateOfVisit) throws SQLException {
        String sql = "UPDATE visit SET symptoms = ?, diagnosisid = ? WHERE dateofvisit = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, visit.getSymptoms());
            statement.setInt(2, visit.getDiagnosisId());
            statement.setDate(3, new java.sql.Date(dateOfVisit.getTime()));
            statement.executeUpdate();
        }
    }

    public void deleteVisit(Date dateOfVisit) throws SQLException {
        String sql = "DELETE FROM visit WHERE dateofvisit = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(dateOfVisit.getTime()));
            statement.executeUpdate();
        }
    }
}