package dto;

public class RegisterDTO {

	private String username;
	private String password;
	private String repeatPassword;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RegisterDTO() {
		super();
	}

	public RegisterDTO(String username, String password, String repeatPassword) {
		super();
		this.username = username;
		this.password = password;
		this.repeatPassword = repeatPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	@Override
	public String toString() {
		return "LoginDTO [username=" + username + ", password=" + password + "]";
	}
}