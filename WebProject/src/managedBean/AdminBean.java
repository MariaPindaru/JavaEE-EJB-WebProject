package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.PostDAORemote;
import dao.UserDAORemote;
import dto.PostDTO;
import dto.UserDTO;

@ManagedBean
@SessionScoped
public class AdminBean {
	@EJB
	PostDAORemote postDAORemote;

	@EJB
	UserDAORemote userDAORemote;

	List<PostDTO> posts = new ArrayList<PostDTO>();
	
	UserDTO userDTO;
	
	public AdminBean() {
		userDTO = (UserDTO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userDTO");
	}

	public List<PostDTO> getPosts() {
		posts = postDAORemote.getPostsByWriter(userDTO.getId());
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public String openPost(int id) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		System.out.println("Opening post...");
		PostDTO postDTO = postDAORemote.findById(id);
		facesContext.getExternalContext().getSessionMap().put("postDTO", postDTO);

		return "/post.xhtml?faces-redirect=true";
	}
	
	public String addPost() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getSessionMap().put("postDTO", new PostDTO());

		return "/post.xhtml?faces-redirect=true";
	}
	
	public String deletePost(int id) {
		postDAORemote.delete(id);
		return "/adminFilter/admin.xhtml?faces-redirect=true";
	}
}
