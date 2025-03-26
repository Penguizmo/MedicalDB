package com.medicaldb.dao;

import com.medicaldb.model.Doctor;
import com.medicaldb.model.Specialist;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDAO {
    private static final Logger logger = Logger.getLogger(DoctorDAO.class.getName());
    private Connection connection;

    public DoctorDAO(Connection connection) {
        this.connection = connection;
    }

    public void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctor (doctorid, firstname, surname, address, email, specialization, experience) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctor.getDoctorId());
            stmt.setString(2, doctor.getFirstName());
            stmt.setString(3, doctor.getSurname());
            stmt.setString(4, doctor.getAddress());
            stmt.setString(5, doctor.getEmail());
            if (doctor instanceof Specialist) {
                Specialist specialist = (Specialist) doctor;
                stmt.setString(6, specialist.getSpecialization());
                stmt.setInt(7, specialist.getExperience());
            } else {
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.INTEGER);
            }
            stmt.executeUpdate();
            logger.log(Level.INFO, "Doctor added successfully: {0}", doctor);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding doctor", e);
        }
    }

    public Doctor getDoctor(int doctorId) {
        String sql = "SELECT * FROM doctor WHERE doctorid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("specialization") != null) {
                    return new Specialist(
                            rs.getInt("doctorid"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("specialization"),
                            rs.getInt("experience")
                    );
                } else {
                    return new Doctor(
                            rs.getInt("doctorid"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving doctor", e);
        }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctor";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (rs.getString("specialization") != null) {
                    doctors.add(new Specialist(
                            rs.getInt("doctorid"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("specialization"),
                            rs.getInt("experience")
                    ));
                } else {
                    doctors.add(new Doctor(
                            rs.getInt("doctorid"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving all doctors", e);
        }
        return doctors;
    }

    public void updateDoctor(Doctor doctor) {
        String sql = "UPDATE doctor SET firstname = ?, surname = ?, address = ?, email = ?, specialization = ?, experience = ? WHERE doctorid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getSurname());
            stmt.setString(3, doctor.getAddress());
            stmt.setString(4, doctor.getEmail());
            if (doctor instanceof Specialist) {
                Specialist specialist = (Specialist) doctor;
                stmt.setString(5, specialist.getSpecialization());
                stmt.setInt(6, specialist.getExperience());
            } else {
                stmt.setNull(5, Types.VARCHAR);
                stmt.setNull(6, Types.INTEGER);
            }
            stmt.setInt(7, doctor.getDoctorId());
            stmt.executeUpdate();
            logger.log(Level.INFO, "Doctor updated successfully: {0}", doctor);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating doctor", e);
        }
    }

    public void deleteDoctor(int doctorId) {
        String sql = "DELETE FROM doctor WHERE doctorid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            stmt.executeUpdate();
            logger.log(Level.INFO, "Doctor deleted successfully: {0}", doctorId);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting doctor", e);
        }
    }
}