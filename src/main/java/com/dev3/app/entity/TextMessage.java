package com.dev3.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TextMessage extends AbstractMessage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;

	/**
	 * 消息id，64位整型
	 */
	private String MsgId;

	/**
	 * 文本消息内容
	 */
	private String Content;
	
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
