package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the news database table.
 * 
 */
@Entity
@NamedQuery(name="News.findAll", query="SELECT n FROM News n")
public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idnews;

	private String description;

	private String title;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="writerid")
	private User userlogin;

	public News() {
	}

	public int getIdnews() {
		return this.idnews;
	}

	public void setIdnews(int idnews) {
		this.idnews = idnews;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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