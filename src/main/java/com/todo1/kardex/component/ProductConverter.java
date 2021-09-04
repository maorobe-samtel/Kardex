package com.todo1.kardex.component;

import org.springframework.stereotype.Component;

import com.todo1.kardex.entity.Product;
import com.todo1.kardex.model.ProductDto;

@Component("productConverter")
public class ProductConverter {

//	-- Entity To Model

	public ProductDto entityToModel(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getCode(), product.getPrice(),
				product.getExistence());

	}

//	-- Model To Entity

	public Product modelToEntity(ProductDto productDto) {
		return new Product(productDto.getId(), productDto.getName(), productDto.getCode(), productDto.getPrice(),
				productDto.getExistence());
	}

}
