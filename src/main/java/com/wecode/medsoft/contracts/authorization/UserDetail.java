package com.wecode.medsoft.contracts.authorization;

import lombok.Data;

@Data
public class UserDetail {
	
	private String userName;
	private String password;
	private String user;
	private Integer roleId;
	
}
