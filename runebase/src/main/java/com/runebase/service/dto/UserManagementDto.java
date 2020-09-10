package com.runebase.service.dto;

public class UserManagementDto extends UserDisplayDto {
	private String password = "";

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
