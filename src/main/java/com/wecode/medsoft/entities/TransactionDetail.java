package com.wecode.medsoft.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="txd_id")
	private Integer txdId;

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