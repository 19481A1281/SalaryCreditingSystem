package com.banking.SalaryCreditingSystem.repo;

import com.banking.SalaryCreditingSystem.model.Bank;
import com.banking.SalaryCreditingSystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Method to find employees by bank name with pagination
    Page<Employee> findByBankName(Bank bankName, Pageable pageable);

    //List<Employee> findByBankName(Bank bankName, int batchSize);
}
