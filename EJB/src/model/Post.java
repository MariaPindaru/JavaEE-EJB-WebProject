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

	@Override
	public String toString() {
		return "Post [idpost=" + idpost + ", content=" + content + ", title=" + title + ", userlogin=" + userlogin
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + idpost;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (idpost != other.idpost)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userlogin == null) {
			if (other.userlogin != null)
				return false;
		} else if (!userlogin.equals(other.userlogin))
			return false;
		return true;
	}
	
	

}