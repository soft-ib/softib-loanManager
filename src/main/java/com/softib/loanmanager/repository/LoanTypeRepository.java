package com.softib.loanmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softib.loanmanager.entity.LoanType;

public interface LoanTypeRepository extends JpaRepository<LoanType, Long> {
	
	@Query(value="select * from LoanType loanType where loanType.id like :id",nativeQuery=true)
	public LoanType loanTypeById(@Param("id") Long id);
	
	
	@Query(value="select * from LoanType loanType where loanType.label like :label",nativeQuery=true)
	public LoanType loanTypeByLabel(@Param("label") String label);

}
