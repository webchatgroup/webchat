package com.dev3.app.dto;

import java.io.Serializable;
import java.util.Date;

public class SuggestionDto implements Serializable {
	
	private static final long serialVersionUID = 6626839914080786775L;

	private int id;

	private String content;

	private Date createDate;

	private int status;

	private int likes;

	private int isSent;
	
	private boolean isHasReply;
	
	private String firstReply;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getIsSent() {
		return isSent;
	}

	public void setIsSent(int isSent) {
		this.isSent = isSent;
	}

	public boolean isHasReply() {
		return isHasReply;
	}

	public void setHasReply(boolean isHasReply) {
		this.isHasReply = isHasReply;
	}

	public String getFirstReply() {
		return firstReply;
	}

	public void setFirstReply(String firstReply) {
		this.firstReply = firstReply;
	}
	
	
	
}
