package com.zhangqiang.sqgl.domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 书签类别实体类
 */
@SuppressWarnings("serial")
@Entity(name="BOOKMARKCLASS")
public class BookmarkClass implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JSONField(name="text")
	@Column
	private String classname;
	@Column
	private Date createdate;
	
	@JSONField(name="parent")
	@Column
	private String parentid;
	
	//private String type;

	public BookmarkClass() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Date getCreateDate() {
		return createdate;
	}

	public void setCreateDate(Date createDate) {
		this.createdate = createDate;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}
