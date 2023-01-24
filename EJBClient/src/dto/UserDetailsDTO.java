package dto;

import java.io.Serializable;

public class UserDetailsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String role;

	public UserDetailsDTO() {
		super();
	}

	@Override
	public String toString() {
		return "UserDetailsDTO [id=" + id + ", name=" + name + ", role=" + role + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
