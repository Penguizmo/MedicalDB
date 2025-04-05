package com.medicaldb.controller;

import com.medicaldb.dao.DoctorDAO;
import com.medicaldb.model.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class DoctorController {

    @FXML
    private TableView<Doctor> doctorTable;
    @FXML
    private TableColumn<Doctor, Integer> doctorIdColumn;
    @FXML
    private TableColumn<Doctor, String> firstNameColumn;
    @FXML
    private TableColumn<Doctor, String> surnameColumn;
    @FXML
    private TableColumn<Doctor, String> addressColumn;
    @FXML
    private TableColumn<Doctor, String> emailColumn;
    @FXML
    private TableColumn<Doctor, String> hospitalColumn;
    @FXML
    private TableColumn<Doctor, String> specializationColumn;
    @FXML
    private TableColumn<Doctor, String> experienceColumn;
    @FXML
    private TextField searchField;

    private ObservableList<Doctor> doctorData = FXCollections.observableArrayList();
    private FilteredList<Doctor> filteredData;
    private DoctorDAO doctorDAO = new DoctorDAO();

    @FXML
    private void initialize() {
        doctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        hospitalColumn.setCellValueFactory(new PropertyValueFactory<>("hospital"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        experienceColumn.setCellValueFactory(new PropertyValueFactory<>("experience"));

        loadDoctorData();

        filteredData = new FilteredList<>(doctorData, p -> true);
        doctorTable.setItems(filteredData);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(doctor -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return doctor.getFirstName().toLowerCase().contains(lowerCaseFilter) ||
                        doctor.getSurname().toLowerCase().contains(lowerCaseFilter) ||
                        doctor.getAddress().toLowerCase().contains(lowerCaseFilter) ||
                        doctor.getEmail().toLowerCase().contains(lowerCaseFilter) ||
                        doctor.getHospital().toLowerCase().contains(lowerCaseFilter) ||
                        (doctor.getSpecialization() != null && doctor.getSpecialization().toLowerCase().contains(lowerCaseFilter)) ||
                        (doctor.getExperience() != null && doctor.getExperience().toLowerCase().contains(lowerCaseFilter));
            });
        });
    }

    private void loadDoctorData() {
        try {
            doctorData.setAll(doctorDAO.getAllDoctors());
        } catch (SQLException e) {
            showErrorAlert("Error loading doctor data", e.getMessage());
        }
    }

    @FXML
    private void handleAddDoctor() {
        // Logic to add a new doctor
    }

    @FXML
    private void handleEditDoctor() {
        Doctor selectedDoctor = doctorTable.getSelectionModel().getSelectedItem();
        if (selectedDoctor != null) {
            // Logic to edit the selected doctor
        } else {
            showWarningAlert("No Selection", "No Doctor Selected", "Please select a doctor in the table.");
        }
    }

    @FXML
    private void handleDeleteDoctor() {
        Doctor selectedDoctor = doctorTable.getSelectionModel().getSelectedItem();
        if (selectedDoctor != null) {
            Optional<ButtonType> result = showConfirmationAlert("Delete Doctor", "Are you sure you want to delete this doctor?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    doctorDAO.deleteDoctor(selectedDoctor.getDoctorId());
                    doctorData.remove(selectedDoctor);
                } catch (SQLException e) {
                    showErrorAlert("Error deleting doctor", e.getMessage());
                }
            }
        } else {
            showWarningAlert("No Selection", "No Doctor Selected", "Please select a doctor in the table.");
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Pages/Main.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Main");
            stage.setScene(new Scene(root));
            stage.show();
            // Close the current window
            Stage currentStage = (Stage) doctorTable.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showWarningAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Optional<ButtonType> showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}

