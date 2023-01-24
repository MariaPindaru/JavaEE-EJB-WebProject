package managedBean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.UserDAORemote;
import dto.ChangePasswordDTO;
import dto.UserDTO;
import exception.ChangePasswordException;

@ManagedBean
@RequestScoped
public class ChangePasswordBean {

	static final Logger LOGGER = Logger.getLogger(ChangePasswordBean.class.getName());

	ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

	@EJB
	UserDAORemote userDAORemote;

	UserDTO userDTO;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext.getExternalContext().getFlash().get("user") != null) {
			userDTO = (UserDTO) facesContext.getExternalContext().getFlash().get("user");
		}
	}

	public String changePassword() {

		LOGGER.log(Level.INFO, this.getClass().getName() + " Changing password: " + changePasswordDTO.toString());
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			userDAORemote.updatePassword(changePasswordDTO);
			facesContext.addMessage("changePassForm",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Password changed successfully!", null));
			return "/index.xhtml?faces-redirect=true";
		} catch (ChangePasswordException e) {
			facesContext.addMessage("changePassForm",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			return null;
		}
	}

	public ChangePasswordDTO getChangePasswordDTO() {
		return changePasswordDTO;
	}

	public void setChangePasswordDTO(ChangePasswordDTO changePasswordDTO) {
		this.changePasswordDTO = changePasswordDTO;
	}

	public UserDAORemote getUserDAORemote() {
		return userDAORemote;
	}

	public void setUserDAORemote(UserDAORemote userDAORemote) {
		this.userDAORemote = userDAORemote;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}