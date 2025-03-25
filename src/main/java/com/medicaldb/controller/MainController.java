package com.medicaldb.controller;

import com.medicaldb.dao.*;
import com.medicaldb.model.*;
import com.medicaldb.util.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MainController {

    public TableColumn patientIdColumn;
    @FXML
    private TextField patientIdField, patientFirstNameField, patientSurnameField, postcodeField, patientAddressField, patientPhoneField, patientEmailField, companyField, insuranceTypeField, durationOfInsuranceField;
    @FXML
    private TableView<Patient> patientTableView;

    @FXML
    private TextField doctorIdField, firstNameField, surnameField, addressField, emailField, specializationField, experienceField;
    @FXML
    private TableView<Doctor> doctorTableView;

    @FXML
    private TextField visitDateField, symptomsField, diagnosisIdField;
    @FXML
    private TableView<Visit> visitTableView;

    @FXML
    private TextField prescriptionIdField, datePrescribedField, dosageField, durationField, commentField;
    @FXML
    private TableView<Prescription> prescriptionTableView;

    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private VisitDAO visitDAO;
    private PrescriptionDAO prescriptionDAO;

    public MainController() {
        Connection connection = DatabaseConnection.getConnection();
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
        visitDAO = new VisitDAO(connection);
        prescriptionDAO = new PrescriptionDAO();
    }

    @FXML
    public void initialize() {
        initializePatientTable();
        initializeDoctorTable();
        initializeVisitTable();
        initializePrescriptionTable();
    }

    private void initializePatientTable() {
        patientTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patientTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        patientTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
        patientTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("postcode"));
        patientTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        patientTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("phone"));
        patientTableView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("email"));
        patientTableView.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("company"));
        patientTableView.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("insuranceType"));
        patientTableView.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("durationOfInsurance"));
        loadPatients();
    }

    private void initializeDoctorTable() {
        doctorTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        doctorTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        doctorTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("surname"));
        doctorTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        doctorTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("email"));
        doctorTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("specialization"));
        doctorTableView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("experience"));
        loadDoctors();
    }

    private void initializeVisitTable() {
        visitTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("dateOfVisit"));
        visitTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("symptoms"));
        visitTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("diagnosisId"));
        loadVisits();
    }

    private void initializePrescriptionTable() {
        prescriptionTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("prescriptionId"));
        prescriptionTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("datePrescribed"));
        prescriptionTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dosage"));
        prescriptionTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("duration"));
        prescriptionTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("comment"));
        loadPrescriptions();
    }

    private void loadPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        patientTableView.getItems().setAll(patients);
    }

    private void loadDoctors() {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        doctorTableView.getItems().setAll(doctors);
    }

    private void loadVisits() {
        try {
            List<Visit> visits = visitDAO.getAllVisits();
            visitTableView.getItems().setAll(visits);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadPrescriptions() {
        List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
        prescriptionTableView.getItems().setAll(prescriptions);
    }

    @FXML
    private void onSavePatient() {
        Patient patient = new Patient(
                Integer.parseInt(patientIdField.getText()),
                patientFirstNameField.getText(),
                patientSurnameField.getText(),
                postcodeField.getText(),
                patientAddressField.getText(),
                patientPhoneField.getText(),
                patientEmailField.getText()
        );
        patientDAO.addPatient(patient);
        loadPatients();
    }

    @FXML
    private void onUpdatePatient() {
        Patient patient = new Patient(
                Integer.parseInt(patientIdField.getText()),
                patientFirstNameField.getText(),
                patientSurnameField.getText(),
                postcodeField.getText(),
                patientAddressField.getText(),
                patientPhoneField.getText(),
                patientEmailField.getText()
        );
        patientDAO.updatePatient(patient);
        loadPatients();
    }

    @FXML
    private void onDeletePatient() {
        int patientId = Integer.parseInt(patientIdField.getText());
        patientDAO.deletePatient(patientId);
        loadPatients();
    }

    @FXML
    private void onSaveDoctor() {
        Doctor doctor = new Doctor(
                Integer.parseInt(doctorIdField.getText()),
                firstNameField.getText(),
                surnameField.getText(),
                addressField.getText(),
                emailField.getText()
        );
        doctorDAO.addDoctor(doctor);
        loadDoctors();
    }

    @FXML
    private void onUpdateDoctor() {
        Doctor doctor = new Doctor(
                Integer.parseInt(doctorIdField.getText()),
                firstNameField.getText(),
                surnameField.getText(),
                addressField.getText(),
                emailField.getText()
        );
        doctorDAO.updateDoctor(doctor);
        loadDoctors();
    }

    @FXML
    private void onDeleteDoctor() {
        int doctorId = Integer.parseInt(doctorIdField.getText());
        doctorDAO.deleteDoctor(doctorId);
        loadDoctors();
    }

    @FXML
    private void onSaveVisit() {
        Visit visit = new Visit(
                Date.valueOf(visitDateField.getText()),
                symptomsField.getText(),
                Integer.parseInt(diagnosisIdField.getText())
        );
        try {
            visitDAO.createVisit(visit);
            loadVisits();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onUpdateVisit() {
        Visit visit = new Visit(
                Date.valueOf(visitDateField.getText()),
                symptomsField.getText(),
                Integer.parseInt(diagnosisIdField.getText())
        );
        try {
            visitDAO.updateVisit(visit, Date.valueOf(visitDateField.getText()));
            loadVisits();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteVisit() {
        try {
            visitDAO.deleteVisit(Date.valueOf(visitDateField.getText()));
            loadVisits();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSavePrescription() {
        Prescription prescription = new Prescription(
                Integer.parseInt(prescriptionIdField.getText()),
                Date.valueOf(datePrescribedField.getText()),
                dosageField.getText(),
                Integer.parseInt(durationField.getText()),
                commentField.getText()
        );
        prescriptionDAO.addPrescription(prescription);
        loadPrescriptions();
    }

    @FXML
    private void onUpdatePrescription() {
        Prescription prescription = new Prescription(
                Integer.parseInt(prescriptionIdField.getText()),
                Date.valueOf(datePrescribedField.getText()),
                dosageField.getText(),
                Integer.parseInt(durationField.getText()),
                commentField.getText()
        );
        prescriptionDAO.updatePrescription(prescription);
        loadPrescriptions();
    }

    @FXML
    private void onDeletePrescription() {
        int prescriptionId = Integer.parseInt(prescriptionIdField.getText());
        prescriptionDAO.deletePrescription(prescriptionId);
        loadPrescriptions();
    }

    public TextField getCompanyField() {
        return companyField;
    }

    public void setCompanyField(TextField companyField) {
        this.companyField = companyField;
    }
}