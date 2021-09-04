package com.todo1.kardex.model;

import javax.validation.constraints.NotEmpty;

public class UserCredentialDto {
	@NotEmpty(message = "The field cannot go empty")
	private String user;
	@NotEmpty(message = "The field cannot go empty")
	private String password;
	
	public UserCredentialDto() {
		
	}
	
	public UserCredentialDto(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
