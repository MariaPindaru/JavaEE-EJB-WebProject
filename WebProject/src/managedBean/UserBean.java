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
public class UserBean {

	@EJB
	PostDAORemote postDAORemote;

	@EJB
	UserDAORemote userDAORemote;

	List<PostDTO> posts = new ArrayList<PostDTO>();

	public List<PostDTO> getPosts() {
		posts = postDAORemote.findAll();
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public String openPost(int id) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		System.out.println("Opening post...");
		PostDTO postDTO = postDAORemote.findById(id);
		System.out.println(postDTO);
		facesContext.getExternalContext().getSessionMap().put("postDTO", postDTO);

		return "/postDetails.xhtml?faces-redirect=true";
	}
}
