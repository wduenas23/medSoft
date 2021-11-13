package com.wecode.medsoft.contracts.product;

import lombok.Data;

@Data
public class ProductPojo {

	private Integer id;
	private Integer categoryId;
	private String categoryName;
	private String name;
	private String prdCode;
	private String description;
	private Integer inventory;
	private Double cost;
	private Double sellingPrice;
	private boolean valid;
	
}
