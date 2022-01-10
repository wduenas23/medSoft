package com.wecode.medsoft.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product_factory database table.
 * 
 */
@Entity
@Table(name="product_factory")
@NamedQuery(name="ProductFactory.findAll", query="SELECT p FROM ProductFactory p")
public class ProductFactory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ft_id")
	private Integer ftId;

	@Temporal(TemporalType.DATE)
	@Column(name="ft_created_date")
	private Date ftCreatedDate;

	@Column(name="ft_description")
	private String ftDescription;

	@Column(name="ft_name")
	private String ftName;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productFactory")
	private List<Product> products;

	public ProductFactory() {
	}

	public Integer getFtId() {
		return this.ftId;
	}

	public void setFtId(Integer ftId) {
		this.ftId = ftId;
	}

	public Date getFtCreatedDate() {
		return this.ftCreatedDate;
	}

	public void setFtCreatedDate(Date ftCreatedDate) {
		this.ftCreatedDate = ftCreatedDate;
	}

	public String getFtDescription() {
		return this.ftDescription;
	}

	public void setFtDescription(String ftDescription) {
		this.ftDescription = ftDescription;
	}

	public String getFtName() {
		return this.ftName;
	}

	public void setFtName(String ftName) {
		this.ftName = ftName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductFactory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductFactory(null);

		return product;
	}

}