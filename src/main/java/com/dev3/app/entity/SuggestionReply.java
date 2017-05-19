package com.dev3.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "suggestion_reply")
public class SuggestionReply implements Serializable {

	private static final long serialVersionUID = -846940545138595598L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	
	private Date createDate;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="suggestionId")
	private Suggestion suggestion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Suggestion getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}
	
	
	
}
