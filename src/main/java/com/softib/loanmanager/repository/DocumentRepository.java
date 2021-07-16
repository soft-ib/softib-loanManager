package com.softib.loanmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softib.loanmanager.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query(value="select * from Document doc where doc.id_loan =:loanId",nativeQuery=true)
	public List<Document> allDocumentsByLoanId(@Param("loanId") Long loanId);
	
	@Query(value="select * from Document doc where doc.id =:docId",nativeQuery=true)
	public Document OneDocumentByID(@Param("docId") Long docId);
}
