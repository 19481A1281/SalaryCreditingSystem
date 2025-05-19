package com.banking.SalaryCreditingSystem.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(nullable = false)
    private String employeeName;


    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    @NaturalId
    private String accountNumber;

    @Column(nullable = false)
    private Double salary;

    public Employee() {
    }
    public Employee(Long employeeId, String employeeName, String bankName, String accountNumber, Double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.salary = salary;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
