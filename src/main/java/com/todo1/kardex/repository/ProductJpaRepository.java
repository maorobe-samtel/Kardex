package com.todo1.kardex.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo1.kardex.entity.Product;

@Repository("productJpaRepository")
public interface ProductJpaRepository extends JpaRepository<Product,Serializable> {
	
	public abstract Product findFirstByCode(String code);

}
