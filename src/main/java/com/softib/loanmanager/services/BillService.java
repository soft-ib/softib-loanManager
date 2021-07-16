package com.softib.loanmanager.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softib.loanmanager.entity.Bill;
import com.softib.loanmanager.entity.Loan;
import com.softib.loanmanager.enumeration.BillStatus;
import com.softib.loanmanager.repository.BillRepository;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepository;
	@Autowired
	private LoanService loanService;
	
	public List<Bill> createBill(Loan loan) {
		List<Bill> billsToADD=new ArrayList<>();
		if(loan != null
				&& loan.getDurationOfAmount() != null){
			Integer numberofBills=loanService.getNumberOfBillsFromYear(loan.getDurationOfAmount());
			Calendar calendar = Calendar.getInstance();
			
			for(int i=0;i<numberofBills;i++){
				 Bill bill=new Bill(convertDateToString(calendar.getTime()),BillStatus.PENDING,loan.getAmountTotal(),loan);
				 billsToADD.add(bill);
				 calendar.add(Calendar.MONTH, 1);
				 
			}
			return billRepository.saveAll(billsToADD);
		}
		return billsToADD;
		  
	}
	

//	public String deleteBill(Long id) {
//		if (billRepository.findById(id).isPresent()) {
//			billRepository.deleteById(id);
//			return "Bill supprimé";
//		} else
//			return "Bill non supprimé";
//	}
	
	public Bill updateBill(Bill myBill){
		if(myBill != null && myBill.getStatus() != null) {
			myBill.setStatus(BillStatus.PAID);
			return billRepository.save(myBill) ;
		}
		return null;
	}
		
	public String convertDateToString(Date date){
		String pattern = "dd/MM/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		String todayAsString = df.format(date);
		return todayAsString;
	}
	
	


}