package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transaction_detail_sales database table.
 * 
 */
@Entity
@Table(name="transaction_detail_sales")
@NamedQuery(name="TransactionDetailSale.findAll", query="SELECT t FROM TransactionDetailSale t")
public class TransactionDetailSale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="txds_id")
	private Integer txdsId;

	@Temporal(TemporalType.DATE)
	@Column(name="txds_created_date")
	private Date txdsCreatedDate;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="txds_prd_id")
	private Product product;

	//bi-directional many-to-one association to Transaction
	@ManyToOne
	@JoinColumn(name="txds_td_id")
	private Transaction transaction;

	public TransactionDetailSale() {
	}

	public Integer getTxdsId() {
		return this.txdsId;
	}

	public void setTxdsId(Integer txdsId) {
		this.txdsId = txdsId;
	}

	public Date getTxdsCreatedDate() {
		return this.txdsCreatedDate;
	}

	public void setTxdsCreatedDate(Date txdsCreatedDate) {
		this.txdsCreatedDate = txdsCreatedDate;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}