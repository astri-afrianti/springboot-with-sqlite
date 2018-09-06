package com.mitrais.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="BLOG")
public class Blog {
	@Id
	@TableGenerator(name = "blogIdGenerator", table = "ID_GEN", pkColumnName = "TABLE_NAME", valueColumnName = "VALUE", pkColumnValue = "BLOG")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "blogIdGenerator")
	private int id;
	
	@Column(name = "TITLE", length = 100, nullable = false)
	private String title;
	
	@Column(name = "CONTENT", length = 500, nullable = false)
	private String content;
	
	@Column(name = "DELETED", nullable = false)
	private boolean deleted = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATED_DATE", nullable = false)
	private Date lastUpdated;


	public Blog() {	}

	
	public Blog(String title, String content) {
		this.setTitle(title);
		this.setContent(content);
	}
	
	public Blog(int id, String title, String content) {
		this(title, content);
		this.setId(id);
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

	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "Blog{" + "id=" + id + ", title='" + title + '\'' + ", content='" + content + '\'' + '}';
	}
}
