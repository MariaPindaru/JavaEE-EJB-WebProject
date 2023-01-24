package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.PostDTO;
import model.Post;
import util.DtoToEntity;
import util.EntityToDto;

@Stateless
@LocalBean
public class PostDAO implements PostDAORemote{
	static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	
	private EntityToDto entityToDTO = new EntityToDto();

	private DtoToEntity dtoToEntity = new DtoToEntity();

	@Override
	public PostDTO findById(int id) {
		Post post = entityManager.find(Post.class, id);
		PostDTO postDTO = entityToDTO.convertPost(post);
		return postDTO;
	}

	@Override
	public List<PostDTO> findAll() {
		Query query = entityManager.createQuery("SELECT u FROM Post u");
		@SuppressWarnings("unchecked")
		List<Post> posts = query.getResultList();
		System.out.println(posts.toString());
		List<PostDTO> dtoPosts = new ArrayList<>();
		for (Post post : posts) {
			dtoPosts.add(entityToDTO.convertPost(post));
		}
		return dtoPosts;
	}

	@Override
	public PostDTO create(PostDTO entity) {
		Post post = dtoToEntity.convertPost(entity);
		entityManager.persist(post);
		entityManager.flush();
		entity.setId(post.getIdpost());
		return entity;
	}

	@Override
	public PostDTO update(PostDTO entity) {
		Post post = dtoToEntity.convertPost(entity);
		post = entityManager.merge(post);
		return entity;
	}

	@Override
	public void delete(int id) {
		Post post = entityManager.find(Post.class, id);
		entityManager.remove(post);
	}
}
