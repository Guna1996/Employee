package com.ideas2it.employeeProject.service;

import com.ideas2it.employeeProject.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> getEmployees();
    public Optional<Employee> getEmployeeById(long id);
    public Employee addEmployee(Employee employee);
    public Employee updateEmployee(long id, Employee employee);
    public ResponseEntity<HttpStatus> deleteEmployeeById(long id);
    public ResponseEntity<HttpStatus> deleteAllEmployees();
}
