package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the services database table.
 * 
 */
@Entity
@Table(name="services")
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sv_id")
	private Integer svId;

	@Column(name="sv_cost")
	private Double svCost;

	@Column(name="sv_description")
	private String svDescription;

	@Column(name="sv_name")
	private String svName;

	//bi-directional many-to-one association to ServicesCategory
	@ManyToOne
	@JoinColumn(name="sv_sc_id")
	private ServicesCategory servicesCategory;

	//bi-directional many-to-one association to TransactionDetail
	@OneToMany(mappedBy="service")
	private List<TransactionDetail> transactionDetails;

	public Service() {
	}

	public Integer getSvId() {
		return this.svId;
	}

	public void setSvId(Integer svId) {
		this.svId = svId;
	}

	public Double getSvCost() {
		return this.svCost;
	}

	public void setSvCost(Double svCost) {
		this.svCost = svCost;
	}

	public String getSvDescription() {
		return this.svDescription;
	}

	public void setSvDescription(String svDescription) {
		this.svDescription = svDescription;
	}

	public String getSvName() {
		return this.svName;
	}

	public void setSvName(String svName) {
		this.svName = svName;
	}

	public ServicesCategory getServicesCategory() {
		return this.servicesCategory;
	}

	public void setServicesCategory(ServicesCategory servicesCategory) {
		this.servicesCategory = servicesCategory;
	}

	public List<TransactionDetail> getTransactionDetails() {
		return this.transactionDetails;
	}

	public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	public TransactionDetail addTransactionDetail(TransactionDetail transactionDetail) {
		getTransactionDetails().add(transactionDetail);
		transactionDetail.setService(this);

		return transactionDetail;
	}

	public TransactionDetail removeTransactionDetail(TransactionDetail transactionDetail) {
		getTransactionDetails().remove(transactionDetail);
		transactionDetail.setService(null);

		return transactionDetail;
	}

}