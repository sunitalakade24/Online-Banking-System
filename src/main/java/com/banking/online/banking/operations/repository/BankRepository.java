package com.banking.online.banking.operations.repository;

import com.banking.online.banking.operations.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankAccount,Long> {
}
