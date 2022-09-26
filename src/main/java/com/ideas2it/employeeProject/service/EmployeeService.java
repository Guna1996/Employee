package com.ideas2it.employeeProject.service;

import com.ideas2it.employeeProject.dto.EmployeeDto;
import com.ideas2it.employeeProject.exception.CustomException;
import com.ideas2it.employeeProject.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDto> getEmployees() throws CustomException;
    EmployeeDto getEmployeeById(int id) throws CustomException;
    Employee addEmployee(EmployeeDto employeeDto) throws CustomException;
    Employee updateEmployee(int id, EmployeeDto employeeDto) throws CustomException;
    ResponseEntity<HttpStatus> deleteEmployeeById(int id) throws CustomException;
}
