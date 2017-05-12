package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;

import java.io.Serializable;

/**
 * Created by A022713 on 2017/4/21.
 */
public interface IMessageHandler {
    AbstractMessage handle(AbstractMessage message);
}
