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

}