package com.banking.SalaryCreditingSystem.service;

import com.banking.SalaryCreditingSystem.model.Bank;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SalaryTransactionScheduler {

    private final SalaryTransactionService salaryTransactionService;

    public SalaryTransactionScheduler(SalaryTransactionService salaryTransactionService) {
        this.salaryTransactionService = salaryTransactionService;
    }

    @Scheduled(cron = "20 19 23 * * ?") // 11:50 PM daily
    public void processHdfcSalary() {
        salaryTransactionService.creditSalaryForBank(Bank.HDFC, 2);
    }

    @Scheduled(cron = "20 19 23 * * ?") // 10:30 AM daily
    public void processIciciSalary() {
        salaryTransactionService.creditSalaryForBank(Bank.ICICI, 2);
    }

    @Scheduled(cron = "20 19 23 * * ?") // 11:00 AM daily
    public void processSbiSalary() {
        salaryTransactionService.creditSalaryForBank(Bank.SBI, 2);
    }

    @Scheduled(cron = "20 19 23 * * ?")
    public void processAxisSalary() {
        salaryTransactionService.creditSalaryForBank(Bank.AXIS, 2);
    }

    @Scheduled(cron = "20 19 23 * * ?")
    public void processPnbSalary() {
        salaryTransactionService.creditSalaryForBank(Bank.PNB, 2);
    }

    @Scheduled(cron = "20 19 23 * * ?")
    public void processHsbcSalary() {
        salaryTransactionService.creditSalaryForBank(Bank.HSBC, 2);
    }

    @Scheduled(cron = "00 00 09 * 1 ?") // 9:00 AM on the first day of every month
    public void scheduleOldTransactionDeletion() {
        salaryTransactionService.deleteOldTransactions();
    }
}
