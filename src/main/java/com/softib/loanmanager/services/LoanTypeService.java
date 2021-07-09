package com.softib.loanmanager.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softib.loanmanager.entity.LoanType;
import com.softib.loanmanager.repository.LoanTypeRepository;

@Service
public class LoanTypeService {
	@Autowired
	private LoanTypeRepository loanTypeRepository;

	public LoanType createLoanType(LoanType loanType) {
		return loanTypeRepository.save(loanType);
	}

	
	//Service to delete LoanType
	public String deleteLoantype(Long id) {
		if (loanTypeRepository.findById(id).isPresent()) {
			loanTypeRepository.deleteById(id);
			return "This loanType is deleted";
		} else
			return "LoanType not deleted";
	}
	
	
	//Service to update One LoanType
	public LoanType updateLoanType(Long id, LoanType loanTypeUpdated) {
		if (loanTypeRepository.findById(id).isPresent()) {
			LoanType existingLoanType= loanTypeRepository.findById(id).get();
			existingLoanType.setLabel(loanTypeUpdated.getLabel());
			existingLoanType.setDescription(loanTypeUpdated.getDescription());
			existingLoanType.setMaximumAmount(loanTypeUpdated.getMaximumAmount());

			return loanTypeRepository.save(existingLoanType);
		} else
			return null;
	}
	
	//Service to getOnLanType
	public LoanType getLoanTypeById (Long id){
		if(loanTypeRepository.findById(id).isPresent()){
			return loanTypeRepository.loanTypeById(id);
		}
		else {
			return null;
		}
	}
	
	//Service to getAllLoanType
	public List<LoanType> getAllLoanType(){
		return loanTypeRepository.findAll();
	}
		
	
	public LoanType getLoanTypeByLabel(String label){
		LoanType loanType=null;
		loanType=loanTypeRepository.loanTypeByLabel(label);
		
		if(loanType != null && loanType.getId() != null){
			return loanTypeRepository.loanTypeById(loanType.getId());
		}
		else {
			return null;
		}
	}
	
	
	public Boolean checkEligibiltyDesiredAmount(Double amount,String label){
		LoanType loanTypetocheck = getLoanTypeByLabel(label);
		
		if( loanTypetocheck != null
				&& loanTypetocheck.getMaximumAmount() !=null 
				&& amount<= loanTypetocheck.getMaximumAmount()){
			return Boolean.TRUE;
		}
		else {
			return Boolean.FALSE;
		}
	}
	
	public String getConformsLoanType(Double amount){
		List<LoanType> listOfLoanType= new ArrayList<>();
		listOfLoanType=getAllLoanType();
		
		String response="";  
		if(listOfLoanType != null){
			for(LoanType loanType:listOfLoanType){
				if(amount<=loanType.getMaximumAmount()){
					response="This is the best LoanType that conforms your amount"+" "+":"+loanType.getLabel();
					return response;
				}
			}
		}	
		response=" Your request does not match with our LoanTypes please check our liste of LoanType for more information";
		return response;
		
	}
		
	
}
