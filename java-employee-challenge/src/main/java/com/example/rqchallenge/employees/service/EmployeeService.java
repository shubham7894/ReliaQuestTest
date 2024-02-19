package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.exception.RpChallengeException;
import com.example.rqchallenge.employees.pojo.Employee;
import com.example.rqchallenge.employees.pojo.EmployeeDeleteResponse;
import com.example.rqchallenge.employees.pojo.EmployeeListResponse;
import com.example.rqchallenge.employees.pojo.EmployeeResponse;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    private final RestTemplate restTemplate;

    @Value("${api.baseUrl}")
    private String baseUrl;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @RateLimiter(name = "global")
    public List<Employee> getAllEmployees() {
        return getAllEmployee();
    }

    @Override
    @RateLimiter(name = "global")
    public List<Employee> getEmployeesByNameSearch(String searchString) {
        return getAllEmployee().stream()
                .filter(e -> e.getName().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    @RateLimiter(name = "global")
    public Employee getEmployeeById(String id) {
        return getEmployee(id);
    }

    @Override
    @RateLimiter(name = "global")
    public int getHighestSalaryOfEmployees() {
        return getAllEmployee().stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElse(0);
    }

    @Override
    @RateLimiter(name = "global")
    public List<String> getTopTenHighestEarningEmployeeNames() {
        return getAllEmployee().stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .limit(10)
                .map(Employee::getName)
                .collect(Collectors.toList());

    }

    @Override
    @RateLimiter(name = "global")
    public Employee createEmployee(Map<String, Object> employeeInput) {
        String url = baseUrl + "/create";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(employeeInput, headers);
            ResponseEntity<EmployeeResponse> responseEntity = restTemplate.postForEntity(url, requestEntity, EmployeeResponse.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return Objects.requireNonNull(responseEntity.getBody()).getData();
            } else {
                throw new RpChallengeException("Error occurred while creating employee.");
            }
        } catch (RestClientException e) {
            throw new RpChallengeException("Error occurred while creating employee.", e);
        }
    }

    @Override
    @RateLimiter(name = "global")
    public Employee deleteEmployeeById(String id) {
        Employee employee = getEmployee(id);
        if (employee != null) {
            String url = baseUrl + "/delete/" + id;
            try {
                ResponseEntity<EmployeeDeleteResponse> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, EmployeeDeleteResponse.class);
                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    return employee;
                } else {
                    throw new RpChallengeException("Error while deleting employee.");
                }
            } catch (RestClientException e) {
                throw new RpChallengeException("Error while deleting employee.", e);
            }
        }
        return null;
    }

    private List<Employee> getAllEmployee() {
        String url = baseUrl + "/employees";
        return getApiCall(url, EmployeeListResponse.class).getData();
    }

    private Employee getEmployee(String id) {
        String url = baseUrl + "/employee/" + id;
        return getApiCall(url, EmployeeResponse.class).getData();
    }

    private <T> T getApiCall(String url, Class<T> responseClass) {
        try {
            ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, responseClass);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                throw new RpChallengeException("Error while making api call.");
            }
        } catch (RestClientException e) {
            throw new RpChallengeException("Error while making api call.", e);
        }
    }


}
