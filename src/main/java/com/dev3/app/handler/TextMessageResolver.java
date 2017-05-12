package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.web.WeChatMessageUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by phoenix on 2017/5/12.
 */
@Component
public class TextMessageResolver implements IMessageResolver<TextMessage, TextMessage> {

    @Override
    public TextMessage getParsedIncomingMessage(AbstractMessage message) {
        Map<String, String> map = WeChatMessageUtil.xmlToMap(message.getRawMessage());

        String msgType = map.get("MsgType");
        String msgContent = map.get("Content");

        TextMessage textMessageReceived = AbstractMessage.parse(message.getRawMessage(), TextMessage.class);

        textMessageReceived.setContent(msgContent);
        textMessageReceived.setMsgType(msgType);

        return textMessageReceived;
    }

    @Override
    public AbstractMessage getParsedOutgoingmessage(TextMessage message) {
        return message;
    }
}
