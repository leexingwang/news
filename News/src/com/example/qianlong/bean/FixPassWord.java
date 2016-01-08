package com.example.qianlong.bean;

public class FixPassWord {

	private String new_password;

	private String old_password;

	public FixPassWord(String new_password, String old_password) {
		super();
		this.new_password = new_password;
		this.old_password = old_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getNew_password() {
		return this.new_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getOld_password() {
		return this.old_password;
	}

}
