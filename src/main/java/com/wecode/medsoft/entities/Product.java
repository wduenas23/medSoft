package com.wecode.medsoft.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prd_id")
	private Integer prdId;

	@Temporal(TemporalType.DATE)
	@Column(name="pc_created_date")
	private Date pcCreatedDate;

	@Column(name="prd_code")
	private String prdCode;

	@Column(name="prd_cost")
	private Double prdCost;

	@Column(name="prd_description")
	private String prdDescription;

	@Column(name="prd_inventory")
	private Integer prdInventory;

	@Column(name="prd_name")
	private String prdName;

	@Column(name="prd_selling_price")
	private Double prdSellingPrice;

	@Column(name = "prd_valid")
	private boolean prdValid;
	
	
	//bi-directional many-to-one association to ProductCategory
	@ManyToOne
	@JoinColumn(name="prd_pc_id")
	private ProductCategory productCategory;

	public Product() {
	}

	public Integer getPrdId() {
		return this.prdId;
	}

	public void setPrdId(Integer prdId) {
		this.prdId = prdId;
	}

	public Date getPcCreatedDate() {
		return this.pcCreatedDate;
	}

	public void setPcCreatedDate(Date pcCreatedDate) {
		this.pcCreatedDate = pcCreatedDate;
	}

	public String getPrdCode() {
		return this.prdCode;
	}

	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}

	public Double getPrdCost() {
		return this.prdCost;
	}

	public void setPrdCost(Double prdCost) {
		this.prdCost = prdCost;
	}

	public String getPrdDescription() {
		return this.prdDescription;
	}

	public void setPrdDescription(String prdDescription) {
		this.prdDescription = prdDescription;
	}

	public Integer getPrdInventory() {
		return this.prdInventory;
	}

	public void setPrdInventory(Integer prdInventory) {
		this.prdInventory = prdInventory;
	}

	public String getPrdName() {
		return this.prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public Double getPrdSellingPrice() {
		return this.prdSellingPrice;
	}

	public void setPrdSellingPrice(Double prdSellingPrice) {
		this.prdSellingPrice = prdSellingPrice;
	}

	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public boolean isPrdValid() {
		return prdValid;
	}

	public void setPrdValid(boolean prdValid) {
		this.prdValid = prdValid;
	}

}