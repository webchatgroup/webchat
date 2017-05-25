/**
 * 
 */
package com.dev3.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author RL
 *
 */


@Entity
@Table(name = "suggestion")
@PrimaryKeyJoinColumn(name="id")
public class Suggestion implements Serializable {
	
	private static final long serialVersionUID = 9062390667795966129L;
	
	public static final int STATUS_NEW = 1;
	public static final int STATUS_REPLIED = 2;
	public static final int STATUS_DELETED = 3;
	
	public static final int SENT = 1;
	public static final int NOTSENT = 2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String content;
	
	private Date createDate;
	
	private int status;
	
	private int likes;
	
	private int isSent;
	
	
	@OneToMany(targetEntity=SuggestionReply.class, mappedBy="suggestion", fetch=FetchType.EAGER)
	private List<SuggestionReply> replies;
	
	
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
	public List<SuggestionReply> getReplies() {
		return replies;
	}
	public void setReplies(List<SuggestionReply> replies) {
		this.replies = replies;
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
	
	
	
	
}
