package com.robby.dao;

import com.robby.entity.Department;
import com.robby.entity.Student;
import com.robby.utility.DBUtil;
import com.robby.utility.DaoService;
import com.robby.utility.ViewUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Robby
 */
public class StudentDaoImpl implements DaoService<Student> {

    @Override
    public int addData(Student object) {
        int result = 0;
        try {
            Connection connection = DBUtil.createMySQLConnection();
            String query
                    = "INSERT INTO student(id, first_name, last_name, birth_date, department_id) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getId());
                ps.setString(2, object.getFirstName());
                ps.setString(3, object.getLastName());
                ps.setDate(4, new java.sql.Date(object.getBirthDate().getTime()));
                ps.setInt(5, object.getDepartment().getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return result;
    }

    @Override
    public int deleteData(Student object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> showAllData() {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = DBUtil.createMySQLConnection();
            String query
                    = "SELECT s.*, d.code, d.name, d.status FROM student s JOIN department d ON d.id = s.department_id ORDER BY d.code";
            try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Department department = new Department();
                    department.setId(rs.getInt("department_id"));
                    department.setCode(rs.getString("code"));
                    department.setName(rs.getString("name"));

                    Student student = new Student();
                    student.setId(rs.getString("id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setBirthDate(rs.getDate("birth_date"));
                    student.setDepartment(department);
                    students.add(student);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return students;
    }

    @Override
    public int updateData(Student object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
