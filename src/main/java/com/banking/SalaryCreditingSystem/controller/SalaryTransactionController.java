package com.banking.SalaryCreditingSystem.controller;

import com.banking.SalaryCreditingSystem.model.SalaryTransaction;
import com.banking.SalaryCreditingSystem.service.SalaryTransactionService;
import jakarta.persistence.Entity;
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

    @Scheduled(cron = "20 54 16 * * ?") // 11:50 PM daily
    public void processHdfcSalary() {
        salaryTransactionService.creditSalaryForBank("HDFC");
    }

    @Scheduled(cron = "20 17 16 * * ?") // 10:30 AM daily
    public void processIciciSalary() {
        salaryTransactionService.creditSalaryForBank("ICICI");
    }

    @Scheduled(cron = "20 42 16 * * ?") // 11:00 AM daily
    public void processSbiSalary() {
        salaryTransactionService.creditSalaryForBank("SBI");
    }

    //@Scheduled(cron = "00 00 12 * 1 ?") // 12:00 AM on the first day of every month
    @Scheduled(cron = "45 22 16 * * ?")
    public void scheduleOldTransactionDeletion() {
        salaryTransactionService.deleteOldTransactions();
    }

}
