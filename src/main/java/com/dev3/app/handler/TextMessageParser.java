package com.dev3.app.handler;

import com.dev3.app.entity.MessageType;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.web.WeChatMessageUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by A022713 on 2017/4/25.
 */
@Component(MessageType.MESSAGE_TEXT)
public class TextMessageParser implements IMessageParser<TextMessage> {
    @Override
    public TextMessage parse(HttpServletRequest request) {
        Map<String, String> map = WeChatMessageUtil.xmlToMap(request);
        // OpenId of sender
        String fromUserName = map.get("FromUserName");
        // OpenId of WeChat public No.
        String toUserName = map.get("ToUserName");
        // Message type
        String msgType = map.get("MsgType");
        // Message Id from WeChat
        String msgId = map.get("MsgId");
        // Message content from sender
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
}
