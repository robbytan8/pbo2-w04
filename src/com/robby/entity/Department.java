package com.robby.entity;

import java.io.Serializable;

/**
 *
 * @author Robby
 */
public class Department implements Serializable {

    private int id;
    private String code;
    private String name;

    public Department() {
    }

    public Department(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
