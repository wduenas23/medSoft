package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@Table(name="transaction")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tx_id")
	private Integer txId;

	@Column(name="tx_date")
	private Timestamp txDate;

	@Column(name="tx_discount_percentage")
	private BigDecimal txDiscountPercentage;

	@Column(name="tx_trans_client_total")
	private BigDecimal txTransClientTotal;

	@Column(name="tx_trans_discount")
	private BigDecimal txTransDiscount;

	@Column(name="tx_trans_fee")
	private BigDecimal txTransFee;

	@Column(name="tx_trans_subtotal")
	private BigDecimal txTransSubtotal;

	@Column(name="tx_trans_total")
	private BigDecimal txTransTotal;

	//bi-directional many-to-one association to PaymentType
	@ManyToOne
	@JoinColumn(name="tx_pt_id")
	private PaymentType paymentType;

	//bi-directional many-to-one association to TransactionDetail
	@OneToMany(mappedBy="transaction")
	private List<TransactionDetail> transactionDetails;

	//bi-directional many-to-one association to TransactionCategory
	@ManyToOne
	@JoinColumn(name="tx_tc_id")
	private TransactionCategory transactionCategory;

	public Transaction() {
	}

	public Integer getTxId() {
		return this.txId;
	}

	public void setTxId(Integer txId) {
		this.txId = txId;
	}

	public Timestamp getTxDate() {
		return this.txDate;
	}

	public void setTxDate(Timestamp txDate) {
		this.txDate = txDate;
	}

	public BigDecimal getTxDiscountPercentage() {
		return this.txDiscountPercentage;
	}

	public void setTxDiscountPercentage(BigDecimal txDiscountPercentage) {
		this.txDiscountPercentage = txDiscountPercentage;
	}

	public BigDecimal getTxTransClientTotal() {
		return this.txTransClientTotal;
	}

	public void setTxTransClientTotal(BigDecimal txTransClientTotal) {
		this.txTransClientTotal = txTransClientTotal;
	}

	public BigDecimal getTxTransDiscount() {
		return this.txTransDiscount;
	}

	public void setTxTransDiscount(BigDecimal txTransDiscount) {
		this.txTransDiscount = txTransDiscount;
	}

	public BigDecimal getTxTransFee() {
		return this.txTransFee;
	}

	public void setTxTransFee(BigDecimal txTransFee) {
		this.txTransFee = txTransFee;
	}

	public BigDecimal getTxTransSubtotal() {
		return this.txTransSubtotal;
	}

	public void setTxTransSubtotal(BigDecimal txTransSubtotal) {
		this.txTransSubtotal = txTransSubtotal;
	}

	public BigDecimal getTxTransTotal() {
		return this.txTransTotal;
	}

	public void setTxTransTotal(BigDecimal txTransTotal) {
		this.txTransTotal = txTransTotal;
	}

	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public List<TransactionDetail> getTransactionDetails() {
		return this.transactionDetails;
	}

	public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	public TransactionDetail addTransactionDetail(TransactionDetail transactionDetail) {
		getTransactionDetails().add(transactionDetail);
		transactionDetail.setTransaction(this);

		return transactionDetail;
	}

	public TransactionDetail removeTransactionDetail(TransactionDetail transactionDetail) {
		getTransactionDetails().remove(transactionDetail);
		transactionDetail.setTransaction(null);

		return transactionDetail;
	}

	public TransactionCategory getTransactionCategory() {
		return this.transactionCategory;
	}

	public void setTransactionCategory(TransactionCategory transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

}