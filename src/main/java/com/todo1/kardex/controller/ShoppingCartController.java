package com.todo1.kardex.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.todo1.kardex.model.ProductCodeDto;
import com.todo1.kardex.model.ProductDto;
import com.todo1.kardex.model.ProductShoppingCartDto;
import com.todo1.kardex.service.ProductsService;
import com.todo1.kardex.service.ShoppingCartService;

@Controller("cardController")
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	@Qualifier("shoppingCartServiceImpl")
	private ShoppingCartService shoppingCartService;
	@Autowired
	@Qualifier("productsServiceImpl")
	private ProductsService productsService;

	private static final String SHOPPING_CART = "shoppingcart";
	private static final Log LOG = LogFactory.getLog(ShoppingCartController.class);

	@GetMapping(value = "/sell")
	public String sell(Model model, HttpServletRequest request) {

		model.addAttribute("productCodeDto", new ProductCodeDto());
		model.addAttribute("total",
				shoppingCartService.totalValue(shoppingCartService.allListShoppingCart(request)));
		return SHOPPING_CART;
	}

	@PostMapping(value = "/add")
	public String agregarAlCarrito(@Valid @ModelAttribute(name = "productCodeDto") ProductCodeDto productCodeDto,
			BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttrs, Model model) {

		ArrayList<ProductShoppingCartDto> productShoppingCartDto = shoppingCartService.allListShoppingCart(request);

		if (bindingResult.hasErrors()) {

			model.addAttribute("total", shoppingCartService.totalValue(productShoppingCartDto));
			return SHOPPING_CART;
		}

		ProductDto productDto = shoppingCartService.getByCode(productCodeDto.getCode());

		if (productDto == null) {
			model.addAttribute("total", shoppingCartService.totalValue(productShoppingCartDto));
			model.addAttribute("error", "The product with code " + productCodeDto.getCode() + " does not exist");
			return SHOPPING_CART;
		} else if (shoppingCartService.determineAvailability(productDto.getExistence())) {
			model.addAttribute("total", shoppingCartService.totalValue(productShoppingCartDto));
			model.addAttribute("error", "The product " + productDto.getName() + " is out of stock");
			return SHOPPING_CART;
		}

		boolean found = false;
		for (ProductShoppingCartDto ProductShoppingCartDtoObj : productShoppingCartDto) {
			if (ProductShoppingCartDtoObj.getCode().equals(productDto.getCode())) {

				if (ProductShoppingCartDtoObj.getQuantity() == productDto.getExistence()) {
					model.addAttribute("total", shoppingCartService.totalValue(productShoppingCartDto));
					model.addAttribute("error", "The product " + productDto.getName() + " exceeds the stock");
					return SHOPPING_CART;
				}
				ProductShoppingCartDtoObj.increaseQuantity();

				found = true;
				break;
			}
		}
		if (!found) {
			productShoppingCartDto.add(new ProductShoppingCartDto(productDto.getId(), productDto.getName(),
					productDto.getCode(), productDto.getPrice(), productDto.getExistence(), 1));
		}
		shoppingCartService.addShoppingCart(productShoppingCartDto, request);

		return "redirect:/shoppingCart/sell";
	}

	@GetMapping("/deletefromshoppingcart")
	public String deleteFromShoppingCart(@RequestParam(name = "index") int index, HttpServletRequest request) {

		ArrayList<ProductShoppingCartDto> shoppingCart = shoppingCartService.allListShoppingCart(request);

		if (shoppingCart.get(index) != null) {
			shoppingCart.remove(index);
			shoppingCartService.addShoppingCart(shoppingCart, request);
		}
		return "redirect:/shoppingCart/sell";
	}

	@GetMapping("/cancelsale")
	public String cancelSale(HttpServletRequest request) {
		shoppingCartService.addShoppingCart(new ArrayList<ProductShoppingCartDto>(), request);

		return "redirect:/shoppingCart/sell";
	}

	@GetMapping("/finishbuying")
	public String finishBuying(HttpServletRequest request, Model model) {

		ArrayList<ProductShoppingCartDto> shoppingCart = shoppingCartService.allListShoppingCart(request);

		if (shoppingCart == null || shoppingCart.size() <= 0) {
			model.addAttribute("productCodeDto", new ProductCodeDto());
			model.addAttribute("total", 0);
			model.addAttribute("errorf", "To make a purchase you must first select the products");
			
			return SHOPPING_CART;
		}
		
		int count=0;
		for(ProductShoppingCartDto productShoppingCartDto :shoppingCart) {
			
			ProductDto productDto =shoppingCartService.getById(productShoppingCartDto.getId());
			if(productDto==null) continue;
			productDto.subtractExistence(productShoppingCartDto.getQuantity());
			
			if(productDto.getExistence()<0) {
				shoppingCart.remove(count);
				model.addAttribute("productCodeDto", new ProductCodeDto());
				shoppingCartService.addShoppingCart(shoppingCart, request);
				model.addAttribute("total", shoppingCartService.totalValue(shoppingCart));				
				model.addAttribute("errorf", "The product "+productShoppingCartDto.getName()+" was out of stock while making the purchase");
				return SHOPPING_CART;
			}
			
			productsService.addProduct(productDto);
			shoppingCartService.addShoppingCart(new ArrayList<ProductShoppingCartDto>(), request);
			
			count=count++;
			
		}
		return "redirect:/shoppingCart/sell";
	}

}
