package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the payment_detail database table.
 * 
 */
@Entity
@Table(name="payment_detail")
@NamedQuery(name="PaymentDetail.findAll", query="SELECT p FROM PaymentDetail p")
public class PaymentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pd_id")
	private Integer pdId;

	

	//bi-directional many-to-one association to PaymentType
	@ManyToOne
	@JoinColumn(name="pd_pt_id")
	private PaymentType paymentType;

	//bi-directional many-to-one association to Transaction
	@ManyToOne
	@JoinColumn(name="pd_tx_id")
	private Transaction transaction;
	
	@JoinColumn(name="pd_amount")
	private Double pdAmount;

	public PaymentDetail() {
	}

	public Integer getPdId() {
		return this.pdId;
	}

	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}

	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Double getPdAmount() {
		return pdAmount;
	}

	public void setPdAmount(Double pdAmount) {
		this.pdAmount = pdAmount;
	}

}