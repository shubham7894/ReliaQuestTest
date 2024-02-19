package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.exception.RpChallengeException;
import com.example.rqchallenge.employees.pojo.Employee;
import com.example.rqchallenge.employees.pojo.EmployeeListResponse;
import com.example.rqchallenge.employees.pojo.EmployeeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EmployeeService.class})
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"api.baseUrl=baseUrl"})
class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testGetAllEmployees() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        assertThrows(RpChallengeException.class, () -> employeeService.getAllEmployees());
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetAllEmployees2() throws RestClientException {
        ResponseEntity<EmployeeListResponse> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getStatusCode()).thenThrow(new RestClientException("Msg"));
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenReturn(responseEntity);

        assertThrows(RpChallengeException.class, () -> employeeService.getAllEmployees());
        verify(responseEntity).getStatusCode();
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetAllEmployees3() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenThrow(new RpChallengeException("An error occurred"));

        assertThrows(RpChallengeException.class, () -> employeeService.getAllEmployees());
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetAllEmployees4() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenThrow(new RestClientException("Msg"));

        assertThrows(RpChallengeException.class, () -> employeeService.getAllEmployees());
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetEmployeesByNameSearch() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        assertThrows(RpChallengeException.class, () -> employeeService.getEmployeesByNameSearch("Search String"));
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetEmployeesByNameSearch2() throws RestClientException {
        ResponseEntity<EmployeeListResponse> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getStatusCode()).thenThrow(new RestClientException("Msg"));
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenReturn(responseEntity);

        assertThrows(RpChallengeException.class, () -> employeeService.getEmployeesByNameSearch("Search String"));
        verify(responseEntity).getStatusCode();
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetEmployeeById() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeResponse>>any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        assertThrows(RpChallengeException.class, () -> employeeService.getEmployeeById("42"));
        verify(restTemplate).getForEntity(eq("baseUrl/employee/42"), Mockito.<Class<EmployeeResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetEmployeeById2() throws RestClientException {
        ResponseEntity<EmployeeResponse> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getStatusCode()).thenThrow(new RestClientException("Msg"));
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeResponse>>any(), (Object[]) any()))
                .thenReturn(responseEntity);

        assertThrows(RpChallengeException.class, () -> employeeService.getEmployeeById("42"));
        verify(responseEntity).getStatusCode();
        verify(restTemplate).getForEntity(eq("baseUrl/employee/42"), Mockito.<Class<EmployeeResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetHighestSalaryOfEmployees() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        assertThrows(RpChallengeException.class, () -> employeeService.getHighestSalaryOfEmployees());
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetHighestSalaryOfEmployees2() throws RestClientException {
        ResponseEntity<EmployeeListResponse> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getStatusCode()).thenThrow(new RestClientException("Msg"));
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenReturn(responseEntity);

        assertThrows(RpChallengeException.class, () -> employeeService.getHighestSalaryOfEmployees());
        verify(responseEntity).getStatusCode();
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetHighestSalaryOfEmployees3() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenThrow(new RpChallengeException("An error occurred"));

        assertThrows(RpChallengeException.class, () -> employeeService.getHighestSalaryOfEmployees());
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetHighestSalaryOfEmployees4() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenThrow(new RestClientException("Msg"));

        assertThrows(RpChallengeException.class, () -> employeeService.getHighestSalaryOfEmployees());
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetTopTenHighestEarningEmployeeNames() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        assertThrows(RpChallengeException.class, () -> employeeService.getTopTenHighestEarningEmployeeNames());
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetTopTenHighestEarningEmployeeNames2() throws RestClientException {
        ResponseEntity<EmployeeListResponse> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getStatusCode()).thenThrow(new RestClientException("Msg"));
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenReturn(responseEntity);

        assertThrows(RpChallengeException.class, () -> employeeService.getTopTenHighestEarningEmployeeNames());
        verify(responseEntity).getStatusCode();
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetTopTenHighestEarningEmployeeNames3() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenThrow(new RpChallengeException("An error occurred"));

        assertThrows(RpChallengeException.class, () -> employeeService.getTopTenHighestEarningEmployeeNames());
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testGetTopTenHighestEarningEmployeeNames4() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeListResponse>>any(), (Object[]) any()))
                .thenThrow(new RestClientException("Msg"));

        assertThrows(RpChallengeException.class, () -> employeeService.getTopTenHighestEarningEmployeeNames());
        verify(restTemplate).getForEntity(eq("baseUrl/employees"), Mockito.<Class<EmployeeListResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testCreateEmployee() throws RestClientException {
        when(restTemplate.postForEntity(Mockito.<String>any(), Mockito.<Object>any(), Mockito.<Class<Object>>any(),
                (Object[]) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        assertThrows(RpChallengeException.class, () -> employeeService.createEmployee(new HashMap<>()));
        verify(restTemplate).postForEntity(eq("baseUrl/create"), Mockito.<Object>any(), Mockito.<Class<Object>>any(),
                (Object[]) any());
    }

    @Test
    void testCreateEmployee2() throws RestClientException {
        ResponseEntity<Object> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(new EmployeeResponse());
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(restTemplate.postForEntity(Mockito.<String>any(), Mockito.<Object>any(), Mockito.<Class<Object>>any(),
                (Object[]) any())).thenReturn(responseEntity);

        Employee actualCreateEmployeeResult = employeeService.createEmployee(new HashMap<>());

        verify(responseEntity).getBody();
        verify(responseEntity).getStatusCode();
        verify(restTemplate).postForEntity(eq("baseUrl/create"), Mockito.<Object>any(), Mockito.<Class<Object>>any(),
                (Object[]) any());
        assertNull(actualCreateEmployeeResult);
    }

    @Test
    void testCreateEmployee3() throws RestClientException {
        EmployeeResponse employeeResponse = mock(EmployeeResponse.class);
        when(employeeResponse.getData()).thenThrow(new RpChallengeException("An error occurred"));
        ResponseEntity<Object> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(employeeResponse);
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(restTemplate.postForEntity(Mockito.<String>any(), Mockito.<Object>any(), Mockito.<Class<Object>>any(),
                (Object[]) any())).thenReturn(responseEntity);

        assertThrows(RpChallengeException.class, () -> employeeService.createEmployee(new HashMap<>()));
        verify(employeeResponse).getData();
        verify(responseEntity).getBody();
        verify(responseEntity).getStatusCode();
        verify(restTemplate).postForEntity(eq("baseUrl/create"), Mockito.<Object>any(), Mockito.<Class<Object>>any(),
                (Object[]) any());
    }

    @Test
    void testCreateEmployee4() throws RestClientException {
        EmployeeResponse employeeResponse = mock(EmployeeResponse.class);
        when(employeeResponse.getData()).thenThrow(new RestClientException("Msg"));
        ResponseEntity<Object> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(employeeResponse);
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(restTemplate.postForEntity(Mockito.<String>any(), Mockito.<Object>any(), Mockito.<Class<Object>>any(),
                (Object[]) any())).thenReturn(responseEntity);

        assertThrows(RpChallengeException.class, () -> employeeService.createEmployee(new HashMap<>()));
        verify(employeeResponse).getData();
        verify(responseEntity).getBody();
        verify(responseEntity).getStatusCode();
        verify(restTemplate).postForEntity(eq("baseUrl/create"), Mockito.<Object>any(), Mockito.<Class<Object>>any(),
                (Object[]) any());
    }

    @Test
    void testDeleteEmployeeById() throws RestClientException {
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeResponse>>any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        assertThrows(RpChallengeException.class, () -> employeeService.deleteEmployeeById("42"));
        verify(restTemplate).getForEntity(eq("baseUrl/employee/42"), Mockito.<Class<EmployeeResponse>>any(),
                (Object[]) any());
    }

    @Test
    void testDeleteEmployeeById2() throws RestClientException {
        ResponseEntity<EmployeeResponse> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getStatusCode()).thenThrow(new RestClientException("Msg"));
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<EmployeeResponse>>any(), (Object[]) any()))
                .thenReturn(responseEntity);

        assertThrows(RpChallengeException.class, () -> employeeService.deleteEmployeeById("42"));
        verify(responseEntity).getStatusCode();
        verify(restTemplate).getForEntity(eq("baseUrl/employee/42"), Mockito.<Class<EmployeeResponse>>any(),
                (Object[]) any());
    }
}
