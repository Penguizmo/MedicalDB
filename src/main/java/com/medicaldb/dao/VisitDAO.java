package com.medicaldb.dao;

import com.medicaldb.model.Visit;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The VisitDAO class provides methods to interact with the Visit table in the database.
 * It includes methods to add, retrieve, update, and delete visit information.
 */
public class VisitDAO {
    private static final Logger logger = Logger.getLogger(VisitDAO.class.getName());

    /**
     * Constructor with connection parameter. This is used when a specific connection is provided.
     *
     * @param connection the database connection to be used
     */
    public VisitDAO(Connection connection) {
    }

    /**
     * Default constructor. This is used when no specific connection is provided.
     */
    public VisitDAO() {
    }

    /**
     * Adds a new visit to the database.
     *
     * @param visit the visit to be added
     * @throws SQLException if a database access error occurs
     */
    public void createVisit(Visit visit) throws SQLException {
        String sql = "INSERT INTO visit (dateofvisit, symptoms, diagnosisid, doctorid, patientid) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(visit.getDateOfVisit().getTime()));
            stmt.setString(2, visit.getSymptoms());
            stmt.setString(3, visit.getDiagnosis());
            stmt.setInt(4, visit.getDoctorId());
            stmt.setInt(5, visit.getPatientId());
            stmt.executeUpdate();
            logger.log(Level.INFO, "Visit created successfully: {0}", visit);
        }
    }

    /**
     * Retrieves a visit by its date.
     *
     * @param dateOfVisit the date of the visit to retrieve
     * @return the visit with the specified date, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Visit getVisit(Date dateOfVisit) throws SQLException {
        String sql = "SELECT * FROM visit WHERE dateofvisit = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(dateOfVisit.getTime()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Visit(
                        rs.getDate("dateofvisit"),
                        rs.getString("symptoms"),
                        rs.getString("diagnosisid"),
                        rs.getInt("doctorid"),
                        rs.getInt("patientid")
                );
            }
        }
        return null;
    }

    /**
     * Retrieves all visits from the database.
     *
     * @return a list of all visits
     * @throws SQLException if a database access error occurs
     */
    public List<Visit> getAllVisits() throws SQLException {
        List<Visit> visits = new ArrayList<>();
        String sql = "SELECT * FROM visit";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                visits.add(new Visit(
                        rs.getDate("dateofvisit"),
                        rs.getString("symptoms"),
                        rs.getString("diagnosisid"),
                        rs.getInt("doctorid"),
                        rs.getInt("patientid")
                ));
            }
        }
        return visits;
    }

    /**
     * Updates the details of an existing visit in the database.
     *
     * @param visit the visit with updated details
     * @param dateOfVisit the date of the visit to update
     * @throws SQLException if a database access error occurs
     */
    public void updateVisit(Visit visit, Date dateOfVisit) throws SQLException {
        String sql = "UPDATE visit SET symptoms = ?, diagnosisid = ?, doctorid = ?, patientid = ? WHERE dateofvisit = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, visit.getSymptoms());
            stmt.setString(2, visit.getDiagnosis());
            stmt.setInt(3, visit.getDoctorId());
            stmt.setInt(4, visit.getPatientId());
            stmt.setDate(5, new java.sql.Date(dateOfVisit.getTime()));
            stmt.executeUpdate();
            logger.log(Level.INFO, "Visit updated successfully: {0}", visit);
        }
    }

    /**
     * Deletes a visit from the database by its date.
     *
     * @param dateOfVisit the date of the visit to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteVisit(Date dateOfVisit) throws SQLException {
        String sql = "DELETE FROM visit WHERE dateofvisit = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(dateOfVisit.getTime()));
            stmt.executeUpdate();
            logger.log(Level.INFO, "Visit deleted successfully: {0}", dateOfVisit);
        }
    }
}