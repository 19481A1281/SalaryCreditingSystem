package com.banking.SalaryCreditingSystem.controller;

import com.banking.SalaryCreditingSystem.model.Bank;
import com.banking.SalaryCreditingSystem.model.SalaryTransaction;
import com.banking.SalaryCreditingSystem.service.SalaryTransactionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/salary-transaction")
public class SalaryTransactionController {
    private final SalaryTransactionService salaryTransactionService;

    public SalaryTransactionController(SalaryTransactionService salaryTransactionService) {
        this.salaryTransactionService = salaryTransactionService;
    }

    @GetMapping("/{transactionId}")
    public SalaryTransaction getSalaryTransaction(@PathVariable Long transactionId) {
        return salaryTransactionService.getSalaryTransactionById(transactionId);
    }


    @GetMapping("/range/{startDate}/{endDate}")
    public List<SalaryTransaction> getSalaryTransactionsInRange(@PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
        // Implement logic to fetch transactions in the specified date range
        return salaryTransactionService.getSalaryTransactionsInRange(startDate, endDate);
    }


}
