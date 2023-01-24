package managedBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UserDAORemote;
import dto.LoginDTO;
import dto.RegisterDTO;
import dto.UserDTO;
import exception.LoginException;

@ManagedBean
@SessionScoped
public class RegisterBean {

	RegisterDTO registerDTO = new RegisterDTO();

	@EJB
	UserDAORemote userDAORemote;

	UserDTO userDTO;

	public RegisterDTO getRegisterDTO() {
		return registerDTO;
	}

	public void setRegisterDTO(RegisterDTO registerDTO) {
		this.registerDTO = registerDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String registerUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!this.registerDTO.getPassword().equals(registerDTO.getRepeatPassword())) {
			System.out.println("The passwords don't match!");
			facesContext.addMessage("registerForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "The passwords don't match!", null));
			return null;
		}
		
		try {
			userDTO = userDAORemote.registerUser(this.registerDTO);
			facesContext.getExternalContext().getSessionMap().put("userDTO", userDTO);
			return "/index.xhtml?faces-redirect=true";

		} catch (LoginException e) {
			System.out.println("Invalid username or password");
			facesContext.addMessage("registerForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			return null;
		}
	}

	public String logout() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		userDTO = null;

		return "/index?faces-redirect=true";
	}

}