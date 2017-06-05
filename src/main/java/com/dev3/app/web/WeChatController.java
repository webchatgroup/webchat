package com.dev3.app.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev3.app.processor.IMessageProcessor;
import com.dev3.app.repositoriy.ITextMessageRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev3.app.entity.MessageType;
import com.dev3.app.entity.TextMessage;

@Controller
@RequestMapping("wechat")
public class WeChatController {
    private Logger logger = Logger.getLogger(WeChatController.class);

    @Autowired
    private IMessageProcessor messageProcessor;

    @RequestMapping(method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public void validate(WeChatTokenInfo tokenInfo, PrintWriter out) {
        String echostr = tokenInfo.getEchostr();
        out.print(echostr);
        out.flush();
        out.close();
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST})
    public void processMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	System.out.println("calling processMessage");
    	
        messageProcessor.process(request, response, null);

        /*
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String responseMessage = "Ok";

        AbstractMessage messageReceived = getParsedAbstractMessage(request);
        IMessageHandler messageHandler = (IMessageHandler) ContextLoader.getCurrentWebApplicationContext().getBean(messageReceived.getMsgType());

        if (messageHandler != null) {
            messageHandler.handle();
        }

        // Message Processing
        if (MessageType.MESSAGE_TEXT.equals(messageReceived.getMsgType())) {

            // Persist Message from sender
            //textMessageRepository.save(messageReceived);

            // Generate Response Message
            //responseMessage = generateResponseMessage(messageReceived);
        }

        PrintWriter out = response.getWriter();
        out.print(responseMessage);
        logger.info(responseMessage);
        out.flush();
        out.close();
        */
    }

    /* Moved to com.dev3.app.handler.TextMessageParser

    */
}
