package com.wecode.medsoft.contracts.medicalservices;

public class MedicalServiceCount {

	private String serviceName;
	private Long count;
	
	
	
	public MedicalServiceCount(String serviceName, Long count) {
		super();
		this.serviceName = serviceName;
		this.count = count;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
