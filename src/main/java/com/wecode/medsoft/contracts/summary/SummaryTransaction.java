package com.wecode.medsoft.contracts.summary;

import lombok.Data;

@Data
public class SummaryTransaction {

	private Double dailySummary;
	private Double monthlySummary;
	private Double rangeSummary;
}
