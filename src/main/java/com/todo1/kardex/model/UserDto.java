package com.todo1.kardex.model;

import javax.validation.constraints.NotEmpty;

public class UserDto {
	
	private int id;
	@NotEmpty(message = "The field cannot go empty")
	private String name;
	@NotEmpty(message = "The field cannot go empty")
	private String identification;
	@NotEmpty(message = "The field cannot go empty")
	private String email;
	@NotEmpty(message = "The field cannot go empty")
	private String user;
	@NotEmpty(message = "The field cannot go empty")
	private String password;
	@NotEmpty(message = "The field cannot go empty")
	private String cardnumber;
	
	public UserDto() {
		
	}

	public UserDto(int id, String name, String identification, String email, String user, String password,
			String cardnumber) {
		super();
		this.id = id;
		this.name = name;
		this.identification = identification;
		this.email = email;
		this.user = user;
		this.password = password;
		this.cardnumber = cardnumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	
	

}
