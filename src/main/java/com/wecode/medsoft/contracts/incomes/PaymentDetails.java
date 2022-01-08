package com.wecode.medsoft.contracts.incomes;

import lombok.Data;

@Data
public class PaymentDetails {

	private int pdId;
	private String description;
	private int txId;
	private int ptId;
	private Double amount;
}
