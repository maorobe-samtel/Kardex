package com.todo1.kardex.model;

import javax.validation.constraints.NotEmpty;

public class ProductCodeDto {
	
	@NotEmpty(message = "The field cannot go empty")
	private String code;
	
	public ProductCodeDto() {
		
	}

	public ProductCodeDto(@NotEmpty(message = "Campo Obligatorio2") String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
