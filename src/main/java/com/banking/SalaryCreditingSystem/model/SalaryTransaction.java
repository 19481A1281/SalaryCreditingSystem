package com.banking.SalaryCreditingSystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class SalaryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long employeeId;
    private String bankName;
    private Double amount;
    private LocalDateTime transactionTime;


    public SalaryTransaction() {
    }


    public SalaryTransaction(Long transactionId, Long employeeId, String bankName, Double amount, LocalDateTime transactionTime) {
        this.transactionId = transactionId;
        this.employeeId = employeeId;
        this.bankName = bankName;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}

