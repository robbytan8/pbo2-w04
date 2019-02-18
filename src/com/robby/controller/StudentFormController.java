package com.robby.controller;

import com.robby.entity.Department;
import com.robby.entity.Student;
import com.robby.utility.TextUtil;
import com.robby.utility.ViewUtil;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Robby
 */
public class StudentFormController implements Initializable {

    @FXML
    private TableColumn<Student, String> col01;
    @FXML
    private TableColumn<Student, String> col02;
    @FXML
    private TableColumn<Student, Date> col03;
    @FXML
    private TableColumn<Student, String> col04;
    @FXML
    private ComboBox<Department> comboDepartment;
    private MainFormController mainController;
    @FXML
    private TableView<Student> tableStudent;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtLastName;

    public void setMainController(MainFormController mainController) {
        this.mainController = mainController;
        this.comboDepartment.setItems(mainController.getDepartments());
        this.tableStudent.setItems(mainController.getStudents());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col01.setCellValueFactory(data -> {
            Student s = data.getValue();
            return new SimpleStringProperty(s.getId());
        });
        col02.setCellValueFactory(data -> {
            Student s = data.getValue();
            return new SimpleStringProperty(s.getFirstName() + " " + s.getLastName());
        });
        col03.setCellValueFactory(data -> {
            Student s = data.getValue();
            return new SimpleObjectProperty<>(s.getBirthDate());
        });
        col04.setCellValueFactory(data -> {
            Student s = data.getValue();
            return new SimpleStringProperty(s.getDepartment().getName());
        });
    }

    @FXML
    private void resetAction(ActionEvent event) {
        txtId.clear();
        txtFirstName.clear();
        txtLastName.clear();
    }

    @FXML
    private void saveAction(ActionEvent event) {
        if (TextUtil.isEmptyField(txtId, txtFirstName, txtLastName) || txtDate.getValue() == null || comboDepartment.
                getValue() == null) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", "Please fill all field");
        } else {
            Student student = new Student();
            student.setId(txtId.getText().trim());
            student.setFirstName(txtFirstName.getText().trim());
            student.setLastName(txtLastName.getText().trim());
            Date studentBirthDate = Date.from(txtDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            student.setBirthDate(studentBirthDate);
            student.setDepartment(comboDepartment.getValue());
            mainController.getStudents().clear();
            mainController.getStudentDao().addData(student);
            mainController.getStudents().addAll(mainController.getStudentDao().showAllData());
            txtId.clear();
            txtFirstName.clear();
            txtLastName.clear();
        }
    }

}
