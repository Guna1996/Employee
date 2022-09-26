package com.ideas2it.employeeProject.service.impl;

import com.ideas2it.employeeProject.dto.EmployeeDto;
import com.ideas2it.employeeProject.exception.CustomException;
import com.ideas2it.employeeProject.model.Employee;
import com.ideas2it.employeeProject.repo.EmployeeRepository;
import com.ideas2it.employeeProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) throws CustomException {
        try {
            Employee employee = mapper.map(employeeDto, Employee.class);
            return employeeRepository.save(employee);
        } catch (Exception exception) {
            throw new CustomException("Error occured while inserting employee", exception);
        }
    }


    @Override
    public Employee updateEmployee(int id, EmployeeDto employeeDto) throws CustomException {
        try {
            if (getEmployeeById(id) != null) {
                Employee employee = mapper.map(employeeDto, Employee.class);
                return employeeRepository.save(employee);
            }
            return null;
        } catch (Exception exception) {
            throw new CustomException("Error occured while updating employee by Id", exception);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteEmployeeById(int id) throws CustomException {
        try {
            Optional<Employee> employee = employeeRepository.findByIdAndStatus(id, "active");
            if (employee.isPresent()) {
                Employee empObj = employee.get();
                empObj.setStatus("inactive");
                employeeRepository.save(empObj);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return null;
        } catch (Exception exception) {
            throw new CustomException("Error occured while deleting employee by Id", exception);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(int id) throws CustomException {
        try {
            Optional<Employee> employee = employeeRepository.findByIdAndStatus(id, "active");
            if (employee.isPresent()) {
                return mapper.map(employee.get(), EmployeeDto.class);
            }
            return null;
        } catch (Exception exception) {
            throw new CustomException("Error occured while Retrieving employee by Id", exception);
        }
    }

    @Override
    public List<EmployeeDto> getEmployees() throws CustomException {
        try {
            List<Employee> employees = employeeRepository.findByStatus("active");
            return employees
                    .stream()
                    .map(employee -> mapper.map(employee, EmployeeDto.class))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new CustomException("Error occured while Retrieving all employees", exception);
        }
    }
}
