package com.dev3.app.handler;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dev3.app.entity.EventMessage;
import com.dev3.app.entity.MessageType;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.web.WeChatMessageUtil;

@Service(MessageType.MESSAGE_EVENT)
public class EventMessageHandler extends AbsMessageHandler<EventMessage, TextMessage> {

	@Override
	public TextMessage handleMessage(EventMessage message) {
		
		return getReplyTextMessage(message);
	}

	
	private TextMessage getReplyTextMessage(EventMessage requestMessage) {
        TextMessage replyMessage = new TextMessage();

        replyMessage.setMsgType(MessageType.MESSAGE_TEXT);
        replyMessage.setToUserName(requestMessage.getFromUserName());
        replyMessage.setFromUserName(requestMessage.getToUserName());
        replyMessage.setCreateTime(System.currentTimeMillis());
        replyMessage.setMsgId(UUID.randomUUID().toString());
        replyMessage.setContent(String.format("回复: %1$s", "感谢关注sits3公众平台"));
        replyMessage.setRawMessage(WeChatMessageUtil.textMessageToXml(replyMessage, TextMessage.class));

        return replyMessage;
    }
	
}
