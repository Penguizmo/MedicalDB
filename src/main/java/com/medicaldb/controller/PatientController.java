package com.medicaldb.controller;

import com.medicaldb.dao.PatientDAO;
import com.medicaldb.model.Patient;
import com.medicaldb.model.InsuredPatient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class PatientController {

    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, String> patientIDColumn;

    @FXML
    private TableColumn<Patient, String> firstNameColumn;

    @FXML
    private TableColumn<Patient, String> surnameColumn;

    @FXML
    private TableColumn<Patient, String> postcodeColumn;

    @FXML
    private TableColumn<Patient, String> addressColumn;

    @FXML
    private TableColumn<Patient, String> phoneColumn;

    @FXML
    private TableColumn<Patient, String> emailColumn;

    @FXML
    private TableColumn<InsuredPatient, String> insuranceIDColumn;

    private PatientDAO patientDAO;

    private ObservableList<Patient> patientData;

    public PatientController() {
        patientDAO = new PatientDAO();
    }

    @FXML
    private void initialize() {
        patientIDColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        postcodeColumn.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        insuranceIDColumn.setCellValueFactory(new PropertyValueFactory<>("insuranceId"));

        loadPatientData();
    }

    private void loadPatientData() {
        try {
            List<Patient> patients = patientDAO.getAllPatients();
            patientData = FXCollections.observableArrayList(patients);
            patientTable.setItems(patientData);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an alert)
        }
    }
}