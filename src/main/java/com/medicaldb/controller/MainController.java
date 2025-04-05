package com.medicaldb.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button; // Add this import statement
import java.io.IOException;

public class MainController {

    @FXML
    private void handleDoctors() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Pages/DoctorTable.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Doctors");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDrugs() {
        // Logic to navigate to the Drugs page
    }

    @FXML
    private void handleInsuranceCompanies() {
        // Logic to navigate to the Insurance Companies page
    }

    @FXML
    private void handlePatients() {
        // Logic to navigate to the Patients page
    }

    @FXML
    private void handlePrescriptions() {
        // Logic to navigate to the Prescriptions page
    }

    @FXML
    private void handleVisits() {
        // Logic to navigate to the Visits page
    }
}