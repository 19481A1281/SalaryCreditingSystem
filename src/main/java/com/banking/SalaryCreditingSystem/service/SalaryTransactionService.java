package com.banking.SalaryCreditingSystem.service;

import com.banking.SalaryCreditingSystem.model.Bank;
import com.banking.SalaryCreditingSystem.model.Employee;
import com.banking.SalaryCreditingSystem.model.SalaryTransaction;
import com.banking.SalaryCreditingSystem.repo.EmployeeRepository;
import com.banking.SalaryCreditingSystem.repo.SalaryTransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SalaryTransactionService {

    private final EmployeeRepository employeeRepo;
    private final SalaryTransactionRepository transactionRepo;

    public SalaryTransactionService(EmployeeRepository employeeRepo, SalaryTransactionRepository transactionRepo) {
        this.employeeRepo = employeeRepo;
        this.transactionRepo = transactionRepo;
    }

    public void creditSalaryForBank(Bank bankName, int batchSize) {
        int page = 0;
        Page<Employee> employeePage;
        do {
            Pageable pageable = PageRequest.of(page, batchSize);
            employeePage = employeeRepo.findByBankName(bankName, pageable);
            List<SalaryTransaction> transactions = new ArrayList<>();
            for (Employee emp : employeePage.getContent()) { // Get employees in the current page
                transactions.add(new SalaryTransaction(LocalDateTime.now(),emp)); // Add transaction to the list
            }
            transactionRepo.saveAll(transactions);
            page++;
        } while (employeePage.hasNext());
    }


    public SalaryTransaction getSalaryTransactionById(Long transactionId) {
        return transactionRepo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + transactionId));
    }

    public List<SalaryTransaction> getSalaryTransactionsInRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<SalaryTransaction> transactions = transactionRepo.findByTransactionTimeBetween(startDate, endDate);
        if (transactions.isEmpty()) {
            throw new RuntimeException("No transactions found in the specified date range.");
        }
        // Sort transactions by transaction time
        return transactions.stream().sorted(Comparator.comparing(SalaryTransaction::getTransactionTime)).toList();
    }

    public void deleteOldTransactions() {
        LocalDateTime thresholdDate = LocalDateTime.now().minusMonths(0); // Adjust the threshold as needed
        List<SalaryTransaction> oldTransactions = transactionRepo.findByTransactionTimeBefore(thresholdDate);
        if (oldTransactions.isEmpty()) {
            System.out.println("No old transactions to delete.");
            return; // Early exit if no old transactions found
        }
        for (SalaryTransaction transaction : oldTransactions) {
            transactionRepo.delete(transaction);
            System.out.println("Deleted old transaction: " + transaction.getTransactionId());
        }
    }


}

/*      | Term           | Meaning                                |
        | -------------- | -------------------------------------- |
        | `Pageable`     | Describes page request (number + size) |
        | `Page<T>`      | Result object returned for each page   |
        | `getContent()` | The actual records (e.g., employees)   |
        | `hasNext()`    | Tells if another page is available     |
*/