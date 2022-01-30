package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	@Column(name="prd_active")
	private Boolean prdActive;

	@Column(name="prd_code")
	private String prdCode;

	@Column(name="prd_cost")
	private Double prdCost;

	@Column(name="prd_description")
	private String prdDescription;

	@Temporal(TemporalType.DATE)
	@Column(name="prd_expiration")
	private Date prdExpiration;

	@Column(name="prd_image_url")
	private String prdImageUrl;

	@Column(name="prd_inventory")
	private Integer prdInventory;

	@Column(name="prd_name")
	private String prdName;

	@Column(name="prd_promotion_price")
	private Double prdPromotionPrice;

	@Column(name="prd_selling_price")
	private Double prdSellingPrice;

	//bi-directional many-to-one association to ProductCategory
	@ManyToOne
	@JoinColumn(name="prd_pc_id")
	private ProductCategory productCategory;

	//bi-directional many-to-one association to ProductFactory
	@ManyToOne
	@JoinColumn(name="prd_ft_id")
	private ProductFactory productFactory;

	public Product() {
	}

	public Integer getPrdId() {
		return this.prdId;
	}

	public void setPrdId(Integer prdId) {
		this.prdId = prdId;
	}

	public Boolean getPrdActive() {
		return this.prdActive;
	}

	public void setPrdActive(Boolean prdActive) {
		this.prdActive = prdActive;
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

	public Date getPrdExpiration() {
		return this.prdExpiration;
	}

	public void setPrdExpiration(Date prdExpiration) {
		this.prdExpiration = prdExpiration;
	}

	public String getPrdImageUrl() {
		return this.prdImageUrl;
	}

	public void setPrdImageUrl(String prdImageUrl) {
		this.prdImageUrl = prdImageUrl;
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

	public Double getPrdPromotionPrice() {
		return this.prdPromotionPrice;
	}

	public void setPrdPromotionPrice(Double prdPromotionPrice) {
		this.prdPromotionPrice = prdPromotionPrice;
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

	public ProductFactory getProductFactory() {
		return this.productFactory;
	}

	public void setProductFactory(ProductFactory productFactory) {
		this.productFactory = productFactory;
	}

}