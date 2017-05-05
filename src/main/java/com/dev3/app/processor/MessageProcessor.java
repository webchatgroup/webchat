package com.dev3.app.processor;

import com.dev3.app.SpringUtils;
import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.MessageType;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.handler.IMessageHandler;
import com.dev3.app.web.WeChatMessageUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by A022713 on 2017/4/24.
 */
@Component
public class MessageProcessor extends AbsMessageProcessor {

    private IMessageHandler messageHandler;

    @Override
    protected void processRequestResponse(HttpServletRequest request, HttpServletResponse response, MessageProcessorIndicator indicator) {
        String content = this.getContentFromRequest(request);

        AbstractMessage requestMessage = this.getAbstractMessageFromContent(content);

        if (requestMessage != null) {
            IMessageHandler messageHandler = SpringUtils.getBean(requestMessage.getMsgType(), IMessageHandler.class);

            if (messageHandler != null) {
                AbstractMessage responseMessage = messageHandler.handle(requestMessage);

                if (responseMessage != null) {
                    content = this.getContentFromAbstractMessage(responseMessage);
                    this.putContentToResponse(response, content);
                }

                indicator.setState(responseMessage);
            }
        }

        indicator.setSuccess(true);
    }

    private String getContentFromRequest(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();

        try {
            InputStream stream = request.getInputStream();

            InputStreamReader sreader = new InputStreamReader(stream, "utf8");
            BufferedReader breader = new BufferedReader(sreader);
            String line = null;

            while ((line = breader.readLine()) != null) {
                builder.append(line);
            }

            return builder.toString();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    private void putContentToResponse(HttpServletResponse response, String content) {
        try {
            PrintWriter out = response.getWriter();
            out.print(content);
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private AbstractMessage getAbstractMessageFromContent(String content) {
        Map<String, String> map = WeChatMessageUtil.xmlToMap(content);

        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");
        String msgId = map.get("MsgId");
        String msgContent = map.get("Content");
        String rawMessage = map.get("__raw__");

        AbstractMessage messageReceived = new TextMessage();
        messageReceived.setMsgType(msgType);
        messageReceived.setCreateTime(System.currentTimeMillis());
        messageReceived.setFromUserName(fromUserName);
        messageReceived.setToUserName(toUserName);
        messageReceived.setRawMessage(rawMessage);

        return messageReceived;
    }

    private String getContentFromAbstractMessage(AbstractMessage message) {
        return message.getRawMessage();
    }
}
