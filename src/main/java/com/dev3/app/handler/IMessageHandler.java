package com.dev3.app.handler;

import com.dev3.app.entity.AbstractMessage;

/**
 * Created by A022713 on 2017/4/21.
 */
public interface IMessageHandler <M extends AbstractMessage> {
    void handle(M message);
}
