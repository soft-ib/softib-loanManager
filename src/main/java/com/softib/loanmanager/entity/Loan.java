package com.softib.loanmanager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softib.loanmanager.enumeration.LoanStatus;

@Entity
@Table(name = "LOAN")
public class Loan implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//how to do it
	@Column(name="userId")
	private String userId;
	//how to do it
	private String bankerId;
	
	@Column(name="creationDate")
	private String creationDate;

	@Column(name="intersetRate")
	private Double interestRate;
	
	@Column(name="insuranceRate")
	private Double insuranceRate;
	
	@Column(name="amountTotal")
	private Double amountTotal;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="durationOfAmount")
	private Integer durationOfAmount;
	
	@Column(name="desiredAmount")
	private Double desiredAmount;
	
	@Enumerated(EnumType.STRING)
	private LoanStatus status;
	
	@ManyToOne(cascade=CascadeType.ALL,optional=false)
	@JoinColumn(name="loanType_id",referencedColumnName="id", nullable=false)
    private LoanType loanType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBankerId() {
		return bankerId;
	}

	public void setBankerId(String bankerId) {
		this.bankerId = bankerId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getInsuranceRate() {
		return insuranceRate;
	}

	public void setInsuranceRate(Double insuranceRate) {
		this.insuranceRate = insuranceRate;
	}


	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getDurationOfAmount() {
		return durationOfAmount;
	}

	public void setDurationOfAmount(Integer durationOfAmount) {
		this.durationOfAmount = durationOfAmount;
	}

	public Double getDesiredAmount() {
		return desiredAmount;
	}

	public void setDesiredAmount(Double desiredAmount) {
		this.desiredAmount = desiredAmount;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public LoanStatus getStatus() {
		return status;
	}

	public void setStatus(LoanStatus status) {
		this.status = status;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	

	public Loan(Long id, String userId, String bankerId, String creationDate, Double interestRate, Double insuranceRate,
			Double amountTotal, String currency, Integer durationOfAmount, Double desiredAmount, LoanStatus status,
			LoanType loanType) {
		super();
		this.id = id;
		this.userId = userId;
		this.bankerId = bankerId;
		this.creationDate = creationDate;
		this.interestRate = interestRate;
		this.insuranceRate = insuranceRate;
		this.amountTotal = amountTotal;
		this.currency = currency;
		this.durationOfAmount = durationOfAmount;
		this.desiredAmount = desiredAmount;
		this.status = status;
		this.loanType = loanType;
	}

	public Loan(String userId, String bankerId, String creationDate, Double interestRate, Double insuranceRate,
			Double amountTotal, String currency, Integer durationOfAmount, Double desiredAmount, LoanStatus status) {
		super();
		this.userId = userId;
		this.bankerId = bankerId;
		this.creationDate = creationDate;
		this.interestRate = interestRate;
		this.insuranceRate = insuranceRate;
		this.amountTotal = amountTotal;
		this.currency = currency;
		this.durationOfAmount = durationOfAmount;
		this.desiredAmount = desiredAmount;
		this.status = status;
	}

	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}

	public Loan() {
		super();
	}
	


}