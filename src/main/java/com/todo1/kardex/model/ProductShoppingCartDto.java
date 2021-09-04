package com.todo1.kardex.model;

public class ProductShoppingCartDto extends ProductDto {
	
	private int quantity;
	
	public ProductShoppingCartDto() {
		super();
	}

	public ProductShoppingCartDto(int id, String name, String code, int price, int existence,int quantity) {
		super(id, name, code, price, existence);
		this.quantity=quantity;
		// TODO Auto-generated constructor stub
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void increaseQuantity() {
        this.quantity++;
    }
	

}
