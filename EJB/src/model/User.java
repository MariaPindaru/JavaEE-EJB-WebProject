package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the userlogin database table.
 * 
 */
@Entity
@Table(name="userlogin")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@NamedQuery(name = "findUserByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String password;

	private String username;

	//bi-directional one-to-one association to UserDetails
	@OneToOne(mappedBy="userlogin")
	private UserDetails userdetail;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="userlogin")
	private List<Post> posts;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserDetails getUserdetail() {
		return this.userdetail;
	}

	public void setUserdetail(UserDetails userdetail) {
		this.userdetail = userdetail;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setUserlogin(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setUserlogin(null);

		return post;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", username=" + username + ", userdetail=" + userdetail
				+ ", posts=" + posts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((posts == null) ? 0 : posts.hashCode());
		result = prime * result + ((userdetail == null) ? 0 : userdetail.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (posts == null) {
			if (other.posts != null)
				return false;
		} else if (!posts.equals(other.posts))
			return false;
		if (userdetail == null) {
			if (other.userdetail != null)
				return false;
		} else if (!userdetail.equals(other.userdetail))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}