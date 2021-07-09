package com.softib.loanmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softib.loanmanager.entity.Bill;
import com.softib.loanmanager.entity.Loan;

public interface BillRepository extends JpaRepository<Bill, Long>{
	@Query(value="select * from Bill bill where bill.loan_id =:loanId",nativeQuery=true)
	public List<Bill> allBillsByLoanId(@Param("loanId") Long loanId);
	
	@Query(value="select count(*) from Bill bill where bill.status ='PENDING'",nativeQuery=true)
	public Integer allBillsWithPendingStatus(@Param("loanId") Long loanId);

}
