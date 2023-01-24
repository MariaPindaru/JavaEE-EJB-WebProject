package dao;

import javax.ejb.Remote;

import dto.PostDTO;

@Remote
public interface PostDAORemote extends GenericDAO<PostDTO> {

}
