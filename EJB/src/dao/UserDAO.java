package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.ChangePasswordDTO;
import dto.LoginDTO;
import dto.RegisterDTO;
import dto.UserDTO;
import exception.ChangePasswordException;
import exception.LoginException;
import model.User;
import util.DtoToEntity;
import util.EntityToDto;

/**
 * Session Bean implementation class UserDAO
 */
@Stateless
@LocalBean
public class UserDAO implements UserDAORemote {

	static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	public UserDAO() {

	}

	private EntityToDto entityToDTO = new EntityToDto();

	private DtoToEntity dtoToEntity = new DtoToEntity();

	@Override
	public UserDTO findById(int id) {
		User user = entityManager.find(User.class, id);
		UserDTO userDTO = entityToDTO.convertUser(user);
		return userDTO;
	}

	@Override
	public List<UserDTO> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM User u");
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		System.out.println(users.toString());
		List<UserDTO> dtoUsers = new ArrayList<>();
		for (User user : users) {
			dtoUsers.add(entityToDTO.convertUser(user));
		}
		return dtoUsers;
	}

	@Override
	public UserDTO create(UserDTO userDTO) {
		User user = dtoToEntity.convertUser(userDTO);
		entityManager.persist(user);
		entityManager.flush();
		userDTO.setId(user.getId());
		return userDTO;
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		User user = dtoToEntity.convertUser(userDTO);
		user.setId(userDTO.getId());
		user = entityManager.merge(user);
		return userDTO;
	}

	@Override
	public void delete(int id) {
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);

	}

	@Override
	public UserDTO loginUser(LoginDTO loginDTO) throws LoginException {
		User user = null;
		try {
			user = entityManager.createNamedQuery("findUserByUsername", User.class)
					.setParameter("username", loginDTO.getUsername()).getSingleResult();
		} catch (NoResultException e) {
			throw new LoginException("Wrong authentication!");
		}
		if (!loginDTO.getPassword().equals(user.getPassword())) {
			throw new LoginException("Wrong authentication!");
		}

		UserDTO userDTO = entityToDTO.convertUser(user);
		return userDTO;

	}

	@Override
	public Boolean updatePassword(ChangePasswordDTO changePasswordDTO) throws ChangePasswordException {
		User user = null;
		LOGGER.log(Level.INFO, "Trying to update password for:  " + changePasswordDTO.toString());
		try {
			user = entityManager.createNamedQuery("findUserByUsername", User.class)
					.setParameter("username", changePasswordDTO.getUsername()).getSingleResult();
			if (user.getPassword().equals(changePasswordDTO.getOldPassword())) {
				if (!changePasswordDTO.getOldPassword().equals(changePasswordDTO.getNewPassword())) {
					user.setPassword(changePasswordDTO.getNewPassword());
					user = entityManager.merge(user);
					LOGGER.log(Level.INFO, "Successfully changed password for:  " + changePasswordDTO.toString());
					return true;
				} else {
					throw new ChangePasswordException(
							"Please choose another new password, not the same as the old one!");
				}
			} else
				throw new ChangePasswordException("The old password is not valid.");
		} catch (NoResultException e) {
			throw new ChangePasswordException("The username is not valid!");
		}

	}

	@Override
	public UserDTO registerUser(RegisterDTO registerDTO) throws LoginException {
		User user = this.getUserByUsername(registerDTO.getUsername());
		
		if (user != null) {
			throw new LoginException("Username already exists!");
		}
		
		user = new User();
		user.setUsername(registerDTO.getUsername());
		user.setPassword(registerDTO.getPassword());
		entityManager.persist(user);
		
		user = this.getUserByUsername(registerDTO.getUsername());
		
		UserDTO userDTO = entityToDTO.convertUser(user);
		return userDTO;
	}
	
	private User getUserByUsername(String username) {
		try {
			User user = entityManager.createNamedQuery("findUserByUsername", User.class)
					.setParameter("username", username).getSingleResult();
			return user;
			
		} catch (NoResultException e) {
			return null;
		}
	}

}
