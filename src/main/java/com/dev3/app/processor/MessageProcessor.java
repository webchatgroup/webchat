package com.dev3.app.processor;

import com.dev3.app.SpringUtils;
import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.MessageType;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.handler.AbsMessageHandler;
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

        AbstractMessage requestMessage = AbstractMessage.parse(content);

        if (requestMessage != null) {
            AbsMessageHandler messageHandler = SpringUtils.getBean(requestMessage.getMsgType(), AbsMessageHandler.class);

            if (messageHandler != null) {
                messageHandler.setHandler(messageHandler);

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
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/xml;charset=utf-8");
            response.setHeader("Cache-control", "no-cache");

            PrintWriter out = response.getWriter();
            out.print(content);
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private String getContentFromAbstractMessage(AbstractMessage message) {
        return message.getRawMessage();
    }
}
