package com.softib.loanmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.softib.loanmanager.entity.Bill;
import com.softib.loanmanager.entity.Document;
import com.softib.loanmanager.entity.Loan;
import com.softib.loanmanager.entity.LoanType;
import com.softib.loanmanager.repository.BillRepository;
import com.softib.loanmanager.repository.DocumentRepository;
import com.softib.loanmanager.repository.LoanRepository;
import com.softib.loanmanager.repository.LoanTypeRepository;
import com.softib.loanmanager.services.LoanService;
import com.softib.loanmanager.services.LoanTypeService;

@RestController
@RequestMapping(value = "/loan")
public class LoanRestAPI {
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private LoanTypeRepository loanTypeRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	
	
	@GetMapping(value = "testTotalCost/{duration}/{amount}/{label}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Double> checkIfEligibleToGetTotalCost(@PathVariable(value = "duration") Integer duration,@PathVariable(value = "amount") Double amount,@PathVariable(value = "label") String label ) {
		return new ResponseEntity<>(loanService.DisplayTotalCost(duration, amount, label), HttpStatus.OK);
	}
	
	@GetMapping(value = "testSimulate/{duration}/{amount}/{label}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> checkSimulate(@PathVariable(value = "duration") Integer duration,@PathVariable(value = "amount") Double amount,@PathVariable(value = "label") String label ) {
		return new ResponseEntity<>(loanService.simulateLoan(duration, amount, label), HttpStatus.OK);
	}
	
	//createLoan
	@PostMapping("/testCreateLoan/{duration}/{amount}/{label}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Loan> createLoan(@PathVariable(value = "duration") Integer duration,@PathVariable(value = "amount") Double amount,@PathVariable(value = "label") String label) {
		//Optional<LoanType> loanType  =  loanTypeRepository.findById(idloantype);
		LoanType loanType  =  loanTypeRepository.loanTypeByLabel(label);
		//LoanType loanTypeObject = loanType.get();
		//loan.setLoan(loanTypeObject);
		return new ResponseEntity<>(loanService.createLoan(duration, amount, label,loanType), HttpStatus.OK);
	}
	
	@GetMapping(value = "checkIfMyLoanisClosed/{idLoan}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> checkIfLoanIsClosed(@PathVariable(value = "idLoan") Long idLoan) {
		
		List<Bill> billsByLoan =billRepository.allBillsByLoanId(idLoan);
		return new ResponseEntity<>(loanService.checkIsLoanClosed(billsByLoan), HttpStatus.OK);
	}
	
	@PutMapping(value="closeMyLoan/{idLoan}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Loan> updateMyLoan(@PathVariable(value = "idLoan") Long idLoan) {
		
		List<Bill> billsByLoan =billRepository.allBillsByLoanId(idLoan);
		Optional<Loan> myLoan=loanRepository.findById(idLoan);
		Loan myLoanObject=myLoan.get();
		return new ResponseEntity<>(loanService.updateMyLoan(billsByLoan,myLoanObject), HttpStatus.OK);
	}
	
	@PutMapping(value = "validateLoan/{idLoan}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Loan> validateLoan(@PathVariable(value = "idLoan") Long idLoan) {
		
		Optional<Loan> loanToValidate =loanRepository.findById(idLoan);
		Loan loanObjectToValidate = loanToValidate.get();
		List<Document> documentList =  documentRepository.allDocumentsByLoanId(idLoan);
		return new ResponseEntity<>(loanService.changeLoanToValidate(loanObjectToValidate,documentList), HttpStatus.OK);
	}

}
