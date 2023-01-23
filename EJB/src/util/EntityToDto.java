package util;

import dto.UserDTO;
import model.User;

public class EntityToDto {

	public UserDTO convertUser(User user) {
		UserDTO globalUserDTO = new UserDTO(user.getUsername(), user.getPassword());
		globalUserDTO.setId(user.getId());
		return globalUserDTO;

	}

}

