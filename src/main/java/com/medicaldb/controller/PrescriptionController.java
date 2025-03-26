package com.medicaldb.controller;

import com.medicaldb.dao.PrescriptionDAO;
import com.medicaldb.model.Prescription;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.List;

public class PrescriptionController {

    @FXML
    private TextField prescriptionSearchField;
    @FXML
    private TextField prescriptionIdField;
    @FXML
    private TextField datePrescribedField;
    @FXML
    private TextField dosageField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField commentField;
    @FXML
    private TableView<Prescription> prescriptionTableView;

    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    @FXML
    private void onSearchPrescription() {
        if (isValidInput(prescriptionSearchField.getText())) {
            int prescriptionId = Integer.parseInt(prescriptionSearchField.getText());
            Prescription prescription = prescriptionDAO.getPrescription(prescriptionId);
            if (prescription != null) {
                prescriptionIdField.setText(String.valueOf(prescription.getPrescriptionId()));
                datePrescribedField.setText(prescription.getDatePrescribed().toString());
                dosageField.setText(prescription.getDosage());
                durationField.setText(String.valueOf(prescription.getDuration()));
                commentField.setText(prescription.getComment());
            } else {
                showAlert("No Prescription Found", "No prescription found with the given ID.");
            }
        }
    }

    @FXML
    private void onSavePrescription() {
        if (isValidInput(prescriptionIdField.getText(), datePrescribedField.getText(), dosageField.getText(), durationField.getText(), commentField.getText())) {
            int prescriptionId = Integer.parseInt(prescriptionIdField.getText());
            Date datePrescribed = Date.valueOf(datePrescribedField.getText());
            String dosage = dosageField.getText();
            int duration = Integer.parseInt(durationField.getText());
            String comment = commentField.getText();

            Prescription prescription = new Prescription(prescriptionId, datePrescribed, dosage, duration, comment);
            prescriptionDAO.addPrescription(prescription);
        }
    }

    @FXML
    private void onUpdatePrescription() {
        if (isValidInput(prescriptionIdField.getText(), datePrescribedField.getText(), dosageField.getText(), durationField.getText(), commentField.getText())) {
            int prescriptionId = Integer.parseInt(prescriptionIdField.getText());
            Date datePrescribed = Date.valueOf(datePrescribedField.getText());
            String dosage = dosageField.getText();
            int duration = Integer.parseInt(durationField.getText());
            String comment = commentField.getText();

            Prescription prescription = new Prescription(prescriptionId, datePrescribed, dosage, duration, comment);
            prescriptionDAO.updatePrescription(prescription);
        }
    }

    @FXML
    private void onDeletePrescription() {
        if (isValidInput(prescriptionIdField.getText())) {
            int prescriptionId = Integer.parseInt(prescriptionIdField.getText());
            prescriptionDAO.deletePrescription(prescriptionId);
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