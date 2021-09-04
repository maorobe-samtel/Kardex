package com.todo1.kardex.service;

import com.todo1.kardex.model.UserDto;

public interface UserService {
	public abstract boolean findFirstByUserAndPassword(String user,String Password);
	public abstract UserDto addUser(UserDto userDto);
}
