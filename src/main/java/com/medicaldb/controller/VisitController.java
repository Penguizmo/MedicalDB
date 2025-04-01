package com.medicaldb.controller;

import com.medicaldb.dao.VisitDAO;
import com.medicaldb.model.Visit;
import com.medicaldb.util.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class VisitController {

    @FXML
    private TextField visitSearchField;
    @FXML
    private TextField visitDateField;
    @FXML
    private TextField symptomsField;
    @FXML
    private TextField diagnosisIdField;
    @FXML
    private TextField doctorIdField;
    @FXML
    private TextField patientIdField;
    @FXML
    private TableView<Visit> visitTableView;

    private VisitDAO visitDAO;

    public VisitController() {
        Connection connection = DatabaseConnection.getConnection();
        visitDAO = new VisitDAO(connection);
    }

    @FXML
    private void onSearchVisit() {
        if (isValidInput(visitSearchField.getText())) {
            Date visitDate = Date.valueOf(visitSearchField.getText());
            try {
                Visit visit = visitDAO.getVisit(visitDate);
                if (visit != null) {
                    visitDateField.setText(visit.getDateOfVisit().toString());
                    symptomsField.setText(visit.getSymptoms());
                    diagnosisIdField.setText(visit.getDiagnosis());
                    doctorIdField.setText(String.valueOf(visit.getDoctorId()));
                    patientIdField.setText(String.valueOf(visit.getPatientId()));
                } else {
                    showAlert("No Visit Found", "No visit found with the given date.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onSaveVisit() {
        if (isValidInput(visitDateField.getText(), symptomsField.getText(), diagnosisIdField.getText(), doctorIdField.getText(), patientIdField.getText())) {
            Date visitDate = Date.valueOf(visitDateField.getText());
            String symptoms = symptomsField.getText();
            String diagnosis = diagnosisIdField.getText();
            int doctorId = Integer.parseInt(doctorIdField.getText());
            int patientId = Integer.parseInt(patientIdField.getText());

            Visit visit = new Visit(visitDate, symptoms, diagnosis, doctorId, patientId);
            try {
                visitDAO.createVisit(visit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onUpdateVisit() {
        if (isValidInput(visitDateField.getText(), symptomsField.getText(), diagnosisIdField.getText(), doctorIdField.getText(), patientIdField.getText())) {
            Date visitDate = Date.valueOf(visitDateField.getText());
            String symptoms = symptomsField.getText();
            String diagnosis = diagnosisIdField.getText();
            int doctorId = Integer.parseInt(doctorIdField.getText());
            int patientId = Integer.parseInt(patientIdField.getText());

            Visit visit = new Visit(visitDate, symptoms, diagnosis, doctorId, patientId);
            try {
                visitDAO.updateVisit(visit, visitDate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onDeleteVisit() {
        if (isValidInput(visitDateField.getText())) {
            Date visitDate = Date.valueOf(visitDateField.getText());
            try {
                visitDAO.deleteVisit(visitDate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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