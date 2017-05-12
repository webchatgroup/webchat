package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.MessageType;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.web.WeChatMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Created by phoenix on 2017/5/12.
 */
public abstract class AbsMessageHandler<I extends AbstractMessage, O extends AbstractMessage> implements IMessageHandler {
    @Autowired
    private IMessageResolver<I, O> resolver;

    private AbsMessageHandler<I, O> handler;

    public abstract O handleMessage(I message);

    @Override
    public AbstractMessage handle(AbstractMessage message) {
        return this.resolver.getParsedOutgoingmessage(this.getHandler().handleMessage(this.resolver.getParsedIncomingMessage(message)));
    }

    public AbsMessageHandler<I, O> getHandler() {
        return handler;
    }

    public void setHandler(AbsMessageHandler<I, O> handler) {
        this.handler = handler;
    }
}
