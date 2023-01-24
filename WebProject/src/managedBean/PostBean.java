package managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.PostDAORemote;
import dto.PostDTO;

@ManagedBean
@SessionScoped
public class PostBean {
	@EJB
	PostDAORemote postDAORemote;
	
	PostDTO postDTO;

	public PostDTO getPostDTO() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		postDTO = (PostDTO)facesContext.getExternalContext().getSessionMap().get("postDTO");
		return postDTO;
	}

	public void setPostDTO(PostDTO postDTO) {
		this.postDTO = postDTO;
	}
}
