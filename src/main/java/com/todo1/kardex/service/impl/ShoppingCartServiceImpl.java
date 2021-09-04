package com.todo1.kardex.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.todo1.kardex.component.ProductConverter;
import com.todo1.kardex.entity.Product;
import com.todo1.kardex.model.ProductDto;
import com.todo1.kardex.model.ProductShoppingCartDto;
import com.todo1.kardex.repository.ProductJpaRepository;
import com.todo1.kardex.service.ShoppingCartService;

@Service("shoppingCartServiceImpl")
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	
	@Autowired
	@Qualifier("productJpaRepository")
	private ProductJpaRepository productJpaRepository;
	
	@Autowired
	@Qualifier("productConverter")
	private ProductConverter productConverter;
	
	@Override
	public ArrayList<ProductShoppingCartDto> allListShoppingCart(HttpServletRequest request) {

		ArrayList<ProductShoppingCartDto> productShoppingCartDtoList = (ArrayList<ProductShoppingCartDto>) request.getSession().getAttribute("shoppingCart");

		if (productShoppingCartDtoList == null)
			productShoppingCartDtoList = new ArrayList<>();

		return productShoppingCartDtoList;
	}

	@Override
	public ProductDto getByCode(String code) {
		Product product = productJpaRepository.findFirstByCode(code);
		if (product == null)  return null;
		return productConverter.entityToModel(product);
	}

	@Override
	public boolean determineAvailability(int existence) {
		return existence<=0;
	}

	@Override
	public void addShoppingCart(ArrayList<ProductShoppingCartDto> shoppingCart, HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("shoppingCart", shoppingCart);
		
	}

	@Override
	public int totalValue(List<ProductShoppingCartDto> shoppingCart) {
		int total = 0;
		for (ProductShoppingCartDto ProductShoppingCartDto : shoppingCart)
			total += ProductShoppingCartDto.getPrice()*ProductShoppingCartDto.getQuantity();
		return total;
	}

	@Override
	public ProductDto getById(int id) {
		// TODO Auto-generated method stub
		Product product = productJpaRepository.getById(id);
		if (product == null)  return null;
		return productConverter.entityToModel(product);
	}


}
