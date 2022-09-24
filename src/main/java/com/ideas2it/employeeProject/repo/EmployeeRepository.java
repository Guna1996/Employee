package com.ideas2it.employeeProject.repo;

import com.ideas2it.employeeProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}