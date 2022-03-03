package com.wecode.medsoft.contracts.product;

import java.util.Date;

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
	private Double promotionPrice;
	private boolean valid;
	private Date expiDate;
	private Integer drogueriaId;
	private String drogueriaName;
	private String prdLot;
	private String user;
	
}
