package com.banking.SalaryCreditingSystem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SalaryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private LocalDateTime transactionTime;

    @ManyToOne(fetch = FetchType.LAZY) //many transactions to one employee
    @JoinColumn(name = "employee_id", nullable = false) // owned by salaryTransaction
    @JsonBackReference //  to avoid infinite recursion
    private Employee employee;


    public SalaryTransaction() {
    }

    public SalaryTransaction(LocalDateTime now, Employee emp) {
        this.transactionTime = now;
        this.employee = emp;
    }

    public SalaryTransaction(Long transactionId, LocalDateTime transactionTime, Employee employee) {
        this.transactionId = transactionId;
        this.transactionTime = transactionTime;
        this.employee = employee;
    }



    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

