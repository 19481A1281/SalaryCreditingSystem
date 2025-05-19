package com.banking.SalaryCreditingSystem.repo;

import com.banking.SalaryCreditingSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByBankName(String bankName);
    // Custom query methods can be defined here if needed
    // For example, findByEmployeeId, findByDepartment, etc.
}
