package com.ideas2it.employeeProject.controller;

import com.ideas2it.employeeProject.dto.EmployeeDto;
import com.ideas2it.employeeProject.exception.CustomException;
import com.ideas2it.employeeProject.model.Employee;
import com.ideas2it.employeeProject.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2020")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    /**
     * Get all the employees
     *
     */
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        try {
            return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the employee by id
     *
     * @param id is employee id
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") int id) throws CustomException {
        EmployeeDto employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Create new employee
     *
     * @param employeeDto is employee object
     */
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDto employeeDto) throws CustomException {
        return new ResponseEntity<>(employeeService.addEmployee(employeeDto), HttpStatus.OK);
    }

    /**
     * Update Employee record by using it's id
     *
     * @param id is employee id
     * @param employeeDto is employee object
     */
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeDto employeeDto) throws CustomException{
        EmployeeDto empObj = employeeService.getEmployeeById(id);

        if (empObj != null) {
            return new ResponseEntity<>(employeeService.updateEmployee(id, employeeDto), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete Employee by Id
     *
     * @param id is employee id
     * @return ResponseEntity
     */
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("id") int id) throws CustomException {
            EmployeeDto emp = employeeService.getEmployeeById(id);

            if (emp != null) {

                return employeeService.deleteEmployeeById(id);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
