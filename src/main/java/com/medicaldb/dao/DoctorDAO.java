package com.medicaldb.dao;

import com.medicaldb.model.Doctor;
import com.medicaldb.model.Specialist;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctor (doctorid, firstname, surname, address, email";
        if (doctor instanceof Specialist) {
            sql += ", specialization, experience) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else {
            sql += ") VALUES (?, ?, ?, ?, ?)";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctor.getDoctorId());
            stmt.setString(2, doctor.getFirstName());
            stmt.setString(3, doctor.getSurname());
            stmt.setString(4, doctor.getAddress());
            stmt.setString(5, doctor.getEmail());

            if (doctor instanceof Specialist specialist) {
                stmt.setString(6, specialist.getSpecialization());
                stmt.setInt(7, specialist.getExperience());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Doctor getDoctor(int doctorId) {
        String sql = "SELECT * FROM doctor WHERE doctorid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("specialization") != null) {
                    return new Specialist(
                            rs.getInt("doctorid"),
                            rs.getString("firstName"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("specialization"),
                            rs.getInt("experience")
                    );
                } else {
                    return new Doctor(
                            rs.getInt("doctorid"),
                            rs.getString("firstName"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctor";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (rs.getString("specialization") != null) {
                    doctors.add(new Specialist(
                            rs.getInt("doctorid"),
                            rs.getString("firstName"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("specialization"),
                            rs.getInt("experience")
                    ));
                } else {
                    doctors.add(new Doctor(
                            rs.getInt("doctorid"),
                            rs.getString("firstName"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public void updateDoctor(Doctor doctor) {
        String sql = "UPDATE doctor SET firstname = ?, surname = ?, address = ?, email = ?";
        if (doctor instanceof Specialist) {
            sql += ", specialization = ?, experience = ? WHERE doctorid = ?";
        } else {
            sql += " WHERE doctorid = ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getSurname());
            stmt.setString(3, doctor.getAddress());
            stmt.setString(4, doctor.getEmail());

            if (doctor instanceof Specialist) {
                Specialist specialist = (Specialist) doctor;
                stmt.setString(5, specialist.getSpecialization());
                stmt.setInt(6, specialist.getExperience());
                stmt.setInt(7, doctor.getDoctorId());
            } else {
                stmt.setInt(5, doctor.getDoctorId());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(int doctorId) {
        String sql = "DELETE FROM doctor WHERE doctorid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}