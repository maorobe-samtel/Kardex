package com.todo1.kardex.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.todo1.kardex.component.UserConverter;
import com.todo1.kardex.entity.User;
import com.todo1.kardex.model.UserDto;
import com.todo1.kardex.repository.UserJpaRepository;
import com.todo1.kardex.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	@Qualifier("userConverter")
	UserConverter userConverter;
	
	@Override
	public boolean findFirstByUserAndPassword(String userObj, String Password) {
		// TODO Auto-generated method stub
		User user =userJpaRepository.findFirstByUserAndPassword(userObj, Password);
		if(user == null) return false;
		
		return true;
	}

	@Override
	public UserDto addUser(UserDto userDto) {
		// TODO Auto-generated method stub
		userJpaRepository.save(userConverter.modelToEntity(userDto));
		return null;
	}

}
