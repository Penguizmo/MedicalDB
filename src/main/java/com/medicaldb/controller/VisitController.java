package com.medicaldb.controller;

import com.medicaldb.dao.VisitDAO;
import com.medicaldb.model.Visit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class VisitController {

    @FXML
    private TableView<Visit> visitTable;
    @FXML
    private TableColumn<Visit, String> patientIDColumn;
    @FXML
    private TableColumn<Visit, Integer> doctorIDColumn;
    @FXML
    private TableColumn<Visit, LocalDate> dateOfVisitColumn;
    @FXML
    private TableColumn<Visit, String> symptomsColumn;
    @FXML
    private TableColumn<Visit, String> diagnosisIDColumn;

    private VisitDAO visitDAO;
    private ObservableList<Visit> visitList;

    public VisitController() {
        visitDAO = new VisitDAO();
        visitList = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        patientIDColumn.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        doctorIDColumn.setCellValueFactory(new PropertyValueFactory<>("doctorid"));
        dateOfVisitColumn.setCellValueFactory(new PropertyValueFactory<>("dateofvisit"));
        symptomsColumn.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
        diagnosisIDColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosisid"));

        loadVisitData();
    }

    private void loadVisitData() {
        try {
            visitList.setAll(visitDAO.getAllVisits());
            visitTable.setItems(visitList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an alert to the user)
        }
    }
}