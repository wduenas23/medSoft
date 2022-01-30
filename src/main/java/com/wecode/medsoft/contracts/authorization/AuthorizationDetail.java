package com.wecode.medsoft.contracts.authorization;

import java.util.List;

import lombok.Data;

@Data
public class AuthorizationDetail {

	private String displayName;
	private String icon;
	private Double order;
	private boolean parent;
	private Integer id;
	private String link;
	private List<AuthorizationDetail> childs;
}
