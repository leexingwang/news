package com.example.qianlong.bean;

import java.util.List;

public class UserEntity {

	private int id;

	private int oid;

	private String phone_number;

	private String username;

	private List<Roles> roles;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getOid() {
		return this.oid;
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

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public List<Roles> getRoles() {
		return this.roles;
	}

	public static class Roles {

	}
}
