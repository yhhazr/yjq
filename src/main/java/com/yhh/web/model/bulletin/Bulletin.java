package com.yhh.web.model.bulletin;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bulletin")
public class Bulletin {

	private int id;
	
	private String title;
	
	private String content;
	
	private String userName;
	
	private String degree;
	
	private Date createDate;
	
	public Bulletin(){};
	
	

	public Bulletin(String title,String degree, String content, String userName,
			Date createDate) {
		super();
		this.title = title;
		this.content = content;
		this.userName = userName;
		this.createDate = createDate;
		this.degree = degree;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
}
