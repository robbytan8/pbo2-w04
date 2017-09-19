package com.robby.dao;

import com.robby.entity.Department;
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
public class DepartmentDaoImpl implements DaoService<Department> {

    @Override
    public int addData(Department object) {
        int result = 0;
        try {
            Connection connection = DBUtil.createMySQLConnection();
            String query = "INSERT INTO department(code, name) VALUES(?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getCode());
                ps.setString(2, object.getName());
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
    public int deleteData(Department object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> showAllData() {
        List<Department> departments = new ArrayList<>();
        try {
            Connection connection = DBUtil.createMySQLConnection();
            String query = "SELECT * FROM department ORDER BY code";
            try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Department department = new Department();
                    department.setId(rs.getInt("id"));
                    department.setCode(rs.getString("code"));
                    department.setName(rs.getString("name"));
                    departments.add(department);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return departments;
    }

    @Override
    public int updateData(Department object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
