package com.medicaldb.controller;

import com.medicaldb.dao.DrugDAO;
import com.medicaldb.model.Drug;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DrugController {

    @FXML
    private TextField drugSearchField;
    @FXML
    private TextField drugIdField;
    @FXML
    private TextField drugNameField;
    @FXML
    private TextField drugSideEffectsField;
    @FXML
    private TextField drugBenefitsField;
    @FXML
    private TableView<Drug> drugTableView;

    private DrugDAO drugDAO = new DrugDAO();

    @FXML
    private void onSearchDrug() {
        int drugId = Integer.parseInt(drugSearchField.getText());
        Drug drug = drugDAO.getDrug(drugId);
        if (drug != null) {
            drugIdField.setText(String.valueOf(drug.getDrugId()));
            drugNameField.setText(drug.getDrugName());
            drugSideEffectsField.setText(drug.getSideEffects());
            drugBenefitsField.setText(drug.getBenefits());
        }
    }

    @FXML
    private void onSaveDrug() {
        int drugId = Integer.parseInt(drugIdField.getText());
        String drugName = drugNameField.getText();
        String sideEffects = drugSideEffectsField.getText();
        String benefits = drugBenefitsField.getText();

        Drug drug = new Drug(drugId, drugName, sideEffects, benefits);
        drugDAO.addDrug(drug);
    }

    @FXML
    private void onUpdateDrug() {
        int drugId = Integer.parseInt(drugIdField.getText());
        String drugName = drugNameField.getText();
        String sideEffects = drugSideEffectsField.getText();
        String benefits = drugBenefitsField.getText();

        Drug drug = new Drug(drugId, drugName, sideEffects, benefits);
        drugDAO.updateDrug(drug);
    }

    @FXML
    private void onDeleteDrug() {
        int drugId = Integer.parseInt(drugIdField.getText());
        drugDAO.deleteDrug(drugId);
    }
}