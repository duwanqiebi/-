package com.zhangqiang.sqgl.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Stack;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Bookmark implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String URL;

	@Column
	private Date createdate;

	@Column
	private Date modifydate;

	@Column
	private String filename;

	@Column
	private Long classid;

	@Column
	private Long clicksnum;

	@Column
	private Date lastdate;

	@Column
	private String icon;

	public Bookmark() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Long getClassid() {
		return classid;
	}

	public void setClassid(Long classid) {
		this.classid = classid;
	}

	public Long getClicksnum() {
		return clicksnum;
	}

	public void setClicksnum(Long clicksnum) {
		this.clicksnum = clicksnum;
	}

	public Date getLastdate() {
		return lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public static void main(String[] args){
		Stack<String> stack = new Stack<>();
		boolean flag = false;		//计算标志
		for(int i = 0 ; i < args.length;i ++){
			String cur = args[i];
			if(cur == "+" && cur == "*"){
				flag = true;
			}else{
				if(!flag){
					stack.push(cur);
				}
			}
			
		}
	}

}
