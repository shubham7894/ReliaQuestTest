package com.example.rqchallenge.employees.pojo;

import java.util.List;

public class EmployeeListResponse {
    private String status;
    private List<Employee> data;

    public EmployeeListResponse() {
    }

    public EmployeeListResponse(String status, List<Employee> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getData() {
        return data;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }
}
