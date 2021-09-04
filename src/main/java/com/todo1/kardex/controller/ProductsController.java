package com.todo1.kardex.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.todo1.kardex.model.ProductDto;
import com.todo1.kardex.service.impl.ProductsServiceImpl;

@Controller
@RequestMapping
public class ProductsController {
	
	private static final String INVENTORY = "inventory";
	private static final String PRODUCT_FORM = "productform";
	private static final Log LOGGER = LogFactory.getLog(ProductsController.class);
	
	@Autowired
	@Qualifier("productsServiceImpl")
	private ProductsServiceImpl productsServiceImpl;
	
	@GetMapping("/showproducts")
	public String showProducts(Model model) {
		
		model.addAttribute("products",productsServiceImpl.listAllProducts());		
		return INVENTORY;
	}
	
	@GetMapping("/productform")
	public ModelAndView productForm(@RequestParam(name="id",required=false,defaultValue=" ") String id) {
		
		ModelAndView mav = new ModelAndView(PRODUCT_FORM);
		
		if(id.equals(" ")) mav.addObject("product",new ProductDto());		
		else mav.addObject("product",productsServiceImpl.getProductById(Integer.parseInt(id)));		
		
		return mav;
	}
	
	@PostMapping("/addproduct")
	public String addProduct(@Valid @ModelAttribute(name="product") ProductDto productDto, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			return PRODUCT_FORM;
		}
		else {
		productsServiceImpl.addProduct(productDto);
		return "redirect:/showproducts";
		}
//		productsServiceImpl.addProduct(productDto);
//		return "redirect:/showproducts";
	}
	
	@GetMapping("deleteproduct")
	public String deleteProduct(@RequestParam(name="id") String id ) {
		productsServiceImpl.deleteProduct(Integer.parseInt(id));
		return "redirect:/showproducts";
	}
	
	@GetMapping("/")
	public String redirectProducts() {
		return "redirect:/showproducts";
	}

}
