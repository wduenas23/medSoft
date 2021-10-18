package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the payment_type database table.
 * 
 */
@Entity
@Table(name="PAYMENT_TYPE")
@NamedQuery(name="PaymentType.findAll", query="SELECT p FROM PaymentType p")
public class PaymentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pt_id")
	private Integer ptId;

	@Temporal(TemporalType.DATE)
	@Column(name="pt_created_date")
	private Date ptCreatedDate;

	@Column(name="pt_description")
	private String ptDescription;

	@Column(name="pt_fee_per_use")
	private BigDecimal ptFeePerUse;

	@Column(name="pt_name")
	private String ptName;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="paymentType")
	private List<Transaction> transactions;

	public PaymentType() {
	}

	public Integer getPtId() {
		return this.ptId;
	}

	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}

	public Date getPtCreatedDate() {
		return this.ptCreatedDate;
	}

	public void setPtCreatedDate(Date ptCreatedDate) {
		this.ptCreatedDate = ptCreatedDate;
	}

	public String getPtDescription() {
		return this.ptDescription;
	}

	public void setPtDescription(String ptDescription) {
		this.ptDescription = ptDescription;
	}

	public BigDecimal getPtFeePerUse() {
		return this.ptFeePerUse;
	}

	public void setPtFeePerUse(BigDecimal ptFeePerUse) {
		this.ptFeePerUse = ptFeePerUse;
	}

	public String getPtName() {
		return this.ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setPaymentType(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setPaymentType(null);

		return transaction;
	}

}