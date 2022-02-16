package com.wecode.medsoft.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tx_id")
	private Integer txId;

	@Column(name="tx_date")
	private Timestamp txDate;

	@Column(name="tx_discount_percentage")
	private Double txDiscountPercentage;

	@Column(name="tx_trans_client_total")
	private Double txTransClientTotal;

	@Column(name="tx_trans_discount")
	private Double txTransDiscount;

	@Column(name="tx_trans_fee")
	private Double txTransFee;

	@Column(name="tx_trans_subtotal")
	private Double txTransSubtotal;

	@Column(name="tx_trans_total")
	private Double txTransTotal;
	
	@Column(name="tx_sale_comission")
	private Double txSaleComission;

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
	
	
	
	//bi-directional many-to-one association to PaymentDetail
	@OneToMany(mappedBy="transaction")
	private List<PaymentDetail> paymentDetails;
	
	@ManyToOne
	@JoinColumn(name="tx_pnt_id")
	private Patient patient;

	
	//bi-directional many-to-one association to TransactionDetailSale
	@OneToMany(mappedBy="transaction")
	private List<TransactionDetailSale> transactionDetailSales;
	
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

	public Double getTxDiscountPercentage() {
		return this.txDiscountPercentage;
	}

	public void setTxDiscountPercentage(Double txDiscountPercentage) {
		this.txDiscountPercentage = txDiscountPercentage;
	}

	public Double getTxTransClientTotal() {
		return this.txTransClientTotal;
	}

	public void setTxTransClientTotal(Double txTransClientTotal) {
		this.txTransClientTotal = txTransClientTotal;
	}

	public Double getTxTransDiscount() {
		return this.txTransDiscount;
	}

	public void setTxTransDiscount(Double txTransDiscount) {
		this.txTransDiscount = txTransDiscount;
	}

	public Double getTxTransFee() {
		return this.txTransFee;
	}

	public void setTxTransFee(Double txTransFee) {
		this.txTransFee = txTransFee;
	}

	public Double getTxTransSubtotal() {
		return this.txTransSubtotal;
	}

	public void setTxTransSubtotal(Double txTransSubtotal) {
		this.txTransSubtotal = txTransSubtotal;
	}

	public Double getTxTransTotal() {
		return this.txTransTotal;
	}

	public void setTxTransTotal(Double txTransTotal) {
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


	public List<PaymentDetail> getPaymentDetails() {
		return this.paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public PaymentDetail addPaymentDetail(PaymentDetail paymentDetail) {
		getPaymentDetails().add(paymentDetail);
		paymentDetail.setTransaction(this);

		return paymentDetail;
	}

	public PaymentDetail removePaymentDetail(PaymentDetail paymentDetail) {
		getPaymentDetails().remove(paymentDetail);
		paymentDetail.setTransaction(null);

		return paymentDetail;
	}
	
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public List<TransactionDetailSale> getTransactionDetailSales() {
		return this.transactionDetailSales;
	}

	public void setTransactionDetailSales(List<TransactionDetailSale> transactionDetailSales) {
		this.transactionDetailSales = transactionDetailSales;
	}

	public TransactionDetailSale addTransactionDetailSale(TransactionDetailSale transactionDetailSale) {
		getTransactionDetailSales().add(transactionDetailSale);
		transactionDetailSale.setTransaction(this);

		return transactionDetailSale;
	}

	public TransactionDetailSale removeTransactionDetailSale(TransactionDetailSale transactionDetailSale) {
		getTransactionDetailSales().remove(transactionDetailSale);
		transactionDetailSale.setTransaction(null);

		return transactionDetailSale;
	}

	public Double getTxSaleComission() {
		return txSaleComission;
	}

	public void setTxSaleComission(Double txSaleComission) {
		this.txSaleComission = txSaleComission;
	}

}