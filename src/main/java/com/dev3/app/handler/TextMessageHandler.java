package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.MessageType;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.repositoriy.TextMessageRepository;
import com.dev3.app.web.WeChatMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * Created by A022713 on 2017/4/21.
 */
@Service(MessageType.MESSAGE_TEXT)
public class TextMessageHandler extends AbsMessageHandler<TextMessage, TextMessage> {
    //@Autowired
    //private TextMessageRepository textMessageRepository;

    @Override
    public TextMessage handleMessage(TextMessage message) {
        TextMessage requestMessage = message, responseMessage = null;

        if (requestMessage != null) {
            //this.textMessageRepository.save(requestMessage);

            responseMessage = this.getReplyTextMessage(requestMessage);

            //this.textMessageRepository.save(responseMessage);
        }

        return responseMessage;
    }

    private TextMessage getReplyTextMessage(TextMessage requestMessage) {
        TextMessage replyMessage = new TextMessage();

        replyMessage.setMsgType(MessageType.MESSAGE_TEXT);
        replyMessage.setToUserName(requestMessage.getFromUserName());
        replyMessage.setFromUserName(requestMessage.getToUserName());
        replyMessage.setCreateTime(System.currentTimeMillis());
        replyMessage.setMsgId(UUID.randomUUID().toString());
        replyMessage.setContent(String.format("回复: %1$s", requestMessage.getContent()));
        replyMessage.setRawMessage(WeChatMessageUtil.textMessageToXml(replyMessage, TextMessage.class));

        return replyMessage;
    }
}
