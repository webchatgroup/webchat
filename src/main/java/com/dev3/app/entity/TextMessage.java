package com.dev3.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TextMessage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;

	/**
	 * 消息id，64位整型
	 */
	private String MsgId;
	
	/**
	 * 开发者微信号
	 */
	private String ToUserName;
	/**
	 * 发送方帐号（一个OpenID）
	 */
	private String FromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	private long CreateTime;
	/**
	 * 消息类型
	 */
	private String MsgType;
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
	

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
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
