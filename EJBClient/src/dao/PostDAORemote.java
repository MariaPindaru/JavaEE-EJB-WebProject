package dao;

import java.util.List;

import javax.ejb.Remote;

import dto.PostDTO;

@Remote
public interface PostDAORemote extends GenericDAO<PostDTO> {
	
	List<PostDTO> getPostsByWriter(int id); 

}
