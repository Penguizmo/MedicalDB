package com.medicaldb.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private void handleDoctors() {
        navigateToPage("/fxml/Pages/DoctorTable.fxml");
    }

    @FXML
    private void handleDrugs() {
        navigateToPage("/fxml/Pages/DrugTable.fxml");
    }

    @FXML
    private void handleInsuranceCompanies() {
        navigateToPage("/fxml/Pages/InsuranceTable.fxml");
    }

    @FXML
    private void handlePatients() {
        navigateToPage("/fxml/Pages/PatientTable.fxml");
    }

    @FXML
    private void handlePrescriptions() {
        navigateToPage("/fxml/Pages/PrescriptionTable.fxml");
    }

    @FXML
    private void handleVisits() {
        navigateToPage("/fxml/Pages/VisitTable.fxml");
    }

    private void navigateToPage(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            Stage stage = (Stage) ((Parent) FXMLLoader.load(getClass().getResource("/fxml/Pages/main.fxml"))).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Navigation Error", "Unable to load the page: " + fxmlFilePath);
        }
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}