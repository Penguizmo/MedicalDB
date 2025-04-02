package com.medicaldb.controller;

import com.medicaldb.dao.DoctorDAO;
import com.medicaldb.model.Doctor;
import com.medicaldb.model.Specialist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class DoctorController {

    @FXML
    private TextField doctorSearchField;
    @FXML
    private TableView<Doctor> doctorTableView;
    @FXML
    private TableColumn<Doctor, Integer> doctorIdColumn;
    @FXML
    private TableColumn<Doctor, String> doctorFirstNameColumn;
    @FXML
    private TableColumn<Doctor, String> doctorSurnameColumn;
    @FXML
    private TableColumn<Doctor, String> doctorAddressColumn;
    @FXML
    private TableColumn<Doctor, String> doctorEmailColumn;
    @FXML
    private TableColumn<Doctor, String> doctorHospitalColumn;
    @FXML
    private TableColumn<Doctor, String> doctorSpecializationColumn;
    @FXML
    private TableColumn<Doctor, String> doctorExperienceColumn;
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
    private TextField doctorHospitalField;
    @FXML
    private CheckBox specialistCheckBox;
    @FXML
    private TextField doctorSpecializationField;
    @FXML
    private TextField doctorExperienceField;

    private DoctorDAO doctorDAO;
    private ObservableList<Doctor> doctorList;

    public DoctorController() {
        doctorDAO = new DoctorDAO();
    }

    @FXML
    private void initialize() {
        doctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        doctorFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        doctorSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        doctorAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        doctorEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        doctorHospitalColumn.setCellValueFactory(new PropertyValueFactory<>("hospital"));
        doctorSpecializationColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        doctorExperienceColumn.setCellValueFactory(new PropertyValueFactory<>("experience"));

        loadDoctorData();
    }

    private void loadDoctorData() {
        try {
            List<Doctor> doctors = doctorDAO.getAllDoctors();
            doctorList = FXCollections.observableArrayList(doctors);
            doctorTableView.setItems(doctorList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an alert)
        }
    }

    @FXML
    private void onSearchDoctor() {
        String doctorId = doctorSearchField.getText();
        if (doctorId != null && !doctorId.isEmpty()) {
            try {
                Doctor doctor = doctorDAO.getDoctorById(Integer.parseInt(doctorId));
                if (doctor != null) {
                    doctorList.clear();
                    doctorList.add(doctor);
                } else {
                    // Handle doctor not found (e.g., show an alert)
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception (e.g., show an alert)
            }
        } else {
            loadDoctorData();
        }
    }

    @FXML
    private void onSaveDoctor() {
        try {
            Doctor doctor;
            if (specialistCheckBox.isSelected()) {
                doctor = new Specialist(
                        Integer.parseInt(doctorIdField.getText()),
                        doctorFirstNameField.getText(),
                        doctorSurnameField.getText(),
                        doctorAddressField.getText(),
                        doctorEmailField.getText(),
                        doctorHospitalField.getText(),
                        doctorSpecializationField.getText(),
                        doctorExperienceField.getText()
                );
            } else {
                doctor = new Doctor(
                        Integer.parseInt(doctorIdField.getText()),
                        doctorFirstNameField.getText(),
                        doctorSurnameField.getText(),
                        doctorAddressField.getText(),
                        doctorEmailField.getText(),
                        doctorHospitalField.getText()
                );
            }
            doctorDAO.addDoctor(doctor);
            loadDoctorData();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an alert)
        }
    }

    @FXML
    private void onUpdateDoctor() {
        try {
            Doctor doctor;
            if (specialistCheckBox.isSelected()) {
                doctor = new Specialist(
                        Integer.parseInt(doctorIdField.getText()),
                        doctorFirstNameField.getText(),
                        doctorSurnameField.getText(),
                        doctorAddressField.getText(),
                        doctorEmailField.getText(),
                        doctorHospitalField.getText(),
                        doctorSpecializationField.getText(),
                        doctorExperienceField.getText()
                );
            } else {
                doctor = new Doctor(
                        Integer.parseInt(doctorIdField.getText()),
                        doctorFirstNameField.getText(),
                        doctorSurnameField.getText(),
                        doctorAddressField.getText(),
                        doctorEmailField.getText(),
                        doctorHospitalField.getText()
                );
            }
            doctorDAO.updateDoctor(doctor);
            loadDoctorData();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an alert)
        }
    }

    @FXML
    private void onDeleteDoctor() {
        try {
            int doctorId = Integer.parseInt(doctorIdField.getText());
            doctorDAO.deleteDoctor(doctorId);
            loadDoctorData();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an alert)
        }
    }

    @FXML
    private void onSpecialistCheckBox() {
        boolean isSpecialist = specialistCheckBox.isSelected();
        doctorSpecializationField.setVisible(isSpecialist);
        doctorExperienceField.setVisible(isSpecialist);
    }

    @FXML
    private void handleMainPage() {
        // Handle navigation to the main page
    }

    @FXML
    private void handleDrugs() {
        // Handle navigation to the drugs page
    }

    @FXML
    private void handleInsuranceCompanies() {
        // Handle navigation to the insurance companies page
    }

    @FXML
    private void handlePatients() {
        // Handle navigation to the patients page
    }

    @FXML
    private void handlePrescriptions() {
        // Handle navigation to the prescriptions page
    }

    @FXML
    private void handleVisits() {
        // Handle navigation to the visits page
    }
}