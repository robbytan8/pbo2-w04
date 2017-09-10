package com.robby.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Robby
 */
public class Student implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Department department;

    public Student() {
    }

    public Student(String id, String firstName, String lastName, Date birthDate, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.department = department;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
