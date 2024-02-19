package com.example.rqchallenge.employees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.rqchallenge.employees.pojo.Employee;
import com.example.rqchallenge.employees.service.IEmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeController.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {
  @Autowired
  private EmployeeController employeeController;

  @MockBean
  private IEmployeeService iEmployeeService;

  @Test
  void testGetAllEmployees() {
    when(iEmployeeService.getAllEmployees()).thenReturn(new ArrayList<>());

    ResponseEntity<List<Employee>> actualAllEmployees = employeeController.getAllEmployees();

    verify(iEmployeeService).getAllEmployees();
    assertEquals(HttpStatus.OK, actualAllEmployees.getStatusCode());
    assertTrue(actualAllEmployees.hasBody());
    assertTrue(actualAllEmployees.getHeaders().isEmpty());
  }

  @Test
  void testGetEmployeesByNameSearch() {
    when(iEmployeeService.getEmployeesByNameSearch(Mockito.<String>any())).thenReturn(new ArrayList<>());

    ResponseEntity<List<Employee>> actualEmployeesByNameSearch = employeeController
            .getEmployeesByNameSearch("Search String");

    verify(iEmployeeService).getEmployeesByNameSearch(eq("Search String"));
    assertEquals(HttpStatus.OK, actualEmployeesByNameSearch.getStatusCode());
    assertTrue(actualEmployeesByNameSearch.hasBody());
    assertTrue(actualEmployeesByNameSearch.getHeaders().isEmpty());
  }

  @Test
  void testGetEmployeeById() {
    when(iEmployeeService.getEmployeeById(Mockito.<String>any()))
            .thenReturn(new Employee("42", "Name", 1, 1, "Profile Image"));

    ResponseEntity<Employee> actualEmployeeById = employeeController.getEmployeeById("42");

    verify(iEmployeeService).getEmployeeById(eq("42"));
    assertEquals(HttpStatus.OK, actualEmployeeById.getStatusCode());
    assertTrue(actualEmployeeById.hasBody());
    assertTrue(actualEmployeeById.getHeaders().isEmpty());
  }

  @Test
  void testGetEmployeeById2() {
    when(iEmployeeService.getEmployeeById(Mockito.<String>any())).thenReturn(null);

    ResponseEntity<Employee> actualEmployeeById = employeeController.getEmployeeById("42");

    verify(iEmployeeService).getEmployeeById(eq("42"));
    assertNull(actualEmployeeById.getBody());
    assertEquals(HttpStatus.NOT_FOUND, actualEmployeeById.getStatusCode());
    assertTrue(actualEmployeeById.getHeaders().isEmpty());
  }

  @Test
  void testGetHighestSalaryOfEmployees() {
    when(iEmployeeService.getHighestSalaryOfEmployees()).thenReturn(1);

    ResponseEntity<Integer> actualHighestSalaryOfEmployees = employeeController.getHighestSalaryOfEmployees();

    verify(iEmployeeService).getHighestSalaryOfEmployees();
    assertEquals(1, Objects.requireNonNull(actualHighestSalaryOfEmployees.getBody()).intValue());
    assertEquals(HttpStatus.OK, actualHighestSalaryOfEmployees.getStatusCode());
    assertTrue(actualHighestSalaryOfEmployees.getHeaders().isEmpty());
  }

  @Test
  void testGetTopTenHighestEarningEmployeeNames() {
    when(iEmployeeService.getTopTenHighestEarningEmployeeNames()).thenReturn(new ArrayList<>());

    ResponseEntity<List<String>> actualTopTenHighestEarningEmployeeNames = employeeController
            .getTopTenHighestEarningEmployeeNames();

    verify(iEmployeeService).getTopTenHighestEarningEmployeeNames();
    assertEquals(HttpStatus.OK, actualTopTenHighestEarningEmployeeNames.getStatusCode());
    assertTrue(actualTopTenHighestEarningEmployeeNames.hasBody());
    assertTrue(actualTopTenHighestEarningEmployeeNames.getHeaders().isEmpty());
  }

  @Test
  void testCreateEmployee() {
    when(iEmployeeService.createEmployee(Mockito.<Map<String, Object>>any()))
            .thenReturn(new Employee("42", "Name", 1, 1, "Profile Image"));

    ResponseEntity<Employee> actualCreateEmployeeResult = employeeController.createEmployee(new HashMap<>());

    verify(iEmployeeService).createEmployee(Mockito.<Map<String, Object>>any());
    assertEquals(HttpStatus.CREATED, actualCreateEmployeeResult.getStatusCode());
    assertTrue(actualCreateEmployeeResult.hasBody());
    assertTrue(actualCreateEmployeeResult.getHeaders().isEmpty());
  }

  @Test
  void testDeleteEmployeeById() {
    when(iEmployeeService.deleteEmployeeById(Mockito.<String>any()))
            .thenReturn(new Employee("42", "Name", 1, 1, "Profile Image"));

    ResponseEntity<String> actualDeleteEmployeeByIdResult = employeeController.deleteEmployeeById("42");

    verify(iEmployeeService).deleteEmployeeById(eq("42"));
    assertEquals("Employee with ID 42 deleted successfully", actualDeleteEmployeeByIdResult.getBody());
    assertEquals(HttpStatus.OK, actualDeleteEmployeeByIdResult.getStatusCode());
    assertTrue(actualDeleteEmployeeByIdResult.getHeaders().isEmpty());
  }

  @Test
  void testDeleteEmployeeById2() {
    when(iEmployeeService.deleteEmployeeById(Mockito.<String>any())).thenReturn(null);

    ResponseEntity<String> actualDeleteEmployeeByIdResult = employeeController.deleteEmployeeById("42");

    verify(iEmployeeService).deleteEmployeeById(eq("42"));
    assertNull(actualDeleteEmployeeByIdResult.getBody());
    assertEquals(HttpStatus.NOT_FOUND, actualDeleteEmployeeByIdResult.getStatusCode());
    assertTrue(actualDeleteEmployeeByIdResult.getHeaders().isEmpty());
  }
}
