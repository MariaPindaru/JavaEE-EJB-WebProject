package managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.PostDAORemote;
import dto.PostDTO;
import dto.UserDTO;

@ManagedBean
@SessionScoped
public class PostBean {
	@EJB
	PostDAORemote postDAORemote;

	PostDTO postDTO;

	public PostDTO getPostDTO() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		postDTO = (PostDTO) facesContext.getExternalContext().getSessionMap().get("postDTO");
		return postDTO;
	}

	public void setPostDTO(PostDTO postDTO) {
		this.postDTO = postDTO;
	}

	public String Save() {
		if (postDAORemote.findById(postDTO.getId()) == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			UserDTO userDTO = (UserDTO) facesContext.getExternalContext().getSessionMap().get("userDTO");
			postDTO.setWriter(userDTO);
			postDAORemote.create(postDTO);
		} else {
			postDAORemote.update(postDTO);
		}
		return "/adminFilter/admin.xhtml?faces-redirect=true";
	}
}
