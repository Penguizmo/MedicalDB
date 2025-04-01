package com.medicaldb.controller;

import com.medicaldb.dao.PatientDAO;
import com.medicaldb.model.Patient;
import com.medicaldb.model.InsuredPatient;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PatientController {

    @FXML
    private TextField patientSearchField;
    @FXML
    private TextField patientIdField;
    @FXML
    private TextField patientFirstNameField;
    @FXML
    private TextField patientSurnameField;
    @FXML
    private TextField patientPostcodeField;
    @FXML
    private TextField patientAddressField;
    @FXML
    private TextField patientPhoneField;
    @FXML
    private TextField patientEmailField;
    @FXML
    private TextField patientCompanyField;
    @FXML
    private TextField patientInsuranceTypeField;
    @FXML
    private TextField patientDurationOfInsuranceField;
    @FXML
    private TableView<Patient> patientTableView;

    private PatientDAO patientDAO = new PatientDAO();

    @FXML
    private void onSearchPatient() {
        if (isValidInput(patientSearchField.getText())) {
            int patientId = Integer.parseInt(patientSearchField.getText());
            Patient patient = patientDAO.getPatient(patientId);
            if (patient != null) {
                // Update UI with patient details
                patientIdField.setText(String.valueOf(patient.getPatientId()));
                patientFirstNameField.setText(patient.getFirstName());
                patientSurnameField.setText(patient.getSurname());
                patientPostcodeField.setText(patient.getPostcode());
                patientAddressField.setText(patient.getAddress());
                patientPhoneField.setText(patient.getPhone());
                patientEmailField.setText(patient.getEmail());
                if (patient instanceof InsuredPatient) {
                    InsuredPatient insuredPatient = (InsuredPatient) patient;
                    patientCompanyField.setText(insuredPatient.getCompany());
                    patientInsuranceTypeField.setText(insuredPatient.getInsuranceType());
                    patientDurationOfInsuranceField.setText(String.valueOf(insuredPatient.getDurationOfInsurance()));
                }
            }
        }
    }

    @FXML
    private void onSavePatient() {
        if (isValidInput(patientIdField.getText(), patientFirstNameField.getText(), patientSurnameField.getText(), patientPostcodeField.getText(), patientAddressField.getText(), patientPhoneField.getText(), patientEmailField.getText())) {
            int patientId = Integer.parseInt(patientIdField.getText());
            String firstName = patientFirstNameField.getText();
            String surname = patientSurnameField.getText();
            String postcode = patientPostcodeField.getText();
            String address = patientAddressField.getText();
            String phone = patientPhoneField.getText();
            String email = patientEmailField.getText();
            String company = patientCompanyField.getText();
            String insuranceType = patientInsuranceTypeField.getText();
            int durationOfInsurance = Integer.parseInt(patientDurationOfInsuranceField.getText());

            Patient patient;
            if (!company.isEmpty() && !insuranceType.isEmpty()) {
                patient = new InsuredPatient(patientI, firstName, surname, postcode, address, phone, email, company, insuranceType, durationOfInsurance);
            } else {
                patient = new Patient(patientId, firstName, surname, postcode, address, phone, email);
            }
            patientDAO.addPatient(patient);
        }
    }

    @FXML
    private void onUpdatePatient() {
        if (isValidInput(patientIdField.getText(), patientFirstNameField.getText(), patientSurnameField.getText(), patientPostcodeField.getText(), patientAddressField.getText(), patientPhoneField.getText(), patientEmailField.getText())) {
            int patientId = Integer.parseInt(patientIdField.getText());
            String firstName = patientFirstNameField.getText();
            String surname = patientSurnameField.getText();
            String postcode = patientPostcodeField.getText();
            String address = patientAddressField.getText();
            String phone = patientPhoneField.getText();
            String email = patientEmailField.getText();
            String company = patientCompanyField.getText();
            String insuranceType = patientInsuranceTypeField.getText();
            int durationOfInsurance = Integer.parseInt(patientDurationOfInsuranceField.getText());

            Patient patient;
            if (!company.isEmpty() && !insuranceType.isEmpty()) {
                patient = new InsuredPatient(patientId, firstName, surname, postcode, address, phone, email, company, insuranceType, durationOfInsurance);
            } else {
                patient = new Patient(patientId, firstName, surname, postcode, address, phone, email);
            }
            patientDAO.updatePatient(patient);
        }
    }

    @FXML
    private void onDeletePatient() {
        if (isValidInput(patientIdField.getText())) {
            int patientId = Integer.parseInt(patientIdField.getText());
            patientDAO.deletePatient(patientId);
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