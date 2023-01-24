package dao;

import javax.ejb.Remote;

import dto.UserDetailsDTO;

@Remote
public interface UserDetailsDAORemote extends GenericDAO<UserDetailsDTO>{

}
