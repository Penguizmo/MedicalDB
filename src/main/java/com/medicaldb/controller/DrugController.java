package com.medicaldb.controller;

import com.medicaldb.dao.DrugDAO;
import com.medicaldb.model.Drug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class DrugController {

    @FXML
    private TableView<Drug> drugTable;

    @FXML
    private TableColumn<Drug, Integer> drugIDColumn;

    @FXML
    private TableColumn<Drug, String> drugNameColumn;

    @FXML
    private TableColumn<Drug, String> sideEffectsColumn;

    @FXML
    private TableColumn<Drug, String> benefitsColumn;

    private DrugDAO drugDAO;

    private ObservableList<Drug> drugList;

    public DrugController() {
        drugDAO = new DrugDAO();
    }

    @FXML
    private void initialize() {
        drugIDColumn.setCellValueFactory(new PropertyValueFactory<>("drugId"));
        drugNameColumn.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        sideEffectsColumn.setCellValueFactory(new PropertyValueFactory<>("sideEffects"));
        benefitsColumn.setCellValueFactory(new PropertyValueFactory<>("benefits"));

        loadDrugData();
    }

    private void loadDrugData() {
        try {
            List<Drug> drugs = drugDAO.getAllDrugs();
            drugList = FXCollections.observableArrayList(drugs);
            drugTable.setItems(drugList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an alert)
        }
    }
}