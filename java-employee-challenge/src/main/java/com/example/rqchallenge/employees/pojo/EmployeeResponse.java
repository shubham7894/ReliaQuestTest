package com.example.rqchallenge.employees.pojo;

public class EmployeeResponse {
    private String status;
    private Employee data;

    public EmployeeResponse() {
    }

    public EmployeeResponse(String status, Employee data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getData() {
        return data;
    }

    public void setData(Employee data) {
        this.data = data;
    }
}
