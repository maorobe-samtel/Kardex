package com.todo1.kardex.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo1.kardex.entity.Product;
import com.todo1.kardex.entity.User;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<User,Serializable> {
	
	public abstract User findFirstByUserAndPassword(String user,String Password);

}
