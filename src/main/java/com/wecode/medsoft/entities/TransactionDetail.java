package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transaction_detail database table.
 * 
 */
@Entity
@Table(name="transaction_detail")
@NamedQuery(name="TransactionDetail.findAll", query="SELECT t FROM TransactionDetail t")
public class TransactionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="txd_id")
	private Integer txdId;

	@Temporal(TemporalType.DATE)
	@Column(name="tx_created_date")
	private Date txCreatedDate;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="tx_sv_id")
	private Service service;

	//bi-directional many-to-one association to Transaction
	@ManyToOne
	@JoinColumn(name="txd_td_id")
	private Transaction transaction;

	public TransactionDetail() {
	}

	public Integer getTxdId() {
		return this.txdId;
	}

	public void setTxdId(Integer txdId) {
		this.txdId = txdId;
	}

	public Date getTxCreatedDate() {
		return this.txCreatedDate;
	}

	public void setTxCreatedDate(Date txCreatedDate) {
		this.txCreatedDate = txCreatedDate;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}