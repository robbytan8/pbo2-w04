package com.robby.controller;

import com.robby.entity.Department;
import com.robby.utility.TextUtil;
import com.robby.utility.ViewUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Robby
 */
public class DepartmentFormController implements Initializable {

    @FXML
    private TableColumn<Department, String> colCode;
    @FXML
    private TableColumn<Department, String> colName;
    private MainFormController mainController;
    @FXML
    private TableView<Department> tableDepartment;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtName;

    @FXML
    private void btnDeleteAction(ActionEvent event) {
    }

    @FXML
    private void btnResetAction(ActionEvent event) {
        txtCode.clear();
        txtName.clear();
    }

    @FXML
    private void btnSaveAction(ActionEvent event) {
        if (!TextUtil.isEmptyField(txtCode, txtName)) {
            Department department = new Department();
            department.setCode(txtCode.getText().trim());
            department.setName(txtName.getText().trim());
            mainController.getDepartmentDao().addData(department);
            mainController.getDepartments().clear();
            mainController.getDepartments().addAll(mainController.getDepartmentDao().showAllData());
            txtCode.clear();
            txtName.clear();
        } else {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", "Please fill all field");
        }
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
    }

    public void setMainController(MainFormController mainController) {
        this.mainController = mainController;
        this.tableDepartment.setItems(mainController.getDepartments());
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        txtCode.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,
                String newValue) -> {
            if (!newValue.matches("\\d+")) {
                if (((StringProperty) observable).getValue() == null
                        || ((StringProperty) observable).getValue().isEmpty()) {
                    oldValue = "";
                }
                ((StringProperty) observable).setValue(oldValue);
            }
        });
    }

}
