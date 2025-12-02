package com.example.myapplication.testDB;
public class EmployeeModel {

    private long id;
    private String name;
    private String email;

    public EmployeeModel(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

