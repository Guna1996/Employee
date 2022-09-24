package com.ideas2it.employeeProject.controller;

import com.ideas2it.employeeProject.model.Employee;
import com.ideas2it.employeeProject.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000") //open for specific port
@CrossOrigin() // open for all ports
@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    /**
     * Get all the employees
     *
     * @return ResponseEntity
     */
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        try {
            return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the employee by id
     *
     * @param id is id
     * @return ResponseEntity
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
            //check if employee exist in database
            Optional<Employee> empObj = employeeService.getEmployeeById(id);

        return empObj.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    /**
     * Create new employee
     *
     * @param employee is employee object
     * @return ResponseEntity
     */
    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
    }

    /**
     * Update Employee record by using it's id
     *
     * @param id is employee id
     * @param employee is employee object
     * @return
     */
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {

        //check if employee exist in database
        Optional<Employee> empObj = employeeService.getEmployeeById(id);

        if (empObj.isPresent()) {
            return new ResponseEntity<>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
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
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("id") long id) {
            //check if employee exist in database
            Optional<Employee> emp = employeeService.getEmployeeById(id);

            if (emp.isPresent()) {

                return employeeService.deleteEmployeeById(id);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    /**
     * Delete all employees
     *
     * @return ResponseEntity
     */
    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
            return employeeService.deleteAllEmployees();
    }
}
