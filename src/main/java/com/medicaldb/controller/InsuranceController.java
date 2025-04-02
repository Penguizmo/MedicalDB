package com.medicaldb.controller;

import com.medicaldb.dao.InsuranceDAO;
import com.medicaldb.model.Insurance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class InsuranceController {

    @FXML
    private TableView<Insurance> insuranceTable;

    @FXML
    private TableColumn<Insurance, String> insuranceIDColumn;

    @FXML
    private TableColumn<Insurance, String> companyColumn;

    @FXML
    private TableColumn<Insurance, String> addressColumn;

    @FXML
    private TableColumn<Insurance, String> phoneColumn;

    private InsuranceDAO insuranceDAO;

    private ObservableList<Insurance> insuranceList;

    public InsuranceController() {
        insuranceDAO = new InsuranceDAO();
    }

    @FXML
    private void initialize() {
        insuranceIDColumn.setCellValueFactory(new PropertyValueFactory<>("insuranceId"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadInsuranceData();
    }

    private void loadInsuranceData() {
        try {
            List<Insurance> insurances = insuranceDAO.getAllInsurances();
            insuranceList = FXCollections.observableArrayList(insurances);
            insuranceTable.setItems(insuranceList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an alert)
        }
    }
}