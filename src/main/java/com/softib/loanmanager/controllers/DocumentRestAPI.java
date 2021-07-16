package com.softib.loanmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.softib.loanmanager.entity.Document;
import com.softib.loanmanager.entity.Loan;
import com.softib.loanmanager.repository.DocumentRepository;
import com.softib.loanmanager.repository.LoanRepository;
import com.softib.loanmanager.services.DocumentService;

@RestController
@RequestMapping(value = "/documents")
public class DocumentRestAPI {
	
	@Autowired
	DocumentService documentService;
	@Autowired
	LoanRepository loanRepository;
	@Autowired
	DocumentRepository documentRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Document> addDocument(@RequestBody Document document, @RequestParam(name = "idLoan") Long idLoan) {
		Optional<Loan> loan = loanRepository.findById(idLoan);
		Loan loanObject = loan.get();
		document.setLoan(loanObject);
		return new ResponseEntity<>(documentService.addDocuments(document), HttpStatus.OK);
	}
	
	@GetMapping(value="/checkIfWeCanValidate")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> checkIfWeCanValidate( @RequestParam(name = "idLoan") Long idLoan) {
	List<Document> documentList=documentRepository.allDocumentsByLoanId(idLoan);
		return new ResponseEntity<>(documentService.checkWeightedDocument(documentList), HttpStatus.OK);
	}
	
	@GetMapping(value="/DisplayAllDocuments")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Document>> DisplayDocument( @RequestParam(name = "idLoan") Long idLoan) {
	List<Document> documentList=documentRepository.allDocumentsByLoanId(idLoan);
		return new ResponseEntity<>(documentList, HttpStatus.OK);
	}
	
	@PutMapping(value="/updateOneDoc")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Document> updateOneDocument(@RequestParam(name = "idDoc") Long idDoc, @RequestBody Document newDoc) {
	Document documentToupdate=documentRepository.OneDocumentByID(idDoc);
		return new ResponseEntity<>(documentService.updateOneDocument(documentToupdate, newDoc), HttpStatus.OK);
	}

}
