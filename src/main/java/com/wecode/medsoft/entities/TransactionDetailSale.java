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