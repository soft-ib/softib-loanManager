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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.softib.loanmanager.entity.Bill;
import com.softib.loanmanager.entity.Loan;
import com.softib.loanmanager.entity.LoanType;
import com.softib.loanmanager.repository.BillRepository;
import com.softib.loanmanager.repository.LoanRepository;
import com.softib.loanmanager.services.BillService;

@RestController
@RequestMapping(value = "/bills")
public class BillRestAPI {

	@Autowired
	private BillService billService;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private BillRepository billRepository;


	@PostMapping("/testCreateBills/{idLoan}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Bill>> createBill(@PathVariable(value = "idLoan") Long idLoan) {
		Optional<Loan> loan  =  loanRepository.findById(idLoan);
		Loan loanObject = loan.get();
		return new ResponseEntity<>(billService.createBill(loanObject), HttpStatus.OK);
	}
		
//	}

	@PutMapping(value = "updateOneBill/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Bill> updateBill(@PathVariable(value = "id") Long id) {
	     Optional<Bill> bill=billRepository.findById(id);
	     Bill billObject=bill.get();
		return new ResponseEntity<>(billService.updateBill(billObject), HttpStatus.OK);
	}

//	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	public ResponseEntity<String> deleteBill(@PathVariable(value = "id") Long id) {
//		return new ResponseEntity<>(billService.deleteBill(id), HttpStatus.OK);
//	}
	
	@GetMapping(value = "BillareNotPayedYet/{idLoan}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Integer> getbillareNotPayedYet(@PathVariable(value = "idLoan") Long idLoan) {	
		Integer billsByLoan =billRepository.allBillsWithPendingStatus(idLoan);
		return new ResponseEntity<>(billsByLoan, HttpStatus.OK);
	}
	

}