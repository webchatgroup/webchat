package com.dev3.app.handler;

/**
 * Created by phoenix on 2017/5/12.
 */

import com.dev3.app.entity.AbstractMessage;

public interface IMessageResolver<I extends AbstractMessage, O extends AbstractMessage> {
    I getParsedIncomingMessage(AbstractMessage message);
    AbstractMessage getParsedOutgoingmessage(O message);
}
