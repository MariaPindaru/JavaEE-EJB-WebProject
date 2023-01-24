package util;

import dto.PostDTO;
import dto.UserDTO;
import dto.UserDetailsDTO;
import model.Post;
import model.User;
import model.UserDetails;

public class EntityToDto {

	public UserDTO convertUser(User user) {
		UserDTO globalUserDTO = new UserDTO(user.getUsername(), user.getPassword());
		globalUserDTO.setId(user.getId());
		return globalUserDTO;
	}
	
	public UserDetailsDTO convertUserDetails(UserDetails userDetails) {
		UserDetailsDTO dto = new UserDetailsDTO();
		dto.setId(userDetails.getIduser());
		dto.setName(userDetails.getName());
		dto.setRole(userDetails.getRole());
		return dto;
	}
	
	public PostDTO convertPost(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setId(post.getIdpost());
		postDTO.setWriter(convertUser(post.getUserlogin()));
		postDTO.setTitle(post.getTitle());
		postDTO.setContent(post.getContent());
		return postDTO;
	}
}

