package com.banking.SalaryCreditingSystem.repo;

import com.banking.SalaryCreditingSystem.model.SalaryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalaryTransactionRepository extends JpaRepository<SalaryTransaction,Long> {

    @Query("SELECT st FROM SalaryTransaction st WHERE st.transactionTime BETWEEN :startDate AND :endDate")
    List<SalaryTransaction> findByTransactionTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT st FROM SalaryTransaction st WHERE st.transactionTime < :thresholdDate")
    List<SalaryTransaction> findByTransactionTimeBefore(LocalDateTime thresholdDate);
}
