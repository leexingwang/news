package com.example.qianlong.bean;

public class LoginEntity {

	private String access_token;

	private int expires_in;

	private String token_type;

	private String scope;

	private String refresh_token;

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getAccess_token() {
		return this.access_token;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public int getExpires_in() {
		return this.expires_in;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getToken_type() {
		return this.token_type;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScope() {
		return this.scope;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getRefresh_token() {
		return this.refresh_token;
	}

}
