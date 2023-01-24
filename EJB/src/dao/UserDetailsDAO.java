package dao;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dto.UserDetailsDTO;
import model.UserDetails;
import util.DtoToEntity;
import util.EntityToDto;

@Stateless
@LocalBean
public class UserDetailsDAO implements UserDetailsDAORemote {
	static final Logger LOGGER = Logger.getLogger(UserDetailsDAO.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	
	private EntityToDto entityToDTO = new EntityToDto();

	private DtoToEntity dtoToEntity = new DtoToEntity();
	
	@Override
	public UserDetailsDTO findById(int id) {
		UserDetails details = entityManager.find(UserDetails.class, id);
		UserDetailsDTO userDetailsDTO = entityToDTO.convertUserDetails(details);
		return userDetailsDTO;
	}

	@Override
	public List<UserDetailsDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetailsDTO create(UserDetailsDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetailsDTO update(UserDetailsDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
