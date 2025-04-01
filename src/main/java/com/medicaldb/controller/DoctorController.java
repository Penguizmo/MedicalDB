package com.medicaldb.controller;

import com.medicaldb.dao.DoctorDAO;
import com.medicaldb.model.Doctor;
import com.medicaldb.model.Specialist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorController {

    // TextField for entering the doctor ID to search
    @FXML
    private TextField doctorSearchField;

    // TextField for entering the doctor ID
    @FXML
    private TextField doctorIdField;

    // TextField for entering the doctor's first name
    @FXML
    private TextField doctorFirstNameField;

    // TextField for entering the doctor's surname
    @FXML
    private TextField doctorSurnameField;

    // TextField for entering the doctor's address
    @FXML
    private TextField doctorAddressField;

    // TextField for entering the doctor's email
    @FXML
    private TextField doctorEmailField;

    // TextField for entering the doctor's hospital
    @FXML
    private TextField doctorHospitalField;

    // TextField for entering the specialist's specialization, initially hidden
    @FXML
    private TextField doctorSpecializationField;

    // TextField for entering the specialist's experience, initially hidden
    @FXML
    private TextField doctorExperienceField;

    // CheckBox to indicate if the doctor is a specialist
    @FXML
    private CheckBox specialistCheckBox;

    // TableView to display the list of doctors
    @FXML
    private TableView<Doctor> doctorTableView;

    // TableColumn for displaying doctor's ID
    @FXML
    private TableColumn<Doctor, Integer> doctorIdColumn;

    // TableColumn for displaying doctor's first name
    @FXML
    private TableColumn<Doctor, String> doctorFirstNameColumn;

    // TableColumn for displaying doctor's surname
    @FXML
    private TableColumn<Doctor, String> doctorSurnameColumn;

    // TableColumn for displaying doctor's address
    @FXML
    private TableColumn<Doctor, String> doctorAddressColumn;

    // TableColumn for displaying doctor's email
    @FXML
    private TableColumn<Doctor, String> doctorEmailColumn;

    // TableColumn for displaying doctor's hospital
    @FXML
    private TableColumn<Doctor, String> doctorHospitalColumn;

    // TableColumn for displaying specialist's specialization
    @FXML
    private TableColumn<Doctor, String> doctorSpecializationColumn;

    // TableColumn for displaying specialist's experience
    @FXML
    private TableColumn<Doctor, Integer> doctorExperienceColumn;

    // Data Access Object for performing database operations related to doctors
    private DoctorDAO doctorDAO;

    // Method called when the controller is initialized
    @FXML
    private void initialize() {
        // Create a new instance of DoctorDAO to interact with the database
        doctorDAO = new DoctorDAO();

        // Set up the table columns to use the appropriate fields from the Doctor class
        doctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        doctorFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        doctorSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        doctorAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        doctorEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        doctorHospitalColumn.setCellValueFactory(new PropertyValueFactory<>("hospital"));
        doctorSpecializationColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        doctorExperienceColumn.setCellValueFactory(new PropertyValueFactory<>("experience"));

        // Load the data of all doctors into the table
        loadDoctorData();
    }

    // Method to search for a doctor by ID
    @FXML
    private void onSearchDoctor() {
        // Check if the input in the search field is valid
        if (isValidInput(doctorSearchField.getText())) {
            // Parse the doctor ID from the search field
            int doctorId = Integer.parseInt(doctorSearchField.getText());
            // Retrieve the doctor from the database using the doctor ID
            Doctor doctor = doctorDAO.getDoctor(doctorId);
            // If the doctor is found, populate the fields with the doctor's details
            if (doctor != null) {
                populateDoctorFields(doctor);
            } else {
                // Show an alert if no doctor is found with the given ID
                showAlert("Doctor Not Found", "No doctor found with ID: " + doctorId);
            }
        }
    }

    // Method to add a new doctor
    @FXML
    private void onSaveDoctor() {
        // Check if the input in the fields is valid
        if (isValidInput(doctorIdField.getText(), doctorFirstNameField.getText(), doctorSurnameField.getText(), doctorAddressField.getText(), doctorEmailField.getText(), doctorHospitalField.getText())) {
            // Parse the doctor ID from the field
            int doctorId = Integer.parseInt(doctorIdField.getText());
            // Get the doctor's first name from the field
            String firstName = doctorFirstNameField.getText();
            // Get the doctor's surname from the field
            String surname = doctorSurnameField.getText();
            // Get the doctor's address from the field
            String address = doctorAddressField.getText();
            // Get the doctor's email from the field
            String email = doctorEmailField.getText();
            // Get the doctor's hospital from the field
            String hospital = doctorHospitalField.getText();

            // Create a Doctor object to store the doctor's details
            Doctor doctor;
            // If the specialist checkbox is selected, create a Specialist object
            if (specialistCheckBox.isSelected()) {
                // Get the specialist's specialization from the field
                String specialization = doctorSpecializationField.getText();
                // Parse the specialist's experience from the field
                int experience = Integer.parseInt(doctorExperienceField.getText());
                // Create a Specialist object with the doctor's details
                doctor = new Specialist(doctorId, firstName, surname, address, email, hospital, specialization, experience);
            } else {
                // Create a Doctor object with the doctor's details
                doctor = new Doctor(doctorId, firstName, surname, address, email, hospital);
            }

            // Add the doctor to the database
            doctorDAO.addDoctor(doctor);
            // Show an alert indicating the doctor was added successfully
            showAlert("Success", "Doctor added successfully.");
            // Reload the data of all doctors into the table
            loadDoctorData();
        }
    }

    // Method to update an existing doctor
    @FXML
    private void onUpdateDoctor() {
        // Check if the input in the fields is valid
        if (isValidInput(doctorIdField.getText(), doctorFirstNameField.getText(), doctorSurnameField.getText(), doctorAddressField.getText(), doctorEmailField.getText(), doctorHospitalField.getText())) {
            // Parse the doctor ID from the field
            int doctorId = Integer.parseInt(doctorIdField.getText());
            // Get the doctor's first name from the field
            String firstName = doctorFirstNameField.getText();
            // Get the doctor's surname from the field
            String surname = doctorSurnameField.getText();
            // Get the doctor's address from the field
            String address = doctorAddressField.getText();
            // Get the doctor's email from the field
            String email = doctorEmailField.getText();
            // Get the doctor's hospital from the field
            String hospital = doctorHospitalField.getText();

            // Create a Doctor object to store the doctor's details
            Doctor doctor;
            // If the specialist checkbox is selected, create a Specialist object
            if (specialistCheckBox.isSelected()) {
                // Get the specialist's specialization from the field
                String specialization = doctorSpecializationField.getText();
                // Parse the specialist's experience from the field
                int experience = Integer.parseInt(doctorExperienceField.getText());
                // Create a Specialist object with the doctor's details
                doctor = new Specialist(doctorId, firstName, surname, address, email, hospital, specialization, experience);
            } else {
                // Create a Doctor object with the doctor's details
                doctor = new Doctor(doctorId, firstName, surname, address, email, hospital);
            }

            // Update the doctor in the database
            doctorDAO.updateDoctor(doctor);
            // Show an alert indicating the doctor was updated successfully
            showAlert("Success", "Doctor updated successfully.");
            // Reload the data of all doctors into the table
            loadDoctorData();
        }
    }

    // Method to delete a doctor
    @FXML
    private void onDeleteDoctor() {
        // Check if the input in the doctor ID field is valid
        if (isValidInput(doctorIdField.getText())) {
            // Parse the doctor ID from the field
            int doctorId = Integer.parseInt(doctorIdField.getText());
            // Delete the doctor from the database using the doctor ID
            doctorDAO.deleteDoctor(doctorId);
            // Show an alert indicating the doctor was deleted successfully
            showAlert("Success", "Doctor deleted successfully.");
            // Reload the data of all doctors into the table
            loadDoctorData();
        }
    }

    // Method to handle the specialist checkbox selection
    @FXML
    private void onSpecialistCheckBox() {
        // Check if the specialist checkbox is selected
        boolean isSelected = specialistCheckBox.isSelected();
        // Show or hide the specialization and experience fields based on the checkbox selection
        doctorSpecializationField.setVisible(isSelected);
        doctorExperienceField.setVisible(isSelected);
    }

    // Method to navigate to the main page
    @FXML
    private void handleMainPage() {
        // Call the method to navigate to the specified FXML file
        navigateToPage("/fxml/Pages/main.fxml");
    }

    // Method to navigate to the drugs page
    @FXML
    private void handleDrugs() {
        // Call the method to navigate to the specified FXML file
        navigateToPage("/fxml/Pages/DrugTable.fxml");
    }

    // Method to navigate to the insurance companies page
    @FXML
    private void handleInsuranceCompanies() {
        // Call the method to navigate to the specified FXML file
        navigateToPage("/fxml/Pages/InsuranceTable.fxml");
    }

    // Method to navigate to the patients page
    @FXML
    private void handlePatients() {
        // Call the method to navigate to the specified FXML file
        navigateToPage("/fxml/Pages/PatientTable.fxml");
    }

    // Method to navigate to the prescriptions page
    @FXML
    private void handlePrescriptions() {
        // Call the method to navigate to the specified FXML file
        navigateToPage("/fxml/Pages/PrescriptionTable.fxml");
    }

    // Method to navigate to the visits page
    @FXML
    private void handleVisits() {
        // Call the method to navigate to the specified FXML file
        navigateToPage("/fxml/Pages/VisitTable.fxml");
    }

    // Method to navigate to a specified FXML file
    private void navigateToPage(String fxmlFilePath) {
        try {
            // Load the specified FXML file
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            // Get the current stage from the scene
            Stage stage = (Stage) doctorTableView.getScene().getWindow();
            // Set the new scene with the loaded FXML content
            stage.setScene(new Scene(root));
            // Show the stage with the new scene
            stage.show();
        } catch (IOException e) {
            // Show an alert if there is an error loading the page
            showAlert("Navigation Error", "Unable to load the page: " + fxmlFilePath);
        }
    }

    // Method to check if the input fields are valid
    private boolean isValidInput(String... inputs) {
        // Iterate over the input fields
        for (String input : inputs) {
            // Check if the input is null or empty
            if (input == null || input.trim().isEmpty()) {
                // Show an alert indicating the input is invalid
                showAlert("Invalid Input", "Please fill all the fields correctly.");
                return false;
            }
        }
        return true;
    }

    // Method to show an alert with a specified title and message
    private void showAlert(String title, String message) {
        // Create a new alert of type INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Set the title of the alert
        alert.setTitle(title);
        // Set the header text of the alert to null
        alert.setHeaderText(null);
        // Set the content text of the alert
        alert.setContentText(message);
        // Show the alert and wait for the user to close it
        alert.showAndWait();
    }

    // Method to populate the fields with the doctor's details
    private void populateDoctorFields(Doctor doctor) {
        // Set the doctor ID field with the doctor's ID
        doctorIdField.setText(String.valueOf(doctor.getDoctorId()));
        // Set the first name field with the doctor's first name
        doctorFirstNameField.setText(doctor.getFirstName());
        // Set the surname field with the doctor's surname
        doctorSurnameField.setText(doctor.getSurname());
        // Set the address field with the doctor's address
        doctorAddressField.setText(doctor.getAddress());
        // Set the email field with the doctor's email
        doctorEmailField.setText(doctor.getEmail());
        // Set the hospital field with the doctor's hospital
        doctorHospitalField.setText(doctor.getHospital());

        // If the doctor is a specialist, populate the specialization and experience fields
        if (doctor instanceof Specialist) {
            Specialist specialist = (Specialist) doctor;
            // Set the specialization field with the specialist's specialization
            doctorSpecializationField.setText(specialist.getSpecialization());
            // Set the experience field with the specialist's experience
            doctorExperienceField.setText(String.valueOf(specialist.getExperience()));
            // Select the specialist checkbox
            specialistCheckBox.setSelected(true);
        } else {
            // Clear the specialization field
            doctorSpecializationField.clear();
            // Clear the experience field
            doctorExperienceField.clear();
            // Deselect the specialist checkbox
            specialistCheckBox.setSelected(false);
        }
    }

    // Method to load the data of all doctors into the table
    private void loadDoctorData() {
        // Get the list of all doctors from the database and set it to the table view
        doctorTableView.getItems().setAll(doctorDAO.getAllDoctors());
    }
}