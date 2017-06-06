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
        replyMessage.setContent("感谢关注Volvo Tianjin SITS3公众平台\\n公众号现提供如下功能\\n1.发送匿名意见\\n回复\"意见-xxxxx\"提交你的意见");
        replyMessage.setRawMessage(WeChatMessageUtil.textMessageToXml(replyMessage, TextMessage.class));

        return replyMessage;
    }
	
}
