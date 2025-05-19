package com.banking.SalaryCreditingSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(nullable = false,length = 25)
    private String employeeName;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Bank bankName;

    @Column(nullable = false,length =10)
    @NaturalId
    private String accountNumber;

    @Column(nullable = false)
    private Double salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) //owned by SalaryTransaction and lazy loaded and one employee can have many transactions and if
    @JsonManagedReference // to avoid infinite recursion
    private List<SalaryTransaction> salaryTransaction;

    public Employee() {
    }

    public Employee(Long employeeId, String employeeName, Bank bankName, String accountNumber, Double salary) {
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

    public Bank getBankName() {
        return bankName;
    }

    public void setBankName(Bank bankName) {
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
