package com.todo1.kardex.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.todo1.kardex.component.ProductConverter;
import com.todo1.kardex.component.UserConverter;
import com.todo1.kardex.entity.Product;
import com.todo1.kardex.model.ProductDto;
import com.todo1.kardex.repository.ProductJpaRepository;
import com.todo1.kardex.service.ProductsService;

@Service("productsServiceImpl")
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
	@Qualifier("productJpaRepository")
	private ProductJpaRepository productJpaRepository;
	
	@Autowired
	@Qualifier("productConverter")
	private ProductConverter productConverter;
	
	
	@Override
	public List<ProductDto> listAllProducts() {
		List<ProductDto> listProductModel = new ArrayList<>();
		productJpaRepository.findAll().forEach((Product) -> listProductModel.add(productConverter.entityToModel(Product)));
		return listProductModel;
	}

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		return productConverter.entityToModel(productJpaRepository.save(productConverter.modelToEntity(productDto)));
	}

	@Override
	public ProductDto getProductById(int id) {
		
		Product product = productJpaRepository.getById(id);
		if (product == null)  return null;
		return productConverter.entityToModel(product);
		
	}

	@Override
	public void deleteProduct(int id) {
		productJpaRepository.deleteById(id);
	}

	@Override
	public ProductDto getProductByCode(String code) {
		Product product = productJpaRepository.findFirstByCode(code);
		if (product == null)  return null;
		return productConverter.entityToModel(product);
	}

}
