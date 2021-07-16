package com.softib.loanmanager.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT")
public class Document implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="weighted")
	private Integer weighted;
	
	@ManyToOne(cascade=CascadeType.ALL,optional=false)
	@JoinColumn(name="id_loan",referencedColumnName="id", nullable=false)
    private Loan loan;
	
	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	public Document() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public Integer getWeighted() {
		return weighted;
	}
	public Document(String name, String description, Integer weighted, Loan loan) {
		super();
		this.name = name;
		this.description = description;
		this.weighted = weighted;
		this.loan = loan;
	}
	public void setWeighted(Integer weighted) {
		this.weighted = weighted;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	



}