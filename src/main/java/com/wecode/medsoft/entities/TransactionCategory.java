package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the transaction_category database table.
 * 
 */
@Entity
@Table(name="transaction_category")
@NamedQuery(name="TransactionCategory.findAll", query="SELECT t FROM TransactionCategory t")
public class TransactionCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tc_id")
	private Integer tcId;

	@Temporal(TemporalType.DATE)
	@Column(name="tc_created_date")
	private Date tcCreatedDate;

	@Column(name="tc_description")
	private String tcDescription;

	@Column(name="tc_name")
	private String tcName;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="transactionCategory")
	private List<Transaction> transactions;

	public TransactionCategory() {
	}

	public Integer getTcId() {
		return this.tcId;
	}

	public void setTcId(Integer tcId) {
		this.tcId = tcId;
	}

	public Date getTcCreatedDate() {
		return this.tcCreatedDate;
	}

	public void setTcCreatedDate(Date tcCreatedDate) {
		this.tcCreatedDate = tcCreatedDate;
	}

	public String getTcDescription() {
		return this.tcDescription;
	}

	public void setTcDescription(String tcDescription) {
		this.tcDescription = tcDescription;
	}

	public String getTcName() {
		return this.tcName;
	}

	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setTransactionCategory(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setTransactionCategory(null);

		return transaction;
	}

}