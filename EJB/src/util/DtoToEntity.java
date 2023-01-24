package util;

import dto.PostDTO;
import dto.UserDTO;
import model.Post;
import model.User;

public class DtoToEntity {

	public User convertUser(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		return user;
	}
	
	public Post convertPost(PostDTO postDTO) {
		Post post = new Post();
		post.setIdpost(postDTO.getId());
		post.setUserlogin(convertUser(postDTO.getWriter()));
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		return post;
	}

}

