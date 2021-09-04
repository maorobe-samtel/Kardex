package com.todo1.kardex.service;

import java.util.List;

import com.todo1.kardex.model.ProductDto;

public interface ProductsService {
	
	public abstract List<ProductDto> listAllProducts();
	public abstract ProductDto addProduct(ProductDto productDto);
	public abstract ProductDto getProductById(int id);
	public abstract ProductDto getProductByCode(String code);
	public abstract void deleteProduct(int id);
}
