package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product_category database table.
 * 
 */
@Entity
@Table(name="product_category")
@NamedQuery(name="ProductCategory.findAll", query="SELECT p FROM ProductCategory p")
public class ProductCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pc_id")
	private Integer pcId;

	@Temporal(TemporalType.DATE)
	@Column(name="pc_created_date")
	private Date pcCreatedDate;

	@Column(name="pc_description")
	private String pcDescription;

	@Column(name="pc_name")
	private String pcName;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productCategory")
	private List<Product> products;

	public ProductCategory() {
	}

	public Integer getPcId() {
		return this.pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public Date getPcCreatedDate() {
		return this.pcCreatedDate;
	}

	public void setPcCreatedDate(Date pcCreatedDate) {
		this.pcCreatedDate = pcCreatedDate;
	}

	public String getPcDescription() {
		return this.pcDescription;
	}

	public void setPcDescription(String pcDescription) {
		this.pcDescription = pcDescription;
	}

	public String getPcName() {
		return this.pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductCategory(null);

		return product;
	}

}