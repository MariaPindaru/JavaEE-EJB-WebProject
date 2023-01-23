package dao;

import javax.ejb.Remote;

import dto.ChangePasswordDTO;
import dto.LoginDTO;
import dto.RegisterDTO;
import dto.UserDTO;
import exception.ChangePasswordException;
import exception.LoginException;

@Remote
public interface UserDAORemote extends GenericDAO<UserDTO> {

	UserDTO loginUser(LoginDTO loginDTO) throws LoginException;
	
	UserDTO registerUser(RegisterDTO registerDTO) throws LoginException;

	Boolean updatePassword(ChangePasswordDTO changePasswordDTO) throws ChangePasswordException;
}