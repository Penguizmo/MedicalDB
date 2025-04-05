package com.medicaldb.dao;

import com.medicaldb.model.Doctor;
import com.medicaldb.model.Specialist;
import com.medicaldb.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The DoctorDAO class provides methods to interact with the Doctor table in the database.
 * It includes methods to add, retrieve, update, and delete doctors.
 */
public class DoctorDAO {

    /**
     * Adds a new doctor to the database.
     *
     * @param doctor the doctor to be added
     * @throws SQLException if a database access error occurs
     */
    public void addDoctor(Doctor doctor) throws SQLException {
        String sql;
        if (doctor instanceof Specialist) {
            sql = "INSERT INTO Doctor (doctorid, firstname, surname, address, email, hospital, specialization, experience) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO Doctor (doctorid, firstname, surname, address, email, hospital) VALUES (?, ?, ?, ?, ?, ?)";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctor.getDoctorId());
            stmt.setString(2, doctor.getFirstName());
            stmt.setString(3, doctor.getSurname());
            stmt.setString(4, doctor.getAddress());
            stmt.setString(5, doctor.getEmail());
            stmt.setString(6, doctor.getHospital());

            if (doctor instanceof Specialist) {
                Specialist specialist = (Specialist) doctor;
                stmt.setString(7, specialist.getSpecialization());
                stmt.setString(8, specialist.getExperience());
            }

            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves all doctors from the database.
     *
     * @return a list of all doctors
     * @throws SQLException if a database access error occurs
     */
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctor";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int doctorid = rs.getInt("doctorid");
                String firstname = rs.getString("firstname");
                String surname = rs.getString("surname");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String hospital = rs.getString("hospital");
                String specialization = rs.getString("specialization");
                String experience = rs.getString("experience");

                Doctor doctor;
                if (specialization != null) {
                    doctor = new Specialist(doctorid, firstname, surname, address, email, hospital, specialization, experience);
                } else {
                    doctor = new Doctor(doctorid, firstname, surname, address, email, hospital);
                }
                doctors.add(doctor);
            }
        }
        return doctors;
    }

    /**
     * Retrieves a doctor by its ID.
     *
     * @param doctorId the ID of the doctor to retrieve
     * @return the doctor with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Doctor getDoctorById(int doctorId) throws SQLException {
        String sql = "SELECT * FROM Doctor WHERE doctorid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String specialization = rs.getString("specialization");
                    String experience = rs.getString("experience");

                    if (specialization != null) {
                        return new Specialist(
                                rs.getInt("doctorid"),
                                rs.getString("firstname"),
                                rs.getString("surname"),
                                rs.getString("address"),
                                rs.getString("email"),
                                rs.getString("hospital"),
                                specialization, experience);
                    } else {
                        return new Doctor(
                                rs.getInt("doctorid"),
                                rs.getString("firstname"),
                                rs.getString("surname"),
                                rs.getString("address"),
                                rs.getString("email"),
                                rs.getString("hospital"));
                    }
                }
            }
        }
        return null;
    }

    /**
     * Updates the details of an existing doctor in the database.
     *
     * @param doctor the doctor with updated details
     * @throws SQLException if a database access error occurs
     */
    public void updateDoctor(Doctor doctor) throws SQLException {
        String sql;
        if (doctor instanceof Specialist) {
            sql = "UPDATE Doctor SET firstname = ?, surname = ?, address = ?, email = ?, hospital = ?, specialization = ?, experience = ? WHERE doctorid = ?";
        } else {
            sql = "UPDATE Doctor SET firstname = ?, surname = ?, address = ?, email = ?, hospital = ? WHERE doctorid = ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getSurname());
            stmt.setString(3, doctor.getAddress());
            stmt.setString(4, doctor.getEmail());
            stmt.setString(5, doctor.getHospital());

            if (doctor instanceof Specialist) {
                Specialist specialist = (Specialist) doctor;
                stmt.setString(6, specialist.getSpecialization());
                stmt.setString(7, specialist.getExperience());
                stmt.setInt(8, doctor.getDoctorId());
            } else {
                stmt.setInt(6, doctor.getDoctorId());
            }

            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a doctor from the database by its ID.
     *
     * @param doctorid the ID of the doctor to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteDoctor(int doctorid) throws SQLException {
        String sql = "DELETE FROM Doctor WHERE doctorid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorid);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves a list of doctors with a specific specialization.
     *
     * @param specialization the specialization to filter by
     * @return a list of doctors with the specified specialization
     * @throws SQLException if a database access error occurs
     */

    public List<Doctor> getDoctorsBySpecialization(String specialization) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctor WHERE specialization = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, specialization);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int doctorid = rs.getInt("doctorid");
                    String firstname = rs.getString("firstname");
                    String surname = rs.getString("surname");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String hospital = rs.getString("hospital");
                    String experience = rs.getString("experience");

                    doctors.add(new Specialist(doctorid, firstname, surname, address, email, hospital, specialization, experience));
                }
            }
        }
        return doctors;
    }

    /**
     * Retrieves a list of all specializations available in the database.
     *
     * @return a list of all specializations
     * @throws SQLException if a database access error occurs
     */

    public List<String> getAllSpecializations() throws SQLException {
        List<String> specializations = new ArrayList<>();
        String sql = "SELECT DISTINCT specialization FROM Doctor";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                specializations.add(rs.getString("specialization"));
            }
        }
        return specializations;
    }

    /**
     * Retrieves a list of all hospitals available in the database.
     *
     * @return a list of all hospitals
     * @throws SQLException if a database access error occurs
     */

    public List<String> getAllHospitals() throws SQLException {
        List<String> hospitals = new ArrayList<>();
        String sql = "SELECT DISTINCT hospital FROM Doctor";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                hospitals.add(rs.getString("hospital"));
            }
        }
        return hospitals;
    }

    /**
     * Retrieves a list of all doctors with a specific hospital affiliation.
     *
     * @param hospital the hospital to filter by
     * @return a list of doctors affiliated with the specified hospital
     * @throws SQLException if a database access error occurs
     */

    public List<Doctor> getDoctorsByHospital(String hospital) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctor WHERE hospital = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hospital);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int doctorid = rs.getInt("doctorid");
                    String firstname = rs.getString("firstname");
                    String surname = rs.getString("surname");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String specialization = rs.getString("specialization");
                    String experience = rs.getString("experience");

                    doctors.add(new Specialist(doctorid, firstname, surname, address, email, hospital, specialization, experience));
                }
            }
        }
        return doctors;
    }

    /**
     * Retrieves a list of all doctors with a specific experience level.
     *
     * @param experience the experience level to filter by
     * @return a list of doctors with the specified experience level
     * @throws SQLException if a database access error occurs
     */

    public List<Doctor> getDoctorsByExperience(String experience) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctor WHERE experience = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, experience);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int doctorid = rs.getInt("doctorid");
                    String firstname = rs.getString("firstname");
                    String surname = rs.getString("surname");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String hospital = rs.getString("hospital");
                    String specialization = rs.getString("specialization");

                    doctors.add(new Specialist(doctorid, firstname, surname, address, email, hospital, specialization, experience));
                }
            }
        }
        return doctors;
    }

    /**
     * Retrieves a list of all doctors with a specific name.
     *
     * @param name the name to filter by
     * @return a list of doctors with the specified name
     * @throws SQLException if a database access error occurs
     */

    public List<Doctor> getDoctorsByName(String name) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctor WHERE firstname LIKE ? OR surname LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int doctorid = rs.getInt("doctorid");
                    String firstname = rs.getString("firstname");
                    String surname = rs.getString("surname");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String hospital = rs.getString("hospital");
                    String specialization = rs.getString("specialization");
                    String experience = rs.getString("experience");

                    doctors.add(new Specialist(doctorid, firstname, surname, address, email, hospital, specialization, experience));
                }
            }
        }
        return doctors;
    }
}