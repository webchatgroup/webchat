package com.dev3.app.processor;

import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.handler.IMessageHandler;
import com.dev3.app.handler.IMessageParser;
import com.dev3.app.web.WeChatMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by A022713 on 2017/4/24.
 */
@Component
public class MessageProcessor extends AbsMessageProcessor {

    private IMessageHandler messageHandler;

    @Override
    protected void processRequestResponse(HttpServletRequest request, HttpServletResponse response, MessageProcessorIndicator indicator) {
        AbstractMessage message = this.getParsedAbstractMessage(request);

        if (message != null) {
            IMessageHandler messageHandler = ContextLoader.getCurrentWebApplicationContext().getBean(message.getMsgType(), IMessageHandler.class);
            IMessageParser messageParser = ContextLoader.getCurrentWebApplicationContext().getBean(message.getMsgType(), IMessageParser.class);

            if (messageHandler != null) {
                messageHandler.handle(messageParser.parse(request));
            }
        }

        indicator.setState(message);
        indicator.setSuccess(true);
    }

    private AbstractMessage getParsedAbstractMessage(HttpServletRequest request) {
        Map<String, String> map = WeChatMessageUtil.xmlToMap(request);

        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");
        String msgId = map.get("MsgId");
        String msgContent = map.get("Content");

        AbstractMessage messageReceived = new TextMessage();
        messageReceived.setMsgType(msgType);
        messageReceived.setCreateTime(System.currentTimeMillis());
        messageReceived.setFromUserName(fromUserName);
        messageReceived.setToUserName(toUserName);

        return messageReceived;
    }
}
