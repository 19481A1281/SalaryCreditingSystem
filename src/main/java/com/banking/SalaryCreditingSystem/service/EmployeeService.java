package com.banking.SalaryCreditingSystem.service;

import com.banking.SalaryCreditingSystem.model.Employee;
import com.banking.SalaryCreditingSystem.repo.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
    }

    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employee.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employee.getEmployeeId()));
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long employeeId, Map<String, String> updates) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        updates.forEach((key, value) -> {
            switch (key) {
                case "employeeName":
                    existingEmployee.setEmployeeName(value);
                    break;
                case "bankName":
                    existingEmployee.setBankName(value);
                    break;
                case "salary":
                    existingEmployee.setSalary(Double.parseDouble(value));
                    break;
                // Add more cases for other fields as needed
            }
        });

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        employeeRepository.delete(existingEmployee);
    }
}
