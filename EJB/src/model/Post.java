package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the post database table.
 * 
 */
@Entity
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpost;

	private String content;

	private String title;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="writerid")
	private User userlogin;

	public Post() {
	}

	public int getIdpost() {
		return this.idpost;
	}

	public void setIdpost(int idpost) {
		this.idpost = idpost;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUserlogin() {
		return this.userlogin;
	}

	public void setUserlogin(User userlogin) {
		this.userlogin = userlogin;
	}

}