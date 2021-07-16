package com.softib.loanmanager.services;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softib.loanmanager.entity.Bill;
import com.softib.loanmanager.entity.Document;
import com.softib.loanmanager.entity.Loan;
import com.softib.loanmanager.entity.LoanType;
import com.softib.loanmanager.enumeration.BillStatus;
import com.softib.loanmanager.enumeration.LoanStatus;
import com.softib.loanmanager.repository.LoanRepository;
import com.softib.loanmanager.repository.LoanTypeRepository;
import com.softib.loanmanager.util.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 


@Service
public class LoanService {
	@Autowired
	private LoanTypeService loanTypeService;
	@Autowired
	IUserService userService;
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private BillService billService;
	@Autowired
	private DocumentService documentService;
	
	
	public String simulateLoan(Integer duration,Double amount, String loanType){
		Loan loan= new Loan();
		Double interestAmount ;
		Double insuranceAmount;
		Double myTotalAmount;
		//String json = null;
		String response="";
		
		
		if(Boolean.TRUE.equals(loanTypeService.checkEligibiltyDesiredAmount(amount, loanType))){
			interestAmount=getInterestRateByYear(duration);
			insuranceAmount=getInsuranceRateByYear(duration);
			myTotalAmount=DisplayTotalCost(duration,amount,loanType);
			loan.setAmountTotal(myTotalAmount);
			loan.setDesiredAmount(amount);
			loan.setCreationDate(convertDateToString(new Date()));
			loan.setDurationOfAmount(duration);
			loan.setInterestRate(interestAmount);
			loan.setInsuranceRate(insuranceAmount);
			loan.setCurrency("TND");
			//add loanType
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			try {
				response = ow.writeValueAsString(loan);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			response=" You can not simulate this request : please  verify your input  Amount if conforms"
					+ " with our LoanType or check our list of LoanType"
					+ "for more inforamtion";
		}				
		return response;
	}
	
	//create Loan
	public Loan createLoan(Integer duration,Double amount, String loanType,LoanType loanTypeToCreate){
		
		Loan loanToCreate=new Loan();
		Double interestAmount ;
		Double insuranceAmount;
		Double myTotalAmount;

		if(Boolean.TRUE.equals(loanTypeService.checkEligibiltyDesiredAmount(amount, loanType))){
			interestAmount=getInterestRateByYear(duration);
			insuranceAmount=getInsuranceRateByYear(duration);
			myTotalAmount=DisplayTotalCost(duration,amount,loanType);
			loanToCreate.setAmountTotal(myTotalAmount);
			loanToCreate.setDesiredAmount(amount);
			loanToCreate.setCreationDate(convertDateToString(new Date()));
			loanToCreate.setDurationOfAmount(duration);
			loanToCreate.setInterestRate(interestAmount);
			loanToCreate.setInsuranceRate(insuranceAmount);
			loanToCreate.setCurrency("TND");
			loanToCreate.setUserId(userService.getCurrentUserName());
			loanToCreate.setStatus(LoanStatus.InProgress);		
			loanToCreate.setLoanType(loanTypeToCreate);
			return  loanRepository.save(loanToCreate);
			
		}
		return loanToCreate;
	}
	
	
	public Double DisplayTotalCost(Integer duration , Double amount, String loanType){
		Double totalCost = null;
		
		//if Eligible calcul totalCost 
		if (Boolean.TRUE.equals(loanTypeService.checkEligibiltyDesiredAmount(amount, loanType))){
			totalCost=getTotalCostOfLoan(amount,duration);
			
			return totalCost;
			
			
		}
		return totalCost;
	}
	
	
	public Double getInterestRateByYear (Integer duration){	
		switch(duration){
		case 1 : return 0.03 ;
		case 2 : return 0.04;
		case 3 : return 0.05;
		case 4 : return 0.06;
		case 5 : return 0.07;
		default : return 0.08;		
		}
	}
	
	public Double getInsuranceRateByYear (Integer duration){		
		switch(duration){
		case 1 : return 0.01;
		case 2 : return 0.01;
		case 3 : return 0.02;
		case 4 : return 0.02;
		case 5 : return 0.03;
		default : return 0.04;	
		}
	}
	
	public Double getInterestRateAlongDuration(Integer myDuration){
		return getInterestRateByYear(myDuration)*myDuration;
	}
	
	public Double getInsuranceRateAlongDuration(Integer myDuration){
		return getInsuranceRateByYear(myDuration)*myDuration;
	}
	
	public Double getTotalCostOfLoan(Double amount,Integer duration ){
		Double totalCost;
		Double myInterestRateValue=getInterestRateAlongDuration(duration);
		Double myInsuranceRateValue=getInsuranceRateAlongDuration(duration);
		totalCost=amount+(amount*myInterestRateValue)+(amount*myInsuranceRateValue);
		return totalCost;	
	}
	
	public String convertDateToString(Date date){
		String pattern = "dd/MM/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		String todayAsString = df.format(date);
		return todayAsString;
	}
	
	public Integer getNumberOfBillsFromYear(Integer duration){
		return duration*12;
	}
	
	public Boolean checkIsLoanClosed (List<Bill> billsByLoan){
		for(Bill bills:billsByLoan){
			if(BillStatus.PENDING == bills.getStatus()){
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;	
	}
	
	public Loan updateMyLoan(List<Bill> billsByLoan, Loan myLoan){
		if(myLoan != null && myLoan.getStatus() != null) {
			if(Boolean.TRUE.equals(checkIsLoanClosed(billsByLoan))){
				myLoan.setStatus(LoanStatus.Closed);
				return loanRepository.save(myLoan);
			}
		}
		
		return myLoan;
		  
	}
	
	
	public Loan changeLoanToValidate(Loan loanToValidate, List<Document> documentList){
		String status=null;
		String response = documentService.checkWeightedDocument(documentList);
		if(!response.equals("")){
			String[] arrOfString=response.split(":");
			status=arrOfString[1];
		}
		if(loanToValidate != null
				&& loanToValidate.getStatus() != null 
				&& LoanStatus.InProgress == loanToValidate.getStatus()
				&&  status !=null
				&& "Validate".equalsIgnoreCase(status)){
			loanToValidate.setStatus(LoanStatus.Validate);
			return loanRepository.save(loanToValidate);
		}
		return loanToValidate;
	}

}
