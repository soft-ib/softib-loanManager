package com.softib.loanmanager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.softib.loanmanager.enumeration.BillStatus;

@Entity
@Table(name = "BILL")
public class Bill implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="date")
	private String date;
	
	@Enumerated(EnumType.STRING)
	private BillStatus status;
	
	@Column(name="amount")
	private Double amount;
	
	@ManyToOne(cascade=CascadeType.ALL,optional=false)
	@JoinColumn(name="loan_id",referencedColumnName="id", nullable=false)
    private Loan loan;
	
	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	public Bill() {
		super();
	}
	public Bill(String date, BillStatus status, Double amount) {
		super();
		this.date = date;
		this.status = status;
		this.amount = amount;
	}
	public Bill(String date, BillStatus status, Double amount, Loan loan) {
		super();
		this.date = date;
		this.status = status;
		this.amount = amount;
		this.loan = loan;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public BillStatus getStatus() {
		return status;
	}
	public void setStatus(BillStatus status) {
		this.status = status;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	



}