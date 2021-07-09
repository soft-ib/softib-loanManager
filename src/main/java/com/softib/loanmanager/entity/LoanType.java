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
@Table(name = "Loantype")
public class LoanType implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="label")
	private String label;
	
	@Column(name="description")
	private String description;
	
	@Column(name="maximumAmount")
	private Double maximumAmount;
	
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getMaximumAmount() {
		return maximumAmount;
	}

	public void setMaximumAmount(Double maximumAmount) {
		this.maximumAmount = maximumAmount;
	}

	public void setId(Long id) {
		this.id = id;
	}


	

	public Long getId() {
		return id;
	}

	
	
	public LoanType() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LoanType(Long id, String label, String description, Double maximumAmount) {
		super();
		this.id = id;
		this.label = label;
		this.description = description;
		this.maximumAmount = maximumAmount;
	}
}