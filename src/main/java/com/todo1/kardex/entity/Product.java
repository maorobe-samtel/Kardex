package com.todo1.kardex.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private int id;	
	private String name;
	private String code;	
	private int price;	
	private int existence;
//    @Temporal(TemporalType.DATE)
//    private Calendar registration;
//    @Temporal(TemporalType.DATE)
//    private Calendar update;
    
    public Product() {
    	
    }
    
    



	public Product(int id, String name, String code, int price, int existence) {
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



//	public LocalDate getRegistration() {
//		return registration;
//	}
//
//
//
//	public void setRegistration(LocalDate registration) {
//		this.registration = registration;
//	}
//
//
//
//	public LocalDate getUpdate() {
//		return update;
//	}
//
//
//
//	public void setUpdate(LocalDate update) {
//		this.update = update;
//	}




    
    
    
    
    

}
