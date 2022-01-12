package com.wecode.medsoft.contracts.product;

import java.util.Date;

import lombok.Data;

@Data
public class ProductImage {

	private Date lastModifiedDate;
	private String lastModified;
	private String name;
	private Double size;
	private String type;
}
