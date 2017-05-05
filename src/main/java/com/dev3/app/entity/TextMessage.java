package com.dev3.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TextMessage")
@PrimaryKeyJoinColumn(name = "TextMessageId")
public class TextMessage extends AbstractMessage implements Serializable {
	/**
	 * 消息id，64位整型
	 */
	private String MsgId;

	/**
	 * 文本消息内容
	 */
	private String Content;

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
