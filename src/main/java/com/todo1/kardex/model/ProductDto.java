package com.todo1.kardex.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDto {

	private int id;
	@NotEmpty(message = "The field cannot go empty")
	private String name;
	@NotEmpty(message = "The field cannot go empty")
	private String code;

	@NotNull(message = "The field cannot go empty")
	@Min(1)
	private int price;

	@NotNull(message = "The field cannot go empty")
	@Min(1)
	private int existence;

	public ProductDto() {

	}

	public ProductDto(int id, String name, String code, int price, int existence) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.price = price;
		this.existence = existence;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getExistence() {
		return existence;
	}

	public void setExistence(int existence) {
		this.existence = existence;
	}
	
	public void subtractExistence(int existence) {
		this.existence -= existence;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", nombre=" + name + ", code=" + code + ", price=" + price + ", existence="
				+ existence + "]";
	}

}
