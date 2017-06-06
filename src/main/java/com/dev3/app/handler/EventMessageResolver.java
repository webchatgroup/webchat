package com.dev3.app.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.EventMessage;
import com.dev3.app.web.WeChatMessageUtil;

@Component
public class EventMessageResolver implements IMessageResolver<EventMessage, EventMessage> {

	@Override
	public EventMessage getParsedIncomingMessage(AbstractMessage message) {
		Map<String, String> map = WeChatMessageUtil.xmlToMap(message.getRawMessage());

        String event = map.get("Event");
        
        EventMessage textMessageReceived = AbstractMessage.parse(message.getRawMessage(), EventMessage.class);

        textMessageReceived.setEvent(event);

        return textMessageReceived;
        
	}

	@Override
	public AbstractMessage getParsedOutgoingmessage(EventMessage message) {
		return message;
	}

}
