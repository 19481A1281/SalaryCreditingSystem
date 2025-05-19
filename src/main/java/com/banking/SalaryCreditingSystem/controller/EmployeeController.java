package com.banking.SalaryCreditingSystem.controller;

import com.banking.SalaryCreditingSystem.model.Employee;
import com.banking.SalaryCreditingSystem.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    //put will replace the entire employee object it is better to use patch
    //if updated through put mapping, and we didn't provide salaryTransactionIds, it will be null and associated transaction will be deleted
    @PutMapping("/")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @PatchMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId,@RequestBody Map<String,String> updates) {
        return employeeService.updateEmployee(employeeId,updates);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

}
