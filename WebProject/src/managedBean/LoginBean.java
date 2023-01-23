package managedBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UserDAORemote;
import dto.LoginDTO;
import dto.UserDTO;
import exception.LoginException;

@ManagedBean
@SessionScoped
public class LoginBean {

	LoginDTO loginDTO = new LoginDTO();

	@EJB
	UserDAORemote userDAORemote;

	UserDTO userDTO;

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String loginUser() {
		System.out.println("Logging user in...");

		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			userDTO = userDAORemote.loginUser(loginDTO);
			facesContext.getExternalContext().getSessionMap().put("userDTO", userDTO);
			// if userDTO is an admin
			System.out.println("admin logged");
			return "/adminFilter/admin.xhtml?faces-redirect=true";

		} catch (LoginException e) {
			System.out.println("Invalid username or password");
			facesContext.addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.message(), null));
			return null;
		}
	}
	
	public String registerUser() {
		System.out.println("Redirecting to register page..");

		return "/register.xhtml?faces-redirect=true";
	}

	public String logout() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		userDTO = null;

		return "/index?faces-redirect=true";
	}

}