package com.todo1.kardex.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.todo1.kardex.model.ProductDto;
import com.todo1.kardex.model.ProductShoppingCartDto;

public interface ShoppingCartService {
	
	public abstract ArrayList<ProductShoppingCartDto> allListShoppingCart(HttpServletRequest request);
	public abstract ProductDto getByCode(String code);
	public abstract ProductDto getById(int id);
	public abstract boolean determineAvailability(int existence);
	public abstract void addShoppingCart(ArrayList<ProductShoppingCartDto> shoppingCart, HttpServletRequest request);
	public abstract int totalValue(List<ProductShoppingCartDto> shoppingCart);
	
}
