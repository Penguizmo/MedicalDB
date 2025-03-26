package com.medicaldb.controller;

import com.medicaldb.dao.InsuranceDAO;
import com.medicaldb.model.Insurance;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InsuranceController {

    @FXML
    private TextField insuranceSearchField;
    @FXML
    private TextField insuranceIdField;
    @FXML
    private TextField insuranceCompanyField;
    @FXML
    private TextField insuranceAddressField;
    @FXML
    private TextField insurancePhoneField;
    @FXML
    private TableView<Insurance> insuranceTableView;

    private InsuranceDAO insuranceDAO = new InsuranceDAO();

    @FXML
    private void onSearchInsurance() {
        String insuranceId = insuranceSearchField.getText();
        Insurance insurance = insuranceDAO.getInsurance(insuranceId);
        if (insurance != null) {
            insuranceIdField.setText(insurance.getInsuranceId());
            insuranceCompanyField.setText(insurance.getCompany());
            insuranceAddressField.setText(insurance.getAddress());
            insurancePhoneField.setText(insurance.getPhone());
        }
    }

    @FXML
    private void onSaveInsurance() {
        String insuranceId = insuranceIdField.getText();
        String company = insuranceCompanyField.getText();
        String address = insuranceAddressField.getText();
        String phone = insurancePhoneField.getText();

        Insurance insurance = new Insurance(insuranceId, company, address, phone);
        insuranceDAO.addInsurance(insurance);
    }

    @FXML
    private void onUpdateInsurance() {
        String insuranceId = insuranceIdField.getText();
        String company = insuranceCompanyField.getText();
        String address = insuranceAddressField.getText();
        String phone = insurancePhoneField.getText();

        Insurance insurance = new Insurance(insuranceId, company, address, phone);
        insuranceDAO.updateInsurance(insurance);
    }

    @FXML
    private void onDeleteInsurance() {
        String insuranceId = insuranceIdField.getText();
        insuranceDAO.deleteInsurance(insuranceId);
    }
}