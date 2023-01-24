package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userdetails database table.
 * 
 */
@Entity
@NamedQuery(name="UserDetails.findAll", query="SELECT u FROM UserDetails u")
public class UserDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int iduser;

	private String name;

	private String role;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="iduser", insertable=false, updatable=false)
	private User userlogin;

	public UserDetails() {
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUserlogin() {
		return this.userlogin;
	}

	public void setUserlogin(User userlogin) {
		this.userlogin = userlogin;
	}

	@Override
	public String toString() {
		return "UserDetails [iduser=" + iduser + ", name=" + name + ", role=" + role + ", userlogin=" + userlogin + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iduser;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userlogin == null) ? 0 : userlogin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetails other = (UserDetails) obj;
		if (iduser != other.iduser)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userlogin == null) {
			if (other.userlogin != null)
				return false;
		} else if (!userlogin.equals(other.userlogin))
			return false;
		return true;
	}
}