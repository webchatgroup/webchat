package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.MessageType;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.web.WeChatMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * Created by A022713 on 2017/4/21.
 */
@Service(MessageType.MESSAGE_TEXT)
public class TextMessageHandler implements IMessageHandler<AbstractMessage, AbstractMessage> {
    @Override
    public AbstractMessage handle(AbstractMessage message) {
        TextMessage requestMessage = this.getParsedTextMessage(message.getRawMessage());
        TextMessage replyMessage = this.getReplyTextMessage(requestMessage);

        return replyMessage;
    }

    private TextMessage getParsedTextMessage(String content) {
        Map<String, String> map = WeChatMessageUtil.xmlToMap(content);

        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");
        String msgId = map.get("MsgId");
        String msgContent = map.get("Content");

        TextMessage textMessageReceived = new TextMessage();

        textMessageReceived.setContent(msgContent);
        textMessageReceived.setMsgType(msgType);
        textMessageReceived.setCreateTime(System.currentTimeMillis());
        textMessageReceived.setFromUserName(fromUserName);
        textMessageReceived.setToUserName(toUserName);
        textMessageReceived.setMsgId(msgId);

        return textMessageReceived;
    }

    private TextMessage getReplyTextMessage(TextMessage requestMessage) {
        TextMessage replyMessage = new TextMessage();

        replyMessage.setMsgType(MessageType.MESSAGE_TEXT);
        replyMessage.setToUserName(requestMessage.getFromUserName());
        replyMessage.setFromUserName(requestMessage.getToUserName());
        replyMessage.setCreateTime(System.currentTimeMillis());
        replyMessage.setMsgId(UUID.randomUUID().toString());
        replyMessage.setContent(String.format("Reply: %1$s", requestMessage.getContent()));
        replyMessage.setRawMessage(WeChatMessageUtil.textMessageToXml(replyMessage, TextMessage.class));

        return replyMessage;
    }
}
