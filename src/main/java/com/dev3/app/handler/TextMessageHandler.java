package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;
import com.dev3.app.entity.MessageType;
import com.dev3.app.entity.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by A022713 on 2017/4/21.
 */
@Service(MessageType.MESSAGE_TEXT)
public class TextMessageHandler implements IMessageHandler<AbstractMessage, TextMessage> {
    @Override
    public TextMessage handle(AbstractMessage message) {
        return null;
    }
}
