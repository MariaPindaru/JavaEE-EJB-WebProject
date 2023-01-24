package dto;

import java.io.Serializable;

public class PostDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String content;
	private UserDTO writer;

	public PostDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", title=" + title + ", content=" + content + ", writer=" + writer + "]";
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserDTO getWriter() {
		return writer;
	}

	public void setWriter(UserDTO writer) {
		this.writer = writer;
	}
	
}
