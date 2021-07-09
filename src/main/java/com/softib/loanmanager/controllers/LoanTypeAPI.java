package com.softib.loanmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.softib.loanmanager.entity.Loan;
import com.softib.loanmanager.entity.LoanType;
import com.softib.loanmanager.repository.LoanRepository;
import com.softib.loanmanager.services.LoanTypeService;

@RestController
@RequestMapping(value = "/loanTypes")
public class LoanTypeAPI {

	@Autowired
	private LoanTypeService loanTypeService;

	@Autowired
	private LoanRepository loanRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<LoanType> createLoanType(@RequestBody LoanType loanType) {
//		Optional<Loan> loan  =  loanRepository.findById(idlon);
//		Loan loanObject = loan.get();
//		loanType.setLoan(loanObject);
		return new ResponseEntity<>(loanTypeService.createLoanType(loanType), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteLoanType(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(loanTypeService.deleteLoantype(id), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LoanType> updateBill(@PathVariable(value = "id") Long id, @RequestBody LoanType loanType) {
		return new ResponseEntity<>(loanTypeService.updateLoanType(id, loanType), HttpStatus.OK);
	}
	
	@GetMapping(value = "getOneLoanTypeByID/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LoanType> getLoanTypeById(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(loanTypeService.getLoanTypeById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "getALLLoanType")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<LoanType>> getAllLoanType() {
		return new ResponseEntity<>(loanTypeService.getAllLoanType(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "checkEligibiltyOFLoanType/{amount}/{label}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> checkIfEligible(@PathVariable(value = "amount") Double amount,@PathVariable(value = "label") String label ) {
		return new ResponseEntity<>(loanTypeService.checkEligibiltyDesiredAmount(amount, label), HttpStatus.OK);
	}
	
	
//	@GetMapping(value = "/checkEligibiltyOFLoanType")
//	@ResponseStatus(HttpStatus.OK)
//	public ResponseEntity<Boolean> checkIfEligible1(@RequestParam(value = "amount") Double amount,@RequestParam(value = "label") String label ) {
//		return new ResponseEntity<>(loanTypeService.checkEligibiltyDesiredAmount(amount, label), HttpStatus.OK);
//	}
	
	
	@GetMapping(value = "conformsLoanType/{amount}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> getLoanTypeByAmount(@PathVariable(value = "amount") Double amount) {
		return new ResponseEntity<>(loanTypeService.getConformsLoanType(amount), HttpStatus.OK);
	}

	

}