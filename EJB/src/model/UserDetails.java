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

}