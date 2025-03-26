package com.medicaldb.controller;

import com.medicaldb.dao.DoctorDAO;
import com.medicaldb.model.Doctor;
import com.medicaldb.model.Specialist;
import com.medicaldb.util.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class DoctorController {

    @FXML
    private TextField doctorSearchField;
    @FXML
    private TextField doctorIdField;
    @FXML
    private TextField doctorFirstNameField;
    @FXML
    private TextField doctorSurnameField;
    @FXML
    private TextField doctorAddressField;
    @FXML
    private TextField doctorEmailField;
    @FXML
    private TextField doctorSpecializationField;
    @FXML
    private TextField doctorExperienceField;
    @FXML
    private TableView<Doctor> doctorTableView;

    private DoctorDAO doctorDAO;

    public DoctorController() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            this.doctorDAO = new DoctorDAO(connection);
        } catch (SQLException e) {
            showAlert("Database Connection Error", "Unable to connect to the database.");
        }
    }

    @FXML
    private void onSearchDoctor() {
        if (isValidInput(doctorSearchField.getText())) {
            int doctorId = Integer.parseInt(doctorSearchField.getText());
            Doctor doctor = doctorDAO.getDoctor(doctorId);
            if (doctor != null) {
                // Update UI with doctor details
            }
        }
    }

    @FXML
    private void onSaveDoctor() {
        if (isValidInput(doctorIdField.getText(), doctorFirstNameField.getText(), doctorSurnameField.getText(), doctorAddressField.getText(), doctorEmailField.getText(), doctorExperienceField.getText())) {
            int doctorId = Integer.parseInt(doctorIdField.getText());
            String firstName = doctorFirstNameField.getText();
            String surname = doctorSurnameField.getText();
            String address = doctorAddressField.getText();
            String email = doctorEmailField.getText();
            String specialization = doctorSpecializationField.getText();
            int experience = Integer.parseInt(doctorExperienceField.getText());

            Doctor doctor;
            if (!specialization.isEmpty()) {
                doctor = new Specialist(doctorId, firstName, surname, address, email, specialization, experience);
            } else {
                doctor = new Doctor(doctorId, firstName, surname, address, email);
            }
            doctorDAO.addDoctor(doctor);
        }
    }

    @FXML
    private void onUpdateDoctor() {
        if (isValidInput(doctorIdField.getText(), doctorFirstNameField.getText(), doctorSurnameField.getText(), doctorAddressField.getText(), doctorEmailField.getText(), doctorExperienceField.getText())) {
            int doctorId = Integer.parseInt(doctorIdField.getText());
            String firstName = doctorFirstNameField.getText();
            String surname = doctorSurnameField.getText();
            String address = doctorAddressField.getText();
            String email = doctorEmailField.getText();
            String specialization = doctorSpecializationField.getText();
            int experience = Integer.parseInt(doctorExperienceField.getText());

            Doctor doctor;
            if (!specialization.isEmpty()) {
                doctor = new Specialist(doctorId, firstName, surname, address, email, specialization, experience);
            } else {
                doctor = new Doctor(doctorId, firstName, surname, address, email);
            }
            doctorDAO.updateDoctor(doctor);
        }
    }

    @FXML
    private void onDeleteDoctor() {
        if (isValidInput(doctorIdField.getText())) {
            int doctorId = Integer.parseInt(doctorIdField.getText());
            doctorDAO.deleteDoctor(doctorId);
        }
    }

    private boolean isValidInput(String... inputs) {
        for (String input : inputs) {
            if (input == null || input.trim().isEmpty()) {
                showAlert("Invalid Input", "Please fill all the fields correctly.");
                return false;
            }
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}