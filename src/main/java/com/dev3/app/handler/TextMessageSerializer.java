package com.dev3.app.handler;

import com.dev3.app.entity.TextMessage;
import com.dev3.app.web.WeChatMessageUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by igouz on 2017/4/28.
 */
@Component
public class TextMessageSerializer implements IMessageSerializer<TextMessage> {
    @Override
    public void serialize(TextMessage message, HttpServletResponse response) {

    }

    @Override
    public TextMessage deserialize(HttpServletRequest request) {
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
