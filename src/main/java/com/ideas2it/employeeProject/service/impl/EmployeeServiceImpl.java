package com.ideas2it.employeeProject.service.impl;

import com.ideas2it.employeeProject.model.Employee;
import com.ideas2it.employeeProject.repo.EmployeeRepository;
import com.ideas2it.employeeProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {
        Employee empObj = getEmployeeById(id).get();
        empObj.setFirstName(employee.getFirstName());
        empObj.setLastName(employee.getLastName());
        empObj.setStatus(employee.getStatus());
        empObj.setDateOfBirth(employee.getDateOfBirth());
        empObj.setGender(employee.getGender());
        empObj.setDateOfJoining(employee.getDateOfJoining());
        empObj.setBatch(employee.getBatch());
        empObj.setDesignation(employee.getDesignation());
        empObj.setCity(employee.getCity());
        empObj.setFatherName(employee.getFatherName());
        empObj.setEmail(employee.getEmail());
        empObj.setPhoneNumber(employee.getPhoneNumber());
        return employeeRepository.save(employee);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        employeeRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
