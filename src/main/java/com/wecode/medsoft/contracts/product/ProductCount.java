package com.wecode.medsoft.contracts.product;

public class ProductCount {

	public ProductCount(String prdName, Long count) {
		super();
		this.setPrdName(prdName);
		this.setCount(count);
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	private String prdName;
	private Long count;
	
	
	
}
