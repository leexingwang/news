package com.example.qianlong.bean;

public class RegistEntity {
	private String phone_number;

	private String username;

	private String plain_password;

	public RegistEntity(String phone_number, String username,
			String plain_password) {
		super();
		this.phone_number = phone_number;
		this.username = username;
		this.plain_password = plain_password;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPhone_number() {
		return this.phone_number;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPlain_password(String plain_password) {
		this.plain_password = plain_password;
	}

	public String getPlain_password() {
		return this.plain_password;
	}

}
