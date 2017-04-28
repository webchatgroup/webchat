package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by igouz on 2017/4/28.
 */
public abstract class AbsMessageHandler<I extends AbstractMessage, O extends Serializable> implements IMessageHandler<TextMessage, TextMessage>, Serializable {
    @Autowired
    private IMessageSerializer<TextMessage> textMessageSerializer;

    @Autowired
    private IMessageSerializer<TextMessage> respMessageSerializer;

    @Override
    public IMessageSerializer<TextMessage> getIncomingSerializer() {
        return this.textMessageSerializer;
    }

    @Override
    public IMessageSerializer<TextMessage> getOutgoingSerializer() {
        return this.respMessageSerializer;
    }
}
