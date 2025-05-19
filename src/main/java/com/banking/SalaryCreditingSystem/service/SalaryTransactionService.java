package com.banking.SalaryCreditingSystem.service;

import com.banking.SalaryCreditingSystem.model.Employee;
import com.banking.SalaryCreditingSystem.model.SalaryTransaction;
import com.banking.SalaryCreditingSystem.repo.EmployeeRepository;
import com.banking.SalaryCreditingSystem.repo.SalaryTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


    public void creditSalaryForBank(String bankName) {
        List<Employee> employees = employeeRepo.findByBankName(bankName);
        for (Employee emp : employees) {
            SalaryTransaction tx = new SalaryTransaction();
            tx.setEmployeeId(emp.getEmployeeId());
            tx.setBankName(bankName);
            tx.setAmount(emp.getSalary());
            tx.setTransactionTime(LocalDateTime.now());
            transactionRepo.save(tx);

            System.out.println("Credited salary to: " + emp.getEmployeeName() + " [" + bankName + "]");
        }
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
