package com.medicaldb.controller;

import com.medicaldb.dao.PrescriptionDAO;
import com.medicaldb.model.Prescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class PrescriptionController {

    @FXML
    private TableView<Prescription> prescriptionTable;
    @FXML
    private TableColumn<Prescription, Integer> prescriptionIdColumn;
    @FXML
    private TableColumn<Prescription, java.util.Date> datePrescribedColumn;
    @FXML
    private TableColumn<Prescription, String> dosageColumn;
    @FXML
    private TableColumn<Prescription, String> durationColumn;
    @FXML
    private TableColumn<Prescription, String> commentColumn;
    @FXML
    private TableColumn<Prescription, Integer> drugIdColumn;
    @FXML
    private TableColumn<Prescription, Integer> doctorIdColumn;
    @FXML
    private TableColumn<Prescription, String> patientIdColumn;

    private PrescriptionDAO prescriptionDAO;

    public PrescriptionController() {
        prescriptionDAO = new PrescriptionDAO();
    }

    @FXML
    private void initialize() {
        prescriptionIdColumn.setCellValueFactory(new PropertyValueFactory<>("prescriptionId"));
        datePrescribedColumn.setCellValueFactory(new PropertyValueFactory<>("datePrescribed"));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        drugIdColumn.setCellValueFactory(new PropertyValueFactory<>("drugId"));
        doctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientID"));

        loadPrescriptionData();
    }

    private void loadPrescriptionData() {
        try {
            List<Prescription> prescriptionList = prescriptionDAO.getAllPrescriptions();
            ObservableList<Prescription> prescriptionData = FXCollections.observableArrayList(prescriptionList);
            prescriptionTable.setItems(prescriptionData);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an alert)
        }
    }
}