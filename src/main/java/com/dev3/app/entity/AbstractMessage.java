package com.dev3.app.entity;

import com.dev3.app.web.WeChatMessageUtil;
import com.thoughtworks.xstream.annotations.XStreamInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by A022713 on 2017/4/21.
 */
@Entity
@Table(name = "message")
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractMessage implements Serializable {

	private static final long serialVersionUID = -5600821006352908996L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

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

    @Column(length = 4000)
    private String RawMessage;

    private Date LogTime;

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

    public String getRawMessage() {
        return RawMessage;
    }

    public void setRawMessage(String rawMessage) {
        RawMessage = rawMessage;
    }

    public Date getLogTime() {
        return LogTime;
    }

    public void setLogTime(Date logTime) {
        LogTime = logTime;
    }

    public static AbstractMessage parse(String content) {
        return parse(content, AbstractMessage.class);
    }

    public static <T extends AbstractMessage> T parse(String content, Class<T> clazz) {
        Map<String, String> map = WeChatMessageUtil.xmlToMap(content);

        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");
        String rawMessage = map.get("__raw__");

        T messageReceived = null;

        try {
            messageReceived = (T) clazz.newInstance();

            messageReceived.setMsgType(msgType);
            messageReceived.setCreateTime(System.currentTimeMillis());
            messageReceived.setFromUserName(fromUserName);
            messageReceived.setToUserName(toUserName);
            messageReceived.setRawMessage(rawMessage);
            messageReceived.setLogTime(new Date());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return messageReceived;
    }
}
