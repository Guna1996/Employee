package com.ideas2it.employeeProject.repo;

import com.ideas2it.employeeProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByStatus(String status);

    Optional<Employee> findByIdAndStatus(int id, String status);
}