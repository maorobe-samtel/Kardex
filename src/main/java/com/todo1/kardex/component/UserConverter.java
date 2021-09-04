package com.todo1.kardex.component;

import org.springframework.stereotype.Component;

import com.todo1.kardex.entity.Product;
import com.todo1.kardex.entity.User;
import com.todo1.kardex.model.ProductDto;
import com.todo1.kardex.model.UserDto;

@Component("userConverter")
public class UserConverter {

//	-- Entity To Model

	public UserDto entityToModel(User user) {
		return new UserDto(user.getId(), user.getName(), user.getIdentification(), user.getEmail(),
				user.getUser(),user.getPassword(),user.getCardnumber());

	}

//	-- Model To Entity

	public User modelToEntity(UserDto userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getIdentification(), userDto.getEmail(),
				userDto.getUser(),userDto.getPassword(),userDto.getCardnumber());
	}

}
