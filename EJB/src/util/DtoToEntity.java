package util;

import dto.UserDTO;
import model.User;

public class DtoToEntity {

	public User convertUser(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		return user;
	}

}

