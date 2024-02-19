package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.pojo.Employee;

import java.util.List;
import java.util.Map;


public interface IEmployeeService {

    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByNameSearch(String searchString);

    Employee getEmployeeById(String id);

    int getHighestSalaryOfEmployees();

    List<String> getTopTenHighestEarningEmployeeNames();

    Employee createEmployee(Map<String, Object> employeeInput);

    Employee deleteEmployeeById(String id);
}
