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

	//bi-directional many-to-one association to News
	@OneToMany(mappedBy="userlogin")
	private List<News> news;

	//bi-directional one-to-one association to UserDetails
	@OneToOne(mappedBy="userlogin")
	private UserDetails userdetail;

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

	public List<News> getNews() {
		return this.news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public News addNew(News news) {
		getNews().add(news);
		news.setUserlogin(this);

		return news;
	}

	public News removeNew(News news) {
		getNews().remove(news);
		news.setUserlogin(null);

		return news;
	}

	public UserDetails getUserdetail() {
		return this.userdetail;
	}

	public void setUserdetail(UserDetails userdetail) {
		this.userdetail = userdetail;
	}

}