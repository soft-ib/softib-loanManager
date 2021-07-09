package com.softib.loanmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softib.loanmanager.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
